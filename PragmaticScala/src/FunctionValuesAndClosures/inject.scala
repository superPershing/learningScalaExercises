package FunctionValuesAndClosures

object RunInject extends App {
  val array = Array(2, 3, 4, 1, 6, 4)

  val sum = inject(array, 0) { (carryOver, elem) => carryOver + elem }  // currying
  println(s"sum: $sum")

  val sum1 = array.foldLeft(0) { (sum, elem) => sum + elem }
  println(s"sum: $sum1")

  val max = array.foldLeft(Integer.MIN_VALUE) { (large, elem) => Math.max(large, elem) }
  println(s"max: $max")

  val sum2 = (0 /: array) ((sum, elem) => sum + elem)
  println(s"sum: $sum2")

  val max2 = (Integer.MIN_VALUE /: array) ((large, elem)=> Math.max(large, elem))
  println(s"max: $max2")

  // currying
  def inject(arr: Array[Int], initial: Int)(operation: (Int, Int) => Int): Int = {
    var carryOver = initial
    arr.foreach(element => carryOver = operation(carryOver, element))
    carryOver
  }
}
