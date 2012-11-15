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
import models.Alert
import models.JobAlertForm
import models.PostAJob
import utils.PasswordHashing
import models.Employer
import models.SignUp

object JobAlertController extends Controller {

  val jobAlertForm = Form(
    mapping(
      "EmailId" -> nonEmptyText,
      "Password" -> nonEmptyText,
      "ConfirmPassword" -> nonEmptyText,
      "KeySkills" -> nonEmptyText)(JobAlertForm.apply)(JobAlertForm.unapply))

  //Edit Profile

  def informMe = Action { implicit request =>
    Ok(views.html.informMe(new Alert(null, null), jobAlertForm, null))
  }

  def registerJobSeeker = Action { implicit request =>
    jobAlertForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert("error", "There Was Some Errors During The Notification"), null, PostAJob.findAllJobs, false)),
      jobAlertForm => {
        if (!SignUp.findUserByEmail(jobAlertForm.emailId).isEmpty) {
          Ok(views.html.informMe(new Alert("error", "This Email Is Already registered With ScalaJobz"), JobAlertController.jobAlertForm, null))
        } else {
          val encryptedPassword = (new PasswordHashing).encryptThePassword(jobAlertForm.password)
          val newJobSeeker = Employer(new ObjectId, jobAlertForm.emailId, encryptedPassword, jobAlertForm.keySkills.split(",").toList, true)
          val userId = SignUp.registerJobSeeker(newJobSeeker)
          if (newJobSeeker != null)
            Ok(views.html.informMe(new Alert("success", "You are successfully enrolled with ScalaJobz."), JobAlertController.jobAlertForm, null))
          else
            Ok(views.html.informMe(new Alert("error", "There Was Some Error"), JobAlertController.jobAlertForm, null))
        }
      })

  }

}