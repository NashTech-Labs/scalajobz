package utils
import com.mongodb.casbah.MongoConnection
import play.api.Play
import com.mongodb.casbah.MongoDB

object MongoHQConfig {
  val mongoDB = MongoConnection("alex.mongohq.com", 10083)("scalajobz")
  mongoDB.authenticate("neel", "neel")
}