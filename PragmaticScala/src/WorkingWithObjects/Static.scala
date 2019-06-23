package WorkingWithObjects

import scala.collection._

class StaticMarker private(val color: String) {
  override def toString: String = s"marker color $color"
}

object StaticMarker {
  private val markers = mutable.Map(
    "red" -> new StaticMarker("red"),
    "blue" -> new StaticMarker("blue"),
    "yellow" -> new StaticMarker("yellow")
  )

  def supportedColors: Iterable[String] = markers.keys

  def apply(color: String): StaticMarker = markers.getOrElseUpdate(color, op = new StaticMarker(color))
}

object Static extends App {
  println(s"Supported colors are : ${StaticMarker.supportedColors}")
  println(StaticMarker("blue"))
  println(StaticMarker("red"))
}