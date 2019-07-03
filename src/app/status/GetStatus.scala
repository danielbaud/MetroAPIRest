package status

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json

class GetStatus @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def getStatus(id: Option[Int]) = Action { implicit request =>
    val history: List[MetroStatus] = History.getHistory()
    id match {
        case Some(id) => Ok(Json.toJson(history.filter(status => status.metro_id == id)))
        case None => Ok(Json.toJson(history))
    }
  }
}
