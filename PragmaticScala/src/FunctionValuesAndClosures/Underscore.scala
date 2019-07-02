package FunctionValuesAndClosures

object Underscore extends App {
  val arr = Array(1, 2, 3, 4, 5)
  val total = (0 /: arr) { _ + _ }
  println(total)

  val negativeNumberExists1 = arr.exists { elem => elem < 3}
  val negativeNumberExists2 = arr.exists { _ < 3}

  println(negativeNumberExists1, negativeNumberExists2)

  val largest = (Integer.MIN_VALUE /: arr) {Math.max _}
  println(largest)

  val largest1 = (Integer.MIN_VALUE /: arr) {Math.max}
  println(largest1)
}
