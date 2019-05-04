package status

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import statusHistory._

class GetStatus @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def getStatus = Action { implicit request =>
        val json = Json.toJson(History.history)
        Ok(json)
    }

}
