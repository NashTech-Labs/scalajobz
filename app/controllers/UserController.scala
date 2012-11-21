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
import utils.SendEmail

object UserController extends Controller {

  val editUserProfileForm = Form(
    mapping(
      "CurrentPassword" -> nonEmptyText,
      "NewPassword" -> nonEmptyText,
      "ConfirmPassword" -> nonEmptyText)(EditUserProfileForm.apply)(EditUserProfileForm.unapply))

  /**
   * Redirect To User Profile edit page
   */

  def editUserProfile = Action { implicit request =>
    val userProfile = SignUp.findUserById(request.session.get("userId").get)
    Ok(views.html.editUserProfile(new Alert(null, null), userProfile.get, editUserProfileForm, request.session.get("userId").getOrElse(null)))
  }

  /**
   * Find list of job post by a user
   */

  def findJobPostByUserId = Action { implicit request =>
    val jobPostByUserList = PostAJob.findJobsPostByUserId(new ObjectId(request.session.get("userId").get))
    Ok(views.html.index(new Alert(null, null), request.session.get("userId").getOrElse(null), jobPostByUserList, true))
  }

  /**
   * Update User Profile
   */
  def updateUserProfile = Action { implicit request =>
    val userProfile = SignUp.findUserById(request.session.get("userId").get).get
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
        } else if (currentEncryptedPassword.equals(encryptedPassword)) {
          Ok(views.html.editUserProfile(new Alert("error", "Current Password & New Password are same"),
            userProfile, UserController.editUserProfileForm, request.session.get("userId").getOrElse(null)))
        } else
          Ok(views.html.editUserProfile(new Alert("error", "Invalid Current Password"),
            userProfile, UserController.editUserProfileForm, request.session.get("userId").getOrElse(null)))

      })
  }

  /**
   * Redirect To Forget Password Page
   */
  def forgetPassword = Action { implicit request =>
    Ok(views.html.forgetPassword(new Alert(null, null)))
  }

  /**
   * Send Password To User Email Id
   */

  def sendForgetPassword(emailId: String) = Action { implicit request =>
    if (SignUp.findUserByEmail(emailId).isEmpty) {
      Ok(false.toString)
    } else {
      val userList = SignUp.findUserByEmail(emailId)
      val user = Option(userList.toList(0)).get
      SendEmail.sendPassword(user.emailId, (new PasswordHashing).decryptThePassword(user.password))
      Ok(true.toString)
    }
  }

  
  /**
   * Register Job seeker for getting Job alert
   */
  def registerJobSeeker(emailId: String, skillsToken: String) = Action { implicit request =>
    val newJobSeeker = Employer(new ObjectId, emailId, "", skillsToken.split(" ").toList.filter(x => !(x == "")), true)
    val userId = SignUp.registerJobSeeker(newJobSeeker)
    Ok
  }
}