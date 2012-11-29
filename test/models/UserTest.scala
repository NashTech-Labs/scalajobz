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

  before {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
  }

  test("create a user & find a user (Job seeker/employer)") {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    val employerId = User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.size === 1)
  }

  test("Update Employer") {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.head.jobSeeker === false)
    val updatedEmployer = User.updateUser(employer, "ABCDEF")
    assert(User.findUserById(employer.id.toString).get.password === "ABCDEF")
  }

  test("Find User(Employer) Via Email Id & Password") {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.head.password === "ABCD")
    assert(employers.head.emailId === "neelkanth@knoldus.com")
  }

  test("Find Job Seeker") {
    val jobSeeker = UserEntity(new ObjectId, "neelkanth@knoldus.com", "", List("Scala"), true)
    User.createUser(jobSeeker)
    val jobseekers = User.findUserById(jobSeeker.id.toString)
    assert(jobseekers.head.jobSeeker === true)
  }

  after {
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
  }

}