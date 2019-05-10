package status

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import scala.io.Source
import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.libs.ws._
import play.api.http.HttpEntity
import scala.concurrent.ExecutionContext

class GetClients @Inject()(cc: ControllerComponents, ws: WSClient) extends AbstractController(cc) {

    def getClients = Action { implicit request =>
        val source = Source.fromFile("../other/clients.json")
        for (line <- source.getLines()) {
          var data = Json.parse(line)
          ws.url("http://localhost:9000/status").post(data)
        }
        source.close()
        Ok("Success")
    }

}
