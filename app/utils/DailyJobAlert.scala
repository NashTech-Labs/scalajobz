package utils
import akka.actor.ActorSystem
import akka.actor.Props
import models.JobEntity
import models.EmployerDAO
import models.User
import models.Job

case class JobAlertMail(emailId: String,
  jobs: List[JobEntity])

object DailyJobAlert extends App {

  /**
   * Send Mail To A User If The New Job Contains The Skills That A User Contains As Well Via  Akka Actor
   */
  def sendMailIForJobAlert = {
    val system = ActorSystem("jobActors")
    val jobActor = system.actorOf(Props[JobAlertActor])
    val jobSeekers = User.findJobSeekers
    val JobPostedInLastNHours = Job.findJobsOfLastNHours
    if (!JobPostedInLastNHours.isEmpty) {
      for (jobSeeker <- jobSeekers) {
        val filteredJobList = Job.searchJobs(jobSeeker.skills, JobPostedInLastNHours)
        if (!filteredJobList.isEmpty) {
          jobActor ! JobAlertMail(jobSeeker.emailId, filteredJobList) //Calling The Actor
        }
      }
    }

  }

  sendMailIForJobAlert

}