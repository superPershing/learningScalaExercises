package MakingUseOfTypes

import scala.language.implicitConversions
import java.time.LocalDate

class DataHelper(offset: Int) {
  def days(when: String): LocalDate = {
    val today = LocalDate.now
    when match {
      case "ago" => today.minusDays(offset)
      case "from_now" => today.plusDays(offset)
      case _ => today
    }
  }
}

object DataHelper {
  val ago = "ago"
  val from_now  = "from_now"
  implicit def convertInt2DataHelper(offset: Int): DataHelper = new DataHelper(offset)
}