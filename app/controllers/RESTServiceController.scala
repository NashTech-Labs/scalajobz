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
import play.api.libs.json.Json
import models.JobFormatter
import utils.MailUtility
import play.api.Logger

/**
 * To Send Detail With Rest Api Call
 */
case class ResponseDescription(query: String, totalResults: Int, pageNumber: Int, jobsPerPage: Int)
/**
 * Response Get When make call to rest Api for query string
 */
case class JsonResponseWithDescription(responseDescription: ResponseDescription, results: List[JobFormatter])

object RESTServiceController extends Controller {

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

    RestApi.isValidRequest(request.queryString) match {
      case true =>
        val queryStringValues = RestApi.readQueryString(request.queryString)
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

  /**
   * REST API To get Jobs Post in last 30 days for a Token String
   */

  def processGetJobListForQueryString(code: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>

    RestApi.isValidRequest(request.queryString) match {
      case true =>
        val queryStringValues = RestApi.readQueryString(request.queryString)
        val page: Int = queryStringValues._1
        val jobsPerPage = queryStringValues._2
        val from = (page - 1) * jobsPerPage;
        val to = from + jobsPerPage;
        val results = Job.searchTheJobForRestAPI(code, Job.findAllJobs)
        val selectedRange = results.slice(from, to)
        if (selectedRange.isEmpty) {
          Ok(write(new Alert("No Result Found", "No Job Exist"))).as("application/json")
        } else {
          val descriptionJson = ResponseDescription(code, results.size, page, selectedRange.size)
          val jsonResponse = JsonResponseWithDescription(descriptionJson, RestApi.jobsListFormatterForRestApi(selectedRange))
          Ok(write(jsonResponse)).as("application/json")
        }
      case false =>
        Ok(write(new Alert("Invalid Request", "Invalid Parameters"))).as("application/json")
    }

  }

  /**
   * REST API To Get Job Detail Via Job Id
   */

  def getJobDetailViaJobId(jobId: String): Action[play.api.mvc.AnyContent] = Action {
    try {
      val jobDetailOpt = Job.findJobDetail(new ObjectId(jobId))
      jobDetailOpt match {
        case None =>
          Ok(write(new Alert("No Result Found", "Invalid Job Id"))).as("application/json")
        case Some(jobDetail: JobEntity) =>
          Ok(write(RestApi.jobFormatterForRestApi(jobDetail))).as("application/json")
      }
    } catch {
      case ex =>
        Logger.error("Error occurred When requesting REST API To Get Job Detail Via Job Id : ", ex)
        Ok(write(new Alert("Error", "Invalid Job Id"))).as("application/json")
    }
  }

  /**
   * Sent Job Detail  Mail For Rest Api Call
   */

  def sendJobDetailMailForRestApi: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    RestApi.isValidQueryStringForSendMail(request.queryString) match {
      case (None, None) => Ok(write(new Alert("Error", "Invalid Parameters"))).as("application/json")
      case (Some(email: String), Some(jobId: String)) =>
        MailUtility.sendMailForJobDetail(email, jobId) match {
          case true => Ok(write(new Alert("Success", "Email Sent"))).as("application/json")
          case false => Ok(write(new Alert("Error", "There is Some Error.Please Try Again Later"))).as("application/json")
        }
    }
  }

}
