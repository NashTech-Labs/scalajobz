package utils
import akka.actor.Actor
import akka.util.duration._
import akka.actor.ActorSystem
import akka.actor.Props
import models.UserEntity
import akka.actor.PoisonPill

case class VerifyJobSeekerRequest(jobSeeker: UserEntity)

/**
 * Call a Actor To Send Verification Mail To Job Seeker For Confirm Valid Email Id
 */

object JobSeekerVerification {

  def sendVerifyMailForJobSeeker(jobSeeker: UserEntity) = {
    val system = ActorSystem("verifyJobSeekerActor")
    val verifyJobSeekerActor = system.actorOf(Props[JobSeekerVerifyActor])
    verifyJobSeekerActor ! VerifyJobSeekerRequest(jobSeeker)
  }

}

/**
 * Send Verify Mail To Job Seeker
 */

class JobSeekerVerifyActor extends Actor {

  def receive: PartialFunction[Any, Unit] = {
    case VerifyJobSeekerRequest(jobSeeker) =>
      MailUtility.verificationMailToJobSeeker(jobSeeker)
      self ! PoisonPill
  }

}