package WorkingWithObjects

import scala.collection._

object Sigleton extends App {
  println(MarkerFactory getMarker "blue")
  println(MarkerFactory getMarker "red")
  println(MarkerFactory getMarker "yellow")
  println(MarkerFactory getMarker "green")
}

class SMarker(val color: String) {
  println(s"Creating ${this}")

  override def toString: String = s"marker color $color"
}

object MarkerFactory {
  private val markers = mutable.Map(
    "red" -> new SMarker("red"),
    "blue" -> new SMarker("blue"),
    "yellow" -> new SMarker("yellow")
  )

  def getMarker(color: String): SMarker =
    markers.getOrElseUpdate(color, new SMarker(color))
}
