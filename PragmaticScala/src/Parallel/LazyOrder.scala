package Parallel

import scala.io._

object LazyOrder extends App {
  def read = StdIn.readInt()
  lazy val first = read
  lazy val second = read

  if (Math.random() < 0.5)
    second

  println(first - second)
}
