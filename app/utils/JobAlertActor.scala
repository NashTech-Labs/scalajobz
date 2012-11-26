package utils
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.util.duration._


class JobAlertActor extends Actor {
  def receive = {
    case msg: String => println("Hello akka")
    case jobAlertMail: JobAlertMail =>
      SendEmail.sendEmail(jobAlertMail.emailId, jobAlertMail.jobs)
      context.system.scheduler.scheduleOnce(24 hours, self, JobAlertMail(jobAlertMail.emailId,jobAlertMail.jobs))
  }
}

