package dbUtils

import java.sql.{Connection, PreparedStatement}

import scala.io.Source

class utils(var tableName: String, var filePath:String) {

  // TODO need be refactor to fit all DB
  def writeMl100k(connection: Connection): Unit = {
    val insertSql: String = "INSERT INTO " + tableName + " (user_id, item_id, rating, time) VALUES (?, ?, ?, ?)"
    val lines: List[List[String]] = Source.fromFile(filePath).getLines.toList.map { case x: String => x.split("\t").toList.map(_.toString) }

    for (line <- lines) {
      val user_id = line(0).toInt
      val item_id = line(1).toInt
      val rating = line(2).toInt
      val time: Long = line(3).toLong
      val preparedStmt: PreparedStatement = connection.prepareStatement(insertSql)
      preparedStmt.setInt(1, user_id)
      preparedStmt.setInt(2, item_id)
      preparedStmt.setInt(3, rating)
      preparedStmt.setLong(4, time)
      preparedStmt.execute
      preparedStmt.close
    }
  }
}
