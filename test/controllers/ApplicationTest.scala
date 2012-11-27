package controllers

import models.Job
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ApplicationTest extends Specification {
  "Hello" in {
    val result = controllers.Application.index(FakeRequest())
    status(result) must equalTo(OK)
     contentType(result) must beSome("text/html")
  }
}