package models

import com.novus.salat.global.ctx
import com.novus.salat.annotations.Key
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.MongoHQConfig
import com.mongodb.casbah.commons.MongoDBObject
import java.util.Date
import java.util.regex.Pattern
import com.mongodb.casbah.Imports.WriteConcern
import com.mongodb.casbah.MongoConnection
import utils.DailyJobAlert
import utils.TwitterTweet
import java.text.SimpleDateFormat

/**
 * Class for Post A Job Form
 */
case class PostAJobForm(position: String,
  company: String,
  location: String,
  jobType: String,
  emailAddress: Option[String],
  linkAddress: Option[String],
  skillsRequired: String,
  applyType: String,
  description: String)

/**
 * Job Entity
 * @param userId the person's id
 * @param position the job position
 * @param company the company name
 * @param location the job location
 * @param emailAddress the person's email to apply to
 * @param skillsRequired the skills required for the Job
 * @param description the job's description
 * @param datePosted the date on which job has been posted
 * @param jobBy the publisher name
 * @param applyType the way to apply for a job
 */
case class JobEntity(@Key("_id") id: ObjectId,
  userId: Option[ObjectId],
  position: String,
  company: String,
  location: String,
  jobType: String,
  emailAddress: String,
  skillsRequired: List[String],
  description: String,
  datePosted: Date,
  jobBy: JobBy.Value,
  applyType: Option[String])

/**
 * JobMailAlertEntity
 * @param userId the jobSeeker's id
 * @param mailSentTime is the time when mail sent to Jobseeker
 */
case class JobMailAlertEntity(@Key("_id") id: ObjectId,
  userId: ObjectId,
  mailSentTime: Date)

/** Factory for [[models.JobEntity]] instances. */
object Job {

  /**
   * Job Type
   * To be displayed in drop down list on UI
   */
  def jobType: Seq[(String, String)] = {
    Seq("Contract" -> "Contract",
      "Permanent" -> "Permanent",
      "Project" -> "Project",
      "Telecommute" -> "Telecommute",
      "Other" -> "Other")
  }

  /**
   * Find Job Apply Address
   * @param emailaddress is the option of email address
   * @param linkAddress is the option of link apply
   */

  def findJobApplyAdress(emailAddress: Option[String], linkAddress: Option[String]): String = {
    emailAddress match {
      case None => linkAddress match {
        case None => ""
        case Some(link: String) => link
      }
      case Some(email: String) => email
    }
  }
  
  /**
   * Find Job Apply Type
   * @param applyType is the option of apply type
   */

  def findApplyType(applyType: Option[String]): String = {
    applyType match {
      case None => "email"
      case Some(value: String) => value
    }
  }

  /**
   *  Create A Job
   *  @param job the job to be created
   */

  def addJob(job: JobEntity): Option[ObjectId] = {
    jobExist(job.position, job.company, job.location, job.jobBy.toString) match {
      case true => None
      case false =>
        JobDAO.insert(job)
    }

  }

  /**
   *  Check For redundancy of job
   *  @param position is the title of job
   *  @param company is the name of employer company
   *  @param location is the job location
   *  @param jobBy is the source of the job
   */
  def jobExist(position: String, company: String, location: String, jobBy: String): Boolean = {
    val jobList = JobDAO.find(MongoDBObject("position" -> position, "company" -> company,
      "location" -> location, "jobBy" -> jobBy)).toList
    (jobList.isEmpty) match {
      case true => false
      case false => true
    }
  }

  /**
   * Find All Jobs Posted In Last 30 days
   */
  def findAllJobs: List[JobEntity] = {
    val jobs = JobDAO.find(MongoDBObject()).sort(orderBy = MongoDBObject("datePosted" -> -1)).toList
    jobs filter (job => ((new Date).getTime - job.datePosted.getTime) / (1000 * 60 * 60 * 24) <= 30)
  }

  /**
   * Find Job posted in last 24 hours
   */
  def findJobsOfLastNHours: List[JobEntity] = {
    findAllJobs filter (job => ((new Date).getTime - job.datePosted.getTime) / (1000 * 60 * 60) <= 24)
  }

  /**
   * Search The Job
   * @param stringTobeSearched contains skills
   */
  def searchTheJob(stringTobeSearched: String): List[JobEntity] = {
    val searchStringTokenList = stringTobeSearched.split(" ").toList.filter(x => !(x == ""))
    val allJobs = findAllJobs
    searchJobs(searchStringTokenList, allJobs)

  }

  /**
   * Search The Job for last 24 hours for rest api
   * @param stringTobeSearched contains skills
   */
  def searchTheJobForRestAPI(stringTobeSearched: String): List[JobEntity] = {
    val searchStringTokenList = stringTobeSearched.split(" ").toList.filter(x => !(x == ""))
    searchJobs(searchStringTokenList, findJobsOfLastNHours)
  }

  /**
   * Search the jobs on the basis of list of searching tokens
   * @param searchStringTokenList contains skills
   * @param allJobs is the list of all jobs in the system
   */

  def searchJobs(searchStringTokenList: List[String], allJobs: List[JobEntity]): List[JobEntity] = {
    val patternToFindJob = Pattern.compile("(?i)" + searchStringTokenList.mkString("|"))
    allJobs filter (job =>
      patternToFindJob.matcher(job.position + "," + job.company
        + "," + job.location + "," + job.jobType
        + "," + job.skillsRequired + "," + new SimpleDateFormat("MMM dd yyyy").format(job.datePosted)
        + "," + job.jobBy).find)
  }

  /**
   * Find Job By Id
   * @param id is the id of job to be searched
   */
  def findJobDetail(jobId: ObjectId): Option[JobEntity] = {
    val jobFound = JobDAO.find(MongoDBObject("_id" -> jobId)).toList
    (jobFound.isEmpty) match {
      case true => None
      case false => Option(jobFound.toList(0))
    }

  }

  /**
   * Job Posted by A Particular User
   * @param userId is the id of user who has posted the different jobs
   */
  def findJobsPostByUserId(userId: ObjectId): List[JobEntity] = {
    JobDAO.find(MongoDBObject("userId" -> userId)).sort(orderBy = MongoDBObject("datePosted" -> -1)).toList
  }

  /**
   * Update The Job
   * @param job is the updated job
   */
  def updateJob(job: JobEntity): Unit = {
    JobDAO.update(MongoDBObject("_id" -> job.id), job, false, false, new WriteConcern)
  }

  /**
   * Delete the Job By Job Id
   * @param jobId is the id of job to be deleted
   */

  def deleteJobByJobId(jobId: ObjectId): Unit = {
    val jobToBeDelete = findJobDetail(jobId).get
    JobDAO.remove(jobToBeDelete)
  }

  /**
   * Count Total Number of Jobs
   */

  def countTotalJobs: Long = {
    JobDAO.count()
  }

  /**
   * Get Job By Pagination
   * @param pageNumber is the current page number
   * @param jobsPerPage is the number of job per page
   */

  def getJobByPagination(pageNumber: Int, jobsPerPage: Int): List[JobEntity] = {
    val jobs = JobDAO.find(MongoDBObject()).sort(orderBy = MongoDBObject("datePosted" -> -1)).skip((pageNumber) * jobsPerPage).limit(jobsPerPage).toList
    jobs
  }

  /**
   * Find Job Seeker Via UserId to Send Job Alert Mail
   * @param userId is the Job Seeker Id
   */

  def findJobseekerForJobMailAlert(userId: ObjectId): Option[JobMailAlertEntity] = {
    val users = JobMailAlertDAO.find(MongoDBObject("userId" -> userId)).toList
    (users.isEmpty) match {
      case true => None
      case false => Option(users.toList(0))
    }
  }

  /**
   * Check For Job Alert Mail Sent to Job Seeker in Last 24 hours
   * @param userId is the Job Seeker Id
   */

  def isJobAlertMailSent(userId: ObjectId): Boolean = {
    findJobseekerForJobMailAlert(userId) match {
      case None =>
        val jobMailAlertEntity = JobMailAlertEntity(new ObjectId, userId, new Date)
        JobMailAlertDAO.insert(jobMailAlertEntity)
        false
      case Some(jobMailAlertEntity) =>
        (((new Date).getTime - jobMailAlertEntity.mailSentTime.getTime) / (1000 * 60 * 60) <= 24) match {
          case true => true
          case false =>
            val updateJobMailAlertEntity = JobMailAlertEntity(jobMailAlertEntity.id, jobMailAlertEntity.userId, new Date)
            JobMailAlertDAO.update(MongoDBObject("_id" -> jobMailAlertEntity.id), updateJobMailAlertEntity, false, false, new WriteConcern)
            false
        }
    }

  }
}

object JobDAO extends SalatDAO[JobEntity, ObjectId](collection = MongoHQConfig.mongoDB("job"))

object JobMailAlertDAO extends SalatDAO[JobMailAlertEntity, ObjectId](collection = MongoHQConfig.mongoDB("jobMailAlert"))
