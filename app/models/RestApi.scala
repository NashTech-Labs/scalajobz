package models

import java.util.Date
import org.bson.types.ObjectId
import play.api.Logger
import utils.GoogleApisUtil

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

  val defaultPage = 1
  val defaultJobsPerPage = 50

  /**
   * Format List of Jobs For Rest Api
   */
  def jobsListFormatterForRestApi(jobs: List[JobEntity]): List[JobFormatter] = {

    jobs map { job =>
      
      val jobDetailLink = "http://" + Common.getContextUrl + "/jobDetail/" + job.id
      
      JobFormatter(job.id.toString,
        job.position,
        job.company,
        job.location,
        job.jobType,
        jobDetailLink,
        job.skillsRequired,
        job.description,
        job.datePosted,
        "url",
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
      case "url" => Option(GoogleApisUtil.getOrCreateTinyUrl(job))
      case "email" => None
    }
  }

  /**
   * Check For Rest Api Contains The Valid  Request Parameters
   */
  def isValidRequest(queryString: Map[String, Seq[String]]): Boolean = {

    val requestList = List("page", "jobsPerPage")
    val filteredMap = queryString.filter { case (k, v) => !requestList.contains(k) }
    filteredMap.isEmpty match {
      case true => true
      case false => false
    }
  }

  /**
   * Read Request Parameters For Page No and JobsPerPage
   */

  def readQueryString(queryString: Map[String, Seq[String]]): (Int, Int) = {
    val pageSeq = queryString.get("page").getOrElse(Seq())
    val jobsPerPageSeq = queryString.get("jobsPerPage").getOrElse(Seq())
    val page = pageSeq.isEmpty match {
      case true => defaultPage
      case false =>
        pageSeq.headOption match {
          case None => defaultPage
          case Some(value: String) =>
            value.matches("^\\d*$") match {
              case true => value.toInt
              case false => defaultPage
            }
        }
    }
    val jobsPerPage = jobsPerPageSeq.isEmpty match {
      case true => defaultJobsPerPage
      case false =>
        jobsPerPageSeq.headOption match {
          case None => defaultJobsPerPage
          case Some(value: String) => value.matches("^\\d*$") match {
            case true => value.toInt
            case false => defaultJobsPerPage
          }
        }
    }
    (page, jobsPerPage)
  }

  def isValidQueryStringForSendMail(queryString: Map[String, Seq[String]]): (Option[String], Option[String]) = {
    try {
      val emailId = queryString.get("emailId")
      val jobId = queryString.get("jobId")

      if (emailId == None || jobId == None) (None, None)
      else if (isValidEmail(emailId.get.head) && isValidJobId(jobId.get.head)) (Option(emailId.get.head), Option(jobId.get.head))
      else (None, None)
    } catch {
      case ex =>
        Logger.error("Error occurred When Checking For a valid Email Id: ", ex)
        (None, None)
    }
  }

  def isValidEmail(email: String): Boolean = {

    """^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$""".r.findFirstIn(email) match {
      case None => false
      case Some(emailId: String) => true
    }

  }

  def isValidJobId(jobId: String) = {
    try {
      Job.findJobDetail(new ObjectId(jobId)) match {
        case None => false
        case Some(job: JobEntity) => true
      }
    } catch {
      case ex =>
        Logger.error("Error occurred When Checking For a valid Parameters : ", ex)
        false
    }

  }
}