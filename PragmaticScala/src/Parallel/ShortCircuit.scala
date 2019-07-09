package Parallel

object ShortCircuit extends App {
  def expensiveComputation() = {
    println("...assume slow operation...")
    false
  }

  def evaluate(input:Int) = {
    println(s"evaluate called with $input")
    lazy val perform = expensiveComputation()
    if (input >= 10 && perform) {
      println("doing work...")
    } else
      println("skipping")
  }

  evaluate(0)
  evaluate(100)
}
