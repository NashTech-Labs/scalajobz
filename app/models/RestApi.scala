package models
import java.util.Date
import utils.BitlyUtil

/**
 * Job formatter Class For Rest Api Json
 */
case class JobFormatter(jobId: String,
  position: String,
  company: String,
  location: String,
  jobType: String,
  contactAddress: String,
  skillsRequired: List[String],
  description: String,
  datePosted: Date,
  contactType: String,
  tinyUrl: Option[String])

object RestApi {

  /**
   * Format List of Jobs For Rest Api
   */
  def jobsListFormatterForRestApi(jobs: List[JobEntity]): List[JobFormatter] = {

    jobs map { job =>
      val contactType = getContactType(job)

      JobFormatter(job.id.toString,
        job.position,
        job.company,
        job.location,
        job.jobType,
        job.emailAddress,
        job.skillsRequired,
        job.description,
        job.datePosted,
        contactType,
        None)
    }

  }

  /**
   * Format a job For Rest Api
   */
  def jobFormatterForRestApi(job: JobEntity): JobFormatter = {
    val contactType = getContactType(job)

    JobFormatter(job.id.toString,
      job.position,
      job.company,
      job.location,
      job.jobType,
      job.emailAddress,
      job.skillsRequired,
      job.description,
      job.datePosted,
      contactType,
      getContactAddress(job, contactType))
  }

  /**
   * Get Contact Type
   */
  def getContactType(job: JobEntity): String = {
    job.applyType match {
      case None =>
        job.jobBy.toString match {
          case "ScalaJobz" => "email"
          case _ => "url"
        }
      case Some(value: String) =>
        value match {
          case "email" => "email"
          case "link" => "url"
        }
    }
  }
  /**
   * Get Tiny Url For Rest Api
   */
  def getContactAddress(job: JobEntity, contactType: String): Option[String] = {
    contactType match {
      case "url" => Option(BitlyUtil.generateTinyUrl(job.emailAddress))
      case "email" => None
    }
  }
}