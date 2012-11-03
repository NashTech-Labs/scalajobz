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
   * Post A Job on scalajobz.com
   */

  def postAJob = Action {
    Ok(views.html.postajob(postAJobForm))
  }

}