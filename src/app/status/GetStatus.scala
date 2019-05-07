package status

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import statusHistory._

class GetStatus @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def getStatus(id: Option[Int]) = Action { implicit request =>
        id match {
            case Some(id) => Ok(Json.toJson(History.history.filter(status => status.id == id)))
            case None => Ok(Json.toJson(History.history))
        }
    }

}
