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

  var fileNames = List[String]()

  val files: Array[File] = new java.io.File(inputPath).listFiles.filter(_.getName.endsWith(".csv"))

  var lines: List[String] = List[String]()

  for (name <- files) {
    lines = lines ::: Source.fromFile(name).getLines().toList.map { case x: String => x.replace(",", " ") }
  }

  val content: String = lines.mkString("\n")

  val writer: PrintWriter = new PrintWriter(new File(outputFilePath))
  writer.write(content)
  writer.close()

}
