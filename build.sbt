//requires partner, apex, metadata and tooling jars generated by wsc and placed in ./lib folder

name := "tooling-force.com"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-deprecation", 
  "-encoding", "UTF-8",
  "-feature", 
  "-unchecked",
  "-Xfatal-warnings"
)

resolvers ++= Seq(
  "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "commons-logging" %  "commons-logging" % "1.1.3",
  "org.scalatest" %  "scalatest_2.11" % "2.2.4" % "test",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.3.10"
)

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2"

//exportJars := true

