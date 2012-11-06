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

object PostAJobController extends Controller {

  val postAJobForm = Form(
    mapping(
      "Position" -> nonEmptyText,
      "Company" -> nonEmptyText,
      "Location" -> nonEmptyText,
      "JobType" -> nonEmptyText,
      "Email_Addrss_To_Apply_To" -> nonEmptyText,
      "Description" -> nonEmptyText)(PostAJobForm.apply)(PostAJobForm.unapply))

  /**
   * Load  Job  Page on scalajobz.com
   */

  def postAJob = Action { implicit request =>
    Ok(views.html.postajob(postAJobForm, request.session.get("userId").getOrElse(null)))
  }

  /**
   * Post A Job on scalajobz.com
   */
  def newJob = Action { implicit request =>
    postAJobForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index("There Was Some Errors During The Registration", request.session.get("userId").getOrElse(null), PostAJob.findAllJobs)),
      postAJobForm => {
        if (postAJobForm.position == "" || postAJobForm.company == "" || postAJobForm.location == ""
          || postAJobForm.jobType == "" || postAJobForm.jobType.equals("-- Select Job Type --") || postAJobForm.emailAddress == "") Ok("Please Fill The Mendatory Fields")
        else {
          if (request.session.get("userId") == None) Ok(views.html.login(Application.logInForm, request.session.get("userId").getOrElse(null)))
          else {
            val job = Job(new ObjectId, new ObjectId(request.session.get("userId").get), postAJobForm.position, postAJobForm.company, postAJobForm.location, postAJobForm.jobType, postAJobForm.emailAddress, postAJobForm.description, new Date)
            PostAJob.addJob(job)
            Ok(views.html.index("Hi Welcome To Scalajobz.com", request.session.get("userId").getOrElse(null), PostAJob.findAllJobs))
          }
        }
      })
  }

}