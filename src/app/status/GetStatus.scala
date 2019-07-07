package status

import javax.inject._
import play.api._
import play.api.mvc._
import com.datastax.spark.connector._
import play.api.libs.json.Json
import org.apache.spark.rdd._

class GetStatus @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def getStatus(id: Option[Int]) = Action { implicit request =>
    val history: RDD[MetroStatus] = History.getHistory()
    id match {
        case Some(id) => Ok(Json.toJson(history.filter(status => status.metro_id == id).collect.toList))
        case None => Ok(Json.toJson(history.collect.toList))
    }
  }
}
