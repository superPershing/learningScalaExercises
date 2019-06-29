package MakingUseOfTypes

import java.util

object Generics extends App {
  val list1: util.ArrayList[Int] = new util.ArrayList[Int]
  val list2: util.ArrayList[Nothing] = new util.ArrayList
//  list2 = list1  //编译错误
}
