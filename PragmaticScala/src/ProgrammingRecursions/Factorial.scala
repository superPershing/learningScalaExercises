package ProgrammingRecursions

object Factorial extends App {
  @scala.annotation.tailrec
  def factorial(fact: BigInt, number: Int): BigInt = {
    if (number == 0)
      fact
    else
      factorial(fact * number, number - 1)
  }

  println(factorial(1, 10000))
}
