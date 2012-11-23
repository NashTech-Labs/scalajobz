package controllers

import play.api.mvc.Action
import play.api.mvc.Controller
import models.PostAJob
import net.liftweb.json.{ parse, DefaultFormats }
import net.liftweb.json.Serialization.{ read, write }
import models.Alert

object RESTServiceController extends Controller {

  implicit val formats = DefaultFormats

  def processGetAllJobsList = Action {
    val results = PostAJob.findAllJobs
    if (results.isEmpty)
      Ok(write(new Alert("No Result Found", "No Job Exist"))).as("application/json")
    else
      Ok(write(results)).as("application/json")
  }

  def processGetJobListForSingleRequest(code: String) = Action {
    val results = PostAJob.searchTheJob(code)
    if (results.isEmpty)
      Ok(write(new Alert("No Result Found", "Your search did not match with any exist job."))).as("application/json")
    else
      Ok(write(results)).as("application/json")
  }
}