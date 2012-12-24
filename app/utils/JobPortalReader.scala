package utils
import akka.actor.Actor
import akka.util.duration._
import akka.actor.ActorSystem
import akka.actor.Props

case class JobPortalReaderRequest()

/**
 * Call a Actor To Read Jobs From Different Job Portals
 */

object JobPortalReader {

  def readJobFromJobPortals = {
    val system = ActorSystem("jobPortalReaderActor")
    val jobReaderActor = system.actorOf(Props[JobPortalReaderActor])
    jobReaderActor ! JobPortalReaderRequest()
  }

}

/**
 * To Read Jobs From Different Job Portals
 */

class JobPortalReaderActor extends Actor {

  def receive: PartialFunction[Any, Unit] = {
    case jobPortalReaderRequest: JobPortalReaderRequest =>
      WSUtil.indeedJobsReader
      WSUtil.simplyHiredJobsReader
      WSUtil.careerbuilderJobsReader
      context.system.scheduler.scheduleOnce(24 hours, self, JobPortalReaderRequest())
  }

}