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
import play.api.data.Forms.nonEmptyText
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
import models.ContactUsForm
import utils.MailUtility

object Application extends Controller {
  val errorString = "error"
  val currentUserId = "userId"
  val loginFlag = "login"
  val signUpForm = Form(
    Forms.mapping(
      "EmailId" -> nonEmptyText,
      "Password" -> nonEmptyText,
      "ConfirmPassword" -> nonEmptyText)(SignUpForm.apply)(SignUpForm.unapply))

  val contactUsForm = Form(
    Forms.mapping(
      "Name" -> nonEmptyText,
      "EmailAddress" -> nonEmptyText,
      "Subject" -> nonEmptyText,
      "Message" -> nonEmptyText)(ContactUsForm.apply)(ContactUsForm.unapply))

  /**
   * Login Form Mapping
   */

  val logInForm = Form(
    Forms.mapping(
      "EmailId" -> nonEmptyText,
      "Password" -> nonEmptyText)(LogInForm.apply)(LogInForm.unapply))

  def index: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    val alert = Common.alert
    Common.setAlert(new Alert("", ""))
    val pageNumber = 0
    val jobsPerPage = 25
    Ok(views.html.index(alert, request.session.get(currentUserId).getOrElse(""), Job.getJobByPagination(pageNumber, jobsPerPage), false))
  }

  /**
   * Job Pagination Call
   */

  def jobPagination(editFlag: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    if (editFlag.equals("true")) {
      val jobPostByUserList = Job.findJobsPostByUserId(new ObjectId(request.session.get("userId").get))
      Ok(views.html.ajax_result(jobPostByUserList, true))
    } else {
      Ok(views.html.ajax_result(Job.findAllJobs, false))
    }
  }

  /**
   * Signup on scalajobz.com
   */

  def signUpOnScalaJobz(flag: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.signup(new Alert("", ""), signUpForm, request.session.get(currentUserId).getOrElse(""), flag))
  }

  /**
   * Create A New User
   */
  def newUser(flag: String): Action[play.api.mvc.AnyContent] = Action { implicit request =>
    signUpForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert(errorString, "There Was Some Errors During The SignUp"),
        request.session.get(currentUserId).getOrElse(""), Job.findAllJobs, false)),
      signUpForm => {
        if (!User.findUserRegisteredWithScalaJobzViaEmailId(signUpForm.emailId).isEmpty) {
          Ok(views.html.signup(new Alert(errorString, "This Email Is Already registered With ScalaJobz"),
            Application.signUpForm, request.session.get(currentUserId).getOrElse(""), flag))
        } else {
          val encryptedPassword = PasswordHashing.encryptPassword(signUpForm.password)
          val newUser = UserEntity(new ObjectId, signUpForm.emailId, encryptedPassword, List(), false, None, None)
          val userId = User.createUser(newUser)
          val userSession = request.session + (currentUserId -> userId.get.toString)
          if (flag.equals(loginFlag)) {
            Common.setAlert(new Alert("success", "Registered Successfully"))
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
      errors => BadRequest(views.html.index(new Alert(errorString, "There Was Some Errors During The Login"), "", Job.findAllJobs, false)),
      logInForm => {
        val encryptedPassword = PasswordHashing.encryptPassword(logInForm.password.trim)
        val users = User.findUser(logInForm.emailId.trim, encryptedPassword)
        if (!users.isEmpty) {
          val userSession = request.session + (currentUserId -> users(0).id.toString)
          if (flag.equals(loginFlag)) {
            Results.Redirect("/findAllJobs").withSession(userSession)
          } else {
            Ok(views.html.postajob(JobController.postAJobForm, users(0).id.toString)).withSession(userSession)
          }
        } else { Ok(views.html.login(new Alert(errorString, "Invalid Credentials"), Application.logInForm, "", loginFlag)) }
      })
  }
  /**
   * Login on scalajobz.com
   */

  def loginOnScalaJobz: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.login(new Alert("", ""), logInForm, request.session.get(currentUserId).getOrElse(""), loginFlag))
  }

  /**
   * Log Out
   */

  def logOutFromScalaJobz: Action[play.api.mvc.AnyContent] = Action {
    Common.setAlert(new Alert("success", "SignOut Successfully"))
    Results.Redirect("/findAllJobs").withNewSession
  }

  /**
   * Redirect To Contact Us Page
   */

  def contactUs: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.contactus(contactUsForm, request.session.get(currentUserId).getOrElse("")))
  }

  /**
   * Send mail to ScalaJobz through Contact Us Page & Send The Acknowledgement Mail To The Sender
   */

  def contactUsEmail: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    contactUsForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(new Alert(errorString, "There Was Some Error"), "", Job.findAllJobs, false)),
      contactUsForm => {
        MailUtility.sendEmailToScalaJobzFromContactUs(contactUsForm.name, contactUsForm.emailAddress, contactUsForm.subject, contactUsForm.message)
        MailUtility.acknowledgementMail(contactUsForm.name, contactUsForm.emailAddress)
        Common.setAlert(new Alert("success", "Your Message Has Been Sent"))
        Results.Redirect("/findAllJobs")
      })
  }

  /**
   * Redirect To error page
   */

  def errorPage: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.errorPage("Page Not Found- " + "There Is Some Error"))
  }

  /**
   * Redirect To Login Page When Login Failed Via Social Networks
   */

  def loginFailureViaSocialNetworks: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.login(new Alert(errorString, "SignIn Failure"), Application.logInForm, "", loginFlag))
  }

}