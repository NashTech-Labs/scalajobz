package router

import org.specs2.mutable.Specification
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.bson.types.ObjectId
import models.JobEntity
import java.util.Date
import models.Job
import org.specs2.mutable.BeforeAfter
import models.JobDAO
import models.UserDAO
import com.mongodb.casbah.commons.MongoDBObject
import models.JobBy

@RunWith(classOf[JUnitRunner])
class RouterTest extends Specification with BeforeAfter {

  override def before {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
  }

  "respond to the index Action" in {
    val Some(result) = routeAndCall(FakeRequest(GET, "/"))
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
  }

  "redirect to sign Up Page Action" in {
    val Some(result) = routeAndCall(FakeRequest(GET, "/signup/login"))
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
    contentAsString(result) must contain("login")
  }

  "add a new user Action" in {
    val Some(result) = routeAndCall(FakeRequest(POST, "/signup/login"))
    status(result) must equalTo(400)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
    contentAsString(result) must contain("login")
  }

  "redirect to login Page Action" in {
    val Some(result) = routeAndCall(FakeRequest(GET, "/login"))
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
  }

  "successfully Login Action" in {
    val Some(result) = routeAndCall(FakeRequest(POST, "/login/login"))
    status(result) must equalTo(400)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
    contentAsString(result) must contain("login")
  }

  "redirect to post A job Action" in {
    val Some(result) = routeAndCall(FakeRequest(GET, "/postAJob"))
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
  }

  "post a job  Action" in {
    val Some(result) = routeAndCall(FakeRequest(POST, "/postAJob"))
    status(result) must equalTo(400)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
  }

  "find all job  Action" in {
    val Some(result) = routeAndCall(FakeRequest(GET, "/findAllJobs"))
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
  }

  "logout Action" in {
    val Some(result) = routeAndCall(FakeRequest(GET, "/logout"))
    status(result) must equalTo(303)
  }

  "find a job with search token Action" in {
    val Some(result) = routeAndCall(FakeRequest(POST, "/findAJob/scala/false"))
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
  }

  "find a job via jobid Action" in {
    val job = JobEntity(new ObjectId, Option(new ObjectId), "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"),Option("email"))
    Job.addJob(job)
    val Some(result) = routeAndCall(FakeRequest(GET, "/jobDetail/" + job.id))
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
  }

  override def after {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
  }

}
