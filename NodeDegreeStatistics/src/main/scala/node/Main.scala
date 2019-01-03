import scala.collection.mutable
import scala.io.Source

object Main extends App {
  val bigDegree: Int = 30
  val outDegree = mutable.Map.empty[Int, Int]
  val inDegree = mutable.Map.empty[Int, Int]
  for (line <- Source.fromFile("data/ml100k-train-timeline.edgelist").getLines()) {
    val begin :: end :: weight :: Nil = line.split(" ").toList.map(_.toInt)

    val oldCountOutDegree: Int =
      if (outDegree.contains(begin)) outDegree(begin)
      else 0
    outDegree += (begin -> (oldCountOutDegree + 1))

    val oldCountInDegree: Int =
      if (inDegree.contains(end)) inDegree(end)
      else 0
    inDegree += (end -> (oldCountInDegree + 1))
  }

  val maxOutDegree: (Int, Int) = outDegree.maxBy(_._2)
  val maxInDegree: (Int, Int) = inDegree.maxBy(_._2)
  println("maxOutDegree:", maxOutDegree._1, " -> ", maxOutDegree._2)
  println("maxInDegree: ", maxInDegree._1, " -> ", maxInDegree._2)

  val bigOutDegree: mutable.Map[Int, Int] = outDegree.filter({ case (_, y) => y >= bigDegree })
  val bigInDegree: mutable.Map[Int, Int] = inDegree.filter({ case (_, y) => y >= bigDegree })
  println("Amount of bigOutDegree is : ", bigOutDegree.size)
  println("Amount of bigInDegree is : ", bigInDegree.size)
}
