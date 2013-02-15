package utils
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.util.duration._
import models.User
import models.JobEntity
import models.Job
import models.PremiumJob

/**
 * Running A Actor Periodically to send daily Job alert
 */

class JobAlertActor extends Actor {
  def receive: PartialFunction[Any, Unit] = {
    case jobAlertMail: JobAlertMail =>
      sendJobAlert
      context.system.scheduler.scheduleOnce(24 hours, self, JobAlertMail())
  }

  /**
   * Send the job alert to users through mail
   */
  def sendJobAlert: Unit = {
    val jobSeekers = User.findJobSeekers
    val JobPostedInLastNHours = PremiumJob.findPremiumJobs ++ Job.findJobsOfLastNHours
    if (!JobPostedInLastNHours.isEmpty) {
      for (jobSeeker <- jobSeekers) {
        val filteredJobList = Job.searchJobs(jobSeeker.skills, JobPostedInLastNHours)
        if (!filteredJobList.isEmpty) {
          if (Job.isJobAlertMailSent(jobSeeker.id) == false)
            MailUtility.sendDailyJobAlertMail(jobSeeker, filteredJobList)
        }
      }
    }
  }
}

/**
 * Initiate Job Alert Actor after 5 minutes of Application Started
 */

class JobAlertActorInitiator extends Actor {
  def receive: PartialFunction[Any, Unit] = {
    case initiateJobAlertMail: InitiateJobAlertMail =>
      val jobActor = context.actorOf(Props[JobAlertActor])
      context.system.scheduler.scheduleOnce(5 minutes, jobActor, JobAlertMail())
  }

}
