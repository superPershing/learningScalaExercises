package PatternMatching

class SampleVal {
  val MAX = 100

  def process(input: Int) = {
    input match {
      case MAX => println("You matched max")
    }
  }
}

object UseSampleVal extends App {
  val sample = new SampleVal
  try {
    sample.process(0)
  } catch {
    case ex: Throwable => println(ex)
  }

  sample.process(100)
}