package com.superPershing

import scala.collection.immutable.IndexedSeq
import org.mongodb.scala._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model._
import com.superPershing.Helpers._

import scala.collection.immutable
import scala.io.Source

object Main extends App {
  val dbName = "myTestDb"
  val collectionName = "myCollection"
  val inputPath = "/home/tiger/Workspace/node2vec_spark_results/node2vecResultsVec.embedding"
  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase(dbName)
  val collection: MongoCollection[Document] = database.getCollection(collectionName)

  collection.find().first().printHeadResult()

  val linesRaw: List[Array[String]] = Source.fromFile(inputPath).getLines().toList.map { case x: String => x.split("\\s+") }
  val vectors: List[(Int, Array[Double])] = linesRaw.map { case x: Array[String] => x.head.toInt -> x.tail.map { case v => v.toDouble } }
//  val documents = vectors map { key => Document("i" -> i) }
  for (line <- linesRaw) {
    val iid: Int = line.head.toInt
    val vector: Array[Float] = line.tail.map { x => x.toFloat}
    val doc: Document = Document(iid -> vector)   // Bug to be fixed FIXME
    collection.insertOne(doc).results()
    //
  }

  val documents: immutable.Seq[Document] = (1 to 100) map { i: Int => Document("i" -> i) }

  val x = "name"
  val y = "MongoDB2"
  val doc: Document = Document(x -> y)

  collection.insertOne(doc).results()
//
//  observable.subscribe(new Observer[Completed] {
//
//    override def onNext(result: Completed): Unit = println("Inserted")
//
//    override def onError(e: Throwable): Unit = println("Failed")
//
//    override def onComplete(): Unit = println("Completed")
//  })


}
