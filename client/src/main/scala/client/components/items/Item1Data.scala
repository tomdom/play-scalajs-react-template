package client.components.items

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Item1Data {

  val component =
    ScalaComponent.static("Item1")(<.div("This is Item1 Page "))

  def apply() = component()
}
