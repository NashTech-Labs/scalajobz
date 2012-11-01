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
    val user = User(new ObjectId, "neelkanth@knoldus.com", "ABCD")
    val userId = SignUp.createUser(user)
    val users=SignUp.findUserByEmail("neelkanth@knoldus.com")
    assert(users.size===1)
  }

}
