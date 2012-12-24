package utils
import com.mongodb.casbah.MongoConnection
import play.api.Play

object MongoHQConfig {

  def mongoDB = {
    Play.maybeApplication match {
      case None => // this would set up the database for testing, since app is not running right now
        MongoConnection("", 27017)("test")
      case Some(application) => //  this would set up the database for development mode
        MongoConnection("", 27017)("scalajobz")
    }
  }
}


