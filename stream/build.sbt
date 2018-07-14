name := "streaming"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "2.3.0",
  "org.apache.spark" %% "spark-mllib" % "2.3.0",
  "org.apache.spark" %% "spark-streaming" % "2.3.0" % "provided"
)