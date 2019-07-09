package status

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json

class PostStatus @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def postStatus = Action { implicit request =>
        val json = request.body.asJson
        val status = MetroStatus(System.currentTimeMillis(),
                                json.get("position").as[Int],
                                json.get("direction").as[Boolean],
                                json.get("passengers").as[Int],
                                json.get("battery").as[Int],
                                json.get("speed").as[Int],
                                json.get("temperature").as[Int],
                                json.get("failure").as[Boolean],
                                json.get("line").as[Int],
                                json.get("metro_id").as[Int])
        History.add(status)
        Ok("Success")
    }

}
