package models

import net.liftweb.json.JsonDSL
import net.liftweb.json.MappingException
import net.liftweb.json.TypeInfo
import net.liftweb.json.Formats
import net.liftweb.json.JsonAST.JString
import net.liftweb.json.Serialization
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.NoTypeHints
import net.liftweb.json.CustomSerializer
import net.liftweb.json.JsonAST.JObject
import org.bson.types.ObjectId
import net.liftweb.json.JsonAST.JField
import net.liftweb.json.Serializer
import net.liftweb.json.JsonAST.JInt
import net.liftweb.json.TypeInfo

case class Alert(alertType: String,
  message: String)

object Common {

  var alert: Alert = new Alert(null, null)
  def setAlert(alert: Alert) = this.alert = alert

  def setContentForJobAlert(jobs: List[Job]): String = {

    var message = "<b>Job Alert from scalajobz.com</b>" +
      "<br/> <br/>" + "<b>Your Job Details</b>" + "<br/> <br/>"
    for (job <- jobs) {
      message += "<b><u>" + job.position + "</u></b>" + "<br/>"
      message += job.company + "<br/>"
      message += job.location + "<br/>"
    }
    message
  }

}

class ObjectIdSerializer extends Serializer[ObjectId] {
  private val Class = classOf[ObjectId]

  def deserialize(implicit format: Formats) = {
    case (TypeInfo(Class, _), json) => json match {
      case JInt(s) => new ObjectId
      case JString(s) => new ObjectId(s)
      case x => throw new MappingException("Can't convert " + x + " to ObjectId")
    }
  }

  def serialize(implicit format: Formats) = {
    case x: ObjectId => JObject(JField("id", JString(x.toString)) :: Nil)

  }
}
