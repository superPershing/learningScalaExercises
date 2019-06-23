package WorkingWithObjects

object Parameterized extends App {
  def echo[T](input1: T, input2: T) = {
    println(s"got $input1 (${input1.getClass}) $input2 (${input2.getClass})")
  }

  echo("hello", "world")
  echo(4, 5)
  echo("hi", 5)

  //  echo[Int]("hi", 5)   // Error: type mismatch;

  def echo2[T1, T2](input1: T1, input2: T2): Unit =
    println(s"received $input1 and $input2")

  echo2("Hi", "5")

  val message1: Message[String] = new Message[String]("howdy")
  val message2 = new Message(42)
  println(message1)
  println(message1.is("howdy"))
  println(message1.is("hi"))
  println(message2.is(22))

}


class Message[T](val content: T) {
  override def toString: String = s"message content is $content"

  def is(value: T):Boolean = value == content
}
