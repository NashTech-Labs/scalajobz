import play.api._
import play.api.mvc.Results.InternalServerError
import utils.DailyJobAlert
import play.api.Logger
import play.api.mvc.RequestHeader
import play.api.mvc.SimpleResult
import play.api.mvc.Result
import utils.WSUtil
import utils.JobPortalReader
import org.omg.CosNaming.NamingContextPackage.NotFound

object Global extends GlobalSettings {

  override def onStart(app: Application): Unit = {
    Logger.info("Application has started")
    DailyJobAlert.sendMailIForJobAlert
    JobPortalReader.readJobFromJobPortals
  }

  override def onStop(app: Application): Unit = {
    Logger.info("Application shutdown...")
  }

  override def onError(request: RequestHeader, ex: Throwable): SimpleResult[play.api.templates.Html] = {
    Logger.error("Error occurred", ex)
    InternalServerError(
      views.html.errorPage("oops ! Some Error Occured"))
  }

  override def onHandlerNotFound(request: RequestHeader): Result = {
    Logger.error("Page Not Found - " + request.path)
    InternalServerError(
      views.html.errorPage("Page Not Found - " + request.path))
  }

  override def onBadRequest(request: RequestHeader, error: String) = {
    Logger.error("Page Not Found - " + request.path)
    InternalServerError(
      views.html.errorPage("Page Not Found - " + request.path + " Error " + error))
  }
  
}
