package status

import play.api.libs.json._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd._
import com.datastax.spark.connector._

case class MetroStatus(timestamp: Long, position: Int, direction: Boolean, passengers: Int, battery: Int,
                       speed: Int, temperature: Int, failure: Boolean, line: Int, metro_id: Int)

object MetroStatus {
  implicit object MetroStatusFormat extends Format[MetroStatus] {
    def writes(status: MetroStatus): JsValue = {
      val statusSeq = Seq(
        "timestamp" -> JsNumber(status.timestamp),
        "position" -> JsNumber(status.position),
        "direction" -> JsBoolean(status.direction),
        "passengers" -> JsNumber(status.passengers),
        "battery" -> JsNumber(status.battery),
        "speed" -> JsNumber(status.speed),
        "temperature" -> JsNumber(status.temperature),
        "failure" -> JsBoolean(status.failure),
        "line" -> JsNumber(status.line),
        "metro_id" -> JsNumber(status.metro_id)
      )
      JsObject(statusSeq)
    }

    def reads(json: JsValue): JsResult[MetroStatus] = {
        JsSuccess(MetroStatus(System.currentTimeMillis(), 0, true, 0, 0, 0, 0, false, 1, 1))
    }
  }
}

object History {
  val conf = new SparkConf(true)
    .setAppName("Test")
    .setMaster("local[*]")
    .set("spark.cassandra.connection.host","127.0.0.1")
    .set("spark.driver.allowMultipleContexts", "true")
    .set("spark.cassandra.connection.port", "9042")
  val sc = new SparkContext(conf)

  def getHistory(): RDD[MetroStatus] = {
    return sc.cassandraTable[MetroStatus]("imetro", "status")
  }

  def add(status: MetroStatus): Unit = {
    val collection = sc.parallelize(Seq(status))
    collection.saveToCassandra("imetro", "status", SomeColumns("timestamp", "position", "direction",
                                                                "passengers", "battery", "speed",
                                                                "temperature", "failure", "line", "metro_id"))
  }
}
