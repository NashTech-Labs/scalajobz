package utils
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class InformUserAboutJobActor extends Actor {
  def receive = {
    case "neel" => println("Hello Neel")
    case message:NeelMessage => println("Sum is : " + ( message.firstNum+message.secondNum)) 
  }
}

