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

object Application extends Controller {

  val signUpForm = Form(
    mapping(
      "EmailId" -> nonEmptyText,
      "Password" -> nonEmptyText,
      "Confirm Password" -> nonEmptyText)(SignUpForm.apply)(SignUpForm.unapply))

  def index = Action {
    Ok(views.html.index("Hi Welcome To scalajobz.com"))
  }

  /*
   * Signup on scalajobz.com
   */

  def signUpOnScalaJobz = Action {
    Ok(views.html.signup(signUpForm))
  }

  def newUser = Action { implicit request =>
    signUpForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index("There Was Some Errors During The Registration")),
      signUpForm => {
        if (signUpForm.password == signUpForm.confirmPassword) SignUp.createUser(signUpForm.emailId, signUpForm.password)
        Redirect(routes.Application.index)
      }
      
    )
  }

}