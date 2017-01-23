package client

import client.css.ClientCSS
import client.routes.ClientRouter
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport
object Client extends JSApp {
  @JSExport
  override def main(): Unit = {
    ClientCSS.load
    ClientRouter.router().render(dom.document.body)
  }
}
