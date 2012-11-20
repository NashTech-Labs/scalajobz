package utils
import akka.actor.ActorSystem
import akka.actor.Props

case class NeelMessage(firstNum: Int , secondNum:Int)

object AskActorToInformUserForJob extends App{
  val system = ActorSystem("neelActors")
  val neelActorRouter = system.actorOf(Props[InformUserAboutJobActor])
  neelActorRouter ! "neel"
  neelActorRouter ! NeelMessage(10,20)
}