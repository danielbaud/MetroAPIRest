package statusHistory

case class MetroStatus(position: Int, direction: Boolean, passengers: Int, battery: Int, speed: Int, temperature: Int, id: Int)

object History {
    var history: List[MetroStatus] = List()

    def add(status: MetroStatus): Unit = {
        history = history :+ status
    }
}
