package controllers

import models.Job
import org.specs2.mutable.Specification
import play.api.test
import play.api.test.Helpers._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.test.FakeRequest

@RunWith(classOf[JUnitRunner])
class ApplicationTest extends Specification {
  "index" in {
    val result = controllers.Application.index(FakeRequest())
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
  }

  "signUpOnScalaJobz" in {
    val result = controllers.Application.signUpOnScalaJobz("login")(FakeRequest())
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    contentAsString(result) must contain("login")
  }

  "newUser" in {
    val result = controllers.Application.newUser("login")(FakeRequest())
    contentType(result) must beSome("text/html")
    contentAsString(result) must contain("login")
    status(result) must equalTo(400)
  }

  "logIn" in {
    val result = controllers.Application.logIn("login")(FakeRequest())
    contentType(result) must beSome("text/html")
    contentAsString(result) must contain("login")
    status(result) must equalTo(400)
  }

  "loginOnScalaJobz" in {
    val result = controllers.Application.loginOnScalaJobz(FakeRequest())
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
  }

  "logOutFromScalaJobz" in {
    val result = controllers.Application.logOutFromScalaJobz(FakeRequest())
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
  }
}