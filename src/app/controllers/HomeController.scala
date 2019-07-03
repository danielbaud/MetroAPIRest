package controllers

import status._
import javax.inject._
import play.api._
import play.api.mvc._

/**
 * Description: Controller de la page d'acceuil.
 * Route: localhost:900/
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def index(id: Option[Int]) = Action { implicit request: Request[AnyContent] =>
        val history: List[MetroStatus] = History.getHistory()
        id match {
            case Some(id) => Ok(views.html.index(history.filter(status => status.metro_id == id)))
            case None => Ok(views.html.index(history))
        }
    }

}
