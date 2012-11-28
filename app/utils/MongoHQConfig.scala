package utils
import com.mongodb.casbah.MongoConnection
import play.api.Play

object MongoHQConfig {

  //  val url = Play.current.configuration.getString("url").get.toString
  //  val port = Play.current.configuration.getString("port").get.toInt
  //  val dbName = Play.current.configuration.getString("dbname").get.toString
  //  val userName = Play.current.configuration.getString("username").get.toString
  //  val password = Play.current.configuration.getString("password").get.toString
  //
  //  val mongoDB = MongoConnection(url, port)(dbName)
  //  mongoDB.authenticate(userName, password)

//   val mongoDB = MongoConnection("alex.mongohq.com", 10083)("scalajobz")
//   mongoDB.authenticate("neel", "neel")

  val mongoDB = MongoConnection("", 27017)("scalajobz")
  mongoDB.authenticate("", "")

}
