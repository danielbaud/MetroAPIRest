package status

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import scala.io.Source

class GetClients @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def getClients = Action { implicit request =>
    val source = Source.fromFile("other/clients.json")
        var jsons = "["
        for (line <- source.getLines()) {
            jsons += line
            jsons += ","
        }
        jsons += "]"
        source.close()
        Ok(jsons)
    }

}
