package FromJavaToScala

object Parameters extends App {
  def max(values: Int*) = values.foldLeft(values(0)) { Math.max }

  println(max(8, 2, 3))

  val numbers = Array(2, 5, 3, 7, 1, 6)
//  println(max(numbers))   // Error!
  println(max(numbers: _*))
}
