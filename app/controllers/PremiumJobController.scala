package controllers

import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.Results
import models.PremiumJob
import models.PremiumJobEntity
import org.bson.types.ObjectId
import java.util.Date
import models.PremiumJob
import play.api.Logger
import models.Common
import models.Alert
import models.PremiumDateValid
import net.liftweb.json.{ parse, DefaultFormats }
import net.liftweb.json.Serialization.{ read, write }
import models.ObjectIdSerializer
import java.util.Calendar
import java.text.SimpleDateFormat

object PremiumJobController extends Controller {

  implicit val formats = new net.liftweb.json.DefaultFormats {
  } + new ObjectIdSerializer

  /**
   * Redirect To Premium Job detail Page
   */

  def premiumJobDetail: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.premiumJobDetail())
  }

  /**
   * Send Request For Post a Premium Job
   * @param jobId is the requested Id For Premium Job
   */
  def premiumJobPost(jobId: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.premiumJobPost(request.session.get("userId").getOrElse(""), jobId)).withSession(request.session + ("premiumJobId" -> jobId))
  }

  /**
   * Deny For Premium Job
   */
  def denyForPremiumJob: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    try {
      request.session.get("premiumJobId") match {
        case None =>
          Logger.error("User Deny For Premium Job -  Premium JobId is not exist in session ")
          Common.setAlert(new Alert("error", "There was some error"))
          Results.Redirect("/findJobPostByUserId")
        case Some(premiumJobId: String) =>
          Common.setAlert(new Alert("success", "Job Posted Successfully!"))
          Results.Redirect("/findJobPostByUserId").withSession(
            request.session - "premiumJobId" - "pageRequested")
      }
    } catch {
      case ex =>
        Logger.error("User Deny For Premium Job - " + ex)
        Common.setAlert(new Alert("error", "There was some error"))
        Results.Redirect("/findJobPostByUserId").withSession(
          request.session - "premiumJobId" - "pageRequested")
    }
  }
  /**
   * CallBack From Paypal After Failure Or Success Of Premium Job
   * If Payment Fails then this callback return failure status in queryString
   *  If Payment Success then this callback return success  status in  queryString
   */
  def callBackForPremiumJob: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    try {
      val seq = request.queryString.get("requestStatus").getOrElse(Seq())
      seq.isEmpty match {
        case true =>
          Common.setAlert(new Alert("success", "Job Posted Successfully!"))
          Results.Redirect("/findJobPostByUserId").withSession(
            request.session - "premiumJobId" - "pageRequested")

        case false => seq.headOption match {
          case None =>
            Common.setAlert(new Alert("success", "Job Posted Successfully!"))
            Results.Redirect("/findJobPostByUserId").withSession(
              request.session - "premiumJobId" - "pageRequested")
          case Some(requestStatus: String) =>
            if (requestStatus.equals("success")) {
              val premiumJobId = request.session.get("premiumJobId")
              val page = request.session.get("pageRequested")
              val pageNumAndPrice = PremiumJob.getPageNumAndPrice(page.get)
              val premiumJobEntity = PremiumJobEntity(new ObjectId, new ObjectId(premiumJobId.get), new Date, pageNumAndPrice._1, pageNumAndPrice._2)
              PremiumJob.savePremiumJob(premiumJobEntity)
              Common.setAlert(new Alert("success", "Premium Job Posted Successfully!"))
              Results.Redirect("/findJobPostByUserId").withSession(
                request.session - "premiumJobId" - "pageRequested")
            } else {
              Common.setAlert(new Alert("success", "Job Posted Successfully!"))
              Results.Redirect("/findJobPostByUserId").withSession(
                request.session - "premiumJobId" - "pageRequested")
            }
        }
      }
    } catch {
      case ex =>
        Logger.error("Deny For Premium Job - " + ex)
        Common.setAlert(new Alert("error", "There was some error"))
        Results.Redirect("/findJobPostByUserId").withSession(
          request.session - "premiumJobId" - "pageRequested")
    }
  }

  /**
   * Set Requested Premium Job Page
   * @param  page is the premium job requesting page
   */
  def setPageForPremiumJob(page: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok.withSession(request.session + ("pageRequested" -> page))
  }

  /**
   * Premium Job Listing For a particular page
   * @param pageNum is the requested page number
   */
  def findPremiumJobs(pageNum: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    val premiumJobs = PremiumJob.findPremiumJobsForAPage(pageNum.toInt)
    Ok(views.html.premiumJobajax(premiumJobs))

  }
  /**
   * Count Premium Job  For a particular page
   * @param pageRequested is the requested page
   */
  def countPremiumJobsForAPage(pageRequested: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    val pageNumAndPrice = PremiumJob.getPageNumAndPrice(pageRequested)
    val count = PremiumJob.countPremiumJobsForAPage(pageNumAndPrice._1)
    if ((count < 3) && (request.session.get("premiumJobId") != None) && (request.session.get("pageRequested") != None))
      Ok("true")
    else
      Ok("false")
  }
  
  /**
   * To check Whether a job is premium or not
   * @param jobId is the job Id
   */
  def isPremiumJob(jobId: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    PremiumJob.isPremiumJob(jobId) match {
      case None => Ok("false")
      case Some(premiumJobEntity: PremiumJobEntity) => Ok("true")
    }
  }
/**
 * Find The Job Valid Date Range
 * @param jobId is the job Id
 */
  def findPremiumJobValidDateRange(jobId: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    PremiumJob.isPremiumJob(jobId) match {
      case None =>
        val premiumDateValid = PremiumDateValid("--", "-")
        Ok(write(premiumDateValid)).as("application/json")
      case Some(premiumJobEntity: PremiumJobEntity) =>
        val from_date: Calendar = Calendar.getInstance
        from_date.setTime(premiumJobEntity.premiumDate)
        val to_date = Calendar.getInstance
        to_date.setTime(premiumJobEntity.premiumDate)
        to_date.add(Calendar.DAY_OF_MONTH, PremiumJob.jobValidDays)
        val premiumDateValid = PremiumDateValid(new SimpleDateFormat("MMM dd yyyy").format(from_date.getTime),
          new SimpleDateFormat("MMM dd yyyy").format(to_date.getTime))
        Ok(write(premiumDateValid)).as("application/json")
    }
  }

  /**
   * In case Of IPN
   */
  def paypalCallback: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    println("request----->" + request)
    println("paypalCallback body----> " + request.body)
    Ok
  }
}