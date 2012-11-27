import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "scalajobz.com"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "com.mongodb.casbah" %% "casbah" % "2.1.5-1",
    "com.novus" %% "salat-core" % "0.0.8-20120223",
    "javax.mail" % "mail" % "1.4.1",
     "org.scalatra" %% "scalatra-lift-json" % "2.0.4",
    "com.typesafe.akka" % "akka-actor" % "2.0.2",
    "com.typesafe.akka" % "akka-remote" % "2.0.2",
    "commons-codec" % "commons-codec" % "1.6",
    "org.specs2" %% "specs2" % "1.12.3" % "test",
    "org.scalatest" %% "scalatest" % "1.7.2")

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      testOptions in Test := Nil
      // Add your own project settings here      
  )

}
