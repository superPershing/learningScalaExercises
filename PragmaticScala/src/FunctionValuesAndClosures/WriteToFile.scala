package FunctionValuesAndClosures

import java.io.{File, PrintWriter}

object WriteToFile extends App {
  def writeToFile(filename: String)(codeBlock: PrintWriter=>Unit) = {
    val writer = new PrintWriter(new File(filename))
    try {
      codeBlock(writer)
    } finally {
      writer.close()
    }
  }

  writeToFile("output.txt") {
    writer => {
      writer write "Hello from Scala"
    }
  }
}
