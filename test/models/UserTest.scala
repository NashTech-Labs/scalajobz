package models

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfter
import org.bson.types.ObjectId
import com.mongodb.casbah.commons.MongoDBObject

@RunWith(classOf[JUnitRunner])
class UserTest extends FunSuite with BeforeAndAfter {
  test("create a user & find a user") {
    val employer = Employer(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    val employerId = User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.size === 1)
  }

  test("Update Employer") {
    val employer = Employer(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.head.jobSeeker === false)
    val updatedEmployer = User.updateUser(employer, "ABCDEF")
    assert(User.findUserById(employer.id.toString).get.password === "ABCDEF")
  }
  after {
    EmployerDAO.remove(MongoDBObject("emailId" -> ".*".r))
  }

}
