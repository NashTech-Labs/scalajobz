package controllers

import models.Job
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class JobControllerTest extends Specification {
  "postAJob" in {
    val result = controllers.JobController.postAJob(FakeRequest())
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
  }

}