package client.routes

import client.components.{Footer, TopNav}
import client.models.Menu
import client.pages.HomePage
import japgolly.scalajs.react.extra.router.{
  Resolution,
  RouterConfigDsl,
  RouterCtl,
  _
}
import japgolly.scalajs.react.vdom.prefix_<^._

object ClientRouter {

  sealed trait AppPage

  case object Home extends AppPage
  case class Items(p: Item) extends AppPage

  val config = RouterConfigDsl[AppPage].buildConfig { dsl =>
    import dsl._
    val itemRoutes: Rule =
      Item.routes.prefixPath_/("#items").pmap[AppPage](Items) {
        case Items(p) => p
      }
    (trimSlashes
      | staticRoute(root, Home) ~> render(HomePage())
      | itemRoutes)
      .notFound(redirectToPage(Home)(Redirect.Replace))
      .renderWith(layout)
  }

  val mainMenu = Vector(
    Menu("Home", Home),
    Menu("Items", Items(Item.Info))
  )

  def layout(c: RouterCtl[AppPage], r: Resolution[AppPage]) = {
    <.div(
      TopNav(TopNav.Props(mainMenu, r.page, c)),
      r.render(),
      Footer()
    )
  }

  val baseUrl = BaseUrl.fromWindowOrigin / "play-scalajs-react-template/"

  val router = Router(baseUrl, config)

}