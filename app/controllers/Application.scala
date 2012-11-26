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
import models.Job
import models.Alert
import models.Employer
import models.Common

object Application extends Controller {

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

  def index = Action { implicit request =>
    val alert = Common.alert
    Common.setAlert(new Alert(null, null))
    Ok(views.html.index(alert, request.session.get("userId").getOrElse(null), Job.findAllJobs, false))
  }

  /**
   * Signup on scalajobz.com
   */

  def signUpOnScalaJobz(flag: String) = Action { implicit request =>
    Ok(views.html.signup(new Alert(null, null), signUpForm, request.session.get("userId").getOrElse(null), flag))
  }

  /**
   * Create A New User
   */
  def newUser(flag: String) = Action { implicit request =>
    signUpForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert("error", "There Was Some Errors During The SignUp"), request.session.get("userId").getOrElse(null), Job.findAllJobs, false)),
      signUpForm => {
        if (!SignUp.findUserByEmail(signUpForm.emailId).isEmpty) {
          Ok(views.html.signup(new Alert("error", "This Email Is Already registered With ScalaJobz"), Application.signUpForm, request.session.get("userId").getOrElse(null), flag))
        } else {
          val encryptedPassword = (new PasswordHashing).encryptThePassword(signUpForm.password)
          val newUser = Employer(new ObjectId, signUpForm.emailId, encryptedPassword, List(), false)
          val userId = SignUp.createUser(newUser)
          val userSession = request.session + ("userId" -> userId.get.toString)
          Common.setAlert(new Alert("success", "Registration Successful"))
          if (flag.equals("login")) {
            Results.Redirect("/findAllJobs").withSession(userSession)
            //Ok(views.html.index(new Alert("success", "Registration Successful"), userId.get.toString, Job.findAllJobs, false)).withSession(userSession)

          } else
            Results.Redirect(routes.PostAJobController.newJob).withSession(userSession)
        }
      })
  }

  /**
   * Login On ScalaJobz
   */

  def logIn(flag: String) = Action { implicit request =>
    logInForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert("error", "There Was Some Errors During The Login"), null, Job.findAllJobs, false)),
      logInForm => {
        val encryptedPassword = (new PasswordHashing).encryptThePassword(logInForm.password)
        val users = LogIn.findUser(logInForm.emailId, encryptedPassword)
        if (!users.isEmpty) {
          val userSession = request.session + ("userId" -> users(0).id.toString)
          if (flag.equals("login"))
            Ok(views.html.index(new Alert(null,null), users(0).id.toString, Job.findAllJobs, false)).withSession(userSession)
          else
            Ok(views.html.postajob(PostAJobController.postAJobForm, users(0).id.toString)).withSession(userSession)
        } else Ok(views.html.login(new Alert("error", "Invalid Credentials"), Application.logInForm, null, "login"))
      })
  }
  /**
   * Login on scalajobz.com
   */

  def loginOnScalaJobz = Action { implicit request =>
    Ok(views.html.login(new Alert(null, null), logInForm, request.session.get("userId").getOrElse(null), "login"))
  }

  /**
   * Log Out
   */

  def logOutFromScalaJobz = Action {
    Ok(views.html.index(new Alert(null, null), null, Job.findAllJobs, false)).withNewSession
  }

}