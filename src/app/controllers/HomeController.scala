package controllers

import status._
import javax.inject._
import play.api._
import play.api.mvc._
import org.apache.spark.rdd._

/**
 * Description: Controller de la page d'acceuil.
 * Route: localhost:900/
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def index(id: Option[Int]) = Action { implicit request: Request[AnyContent] =>
        val history: RDD[MetroStatus] = History.getHistory()
        id match {
            case Some(id) => Ok(views.html.index(history.filter(status => status.metro_id == id).collect.toList))
            case None => Ok(views.html.index(history.collect.toList))
        }
    }

}
