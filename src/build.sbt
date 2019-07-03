name := """play-scala-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.0"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}
//crossScalaVersions := Seq("2.12.6", "2.11.12")

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.3"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "2.4.1"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.9"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.7"


libraryDependencies ++= Seq(
  ws
)
