package utils
import akka.actor.ActorSystem
import akka.actor.Props
import models.Job

case class NeelMessage(firstNum: Int , secondNum:Int)
case class SendMailToUserInformingAboutTheJob(emailId: String , job:Job)

object AskActorToInformUserForJob extends App{
  val system = ActorSystem("neelActors")
  val neelActorRouter = system.actorOf(Props[InformUserAboutJobActor])
  neelActorRouter ! "neel"
  neelActorRouter ! NeelMessage(10,20)
  
  def sendMailIfUserExistWithTheSkillsRequiredForTheJob(skills: List[String])={
    
  }
}