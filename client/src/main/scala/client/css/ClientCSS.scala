package client.css

import client.components.{LeftNav, TopNav}
import client.pages.{HomePage, ItemsPage}

import scalacss.ScalaCssReact._
import scalacss.mutable.GlobalRegistry
import scalacss.Defaults._

object ClientCSS {

  def load = {
    GlobalRegistry.register(GlobalStyle,
                            TopNav.Style,
                            LeftNav.Style,
                            ItemsPage.Style,
                            HomePage.Style)
    GlobalRegistry.onRegistration(_.addToDocument())
  }
}
