package UsingCollections

class Sample {
  def unary_+() = println("Called unary +")
  def unary_-() = println("Called unary -")
  def unary_!() = println("Called unary !")
  def unary_~() = println("Called unary ~")
}

object Unary extends App {
  val sample = new Sample
  +sample
  -sample
  !sample
  ~sample
}
