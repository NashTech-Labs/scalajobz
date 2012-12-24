package utils
import akka.actor.ActorSystem
import akka.actor.Props

case class JobAlertMail()
case class InitiateJobAlertMail()

object DailyJobAlert {
  val system = ActorSystem("jobInitiateActors")

  /**
   * Send Mail To Job Seekers on the basis of their search criteria Via  Akka Actor
   */
  def sendMailIForJobAlert: Unit = {
    val jobActor = system.actorOf(Props[JobAlertActorInitiator])
    jobActor ! InitiateJobAlertMail()
  }
}