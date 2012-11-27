package template

import models.Job
import org.specs2.mutable.Specification
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import models.Alert

@RunWith(classOf[JUnitRunner])
class TemplateTest extends Specification {

  "index template" in {
    val html = views.html.index(new Alert("Welcome", "Welcome in scalajobz"), null, List(), false)
    println("html")
    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("Welcome")
  }
  
  "ajax result template" in {
    val html = views.html.ajax_result(List(), false)
    println("html")
    contentType(html) must equalTo("text/html")
  }

}