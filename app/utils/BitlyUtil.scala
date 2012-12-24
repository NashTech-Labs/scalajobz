package utils

import play.libs.WS
import play.api.Play
import scala.util.parsing.json.JSONObject

object BitlyUtil {

  def generateTinyUrl(longUrl: String): String = {
    val apiKey = Play.current.configuration.getString("bitly_access_key").get
    val URL = "https://api-ssl.bitly.com/v3/shorten"
    val promise = WS.url(URL).setQueryParameter("apiKey", apiKey).setQueryParameter("login", "scalajobz").setQueryParameter("longUrl", longUrl).get
    val shortUrlJson = promise.get.asJson
    shortUrlJson.get("data").get("url")toString
  }
}