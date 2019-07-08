package UsingCollections

object PowerOfFor extends App {
  for (_ <- 1 to 3) {print("ho ")}

  val result = for (i <- 1 to 10)
    yield i *2
  println(result)

  val result2 = (1 to 10).map(_*2)
  println(result2)

  val doubleEven = for (i <- 1 to 10; if i % 2 == 0)
    yield i * 2
  println(doubleEven)

  for (i <- 1 to 3; j <- 4 to 6) {
    println(s"[$i, $j] ")
  }
}
