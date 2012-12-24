package controllers
import play.api.mvc.Controller
import org.scribe.oauth.OAuthService
import play.api.Play
import play.api.mvc.Action
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.GoogleApi
import models.Common
import org.scribe.model.Token
import play.api.Logger
import org.scribe.model.Verifier
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import org.scribe.model.Response
import models.User
import play.api.libs.json.Json

object GoogleController extends Controller {

  val apiKey: String = Play.current.configuration.getString("google_api_key").get
  val apiSecret: String = Play.current.configuration.getString("google_api_secret").get
  var requestToken: Token = null
  val authorizationUrlGoogle: String = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token="
  val protectedResourceUrl: String = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json"
  val scope: String = "https://www.googleapis.com/auth/userinfo.profile"
  val currentUserId = "userId"

  /**
   * Get OAuthService Request
   */

  def getOAuthService: OAuthService = {
    var service: OAuthService = new ServiceBuilder()
      .provider(classOf[GoogleApi])
      .apiKey(apiKey)
      .apiSecret(apiSecret)
      .scope(scope)
      .callback("http://" + Common.getContextUrl + "/google/callback")
      .build();
    service
  }

  def googleLogin: Action[play.api.mvc.AnyContent] = Action {
    try {

      requestToken = getOAuthService.getRequestToken();
      val authorizationUrl: String = authorizationUrlGoogle + requestToken.getToken()
      Redirect(authorizationUrl)
    } catch {
      case ex => {
        Logger.error("Error During Login Through Google - " + ex)
        Ok(views.html.redirectmain("", "failure"))
      }
    }
  }

  def googleCallback: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    try {
      getVerifier(request.queryString) match {
        case None => Ok(views.html.redirectmain("", "failure"))
        case Some(oauth_verifier) =>
          val verifier: Verifier = new Verifier(oauth_verifier)
          val accessToken: Token = getOAuthService.getAccessToken(requestToken, verifier)
          val oAuthRequest: OAuthRequest = new OAuthRequest(Verb.GET, protectedResourceUrl)
          getOAuthService.signRequest(accessToken, oAuthRequest)
          oAuthRequest.addHeader("GData-Version", "3.0")
          val response: Response = oAuthRequest.send
          response.getCode match {
            case 200 =>
              val json = Json.parse(response.getBody)
              val userEmailId = (json \ "name").asOpt[String]
              val userNetworkId = (json \ "id").asOpt[String]
              val userId = User.getOrCreateUserBySocialNetwork(userEmailId.get, "google", userNetworkId.get)
              val userSession = request.session + (currentUserId -> userId)
              Ok(views.html.redirectmain(userId, "success")).withSession(userSession)
            case 400 =>
              Logger.error("Error 400 :  During Login Through Google- " + response.getBody)
              Ok(views.html.redirectmain("", "failure"))
            case _ =>
              Logger.error("Error " + response.getCode + " : During Login Through Google - " + response.getBody)
              Ok(views.html.redirectmain("", "failure"))
          }
      }

    } catch {
      case ex => {
        Logger.error("Error During Login Through Google - " + ex)
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