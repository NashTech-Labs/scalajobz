package controllers
import play.api.mvc.Controller
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.text
import play.api.data.Forms.optional
import play.api.data.Form
import play.api.mvc.Controller
import play.api.mvc
import play.api.data
import play.api.data.Forms
import play.mvc.Http.Request
import org.bson.types.ObjectId
import models.PostAJobForm
import models.JobEntity
import models.Job
import java.util.Date
import models.Alert
import models.Common
import play.api.mvc.Action
import play.api.mvc.Results
import models.JobBy
import utils.TwitterTweet
import utils.FacebookFeed
import utils.GoogleApisUtil

object JobController extends Controller {

  val postAJobForm = Form(
    Forms.mapping(
      "Position" -> nonEmptyText,
      "Company" -> nonEmptyText,
      "Location" -> nonEmptyText,
      "JobType" -> nonEmptyText,
      "Email_Addrss_To_Apply_To" -> optional(text),
      "Link_To_Apply_To" -> optional(text),
      "Skills" -> nonEmptyText,
      "Apply_Type" -> text,
      "Description" -> text)(PostAJobForm.apply)(PostAJobForm.unapply))

  /**
   * Load  Job  Page on scalajobz.com
   */

  def postAJob: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    if (request.session.get("userId") == None) {
      Ok(views.html.login(new Alert("", ""), Application.logInForm, request.session.get("userId").getOrElse(""), "jobPost"))
    } else {
      Ok(views.html.postajob(postAJobForm, request.session.get("userId").getOrElse("")))
    }
  }

  /**
   * Post A Job on scalajobz.com
   */
  def newJob: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    postAJobForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert("error", "There Was Some Errors During Job Posting"),
        request.session.get("userId").getOrElse(""), Job.findAllJobs, false)),
      postAJobForm => {
        if (request.session.get("userId") == None) {
          Ok(views.html.login(new Alert("", ""),
            Application.logInForm, request.session.get("userId").getOrElse(""), "jobPost"))
        } else {
          val emailaddress = Job.findJobApplyAdress(postAJobForm.emailAddress, postAJobForm.linkAddress)
          val job = JobEntity(new ObjectId, Option(new ObjectId(request.session.get("userId").get)),
            postAJobForm.position, postAJobForm.company, postAJobForm.location, postAJobForm.jobType,
            emailaddress, postAJobForm.skillsRequired.split(",").toList,
            postAJobForm.description, new Date, JobBy.withName("ScalaJobz"), Option(postAJobForm.applyType), GoogleApisUtil.createTinyUrl(Option(postAJobForm.applyType), emailaddress))
          Job.addJob(job) match {
            case None =>
              Common.setAlert(new Alert("error", "Job Already Exist!"))
              Results.Redirect("/findJobPostByUserId")
            case _ =>
              TwitterTweet.tweetANewJobPost(job)
              //FacebookFeed.publishMessage(job)
              Common.setAlert(new Alert("success", "Job Posted Successfully!"))
              Results.Redirect("/findJobPostByUserId")
          }

        }
      })
  }

  /**
   * Load  Job  Page on scalajobz.com
   */

  def findAJob(searchString: String, editFlag: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    val searchJobList = Job.searchTheJob(searchString)
    if (editFlag.equals("true")) {
      Ok(views.html.ajax_result(searchJobList, true))
    } else {
      Ok(views.html.ajax_result(searchJobList, false))
    }
  }

  /**
   * Find Job Detail By JobId
   */
  def findJobDetail(jobId: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    val job: Option[JobEntity] = Job.findJobDetail(new ObjectId(jobId))
    job match {
      case None => Results.Redirect("/errorPage")
      case Some(jobDetail: JobEntity) => Ok(views.html.jobDetail(jobDetail, request.session.get("userId").getOrElse("")))
    }

  }

  /**
   * Find Job Detail By JobId For Edit
   */
  def findJobPostForEdit(jobId: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    val job = Job.findJobDetail(new ObjectId(jobId)).get
    Ok(views.html.editJob(job, postAJobForm, request.session.get("userId").getOrElse("")))
  }

  /**
   * Edit Job
   */
  def editJob(jobId: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    val existJob = Job.findJobDetail(new ObjectId(jobId))
    existJob match {
      case None => Results.Redirect(routes.UserController.findJobPostByUserId)
      case Some(job: JobEntity) =>
        postAJobForm.bindFromRequest.fold(
          errors => BadRequest(views.html.editJob(job, postAJobForm, request.session.get("userId").getOrElse(""))),
          postAJobForm => {
            val emailaddress = Job.findJobApplyAdress(postAJobForm.emailAddress, postAJobForm.linkAddress)
            val editJob = JobEntity(job.id, job.userId, postAJobForm.position,
              postAJobForm.company, postAJobForm.location, postAJobForm.jobType,
              emailaddress, postAJobForm.skillsRequired.split(",").toList,
              postAJobForm.description, new Date, job.jobBy, Option(postAJobForm.applyType), job.tinyUrl)
            Job.updateJob(editJob)
            Common.setAlert(new Alert("success", "Job Updated"))
            Results.Redirect("/findJobPostByUserId")
          })
    }
  }

  /**
   * Delete Job by JobId
   */

  def deleteJob(jobId: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Common.setAlert(new Alert("success", "Job Deleted"))
    Job.deleteJobByJobId(new ObjectId(jobId))
    Ok
  }

}
