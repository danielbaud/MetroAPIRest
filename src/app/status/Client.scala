package status

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import scala.io.Source
import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._

import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString
import play.api.libs.json._
import scala.concurrent.ExecutionContext

class Application @Inject() (ws: WSClient) extends Controller {
  val source = Source.fromFile("other/clients.json")
  for (line <- source.getLines()) {
    var data = Json.parse(line)
    ws.url("localhost:9000/status").post(data)
  }
  source.close()
}