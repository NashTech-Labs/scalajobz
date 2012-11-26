//package models;
//
//import org.junit.runner.RunWith
//import org.scalatest.FunSuite
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
//import org.scalatest.BeforeAndAfter
//import org.bson.types.ObjectId
//
//@RunWith(classOf[JUnitRunner])
//class SignUpTest extends FunSuite with BeforeAndAfter {
//  
// 
//  test("create a user & find a user"){
//    val employer = Employer(new ObjectId, "neelkanth@knoldus.com", "ABCD",List(),false)
//    val employerId = User.createUser(employer)
//    val employers=User.findUserByEmail("neelkanth@knoldus.com")
//    assert(employers.size===1)
//  }
//}
