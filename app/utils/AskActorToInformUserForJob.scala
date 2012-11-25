package utils
import akka.actor.ActorSystem
import akka.actor.Props
import models.Job
import models.EmployerDAO
import models.LogIn
import models.PostAJob

case class SendMailToUserInformingAboutTheJob(emailId: String, jobs: List[Job])

object AskActorToInformUserForJob extends App {

  /**
   * Send Mail To A User If The New Job Contains The Skills That A User Contains As Well Via  Akka Actor
   */
  def sendMailIForJobAlert= {
    println("sendMailIfUserExistWithTheSkillsRequiredForTheJob")
    val system = ActorSystem("jobActors")
    val jobActor= system.actorOf(Props[InformUserAboutJobActor])
    val jobSeekers = LogIn.findJobSeekers
    val JobPostedInLastNHours=PostAJob.findJobsOfLastNHours
    for (jobSeeker <- jobSeekers) {
      val filteredJobList=PostAJob.searchJobs(jobSeeker.skills,JobPostedInLastNHours)
        if (!filteredJobList.isEmpty) {
          jobActor ! SendMailToUserInformingAboutTheJob(jobSeeker.emailId, filteredJobList) //Calling The Actor
        }
    }

  }
  
  sendMailIForJobAlert
 
}