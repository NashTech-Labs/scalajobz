package controllers

import play.api.mvc.Controller
import play.api
import play.api.mvc
import play.api.data.Forms
import play.api.data
import models.User
import models.SignUpForm
import play.api.mvc.Controller
import play.api.data.Form
import play.api.data.Forms._
import play.mvc.Http.Request
import play.libs
import org.bson.types.ObjectId
import models.LogInForm
import utils.PasswordHashing
import models.Job
import models.Alert
import models.UserEntity
import models.Common
import play.api.mvc.Action
import play.api.mvc.Results

object Application extends Controller {
  val errorString = "error"
  val currentUserId = "userId"
  val loginFlag = "login"
  val signUpForm = Form(
    mapping(
      "EmailId" -> nonEmptyText,
      "Password" -> nonEmptyText,
      "ConfirmPassword" -> nonEmptyText)(SignUpForm.apply)(SignUpForm.unapply))

  /**
   * Login Form Mapping
   */

  val logInForm = Form(
    mapping(
      "EmailId" -> nonEmptyText,
      "Password" -> nonEmptyText)(LogInForm.apply)(LogInForm.unapply))

  def index: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    val alert = Common.alert
    Common.setAlert(new Alert(null, null))
    Ok(views.html.index(alert, request.session.get(currentUserId).getOrElse(null), Job.findAllJobs, false))
  }

  /**
   * Signup on scalajobz.com
   */

  def signUpOnScalaJobz(flag: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.signup(new Alert(null, null), signUpForm, request.session.get(currentUserId).getOrElse(null), flag))
  }

  /**
   * Create A New User
   */
  def newUser(flag: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    signUpForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert(errorString, "There Was Some Errors During The SignUp"),
        request.session.get(currentUserId).getOrElse(null), Job.findAllJobs, false)),
      signUpForm => {
        if (!User.findUserByEmail(signUpForm.emailId).isEmpty) {
          Ok(views.html.signup(new Alert(errorString, "This Email Is Already registered With ScalaJobz"),
              Application.signUpForm, request.session.get(currentUserId).getOrElse(null), flag))
        } else {
          val encryptedPassword = (new PasswordHashing).encryptThePassword(signUpForm.password)
          val newUser = UserEntity(new ObjectId, signUpForm.emailId, encryptedPassword, List(), false)
          val userId = User.createUser(newUser)
          val userSession = request.session + (currentUserId -> userId.get.toString)
          Common.setAlert(new Alert("success", "Registration Successful"))
          if (flag.equals(loginFlag)) {
            Results.Redirect("/findAllJobs").withSession(userSession)
          } else {
            Results.Redirect(routes.JobController.newJob).withSession(userSession)
          }
        }
      })
  }

  /**
   * Login On ScalaJobz
   */

  def logIn(flag: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    logInForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert(errorString, "There Was Some Errors During The Login"), null, Job.findAllJobs, false)),
      logInForm => {
        val encryptedPassword = (new PasswordHashing).encryptThePassword(logInForm.password)
        val users = User.findUser(logInForm.emailId, encryptedPassword)
        if (!users.isEmpty) {
          val userSession = request.session + (currentUserId -> users(0).id.toString)
          if (flag.equals(loginFlag)) {
            Ok(views.html.index(new Alert(null, null), users(0).id.toString, Job.findAllJobs, false)).withSession(userSession)
          } else {
            Ok(views.html.postajob(JobController.postAJobForm, users(0).id.toString)).withSession(userSession)
          }
        } else { Ok(views.html.login(new Alert(errorString, "Invalid Credentials"), Application.logInForm, null, loginFlag)) }
      })
  }
  /**
   * Login on scalajobz.com
   */

  def loginOnScalaJobz: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.login(new Alert(null, null), logInForm, request.session.get(currentUserId).getOrElse(null), loginFlag))
  }

  /**
   * Log Out
   */

  def logOutFromScalaJobz: Action[play.api.mvc.AnyContent] = Action {
    Ok(views.html.index(new Alert(null, null), null, Job.findAllJobs, false)).withNewSession
  }

}
