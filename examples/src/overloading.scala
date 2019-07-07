object overloading extends App {
  val aFoo: Foo = new Foo()

  val foo: Foo = new Bar()
  val bar: Bar = new Bar()

  println(aFoo.doEet(foo))
  println(aFoo.doEet(bar))
}

class Foo() {
  def doEet(foo: Foo): String = "FooFoo"

  def doEet(foo: Bar): String = "FooBar"
}

class Bar() extends Foo() {
}
