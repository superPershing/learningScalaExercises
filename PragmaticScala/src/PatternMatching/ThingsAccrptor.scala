package PatternMatching

case class Apple()
case class Orange()
case class Book()

object ThingsAcceptor {
  def acceptStuff(thing: Any) = {
    thing match {
      case Apple() => println("Thanks for the Apple")
      case Orange() => println("Thanks for the Orange")
      case Book() => println("Thanks for the Book")
      case _ => println(s"Excuse me, why did you send me $thing")
    }
  }
}

object UseThingsAcceptor extends App {
  ThingsAcceptor.acceptStuff(Apple())
  ThingsAcceptor.acceptStuff(Orange())
  ThingsAcceptor.acceptStuff(Apple)  // Apple是case类的伴生对象，伴生对象混合了scala.Function0特质，可以被视为一个函数
}
