package utils
import com.mongodb.casbah.MongoConnection
import play.api.Play

object MongoHQConfig {

  val url = Play.current.configuration.getString("url").get.toString
  val port = Play.current.configuration.getString("port").get.toInt
  val db = Play.current.configuration.getString("dbname").get.toString
  val username = Play.current.configuration.getString("username").get.toString
  val password = Play.current.configuration.getString("password").get.toString

  val mongoDB = MongoConnection(url, port)(db)
  mongoDB.authenticate(username, password)

  // val mongoDB = MongoConnection("alex.mongohq.com", 10083)("scalajobz")
  // mongoDB.authenticate("neel", "neel")

}