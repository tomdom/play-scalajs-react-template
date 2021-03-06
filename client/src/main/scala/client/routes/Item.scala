package client.routes

import client.components.items.{Item1Data, Item2Data, ItemsInfo}
import client.pages.ItemsPage
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.router.RouterConfigDsl
import japgolly.scalajs.react.vdom.Implicits._

sealed abstract class Item(val title: String,
                           val routerPath: String,
                           val render: () => ScalaComponent.Unmounted[Unit, Unit, Unit])

object Item {

  case object Info extends Item("Info", "info", () => ItemsInfo())

  case object Item1 extends Item("Item1", "item1", () => Item1Data())

  case object Item2 extends Item("Item2", "item2", () => Item2Data())

  val menu = Vector(Info, Item1, Item2)

  val routes = RouterConfigDsl[Item].buildRule { dsl =>
    import dsl._

    menu
      .map(i =>
        staticRoute(i.routerPath, i) ~> renderR(r =>
          ItemsPage(props = ItemsPage.Props(i, r))))
      .reduce(_ | _)

  }

}
