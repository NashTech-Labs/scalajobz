package utils

import play.libs.WS
import play.api.Play
import scala.util.parsing.json.JSONObject
import models.JobEntity
import models.JobEntity
import models.Job

object BitlyUtil {
  
/**
   *generate Tiny Url at time of new job posting
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
   *get or create Tiny Url at time of viewing job detail
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
   * Generate Tiny Url
   */
  def generateTinyUrl(longUrl: String): String = {
    val apiKey = Play.current.configuration.getString("bitly_access_key").get
    val URL = "https://api-ssl.bitly.com/v3/shorten"
    val promise = WS.url(URL).setQueryParameter("apiKey", apiKey).setQueryParameter("login", "scalajobz").setQueryParameter("longUrl", longUrl).get
    val shortUrlJson = promise.get.asJson
    shortUrlJson.get("data").get("url")toString
  }
}