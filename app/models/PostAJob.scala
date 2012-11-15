package models

import com.novus.salat.global._
import com.novus.salat.annotations._
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.MongoHQConfig
import com.mongodb.casbah.commons.MongoDBObject
import java.util.Date
import java.util.regex.Pattern
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection

case class PostAJobForm(position: String, company: String, location: String, jobType: String, emailAddress: String, skillsRequired: String, description: String)
case class Job(@Key("_id") id: ObjectId, userId: ObjectId, position: String, company: String, location: String, jobType: String, emailAddress: String, skillsRequired: List[String], description: String, datePosted: Date)
object PostAJob {

  /*
   * Job Type
   */
  def jobType: Seq[(String, String)] = {
    Seq("Contract" -> "Contract", "Permanent" -> "Permanent")
  }

  /**
   *  Create A Job
   */

  def addJob(job: Job) = {
    JobDAO.insert(job)
  }

  /**
   * Find All Jobs
   */
  def findAllJobs: List[Job] = {
    JobDAO.find(MongoDBObject()).sort(orderBy = MongoDBObject("datePosted" -> -1)).toList
  }

  /**
   * Search The Job
   */
  def searchTheJob(stringTobeSearched: String): List[Job] = {
    var jobsFound: List[Job] = List()
    val allJobs = JobDAO.find(MongoDBObject()).toList
    for (eachJob <- allJobs) {
      if (eachJob.position.toUpperCase.contains(stringTobeSearched.toUpperCase) || eachJob.company.toUpperCase.contains(stringTobeSearched.toUpperCase) ||
        eachJob.jobType.toUpperCase.contains(stringTobeSearched.toUpperCase) || eachJob.location.toUpperCase.contains(stringTobeSearched.toUpperCase) ||
        isListContainElement(stringTobeSearched, eachJob.skillsRequired) || eachJob.datePosted.toString.toUpperCase.contains(stringTobeSearched.toUpperCase))
        jobsFound ++= List(eachJob)
    }
    jobsFound
  }

  def isListContainElement(stringTobeSearched: String, searchList: List[String]) = {
    val resultList = searchList.map(_.toUpperCase.trim.contains(stringTobeSearched.toUpperCase))
    resultList.contains(true)
  }

  /**
   * Find Job By Id
   */
  def findJobDetail(jobId: ObjectId): Option[Job] = {
    val jobFound = JobDAO.find(MongoDBObject("_id" -> jobId)).toList
    (jobFound.isEmpty) match {
      case true => None
      case false => Option(jobFound.toList(0))
    }

  }

  /**
   * Job Posted by A Particular User
   */
  def findJobsPostByUserId(userId: String): List[Job] = {
    JobDAO.find(MongoDBObject("userId" -> new ObjectId(userId))).sort(orderBy = MongoDBObject("datePosted" -> -1)).toList
  }

  /**
   * Update The Job
   */
  def updateJob(job: Job) = {
    JobDAO.update(MongoDBObject("_id" -> job.id), job, false, false, new WriteConcern)
  }

  /**
   * Find Job Matching User's Skills
   */
  //TO DO : Find A Good Approach Via MongoDB
  def findJobMatchingUserKeySkills(keySkills: List[String]) = {
    var jobsFound: List[Job] = List()
    val jobs = JobDAO.find(MongoDBObject()).toList

    for (eachJob <- jobs) {
      for (eackSkill <- keySkills) {
        if (eachJob.skillsRequired.contains(eackSkill)) jobsFound ++= List(eachJob)
      }
    }
    jobsFound
  }

  /**
   * Delete the Job By Job Id
   */

  def deleteJobByJobId(jobId: ObjectId) = {
    val jobToBeDelete = findJobDetail(jobId).get
    JobDAO.remove(jobToBeDelete)
  }

  /*
 * Search job for Job alert
 * */
  def searchTheJobForJobAlert(what: String, where: String): List[Job] = {
    var jobsFound: List[Job] = List()
    val allJobs = JobDAO.find(MongoDBObject()).toList
    for (eachJob <- allJobs) {
      if (eachJob.location.toUpperCase.contains(where.toUpperCase) && isListContainElement(what, eachJob.skillsRequired))
        jobsFound ++= List(eachJob)
    }
    jobsFound
  }

}

object JobDAO extends SalatDAO[Job, ObjectId](collection = MongoHQConfig.mongoDB("job"))