package controllers

import play.api.mvc.Action
import play.api.mvc.Controller
import models.Job
import net.liftweb.json.{ parse, DefaultFormats }
import net.liftweb.json.Serialization.{ read, write }
import models.Alert
import models.ObjectIdSerializer
import org.bson.types.ObjectId
import models.JobEntity
import models.RestApi
import java.util.regex.Pattern

object RESTServiceController extends Controller {

  val defaultPage = 1
  val defaultJobsPerPage = 50

  implicit val formats = new net.liftweb.json.DefaultFormats {
  } + new ObjectIdSerializer

  /**
   * Redirect To Rest API UI
   */
  def restApi: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.restapi(request.session.get("userId").getOrElse("")))
  }

  /**
   * REST API To get all Jobs Post in last 30 days
   */
  def processGetAllJobsList: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    isQueryStringContainsRequestForPagination(request.queryString) match {
      case false =>
        val results = Job.findAllJobs
        if (results.isEmpty) {
          Ok(write(new Alert("No Result Found", "No Job Exist"))).as("application/json")
        } else {
          Ok(write(RestApi.jobsListFormatterForRestApi(results))).as("application/json")
        }
      case true =>
        isValidRequest(request.queryString) match {
          case true =>
            val queryStringValues = readQueryString(request.queryString)
            val page: Int = queryStringValues._1
            val jobsPerPage = queryStringValues._2
            val results = Job.getJobByPagination(page - 1, jobsPerPage)
            if (results.isEmpty) {
              Ok(write(new Alert("No Result Found", "No Job Exist"))).as("application/json")
            } else {
              Ok(write(RestApi.jobsListFormatterForRestApi(results))).as("application/json")
            }
          case false =>
            Ok(write(new Alert("Invalid Request", "Invalid Parameters"))).as("application/json")
        }
    }

  }

  /**
   * REST API To get Jobs Post in last 30 days for a Token String
   */

  def processGetJobListForQueryString(code: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>

    isQueryStringContainsRequestForPagination(request.queryString) match {
      case false =>
        val results = Job.searchTheJobForRestAPI(code, Job.findAllJobs)
        if (results.isEmpty) {
          Ok(write(new Alert("No Result Found", "No Job Exist"))).as("application/json")
        } else {
          Ok(write(RestApi.jobsListFormatterForRestApi(results))).as("application/json")
        }

      case true =>
        isValidRequest(request.queryString) match {
          case true =>
            val queryStringValues = readQueryString(request.queryString)
            val page: Int = queryStringValues._1
            val jobsPerPage = queryStringValues._2
            val from = (page - 1) * jobsPerPage + 1;
            val to = from + jobsPerPage - 1;
            val results = Job.searchTheJobForRestAPI(code, Job.findAllJobs)
            val selectedRange = results.slice(from, to)
            if (selectedRange.isEmpty) {
              Ok(write(new Alert("No Result Found", "No Job Exist"))).as("application/json")
            } else {
              Ok(write(RestApi.jobsListFormatterForRestApi(selectedRange))).as("application/json")
            }
          case false =>
            Ok(write(new Alert("Invalid Request", "Invalid Parameters"))).as("application/json")
        }
    }

  }

  /**
   * REST API To Get Job Detail Via Job Id
   */

  def getJobDetailViaJobId(jobId: String): Action[play.api.mvc.AnyContent] = Action {
    val jobDetailOpt = Job.findJobDetail(new ObjectId(jobId))
    jobDetailOpt match {
      case None =>
        Ok(write(new Alert("No Result Found", "Invalid Job Id"))).as("application/json")
      case Some(jobDetail: JobEntity) =>
        Ok(write(RestApi.jobFormatterForRestApi(jobDetail))).as("application/json")
    }
  }

  /**
   * Check For Rest Api Contains The Request For Pagination
   */

  def isQueryStringContainsRequestForPagination(queryString: Map[String, Seq[String]]): Boolean = {
    queryString.isEmpty match {
      case true => false
      case false => true
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
}
