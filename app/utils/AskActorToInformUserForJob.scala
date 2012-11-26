package utils
import akka.actor.ActorSystem
import akka.actor.Props
import models.JobEntity
import models.EmployerDAO
import models.LogIn
import models.Job

case class SendMailToUserInformingAboutTheJob(emailId: String,
  jobs: List[JobEntity])

object AskActorToInformUserForJob extends App {

  /**
   * Send Mail To A User If The New Job Contains The Skills That A User Contains As Well Via  Akka Actor
   */
  def sendMailIForJobAlert = {
    val system = ActorSystem("jobActors")
    val jobActor = system.actorOf(Props[InformUserAboutJobActor])
    val jobSeekers = LogIn.findJobSeekers
    val JobPostedInLastNHours = Job.findJobsOfLastNHours
    for (jobSeeker <- jobSeekers) {
      val filteredJobList = Job.searchJobs(jobSeeker.skills, JobPostedInLastNHours)
      if (!filteredJobList.isEmpty) {
        jobActor ! SendMailToUserInformingAboutTheJob(jobSeeker.emailId, filteredJobList) //Calling The Actor
      }
    }

  }

  sendMailIForJobAlert

}