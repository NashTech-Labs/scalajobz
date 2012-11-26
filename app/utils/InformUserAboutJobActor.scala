package utils
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.util.duration._


class InformUserAboutJobActor extends Actor {
  def receive = {
    case msg: String => println("Hello akka")
    case sendEmailToUser: SendMailToUserInformingAboutTheJob =>
      SendEmail.sendEmail(sendEmailToUser.emailId, sendEmailToUser.jobs)
      context.system.scheduler.scheduleOnce(24 hours, self, SendMailToUserInformingAboutTheJob(sendEmailToUser.emailId,sendEmailToUser.jobs))
  }
}

