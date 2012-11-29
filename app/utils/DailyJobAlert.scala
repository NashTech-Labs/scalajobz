package utils
import akka.actor.ActorSystem
import akka.actor.Props

case class JobAlertMail()

object DailyJobAlert {

  /**
   * Send Mail To Job Seekers on the basis of their search criteria Via  Akka Actor
   */
  def sendMailIForJobAlert: Unit = {
    val system = ActorSystem("jobActors")
    val jobActor = system.actorOf(Props[JobAlertActor])
    jobActor ! JobAlertMail()
  }

}


