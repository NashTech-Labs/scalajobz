package utils
import akka.actor.ActorSystem
import akka.actor.Props
import models.JobEntity
import models.User
import models.Job
import models.UserEntity

case class JobAlertMail(jobSeeker: UserEntity,
  jobs: List[JobEntity])

object DailyJobAlert  {

  /**
   * Send Mail To Job Seekers on the basis of their search criteria Via  Akka Actor
   */
  def sendMailIForJobAlert:Unit = {
    val system = ActorSystem("jobActors")
    val jobActor = system.actorOf(Props[JobAlertActor])
    val jobSeekers = User.findJobSeekers
    val JobPostedInLastNHours = Job.findJobsOfLastNHours
    if (!JobPostedInLastNHours.isEmpty) {
      for (jobSeeker <- jobSeekers) {
        val filteredJobList = Job.searchJobs(jobSeeker.skills, JobPostedInLastNHours)
        if (!filteredJobList.isEmpty) {
          jobActor ! JobAlertMail(jobSeeker, filteredJobList) //Calling The Actor
        }
      }
    }

  }

  sendMailIForJobAlert

}