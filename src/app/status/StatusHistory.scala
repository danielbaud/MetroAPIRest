package status

import play.api.libs.json._

case class MetroStatus(position: Int, direction: Boolean, passengers: Int, battery: Int, speed: Int, temperature: Int, id: Int)

object MetroStatus {
    implicit object MetroStatusFormat extends Format[MetroStatus] {
            def writes(status: MetroStatus): JsValue = {
                val statusSeq = Seq(
                    "position" -> JsNumber(status.position),
                    "direction" -> JsBoolean(status.direction),
                    "passengers" -> JsNumber(status.passengers),
                    "battery" -> JsNumber(status.battery),
                    "speed" -> JsNumber(status.speed),
                    "temperature" -> JsNumber(status.temperature),
                    "id" -> JsNumber(status.id)
                )
                JsObject(statusSeq)
            }

            def reads(json: JsValue): JsResult[MetroStatus] = {
                JsSuccess(MetroStatus(0, true, 0, 0, 0, 0, 0))
            }
        }
}

object History {
    var history: List[MetroStatus] = List()

    def add(status: MetroStatus): Unit = {
        history = history :+ status
    }
}
