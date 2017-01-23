name := "play-scalajs-react-template"

lazy val commonSettings = Seq(
  organization := "com.github.tomdom",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.8"
)

lazy val server = (project in file("server"))
  .settings(commonSettings: _*)
  .settings(
    scalaJSProjects := Seq(client),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    pipelineStages := Seq(digest, gzip),
    // triggers scalaJSPipeline when using compile or continuous compilation
    compile in Compile <<= (compile in Compile) dependsOn scalaJSPipeline,
    libraryDependencies ++= Seq(
      "com.vmunier" %% "scalajs-scripts" % "1.0.0",
      specs2 % Test,
      jdbc,
      cache,
      ws,
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
    )
  )
  .enablePlugins(PlayScala)
  .dependsOn(sharedJvm)

lazy val client = (project in file("client"))
  .settings(commonSettings: _*)
  .settings(
    persistLauncher := true,
    persistLauncher in Test := false,
    libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core" % "0.10.1",
      "com.github.japgolly.scalajs-react" %%% "extra" % "0.10.1",
      "com.github.japgolly.scalacss" %%% "core" % "0.3.1",
      "com.github.japgolly.scalacss" %%% "ext-react" % "0.3.1"
    ),
    jsDependencies ++= Seq(
      "org.webjars.npm" % "react" % "15.3.2" / "react-with-addons.js" commonJSName "React" minified "react-with-addons.min.js",
      "org.webjars.npm" % "react-dom" % "15.3.2" / "react-dom.js" commonJSName "ReactDOM" minified "react-dom.min.js" dependsOn "react-with-addons.js"
    )
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .dependsOn(sharedJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(commonSettings: _*)
  .jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

// loads the server project at sbt startup
onLoad in Global := (Command
  .process("project server", _: State)) compose (onLoad in Global).value
