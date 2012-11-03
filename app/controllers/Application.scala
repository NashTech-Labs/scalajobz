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
import models.User
import org.bson.types.ObjectId

object Application extends Controller {

  val signUpForm = Form(
    mapping(
      "EmailId" -> nonEmptyText,
      "Password" -> nonEmptyText,
      "Confirm Password" -> nonEmptyText)(SignUpForm.apply)(SignUpForm.unapply))

  def index = Action {
    Ok(views.html.index("Hi Welcome To scalajobz.com"))
  }

  /**
   * Signup on scalajobz.com
   */

  def signUpOnScalaJobz = Action {
    Ok(views.html.signup(signUpForm))
  }

  /**
   * Create A New User
   */
  def newUser = Action { implicit request =>
    signUpForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index("There Was Some Errors During The Registration")),
      signUpForm => {

        if (!SignUp.findUserByEmail(signUpForm.emailId).isEmpty) Ok("This Email Is Already registered With ScalaJobz")
        else if (!signUpForm.password.equals(signUpForm.confirmPassword)) Ok("Passwords Do Not match. Please try again")
        else {
          val newUser = User(new ObjectId, signUpForm.emailId, signUpForm.password)
          SignUp.createUser(newUser)
          Ok("You've Signed Up Successfully")
        }
      })
  }

}