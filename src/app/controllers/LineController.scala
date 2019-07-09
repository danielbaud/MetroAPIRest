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
class LineController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def index() = Action { implicit request: Request[AnyContent] =>
        val history: RDD[MetroStatus] = History.getHistory()
          Ok(views.html.line(history.map(status => (status.metro_id, status.line))
                                    .groupBy(x => (x._1, x._2))
                                    .map{case(k, v) => k}.map(x => (x._2, 1))
                                    .reduceByKey(_ + _)
                                    .collect.toList))
    }

}
