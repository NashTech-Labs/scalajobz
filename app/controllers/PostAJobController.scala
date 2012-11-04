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

  def postAJob = Action {
    Ok(views.html.postajob(postAJobForm))
  }

  /**
   * Post A Job on scalajobz.com
   */
  def newJob = Action { implicit request =>
    postAJobForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index("There Was Some Errors During The Registration")),
      postAJobForm => {
        if (postAJobForm.position == "" || postAJobForm.company == "" || postAJobForm.location == ""
          || postAJobForm.jobType == "" || postAJobForm.jobType.equals("-- Select Job Type --") ||postAJobForm.emailAddress == "") Ok("Please Fill The Mendatory Fields")
        else {
          val job = Job(new ObjectId, postAJobForm.position, postAJobForm.company, postAJobForm.location, postAJobForm.jobType, postAJobForm.emailAddress, postAJobForm.description,new Date)
          PostAJob.addJob(job)
          Ok("Your Job has been Posted")
        }
      })
  }

}