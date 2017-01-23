package controllers

import javax.inject._

import akka.actor.ActorSystem
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class IndexController @Inject()(actorSystem: ActorSystem)(
    implicit exec: ExecutionContext)
    extends Controller {

  def index = Action.async {
    Future.successful(Ok(views.html.index()))
  }
}
