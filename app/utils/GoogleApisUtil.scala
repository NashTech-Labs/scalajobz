package utils
import play.libs.WS
import play.api.Play
import models.Job
import models.JobEntity

object GoogleApisUtil {

  /**
   * generate Tiny Url at time of new job posting
   */
  def createTinyUrl(applyType: Option[String], contactAddress: String): Option[String] = {

    applyType match {
      case None => None
      case Some(jobApplyType: String) =>
        jobApplyType match {
          case "link" => Option(generateTinyUrl(contactAddress))
          case _ => None
        }
    }
  }
  /**
   * get or create Tiny Url at time of viewing job detail
   */
  def getOrCreateTinyUrl(job: JobEntity): String = {
    job.tinyUrl match {
      case None =>
        val tinyUrl = generateTinyUrl(job.emailAddress)
        val jobUpdate = JobEntity(job.id, job.userId, job.position, job.company, job.location,
          job.jobType, job.emailAddress, job.skillsRequired, job.description, job.datePosted, job.jobBy, job.applyType, Option(tinyUrl))
        Job.updateJob(jobUpdate)
        tinyUrl
      case Some(tinyUrl: String) => tinyUrl
    }
  }

  /**
   * Generate Tiny Url By Using goo.gl
   */

  def generateTinyUrl(longUrl: String): String = {

    val apiKey = Play.current.configuration.getString("goo.gle_api_key").get

    val jpromise = WS.url("https://www.googleapis.com/urlshortener/v1/url")
      .setHeader("Content-Type", "application/json")
      .setQueryParameter("key", apiKey)
      .post("""{"longUrl": """" + longUrl + """"}""")
    val shortUrlJson = jpromise.get().asJson
    shortUrlJson.get("id").toString
  }

}