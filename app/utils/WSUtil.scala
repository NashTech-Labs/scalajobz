package utils
import play.libs.WS
import scala.xml.XML
import scala.xml.Elem
import java.util.Date
import models.JobEntity
import org.bson.types.ObjectId
import models.Job
import models.JobBy

object WSUtil {

  /**
   * Read Job From Indeed Job Portal
   */
  def indeedJobsReader = {
    val indeedJobReaderUrl = "http://api.indeed.com/ads/apisearch"
    val indeedWS = WS.url(indeedJobReaderUrl).setQueryParameter("publisher", "PUBLISHER_ID")
      .setQueryParameter("format", "xml")
      .setQueryParameter("q", "scala or akka")
      .setQueryParameter("v", "2")
      .setQueryParameter("fromage", "1")
      .setQueryParameter("limit", "100")
      .setQueryParameter("sort", "date")
      .setQueryParameter("jt", "fulltime")
      .get
    val resultOfIndeedJobReaderUrl = indeedWS.get
    val indeedJobsXmlBody = resultOfIndeedJobReaderUrl.getBody
    val indeedXml = XML.loadString(indeedJobsXmlBody)
    saveJobsFromIndeed(indeedXml)
  }

  /**
   * Read XML returned from  Indeed Job Portal Rest Call API
   */
  def saveJobsFromIndeed(indeedXml: Elem) = {
    for (indeed <- (indeedXml \\ "result")) {
      val position = (indeed \ "jobtitle").text.trim
      val company = (indeed \ "company").text.trim
      val location = (indeed \ "city").text.trim + " " + (indeed \ "state").text.trim + "," + (indeed \ "country").text.trim
      val jobType = "Permanent"
      val emailAdress = (indeed \ "url").text.trim
      val skillsRequired = "scala".split(",").toList
      val description = (indeed \ "snippet").text.trim
      val datePosted = new Date()
      val job = new JobEntity(new ObjectId, None, position, company, location,
        jobType, emailAdress, skillsRequired, description, datePosted, JobBy.withName("Indeed"), Option("link"), None)
      val jobExist = Job.jobExist(position, company, location, "Indeed")
      if (!jobExist) {
        Job.addJob(job)
        TwitterTweet.tweetANewJobPost(job)
        //FacebookFeed.publishMessage(job)
      }

    }
  }

  /**
   * Read Job From Simply Hired Job Portal
   */
  def simplyHiredJobsReader = {
    val simplyHiredJobReaderUrl = "http://api.simplyhired.com.au/a/jobs-api/xml-v2/qo-scala+akka+Play2.0/ws-50/sb-dd"
    val simplyHiredWS = WS.url(simplyHiredJobReaderUrl)
      .setQueryParameter("pshid", "PUBLISHER_ID")
      .setQueryParameter("ssty", "3")
      .setQueryParameter("cflg", "r")
      .setQueryParameter("clip", "122.162.46.216")
      .get
    val resultOfSimplyHiredJobReaderUrl = simplyHiredWS.get
    val simplyHiredJobsXmlBody = resultOfSimplyHiredJobReaderUrl.getBody
    val simplyHiredXml = XML.loadString(simplyHiredJobsXmlBody)
    saveJobsFromSimplyHired(simplyHiredXml)

  }

  /**
   * Read Xml returned from  Simply Hired Job Portal Rest Call API
   */

  def saveJobsFromSimplyHired(simplyHiredXml: Elem) = {
    for (simplyHired <- (simplyHiredXml \\ "r")) {
      val position = (simplyHired \ "jt").text.trim
      val company = (simplyHired \ "cn").text match {
        case "" => "---"
        case _ => (simplyHired \ "cn").text.trim
      }
      val location = (simplyHired \ "loc").text.trim
      val jobType = "Permanent"
      val emailAdress = (simplyHired \ "src" \ "@url").text.trim
      val skillsRequired = "scala".split(",").toList
      val description = (simplyHired \ "e").text.trim
      val datePosted = new Date()
      val job = new JobEntity(new ObjectId, None, position, company, location,
        jobType, emailAdress, skillsRequired, description, datePosted, JobBy.withName("SimplyHired"), Option("link"),
        None)
      val jobExist = Job.jobExist(position, company, location, JobBy.withName("SimplyHired").toString)
      if (!jobExist) {
        Job.addJob(job)
        TwitterTweet.tweetANewJobPost(job)
        //FacebookFeed.publishMessage(job)
      }
    }
  }

  /**
   * Read Job From Career Builder Job Portal
   */

  def careerbuilderJobsReader = {
    val careerbuilderJobReaderUrl = "http://api.careerbuilder.com/v1/jobsearch"
    val careerbuilderWS = WS.url(careerbuilderJobReaderUrl)
      .setQueryParameter("DeveloperKey", "PUBLISHER_ID")
      .setQueryParameter("Keywords", "scala")
      .setQueryParameter("OrderBy", "date")
      .setQueryParameter("PerPage", "100")
      .setQueryParameter("PostedWithin", "1")
      .get
    val resultOfCareerbuilderJobReaderUrl = careerbuilderWS.get
    val careerbuilderJobsXmlBody = resultOfCareerbuilderJobReaderUrl.getBody
    val careerbuilderXml = XML.loadString(careerbuilderJobsXmlBody)
    saveJobsFromCareerBuilder(careerbuilderXml)

  }

  /**
   * Read Xml returned from  Career Builder Job Portal Rest Call API
   */

  def saveJobsFromCareerBuilder(careerBuilderXml: Elem) = {
    for (careerBuilder <- (careerBuilderXml \\ "JobSearchResult")) {
      val position = (careerBuilder \ "JobTitle").text.trim
      val company = (careerBuilder \ "Company").text.trim
      val location = (careerBuilder \ "Location").text.trim
      val jobType = (careerBuilder \ "EmploymentType").text.trim
      val emailAdress = (careerBuilder \ "JobDetailsURL").text.trim
      val skillsRequired = "scala".split(",").toList
      val description = (careerBuilder \ "DescriptionTeaser").text.trim
      val datePosted = new Date()
      val job = new JobEntity(new ObjectId, None, position, company, location,
        jobType, emailAdress, skillsRequired, description, datePosted, JobBy.withName("CareerBuilder"), Option("link"),
        None)
      val jobExist = Job.jobExist(position, company, location, JobBy.withName("CareerBuilder").toString)
      if (!jobExist) {
        Job.addJob(job)
        TwitterTweet.tweetANewJobPost(job)
        //FacebookFeed.publishMessage(job)
      }
    }
  }

}
