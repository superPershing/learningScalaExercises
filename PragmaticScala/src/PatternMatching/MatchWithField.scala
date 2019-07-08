package PatternMatching

class Sample {
  val max = 100

  def process(input: Int) = {
    input match {
      case this.max => println(s"You matched max $max")
//      case `max` => println(s"You matched max $max")
//      case max: Int if max == 100 => println(s"You matched max $max")
//      case max: Int if max == this.max => println(s"You matched max $max")
    }
  }
}

object UseSample extends App {
  val sample = new Sample
  try {
    sample.process(0)
  } catch {
    case ex: Throwable => println(ex)
  }

  sample.process(100)
}