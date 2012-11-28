//package server
//
//import org.specs2.mutable.Specification
//import play.api.test.FakeRequest
//import play.api.test.Helpers._
//import org.junit.runner.RunWith
//import org.specs2.runner.JUnitRunner
//import org.bson.types.ObjectId
//import play.api.test.TestServer
//import play.api.libs.ws.WS
//
//@RunWith(classOf[JUnitRunner])
//class ServerStartTest extends Specification {
//
//  "run in a server" in {
//    running(TestServer(9000)) {
//      await(WS.url("http://localhost:9000").get).status must equalTo(OK)
//
//    }
//  }
//
//}