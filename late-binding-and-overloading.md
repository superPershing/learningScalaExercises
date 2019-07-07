## Types and Tags
在类型系统中，Types是`compile-time`时对已知值的约束。Types只存在于`compile-time`。  
Tags是runtime时的可用的关于值的信息。  
比如`Type`是`Option[Int]`,而`Tag`是`Some`.
```Scala
val anOption: Option[Int] = Some(1)
```
## Early and Late Binding
```Scala
class Foo() {
    def doIt: String = "Foo"
}

class Bar() extends Foo() {
    override def doIt: String = "Bar"
}

val foo: Foo = new Bar()

scala> foo.doIt
res0: String = Bar
```
`foo`的Type是`Foo`,tag是`Bar`。  
如果输出是`Foo`，就是`early binding`(static polymorphism)；如果输出是`Bar`，就是`late binding`(dynamic polymorphism)。Scala、Java等大多数OO语言，都是`late binding`(延迟绑定)

## Overloading
```Scala
class Foo() {
    def doEet(foo: Foo): String = "FooFoo"

    def doEet(foo: Bar): String = "FooBar"
}

class Bar() extends Foo(){
}

val aFoo: Foo = new Foo()

val foo: Foo = new Bar()
val bar: Bar = new Bar()

aFoo.doEet(foo)  //FooFoo
aFoo.doEet(bar)  //FooBar
```
`aFoo.doEet(foo)`的输出是`FooFoo`，`aFoo.doEet(bar)`的输出是`FooBar`。  
这说明了是`Type`而不是`Tag`被用于重载方法的参数选择。  
这也说明了Scala对于方法的接收者是`late binding`(调用方法时)，但对于方法参数是`early binding`(对于overloading)。  

### 参考资料
 - <https://underscore.io/blog/posts/2015/11/04/late-binding-and-overloading.html>