package client

import client.css.ClientCSS
import client.routes.ClientRouter
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Client extends JSApp {
  def main(): Unit = {
    ClientCSS.load
    ClientRouter.router().renderIntoDOM(dom.document.body)
  }
}
