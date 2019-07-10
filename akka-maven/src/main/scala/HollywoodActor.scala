import akka.actor._

class HollywoodActor() extends Actor {
  override def receive: Receive = {
    case message => println(s"$message - ${Thread.currentThread}")
  }
}

import scala.collection._

case class Play(role: String)
case class ReportCount(role: String)

class SuperHollywoodActor() extends Actor {
  val messagesCount: mutable.Map[String, Int] = mutable.Map()
  override def receive: Receive = {
    case Play(role) =>
      val currentCount = messagesCount.getOrElseUpdate(role, 0)
      messagesCount.update(role, currentCount + 1)
      println(s"Playing $role")

    case ReportCount(role) =>
      sender ! messagesCount.getOrElseUpdate(role, 0)
  }
}
