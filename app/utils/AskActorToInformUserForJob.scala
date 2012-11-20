package utils
import akka.actor.ActorSystem
import akka.actor.Props
import models.Job
import models.EmployerDAO
import models.LogIn

case class NeelMessage(firstNum: Int, secondNum: Int)
case class SendMailToUserInformingAboutTheJob(emailId: String, job: Job)

object AskActorToInformUserForJob extends App {
  
//  val system = ActorSystem("neelActors")
//  val neelActorRouter = system.actorOf(Props[InformUserAboutJobActor])
//  neelActorRouter ! "neel"
//  neelActorRouter ! NeelMessage(10, 20)

  /**
   * Send Mail To A User If The New Job Contains The Skills That A User Contains As Well Via  Akka Actor
   */
  def sendMailIfUserExistWithTheSkillsRequiredForTheJob(job: Job) = {
    val system = ActorSystem("jobActors")
     val jobActor= system.actorOf(Props[InformUserAboutJobActor])
    val jobSeekers = LogIn.findJobSeekers
    for (jobSeeker <- jobSeekers) {
      for (eachSkill <- jobSeeker.skills) {
        if (job.skillsRequired.contains(eachSkill)) {
          jobActor ! SendMailToUserInformingAboutTheJob(jobSeeker.emailId, job) //Calling The Actor
        }
      }
    }

  }
}