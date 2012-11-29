package utils
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.util.duration._
import models.User
import models.JobEntity
import models.Job

/**
 * Running A Actor Periodically to send daily Job alert
 */

class JobAlertActor extends Actor {
  def receive : PartialFunction[Any,Unit] = {
    case jobAlertMail: JobAlertMail =>
      Thread.sleep(10000)
      sendJobAlert
      context.system.scheduler.scheduleOnce(30 seconds, self, JobAlertMail())
  }

  def sendJobAlert: Unit = {
    val jobSeekers = User.findJobSeekers
    val JobPostedInLastNHours = Job.findJobsOfLastNHours
    if (!JobPostedInLastNHours.isEmpty) {
      for (jobSeeker <- jobSeekers) {
        val filteredJobList = Job.searchJobs(jobSeeker.skills, JobPostedInLastNHours)
        if (!filteredJobList.isEmpty) { MailUtility.sendEmail(jobSeeker, filteredJobList) }
      }
    }
  }
}