package models

import com.novus.salat.global._
import com.novus.salat.annotations._
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.MongoHQConfig
import com.mongodb.casbah.commons.MongoDBObject
import java.util.Date

case class PostAJobForm(position: String, company: String, location: String, jobType: String, emailAddress: String, description: String)
case class Job(@Key("_id") id: ObjectId, userId: ObjectId, position: String, company: String, location: String, jobType: String, emailAddress: String, description: String, datePosted: Date)
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
      if (eachJob.position.contains(stringTobeSearched) || eachJob.company.contains(stringTobeSearched) ||
        eachJob.jobType.contains(stringTobeSearched) || eachJob.location.contains(stringTobeSearched))
        jobsFound ++= List(eachJob)
    }
    jobsFound
  }

  /**
   * Find Job By Id
   */
  def findJobDetail(jobId: String): Option[Job] = {
    val jobFound = JobDAO.find(MongoDBObject("_id" -> new ObjectId(jobId))).toList
    (jobFound.isEmpty) match {
      case true => None
      case false => Option(jobFound.toList(0))
    }

  }

  /**
 * Job Posted by A Particular User
 */
  def findJobsPostByUserId(userId: String): List[Job] = {
    JobDAO.find(MongoDBObject("userId" -> new ObjectId(userId))).toList
  }

}

object JobDAO extends SalatDAO[Job, ObjectId](collection = MongoHQConfig.mongoDB("job"))