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
class MetroController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def index() = Action { implicit request: Request[AnyContent] =>
        val history: RDD[MetroStatus] = History.getHistory()
          Ok(views.html.metro(history.map(status => (status.metro_id, 1)).reduceByKey((n1: Int, n2: Int) => n1 + n2).collect.toList))
    }

}
