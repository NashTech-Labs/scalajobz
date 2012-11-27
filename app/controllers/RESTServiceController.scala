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

  /**
   * Redirect To Rest Api UI
   */
  def restApi: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    Ok(views.html.restapi(request.session.get("userId").getOrElse(null)))
  }

  /**
   * REST Api To get all Jobs
   */
  def processGetAllJobsList: Action[play.api.mvc.AnyContent] = Action {
    val results = Job.findAllJobs
    if (results.isEmpty)
      Ok(write(new Alert("No Result Found", "No Job Exist"))).as("application/json")
    else
      Ok(write(results)).as("application/json")
  }

  /**
   * REST Api To get Jobs for a Token String
   */

  def processGetJobListForSingleRequest(code: String): Action[play.api.mvc.AnyContent] = Action {
    val results = Job.searchTheJob(code)
    if (results.isEmpty)
      Ok(write(new Alert("No Result Found", "Your search did not match with any exist job."))).as("application/json")
    else
      Ok(write(results)).as("application/json")
  }
}