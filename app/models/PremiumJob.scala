package models

import org.bson.types.ObjectId
import com.novus.salat.annotations.Key
import java.util.Date
import com.novus.salat.dao.SalatDAO
import com.mongodb.casbah.Imports.WriteConcern
import com.mongodb.casbah.MongoConnection
import utils.MongoHQConfig
import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.global.ctx
import java.util.Calendar
import play.api.Play

/**
 * PremiumJobEntity
 * @param jobid the premium job id
 * @param premiumDate the premium date
 * @param pageNum is page requested for premium
 * @param premiumPrice is the premium job price
 */
case class PremiumJobEntity(@Key("_id") id: ObjectId,
  jobId: ObjectId,
  premiumDate: Date,
  pageNum: Int,
  premiumPrice: Float)

/**
 * PremiumDateValid
 * @param  from : Premium Job Valid From Date
 * @param to: Date till which Premum Job Valid
 */
case class PremiumDateValid(from: String, to: String)

object PremiumJob {
  /**
   * Number Of Premium Jobs Per Page
   */
  val premiumJobsPerPage = 2//Play.current.configuration.getString("premiumJobsPerPage").get.toInt
   val jobValidDays = 10//Play.current.configuration.getString("jobValidDays").get.toInt

  /**
   * Get Set of Types Of Premium Job Available
   */
  def jobType: Set[(String, String)] = {
    var PremiumJobType: Set[(String, String)] = Set()
    if (countPremiumJobsForAPage(1) < premiumJobsPerPage)
      PremiumJobType ++= Seq("p1" -> "First Page Listing $3.00 USD")
    if (countPremiumJobsForAPage(2) < premiumJobsPerPage)
      PremiumJobType ++= Seq("p2" -> "Second Page Listing $2.00 USD")
    if (countPremiumJobsForAPage(3) < premiumJobsPerPage)
      PremiumJobType ++= Seq("p3" -> "Third Page Listing $1.00 USD")
    PremiumJobType
  }

  /**
   * Save Premium Jobs
   * @param premiumJobEntity is object of PremiumJobEntity
   */
  def savePremiumJob(premiumJobEntity: PremiumJobEntity): Option[ObjectId] = {
    PremiumJobDAO.insert(premiumJobEntity)
  }
  /**
   * Get List of Premium Jobs
   * It will return the list of premium jobs
   */
  def getPremiumJobs: List[PremiumJobEntity] = {
    val current_date: Calendar = Calendar.getInstance
    val date_before = Calendar.getInstance
    date_before.add(Calendar.DAY_OF_MONTH, -jobValidDays)
    PremiumJobDAO.find(ref = MongoDBObject("premiumDate" -> MongoDBObject("$gte" -> date_before.getTime, "$lte" -> current_date.getTime))).toList
  }
  /**
   * Get List of Premium Jobs For A particular page
   * @param pageNum is the page to get the list of premium jobs
   */
  def getPremiumJobsForApage(pageNum: Int): List[PremiumJobEntity] = {
    val current_date: Calendar = Calendar.getInstance
    val date_before = Calendar.getInstance
    date_before.add(Calendar.DAY_OF_MONTH, -jobValidDays)
    PremiumJobDAO.find(ref = MongoDBObject("premiumDate" -> MongoDBObject("$gte" -> date_before.getTime, "$lte" -> current_date.getTime),
      "pageNum" -> pageNum)).toList
  }
  /**
   * Get List of Premium Jobs Ids
   * @param premiumJobsList is the List of Premium Jobs
   */
  def getListofPremiumJobIds(premiumJobsList: List[PremiumJobEntity]): List[ObjectId] = {
    premiumJobsList map {
      premiumJob => premiumJob.jobId
    }
  }

  /**
   * Get List of JobEntity which mapped with Premium Jobs
   */
  def findPremiumJobs: List[JobEntity] = {
    val premiumJobs = getPremiumJobs
    val listOfPremiumJobIds = PremiumJob.getListofPremiumJobIds(premiumJobs)
    JobDAO.find(MongoDBObject("_id" -> MongoDBObject("$in" -> listOfPremiumJobIds))).toList
  }
  /**
   * Get List of JobEntity which mapped with Premium Jobs
   * @param pageNum is the page to get premium jobs
   */
  def findPremiumJobsForAPage(pageNum: Int): List[JobEntity] = {
    val premiumJobs = getPremiumJobsForApage(pageNum)
    val listOfPremiumJobIds = PremiumJob.getListofPremiumJobIds(premiumJobs)
    JobDAO.find(MongoDBObject("_id" -> MongoDBObject("$in" -> listOfPremiumJobIds))).toList
  }
  /**
   * Count Number Of Premium Jobs Per Page
   * @param pageNum is the given page
   */
  def countPremiumJobsForAPage(pageNum: Int): Int = {
    getPremiumJobsForApage(pageNum).size
  }
  /**
   * To get The page number and price for the option selected for premium job
   * @param pageRequested is the requested page
   */
  def getPageNumAndPrice(pageRequested: String): (Int, Float) = {
    pageRequested match {
      case "p1" => (1, 3)
      case "p2" => (2, 2)
      case "p3" => (3, 1)
    }
  }
  /**
   * To get The Premim Job Via  Jobid
   * @param jobId is the Job id
   */
  def getPremiumJobByJobId(jobId: String): Option[PremiumJobEntity] = {
    val jobFound = PremiumJobDAO.find(MongoDBObject("jobId" -> new ObjectId(jobId))).toList
    (jobFound.isEmpty) match {
      case true => None
      case false => Option(jobFound.toList(0))
    }
  }
  /**
   * To check for a Premium Job by Job Id
   * @param jobId is the Job id
   */
  def isPremiumJob(jobId: String): Option[PremiumJobEntity] = {
    val current_date: Calendar = Calendar.getInstance
    val date_before = Calendar.getInstance
    date_before.add(Calendar.DAY_OF_MONTH, -jobValidDays)
    val premiumJobs = PremiumJobDAO.find(ref = MongoDBObject("premiumDate" -> MongoDBObject("$gte" -> date_before.getTime, "$lte" -> current_date.getTime),
      "jobId" -> (new ObjectId(jobId)))).toList
    premiumJobs.isEmpty match {
      case true => None
      case false => premiumJobs.headOption
    }

  }

}

object PremiumJobDAO extends SalatDAO[PremiumJobEntity, ObjectId](collection = MongoHQConfig.mongoDB("premiumJob"))
