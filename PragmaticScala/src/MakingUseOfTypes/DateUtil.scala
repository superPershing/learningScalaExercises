package MakingUseOfTypes

object DateUtil {
  val ago = "ago"
  val from_now = "from_now"

  implicit class DataHelper(val offset: Int) extends AnyVal {
    import java.time.LocalDate
    def days(when: String): LocalDate = {
      val today = LocalDate.now
      when match {
        case "ago" => today.minusDays(offset)
        case "from_now" => today.plusDays(offset)
        case _ => today
      }
    }
  }
}

object UseDataUtil extends App {
  import DateUtil._

  val past = 2 days ago
  val appointment = 3 days from_now

  println(past)
  println(appointment)
}