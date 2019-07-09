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
import java.time.Instant

class GetClients @Inject()(cc: ControllerComponents, ws: WSClient) extends AbstractController(cc) {

    def getClients(file: String) = Action { implicit request =>
        file match {
            case "" => {
                val r = scala.util.Random
                val ids: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                val status: List[MetroStatus] = ids.map(x => MetroStatus(Instant.now().getEpochSecond(),
                                                                        r.nextInt(100), r.nextInt(2) == 1,
                                                                        r.nextInt(300), r.nextInt(100),
                                                                        r.nextInt(50), r.nextInt(40),
                                                                        r.nextInt(20) == 1, x, x * 100 + r.nextInt(99)))
                status.map(x => ws.url("http://localhost:9000/status").post(Json.toJson(x)))
                Redirect("/")
            }
            case file => {
                val source = Source.fromFile("../other/" + file)
                for (line <- source.getLines()) {
                  var data = Json.parse(line)
                  ws.url("http://localhost:9000/status").post(data)
                }
                source.close()
                Redirect("/")
            }
        }
    }

}
