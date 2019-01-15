package collectRmb

import java.io.{File, PrintWriter}

import scala.io.Source

object Main extends App {

  if (args.length == 0) {
    println("Need input and output path")
    System.exit(0)
  }

  val inputPath = args(0)
  val outputFilePath = args(1)

  val numPartition = args(2).toInt

  var fileNames = List[String]()
  for (num <- 0 until numPartition) {
    val count: Int = (num + "").length
    val name: String = inputPath + "part-" + "0" * (5 - count) + num.toString
    fileNames = name :: fileNames
  }

  var lines: List[String] = List[String]()

  for (name <- fileNames) {
    lines = lines ::: Source.fromFile(name).getLines().toList.map { case x: String => x.replace(",", " ") }
  }

  val content: String = lines.mkString("\n")

  val writer: PrintWriter = new PrintWriter(new File(outputFilePath))
  writer.write(content)
  writer.close()

}
