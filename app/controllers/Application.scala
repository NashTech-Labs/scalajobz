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
import models.Employer
import org.bson.types.ObjectId
import models.LogInForm
import models.LogIn
import utils.PasswordHashing
import models.PostAJob

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
    Ok(views.html.index("Hi Welcome To Scalajobz.com", request.session.get("employerId").getOrElse(null), PostAJob.findAllJobs))
  }

  /**
   * Signup on scalajobz.com
   */

  def signUpOnScalaJobz(flag: String) = Action { implicit request =>
    Ok(views.html.signup(signUpForm, request.session.get("employerId").getOrElse(null), flag))
  }

  /**
   * Create A New User
   */
  def newUser(flag: String) = Action { implicit request =>
    signUpForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index("There Was Some Errors During The SignUp", request.session.get("userId").getOrElse(null), PostAJob.findAllJobs)),
      signUpForm => {

        if (!SignUp.findUserByEmail(signUpForm.emailId).isEmpty) Ok("This Email Is Already registered With ScalaJobz")
        else if (!signUpForm.password.equals(signUpForm.confirmPassword)) Ok("Passwords Do Not match. Please try again")
        else {
          val encryptedPassword = (new PasswordHashing).encryptThePassword(signUpForm.password)
          val newEmployer = Employer(new ObjectId, signUpForm.emailId, encryptedPassword)
          val employerId = SignUp.createUser(newEmployer)
          val userSession = request.session + ("employerId" -> employerId.get.toString)
          if (flag.equals("login"))
            Ok(views.html.index("Hi Welcome To Scalajobz.com", employerId.get.toString, PostAJob.findAllJobs)).withSession(userSession)
          else
            Ok(views.html.postajob(PostAJobController.postAJobForm, employerId.get.toString)).withSession(userSession)

        }
      })
  }

  /**
   * Login On ScalaJobz
   */

  def logIn(flag: String) = Action { implicit request =>
    println("hello")
    logInForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index("There Was Some Errors During The Login", null, PostAJob.findAllJobs)),
      logInForm => {
        val encryptedPassword = (new PasswordHashing).encryptThePassword(logInForm.password)
        val employers = LogIn.findUser(logInForm.emailId, encryptedPassword)
        if (!employers.isEmpty) {
          val employerSession = request.session + ("employerId" -> employers(0).id.toString)
          if (flag.equals("login"))
            Ok(views.html.index("Hi Welcome To Scalajobz.com", employers(0).id.toString, PostAJob.findAllJobs)).withSession(employerSession)
          else
            Ok(views.html.postajob(PostAJobController.postAJobForm, employers(0).id.toString)).withSession(employerSession)
        } else Ok("Login Unsuccessfull")
      })
  }
  /**
   * Login on scalajobz.com
   */

  def loginOnScalaJobz = Action { implicit request =>
    Ok(views.html.login(logInForm, request.session.get("employerId").getOrElse(null), "login"))
  }

  /**
   * Log Out
   */

  def logOutFromScalaJobz = Action {
    Ok(views.html.index("Hi Welcome To Scalajobz.com", null, PostAJob.findAllJobs)).withNewSession
  }

}