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
import play.api.Play
import org.jsoup.Jsoup

/**
 * class to show alert
 * @param alertType the Alert Type
 * @param message the message on Alert
 */
case class Alert(alertType: String,
  message: String)

/**
 * class for creating Contact Us Form
 * @param name is mail sender name
 * @param emailAddress is sender email id
 * @param subject is mail subject
 * @param message is sender message
 */
case class ContactUsForm(name: String,
  emailAddress: String,
  subject: String,
  message: String)

object Common {

  val break = "<br/>"
  var alert: Alert = new Alert("", "")
  def setAlert(alert: Alert): Unit = this.alert = alert

  /**
   * Getter Setter For Number of methods
   */

  var totalJobs: Int = 0

  def setTotalJobs(jobs: Int) = {
    totalJobs = jobs
  }

  /**
   * Set Content For Sending Mail For Daily Job Alert
   * @param jobs is the list of jobs that matches the jobseekere's skills
   * @param jobSeeker is the jobseeker with same the skills required for job
   */

  def setContentForJobAlert(jobs: List[JobEntity], jobSeeker: UserEntity): String = {

    val removeJobAlertLink = "http://" + getContextUrl + "/unSubscribeJobSeeker/" + jobSeeker.id
    var message = "<b>Job Alert from scalajobz.com</b>" +
      break + break + "Your Job Details For <b>" + jobSeeker.skills.mkString(" ") + " </b>" + break + break
    for (job <- jobs) {
      val redirectToJobLink = "http://" + getContextUrl + "/jobDetail/" + job.id
      message += "<b><u><a href= " + redirectToJobLink + ">" + job.position + "</a></u></b>" + break
      message += job.company + " - " + job.location + break
      //remove html tags from the job description
      val jobDescription = Jsoup.parse(job.description).text()
      if (jobDescription.length > 150) {
        message += jobDescription.substring(0, 150) + " ..." + break
      } else {
        message += jobDescription + break
      }

    }
    message += break + "Click  <u>" + removeJobAlertLink + "</u> to unsubscribe from ScalaJobz"
    message
  }

  /**
   * Set Content For Sending Mail To Scalajobz Through Contact Us Page
   * @param name is mail sender name
   * @param emailAddress is sender email id
   * @param subject is mail subject
   * @param message is sender message
   */

  def setContentForContactUsMail(name: String, emailAddress: String, subject: String, sendermessage: String): String = {
    var message = "<b>" + name + "</b>" + " sent you a mail through scalajobz contact us page" + break + break
    message += "<b> Sender Email Address is : </b>" + emailAddress + break
    message += "<b> Subject is : </b>" + subject + break
    message += "<b> Message is : </b>" + sendermessage + break
    message
  }

  /**
   * Set Content For Acknowledgement Mail Send Through ScalaJobz
   * @param name is mail sender name
   */

  def setContentForAcknowledgementMail(name: String): String = {
    val scalaJobzSiteLink = "http://" + getContextUrl
    var message = "This is an acknowledgement Mail From ScalaJobz. " + break + break
    message += "Thanks  <b>" + name + "</b> For Contacting Us." + break + break + break
    message += "Feel Free To Contact Us." + break + break
    message += "Thanks & Regards !" + break
    message += "<b> ScalaJobz Support Team </b>" + break
    message += scalaJobzSiteLink
    message
  }

  /**
   * Set Content For Job Seeker Verification Mail
   * @param jobSeeker contains the job seeker details
   */

  def setContentForJobSeekerVerificationMail(jobSeeker: UserEntity): String = {
    val scalaJobzSiteLink = "http://" + getContextUrl
    val activateJobAlertLink = "http://" + getContextUrl + "/activateJobAlert/" + jobSeeker.id
    var message = "Thank you for sent request to enroll with <b> Scalajobz </b> for Job Alert Mail : <b>" + jobSeeker.skills.mkString(" ") + " </b>" + break 
    message += "<h1><b><u><a href= " + activateJobAlertLink + ">" + "Click here to activate your job alert " + "</a></u></b></h1>" + break + break
    message += "and start receiving your daily job alert mail." + break + break
    message += "Or copy and paste the following into your browser:" + break + break
    message += activateJobAlertLink + break + break
    message += "Thanks & Regards !" + break
    message += "<b> ScalaJobz Support Team </b>" + break
    message += scalaJobzSiteLink
    message
  }

  /**
   * To get The root context from application.config
   */
  def getContextUrl: String = {
    Play.current.configuration.getString("contextUrl").get
  }

}

/**
 * Override Object To get Object Id In json Response
 */
class ObjectIdSerializer extends Serializer[ObjectId] {
  private val Class = classOf[ObjectId]

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), ObjectId] = {
    case (TypeInfo(Class, _), json) => json match {
      case JInt(s) => new ObjectId
      case JString(s) => new ObjectId(s)
      case x: Any => throw new MappingException("Can't convert " + x + " to ObjectId")
    }
  }

  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: ObjectId => JObject(JField("id", JString(x.toString)) :: Nil)

  }
}

object JobBy extends Enumeration {
  val ScalaJobz = Value(0, "ScalaJobz")
  val Indeed = Value(1, "Indeed")
  val SimplyHired = Value(2, "SimplyHired")
  val CareerBuilder = Value(3, "CareerBuilder")
}
