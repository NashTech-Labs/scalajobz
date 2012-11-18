package models;

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfter
import org.bson.types.ObjectId

@RunWith(classOf[JUnitRunner])
class SignUpTest extends FunSuite with BeforeAndAfter {
  
 
  test("create a user & find a user"){
    val employer = Employer(new ObjectId, "neelkanth@knoldus.com", "ABCD",List(),false)
    val employerId = SignUp.createUser(employer)
    val employers=SignUp.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.size===1)
  }
}
