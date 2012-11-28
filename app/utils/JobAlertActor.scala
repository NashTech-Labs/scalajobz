package utils
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.util.duration._

/**
 * Running A Actor Periodically to send daily Job alert
 */

class JobAlertActor extends Actor {
  def receive : PartialFunction[Any,Unit] = {
    case jobAlertMail: JobAlertMail =>
      SendEmail.sendEmail(jobAlertMail.jobSeeker, jobAlertMail.jobs)
      context.system.scheduler.scheduleOnce(1 hours, self, JobAlertMail(jobAlertMail.jobSeeker, jobAlertMail.jobs))
  }
}

