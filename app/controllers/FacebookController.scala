package controllers
import play.api.mvc.Controller
import play.api.Play
import org.scribe.oauth.OAuthService
import org.scribe.model.Token
import play.api.mvc.Action
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.FacebookApi
import models.Common
import play.api.Logger
import org.scribe.model.Verifier
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import org.scribe.model.Response
import play.api.libs.json.Json
import models.User

object FacebookController extends Controller {

  val apiKey: String = Play.current.configuration.getString("facebook_api_id").get
  val apiSecret: String = Play.current.configuration.getString("facebook_api_secret").get
  val currentUserId = "userId"
  val protectedResourceUrl: String = "https://graph.facebook.com/me";
  val emptyToken: Token = null;

  /**
   * Get OAuthService Request
   */

  def getOAuthService: OAuthService = {
    val service: OAuthService = new ServiceBuilder()
      .provider(classOf[FacebookApi])
      .apiKey(apiKey)
      .apiSecret(apiSecret)
      .scope("email")
      .callback("http://" + Common.getContextUrl + "/facebook/callback")
      .build()
    service
  }

  def facebookLogin: Action[play.api.mvc.AnyContent] = Action {
    try {
      val authorizationUrl: String = getOAuthService.getAuthorizationUrl(emptyToken);
      Redirect(authorizationUrl)
    } catch {
      case ex => {
        Logger.error("Error During Login Through Facebook - " + ex)
        Ok(views.html.redirectmain("", "failure"))
      }
    }
  }

  def facebookCallback: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    try {
      getVerifier(request.queryString) match {
        case None => Ok(views.html.redirectmain("", "failure"))
        case Some(code) =>
          val verifier: Verifier = new Verifier(code)
          val accessToken: Token = getOAuthService.getAccessToken(emptyToken, verifier)
          val oAuthRequest: OAuthRequest = new OAuthRequest(Verb.GET, protectedResourceUrl)
          getOAuthService.signRequest(accessToken, oAuthRequest)
          val response: Response = oAuthRequest.send
          response.getCode match {
            case 200 =>
              val responseBody = response.getBody
              val json = Json.parse(responseBody)
              val userEmailId = (json \ "email").asOpt[String]
              val userNetworkId = (json \ "id").asOpt[String]
              val userId = User.getOrCreateUserBySocialNetwork(userEmailId.get, "facebook", userNetworkId.get)
              val userSession = request.session + (currentUserId -> userId)
              Ok(views.html.redirectmain(userId, "success")).withSession(userSession)
            case 400 =>
              Logger.error("Error 400-  During Login Through Facebook " + response.getBody)
              Ok(views.html.redirectmain("", "failure"))
            case _ =>
              Logger.error("Error " + response.getCode + " : During Login Through Facebook - " + response.getBody)
              Ok(views.html.redirectmain("", "failure"))
          }
      }

    } catch {
      case ex => {
        Logger.error("Error During Login Through Facebook - " + ex)
        Ok(views.html.redirectmain("", "failure"))
      }
    }
  }

  def getVerifier(queryString: Map[String, Seq[String]]): Option[String] = {
    val seq = queryString.get("code").getOrElse(Seq())
    seq.isEmpty match {
      case true => None
      case false => seq.headOption
    }
  }

}