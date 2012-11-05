package models

import com.novus.salat.global._
import com.novus.salat.annotations._
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.MongoHQConfig
import com.mongodb.casbah.commons.MongoDBObject
import java.util.Date

case class PostAJobForm(position: String, company: String, location: String, jobType: String, emailAddress: String, description: String)
case class Job(@Key("_id") id: ObjectId, userId :ObjectId,position: String, company: String, location: String, jobType: String, emailAddress: String, description: String, datePosted: Date)
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

  def findAllJobs: List[Job] = {
    JobDAO.find(MongoDBObject()).toList
  }

}

object JobDAO extends SalatDAO[Job, ObjectId](collection = MongoHQConfig.mongoDB("job"))