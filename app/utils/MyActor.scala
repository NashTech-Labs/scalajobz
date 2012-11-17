package utils
import akka.actor.Actor
import akka.event.Logging
import akka.actor.ActorSystem
import akka.actor.Props

class MyActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case "test" ⇒ log.info("received test");println("Hi Neelkanth Sachdeva")
     case "wow" ⇒ log.info("received test");println("Hi Mr. Neelkanth Sachdeva")
    case _      ⇒ log.info("received unknown message")
    
    def sayHelloToNeel={
       println("Hello Neel , How Are You ? ")
     }
  }

}

object Main extends App {
  val system = ActorSystem("MySystem")
  val myActor = system.actorOf(Props[MyActor], name = "myactor")
  myActor ! "test"
  myActor ! "wow"
  myActor ! "trashcase"

  
}