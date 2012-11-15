package utils
import com.mongodb.casbah.MongoConnection

object MongoHQConfig {

  //val mongoDB = MongoConnection("", 27017)("scalajobz")
 // mongoDB.authenticate("", "")

  val mongoDB = MongoConnection("alex.mongohq.com", 10083)("scalajobz")
 mongoDB.authenticate("neel", "neel")

}