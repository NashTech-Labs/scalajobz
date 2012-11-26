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
import utils.DailyJobAlert

case class PostAJobForm(position: String,
  company: String,
  location: String,
  jobType: String,
  emailAddress: String,
  skillsRequired: String,
  description: String)
case class JobEntity(@Key("_id") id: ObjectId,
  userId: ObjectId,
  position: String,
  company: String, location: String,
  jobType: String,
  emailAddress: String,
  skillsRequired: List[String],
  description: String,
  datePosted: Date)
  
  
object Job extends App {

  /**
   * Job Type
   */
  def jobType: Seq[(String, String)] = {
    Seq("Contract" -> "Contract", "Permanent" -> "Permanent")
  }

  /**
   *  Create A Job
   */

  def addJob(job: JobEntity) = {
    JobDAO.insert(job)
  }

  /**
   * Find All Jobs
   */
  def findAllJobs: List[JobEntity] = {
    JobDAO.find(MongoDBObject()).sort(orderBy = MongoDBObject("datePosted" -> -1)).toList
  }

  /**
   * Find Job posted in last N hours
   */
  def findJobsOfLastNHours: List[JobEntity] = {
    findAllJobs filter (job => ((new Date).getTime - job.datePosted.getTime) / (1000 * 60 * 60) <= 24)
  }

  /**
   * Search The Job
   */
  def searchTheJob(stringTobeSearched: String): List[JobEntity] = {
    val searchStringTokenList = stringTobeSearched.split(" ").toList.filter(x => !(x == ""))
    val allJobs = JobDAO.find(MongoDBObject()).toList
    searchJobs(searchStringTokenList, allJobs)
  }

  /**
   * Search the jobs on the basis of list of searching tokens
   */

  def searchJobs(searchStringTokenList: List[String], allJobs: List[JobEntity]): List[JobEntity] = {
    var jobsFound: Set[JobEntity] = Set()
    for (searchToken <- searchStringTokenList) {
      for (eachJob <- allJobs) {
        if (eachJob.position.toUpperCase.contains(searchToken.toUpperCase) || eachJob.company.toUpperCase.contains(searchToken.toUpperCase) ||
          eachJob.jobType.toUpperCase.contains(searchToken.toUpperCase) || eachJob.location.toUpperCase.contains(searchToken.toUpperCase) ||
          isListContainElement(searchToken, eachJob.skillsRequired) || eachJob.datePosted.toString.toUpperCase.contains(searchToken.toUpperCase))
          jobsFound ++= List(eachJob)
      }
    }
    jobsFound.toList

  }

  /**
   * Matching for a key skill in the list of skills Required
   */

  def isListContainElement(stringTobeSearched: String, searchList: List[String]) = {
    val resultList = searchList.map(_.toUpperCase.trim.contains(stringTobeSearched.toUpperCase))
    resultList.contains(true)
  }

  /**
   * Find Job By Id
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
   */
  def findJobsPostByUserId(userId: ObjectId): List[JobEntity] = {
    JobDAO.find(MongoDBObject("userId" -> userId)).sort(orderBy = MongoDBObject("datePosted" -> -1)).toList
  }

  /**
   * Update The Job
   */
  def updateJob(job: JobEntity) {
    JobDAO.update(MongoDBObject("_id" -> job.id), job, false, false, new WriteConcern)
  }


  /**
   * Delete the Job By Job Id
   */

  def deleteJobByJobId(jobId: ObjectId) = {
    val jobToBeDelete = findJobDetail(jobId).get
    JobDAO.remove(jobToBeDelete)
  }
}

object JobDAO extends SalatDAO[JobEntity, ObjectId](collection = MongoHQConfig.mongoDB("job"))