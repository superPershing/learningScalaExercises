package dbUtils

import java.sql.{Connection, DriverManager, PreparedStatement}
import dbUtils.utils

object Main extends App {
  val url = "jdbc:mysql://localhost/recommender?serverTimezone=UTC"
  val driver = "com.mysql.cj.jdbc.Driver"
  val username = "tiger"
  val password = "mysql"
  var connection:Connection = _

  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val writeDB = new utils("ml100k", "/home/tiger/Workspace/Dataset/ml-100k/u.data")
    writeDB.writeMl100k(connection)
  } catch {
    case e: Exception => e.printStackTrace
  }
  connection.close

}
