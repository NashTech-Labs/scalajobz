package template

import models.Job
import org.specs2.mutable.Specification
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import models.Alert
import models.JobEntity
import org.bson.types.ObjectId
import java.util.Date
import models.UserEntity
import play.api.data.Form
import models.LogInForm
import controllers.Application
import controllers.JobController
import controllers.UserController
import models.User
import org.specs2.mutable.BeforeAfter
import models.JobDAO
import models.UserDAO
import com.mongodb.casbah.commons.MongoDBObject

@RunWith(classOf[JUnitRunner])
class TemplateTest extends Specification with BeforeAfter {

  override def before {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
  }

  "index template" in {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    val html = views.html.index(new Alert("Welcome", "Welcome in scalajobz"), employers.head.id.toString, List(), false)
    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("Welcome")
  }

  "ajax result template" in {
    val html = views.html.ajax_result(List(), false)
    contentType(html) must equalTo("text/html")
  }

  "edit job template" in {
    val job = JobEntity(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date)
    Job.addJob(job)
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    val html = views.html.editJob(job, JobController.postAJobForm, employers.head.id.toString)
    contentType(html) must equalTo("text/html")
  }

  "edit user profile  template" in {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    val html = views.html.editUserProfile(new Alert("Welcome", "Welcome in scalajobz"), employers.head,
      UserController.editUserProfileForm, employers.head.id.toString)
    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("Welcome")
  }

  "error page template" in {
    val html = views.html.errorPage("There Is Some Error")
    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("There Is Some Error")
  }

  "forget Password template" in {
    val html = views.html.forgetPassword(new Alert("Success", "Password Sent To Id"))
    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("Success")
  }

  "job detail template" in {
    val job = JobEntity(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date)
    Job.addJob(job)
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    val html = views.html.jobDetail(job, employers.head.id.toString)
    contentType(html) must equalTo("text/html")
  }

  "login template" in {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    val html = views.html.login(new Alert("Success", "Login SuccessFull"), Application.logInForm, employers.head.id.toString, "login")
    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("Success")
  }

  "post a job template" in {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    val html = views.html.postajob(JobController.postAJobForm, employers.head.id.toString)
    contentType(html) must equalTo("text/html")
  }

  "sign up template" in {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    val html = views.html.signup(new Alert("Success", "Login SuccessFull"), Application.signUpForm, employers.head.id.toString, "login")
    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("login")
  }

  override def after {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
  }

}