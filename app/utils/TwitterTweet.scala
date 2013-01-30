package utils

import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import twitter4j.ResponseList
import twitter4j.QueryResult
import twitter4j.Query
import twitter4j.Paging
import twitter4j.auth.RequestToken
import play.api.Play
import models.JobEntity
import twitter4j.Status

object TwitterTweet {

  /**
   * Tweet for a new job
   * @param tweetmsg is tweet for a new job on twitter
   */

  def tweetANewJobPost(job: JobEntity): Status = {
    val jobDetailUrl = "http://www.scalajobz.com/jobDetail/" + job.id
    val tinyUrlForJobDetail = GoogleApisUtil.generateTinyUrl(jobDetailUrl)
    val twitter = new TwitterFactory().getInstance()

    val consumer_key = Play.current.configuration.getString("consumer_key").get
    val consumer_secret = Play.current.configuration.getString("consumer_secret").get
    val access_key = Play.current.configuration.getString("access_token").get
    val access_token = Play.current.configuration.getString("access_token_secret").get

    twitter.setOAuthConsumer(consumer_key, consumer_secret)
    twitter.setOAuthAccessToken(new AccessToken(access_key, access_token))

    val tweetMessage = "New Job posted by " + job.company + " " +
      job.location + " for " + job.position + " at " + tinyUrlForJobDetail

    if (tweetMessage.length > 140) {
      twitter.updateStatus(tweetMessage.substring(0, 80) + " ..." + " at " + tinyUrlForJobDetail)
    } else {
      twitter.updateStatus(tweetMessage)
    }
  }

}