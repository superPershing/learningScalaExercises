import java.io.{File, PrintWriter}

import scala.collection.mutable
import scala.io.Source


object Main extends App {
  def sortFile(oldFilePath: String, newFilePath: String): Unit = {
    val lines: List[List[Int]] = Source.fromFile(oldFilePath).getLines.toList.map { case x: String => x.split(" ").toList.map(_.toInt) }
    val sortedLines: List[List[Int]] = lines.sortBy(x => (x(0), x(1), x(2)))
    val content: String = sortedLines.map { case x: List[Int] => x.mkString(" ") }.mkString("\n")
    val writer: PrintWriter = new PrintWriter(new File(newFilePath))
    writer.write(content)
    writer.close()
  }

  def getDegreeFromFile(datasetFilePath: String, out: Boolean): mutable.Map[Int, Int] = {
    val degree = mutable.Map.empty[Int, Int]
    for (line <- Source.fromFile("data/ml100k-train-timeline.edgelist").getLines()) {
      val begin :: end :: weight :: Nil = line.split(" ").toList.map(_.toInt)
      val node = if (out) begin else end
      val oldCountDegree: Int =
        if (degree.contains(node)) degree(node)
        else 0
      degree += (node -> (oldCountDegree + 1))
    }
    degree
  }

  sortFile("data/ml100k-train-timeline.edgelist", "data/ml100k-train-timeline-sorted.edgelist")

  val outDegree: mutable.Map[Int, Int] = getDegreeFromFile("data/ml100k-train-timeline.edgelist", true)
  val inDegree: mutable.Map[Int, Int] = getDegreeFromFile("data/ml100k-train-timeline.edgelist", false)

  val maxOutDegree: (Int, Int) = outDegree.maxBy(_._2)
  val maxInDegree: (Int, Int) = inDegree.maxBy(_._2)
  println("maxOutDegree:", maxOutDegree._1, " -> ", maxOutDegree._2)
  println("maxInDegree: ", maxInDegree._1, " -> ", maxInDegree._2)

  val bigDegree = 30

  val bigOutDegree: mutable.Map[Int, Int] = outDegree.filter({ case (_, y) => y >= bigDegree })
  val bigInDegree: mutable.Map[Int, Int] = inDegree.filter({ case (_, y) => y >= bigDegree })
  println("Amount of bigOutDegree is : ", bigOutDegree.size)
  println("Amount of bigInDegree is : ", bigInDegree.size)
}
