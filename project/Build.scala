import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "scalajobz.com"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
        "com.mongodb.casbah" %% "casbah" % "2.1.5-1",
        "com.novus" %% "salat-core" % "0.0.8-20120223"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
