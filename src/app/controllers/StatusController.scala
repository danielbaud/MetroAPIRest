import play.api._
import play.api.mvc._

object Application extends Controller {

  import play.api.libs.json.Json

case class Status(position: Int, direction: Boolean, passengers: Int, battery: Int, speed: Int, temperature: Int, id: Int)

  def getStatus = Action { request =>
    val json = request.body.asJson.get
    val status = json.as[Status]
    println(status)
    Ok
  }

}
