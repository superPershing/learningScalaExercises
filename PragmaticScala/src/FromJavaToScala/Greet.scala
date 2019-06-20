package FromJavaToScala

object Greet extends App {
  for (i <- 1 to 3) {
    print(s"$i,")
  }
  println("Scala Rocks!!!")

  (1 to 3).foreach(i => print(s"$i,"))
  println("Scala Rocks!!!")
}
