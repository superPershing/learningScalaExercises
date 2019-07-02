package FunctionValuesAndClosures

import java.util.Date

object Log extends App {
  def log(date: Date, message: String) = {
    println(s"$date ---- $message")
  }

  val date = new Date(1420095600000L)
  log(date, "message1")

  val logWithDateBound = log(date, _)
  logWithDateBound("message2")
}
