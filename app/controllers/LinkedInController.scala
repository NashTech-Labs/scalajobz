package controllers
import play.api.mvc.Controller
import org.scribe.builder.ServiceBuilder
import org.scribe.oauth.OAuthService
import org.scribe.builder.api.LinkedInApi
import org.scribe.model.Token
import play.api.mvc.Action
import org.scribe.model.Verifier
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import org.scribe.model.Response
import scala.xml.XML
import play.api.Logger
import play.api.Play
import models.User
import models.UserEntity
import org.bson.types.ObjectId
import models.Common

object LinkedInController extends Controller {
  val apiKey: String = Play.current.configuration.getString("linkedin_api_key").get
  val apiSecret: String = Play.current.configuration.getString("linkedin_secret_key").get
  var requestToken: Token = null
  val currentUserId = "userId"
  val protectedResourceUrl: String = "http://api.linkedin.com/v1/people/~:(id,first-name,last-name,email-address)";

  /**
   * Get OAuthService Request
   */

  def getOAuthService: OAuthService = {
    val service: OAuthService = new ServiceBuilder()
      .provider(classOf[LinkedInApi])
      .apiKey(apiKey)
      .apiSecret(apiSecret)
      .scope("r_fullprofile")
      .scope("r_emailaddress")
      .callback("http://" + Common.getContextUrl + "/linkedin/callback")
      .build();
    service
  }

  def linkedinLogin: Action[play.api.mvc.AnyContent] = Action {
    try {
      requestToken = getOAuthService.getRequestToken
      val authUrl: String = getOAuthService.getAuthorizationUrl(requestToken)
      Redirect(authUrl)
    } catch {
      case ex => {
        Logger.error("Error During Login Through LinkedIn - " + ex)
        Ok(views.html.redirectmain("", "failure"))
      }
    }
  }

  def linkedinCallback: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    try {
      getVerifier(request.queryString) match {
        case None => Ok(views.html.redirectmain("", "failure"))
        case Some(oauth_verifier) =>
          val verifier: Verifier = new Verifier(oauth_verifier)
          val accessToken: Token = getOAuthService.getAccessToken(requestToken, verifier);
          val oAuthRequest: OAuthRequest = new OAuthRequest(Verb.GET, protectedResourceUrl)
          getOAuthService.signRequest(accessToken, oAuthRequest)
          val response: Response = oAuthRequest.send
          response.getCode match {
            case 200 =>
              val linkedinXML = scala.xml.XML.loadString(response.getBody)
              val userEmailId = (linkedinXML \\ "email-address").text.trim
              val userNetwokId = (linkedinXML \\ "id").text.trim
              val userId = User.getOrCreateUserBySocialNetwork(userEmailId, "linkedIn", userNetwokId)
              val userSession = request.session + (currentUserId -> userId)
              Ok(views.html.redirectmain(userId, "success")).withSession(userSession)
            case 400 =>
              Logger.error("Error 400-  During Login Through LinkedIn- " + response.getBody)
              Ok(views.html.redirectmain("", "failure"))
            case _ =>
              Logger.error("Error " + response.getCode + " : During Login Through LinkedIn - " + response.getBody)
              Ok(views.html.redirectmain("", "failure"))
          }
      }

    } catch {
      case ex => {
        Logger.error("Error During Login Through LinkedIn - " + ex)
        Ok(views.html.redirectmain("", "failure"))
      }
    }
  }

  def getVerifier(queryString: Map[String, Seq[String]]): Option[String] = {
    val seq = queryString.get("oauth_verifier").getOrElse(Seq())
    seq.isEmpty match {
      case true => None
      case false => seq.headOption
    }
  }

}