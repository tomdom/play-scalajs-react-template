package client.components.items

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._

object ItemsInfo {

  val component =
    ScalaComponent.static("ItemsInfo")(<.div(" Items Root Page  "))

  def apply() = component()
}
