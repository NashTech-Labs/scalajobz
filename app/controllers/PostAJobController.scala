package controllers
import play.api.mvc.Controller
import play.api._
import play.api.mvc._
import play.api.data.Forms
import play.api.data._
import play.api.mvc.Controller
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.mvc.Http.Request
import play.libs._
import org.bson.types.ObjectId
import models.PostAJobForm
import models.Job
import models.Job
import models.PostAJob
import java.util.Date
import models.Alert

object PostAJobController extends Controller {

  val postAJobForm = Form(
    mapping(
      "Position" -> nonEmptyText,
      "Company" -> nonEmptyText,
      "Location" -> nonEmptyText,
      "JobType" -> nonEmptyText,
      "Email_Addrss_To_Apply_To" -> nonEmptyText,
      "Skills" -> nonEmptyText,
      "Description" -> nonEmptyText)(PostAJobForm.apply)(PostAJobForm.unapply))

  /**
   * Load  Job  Page on scalajobz.com
   */

  def postAJob = Action { implicit request =>
    if (request.session.get("userId") == None)
      Ok(views.html.login(new Alert(null, null), Application.logInForm, request.session.get("userId").getOrElse(null), "jobPost"))
    else
      Ok(views.html.postajob(postAJobForm, request.session.get("userId").getOrElse(null)))
  }

  /**
   * Post A Job on scalajobz.com
   */
  def newJob = Action { implicit request =>
    postAJobForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert("error", "There Was Some Errors During Job Posting"), request.session.get("userId").getOrElse(null), PostAJob.findAllJobs, false)),
      postAJobForm => {
        if (postAJobForm.position == "" || postAJobForm.company == "" || postAJobForm.location == ""
          || postAJobForm.jobType == "" || postAJobForm.jobType.equals("-- Select Job Type --") || postAJobForm.emailAddress == "") Ok("Please Fill The Mendatory Fields")
        else {
          if (request.session.get("userId") == None) Ok(views.html.login(new Alert(null, null), Application.logInForm, request.session.get("userId").getOrElse(null), "jobPost"))
          else {
            val job = Job(new ObjectId, new ObjectId(request.session.get("userId").get), postAJobForm.position, postAJobForm.company, postAJobForm.location, postAJobForm.jobType, postAJobForm.emailAddress, List(postAJobForm.skillsRequired), postAJobForm.description, new Date)
            PostAJob.addJob(job)
            Ok(views.html.index(new Alert("success", "Job Posted Successfully"), request.session.get("userId").getOrElse(null), PostAJob.findAllJobs, false))
          }
        }
      })
  }

  /**
   * Load  Job  Page on scalajobz.com
   */

  def findAJob(searchString: String, editFlag: String) = Action { implicit request =>
    println("++++++ " + editFlag)
    val searchJobList = PostAJob.searchTheJob(searchString)
    if (editFlag.equals("true"))
      Ok(views.html.ajax_result(searchJobList, true))
    else
      Ok(views.html.ajax_result(searchJobList, false))
  }

  def findJobDetail(jobId: String) = Action { implicit request =>
    val job: Option[Job] = PostAJob.findJobDetail(new ObjectId(jobId))
    Ok(views.html.jobDetail(job.get, request.session.get("userId").getOrElse(null)))
  }

  def findJobPostForEdit(jobId: String) = Action { implicit request =>
    val job = PostAJob.findJobDetail(new ObjectId(jobId)).get
    Ok(views.html.editJob(job, postAJobForm, request.session.get("userId").getOrElse(null)))
  }

  /**
   * Edit Job
   */
  def editJob(jobId: String) = Action { implicit request =>
    val existJob = PostAJob.findJobDetail(new ObjectId(jobId)).get
    postAJobForm.bindFromRequest.fold(
      errors => BadRequest(views.html.editJob(existJob, postAJobForm, request.session.get("userId").getOrElse(null))),
      postAJobForm => {
        val job = Job(existJob.id, existJob.userId, postAJobForm.position, postAJobForm.company, postAJobForm.location, postAJobForm.jobType, postAJobForm.emailAddress, List(postAJobForm.skillsRequired), postAJobForm.description, new Date)
        PostAJob.updateJob(job)
        Ok(views.html.index(new Alert("success", "Job Updated Successfully"), request.session.get("userId").getOrElse(null), PostAJob.findAllJobs, true))

      })
  }

  /*
   * Delete Job
   * */

  def deleteJob(jobId: String) = Action { implicit request =>
    PostAJob.deleteJobByJobId(new ObjectId(jobId))
    val jobPostByUserList = PostAJob.findJobsPostByUserId(request.session.get("userId").get)
    Ok
  }

}