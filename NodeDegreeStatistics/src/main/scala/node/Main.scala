import scala.collection.mutable
import scala.io.Source

object Main extends App {
  val outDegree = mutable.Map.empty[Int, Int]
  val inDegree = mutable.Map.empty[Int, Int]
  for (line <- Source.fromFile("data/ml100k-train-timeline.edgelist").getLines()) {
    val begin :: end :: weight :: Nil = line.split(" ").toList.map(_.toInt)

    val oldCountOutDegree =
      if (outDegree.contains(begin)) outDegree(begin)
      else 0
    outDegree += (begin -> (oldCountOutDegree + 1))

    val oldCountInDegree =
      if (inDegree.contains(end)) inDegree(end)
      else 0
    inDegree += (end -> (oldCountInDegree + 1))
  }

}
