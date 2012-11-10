package controllers

import play.api.mvc.Controller
import play.api._
import play.api.mvc._
import play.api.data.Forms
import play.api.data._
import models.SignUp
import models.SignUpForm
import play.api.mvc.Controller
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.mvc.Http.Request
import play.libs._
import org.bson.types.ObjectId
import models.LogInForm
import models.LogIn
import utils.PasswordHashing
import models.PostAJob
import models.Alert
import models.Employer
import models.EditUserProfileForm

object UserController extends Controller {

  val editUserProfileForm = Form(
    mapping(
      "CurrentPassword" -> nonEmptyText,
      "NewPassword" -> nonEmptyText,
      "ConfirmPassword" -> nonEmptyText)(EditUserProfileForm.apply)(EditUserProfileForm.unapply))

  //Edit Profile

  def editUserProfile = Action { implicit request =>
    val userProfile = LogIn.findUserProfile(request.session.get("userId").get)
    Ok(views.html.editUserProfile(new Alert(null, null), userProfile.get, editUserProfileForm, request.session.get("userId").getOrElse(null)))
  }

  def findJobPostByUserId = Action { implicit request =>
    val jobPostByUserList = PostAJob.findJobsPostByUserId(request.session.get("userId").get)
    Ok(views.html.index(new Alert(null, null), request.session.get("userId").getOrElse(null), jobPostByUserList))
  }

  def updateUserProfile = Action { implicit request =>
    val userProfile = LogIn.findUserProfile(request.session.get("userId").get).get
    editUserProfileForm.bindFromRequest.fold(
      errors => BadRequest(views.html.editUserProfile(new Alert("error", "There Was Some Errors During Profile Editing"),
        userProfile, editUserProfileForm, request.session.get("userId").getOrElse(null))),
      editUserProfileForm => {
        val currentEncryptedPassword = (new PasswordHashing).encryptThePassword(editUserProfileForm.currentPassword)
        val encryptedPassword = (new PasswordHashing).encryptThePassword(editUserProfileForm.newPassword)
        if ((currentEncryptedPassword.equals(userProfile.password)) && (!currentEncryptedPassword.equals(encryptedPassword))) {
          LogIn.updateUser(userProfile, encryptedPassword)
          Ok(views.html.editUserProfile(new Alert("success", "Profile Updated"),
            userProfile, UserController.editUserProfileForm, request.session.get("userId").getOrElse(null)))
        }else if (currentEncryptedPassword.equals(encryptedPassword)) {
          val encryptedPassword = (new PasswordHashing).encryptThePassword(editUserProfileForm.newPassword)
          LogIn.updateUser(userProfile, encryptedPassword)
          Ok(views.html.editUserProfile(new Alert("error", "Current Password & New Password are same"),
            userProfile, UserController.editUserProfileForm, request.session.get("userId").getOrElse(null)))
        } 
        else
          Ok(views.html.editUserProfile(new Alert("error", "Invalid Current Password"),
            userProfile, UserController.editUserProfileForm, request.session.get("userId").getOrElse(null)))

      })
  }

}