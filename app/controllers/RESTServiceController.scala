package controllers

import play.api.mvc.Action
import play.api.mvc.Controller
import models.Job
import net.liftweb.json.{ parse, DefaultFormats }
import net.liftweb.json.Serialization.{ read, write }
import models.Alert
import models.ObjectIdSerializer

object RESTServiceController extends Controller {

  implicit val formats = new net.liftweb.json.DefaultFormats {
  } + new ObjectIdSerializer

  def restApi = Action { implicit request =>
    Ok(views.html.restapi(request.session.get("userId").getOrElse(null)))
  }

  def processGetAllJobsList = Action {
    val results = Job.findAllJobs
    if (results.isEmpty)
      Ok(write(new Alert("No Result Found", "No Job Exist"))).as("application/json")
    else
      Ok(write(results)).as("application/json")
  }

  def processGetJobListForSingleRequest(code: String) = Action {
    val results = Job.searchTheJob(code)
    if (results.isEmpty)
      Ok(write(new Alert("No Result Found", "Your search did not match with any exist job."))).as("application/json")
    else
      Ok(write(results)).as("application/json")
  }
}