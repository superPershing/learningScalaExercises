package ProgrammingActors

import java.io.File

object CountFilesSequential extends App {
  def getChildren(file: File) = {
    val children: Array[File] = file.listFiles
    if (children != null)
      children.toList
    else
      List()
  }

  val start = System.nanoTime
  val exploreFrom = new File(args(0))

  var count = 0
  var fileToVisit = List(exploreFrom)

  while(fileToVisit.nonEmpty) {
    val head = fileToVisit.head
    fileToVisit = fileToVisit.tail

    val children = getChildren(head)
    count = count + children.count { !_.isDirectory }
    fileToVisit = fileToVisit ::: children.filter { _.isDirectory }
  }

  val end = System.nanoTime
  println(s"Number of files found: $count")
  println(s"Time taken: ${(end-start) / 1.0e9} seconds")
}
