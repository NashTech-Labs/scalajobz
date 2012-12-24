package utils

import com.restfb.DefaultFacebookClient
import com.restfb.FacebookClient
import com.restfb.types.FacebookType
import play.api.Play
import scala.reflect.Class
import play.mvc.With
import models.JobEntity
import com.restfb.Parameter

object FacebookFeed {

  val access_key = Play.current.configuration.getString("facebook_access_token").get
  val facebookClient: FacebookClient = new DefaultFacebookClient(access_key)

  def publishMessage(job: JobEntity): String = {

    val facebookMsg = "New Job posted by @" + job.company + " " +
      job.location + " for " + job.position

    val publishMessageResponse: FacebookType = facebookClient.publish("me/feed", classOf[FacebookType],
      Parameter.`with`("message", facebookMsg))
    publishMessageResponse.getId()
  }

}