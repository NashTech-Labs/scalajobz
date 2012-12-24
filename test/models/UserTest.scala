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
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false, None, None)
    val employerId = User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.size === 1)
  }

  test("Update Employer") {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false, None, None)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.head.jobSeeker === false)
    val updatedEmployer = User.updateUser(employer, "ABCDEF")
    assert(User.findUserById(employer.id.toString).get.password === "ABCDEF")
  }

  test("Find User(Employer) Via Email Id & Password") {
    val employer = UserEntity(new ObjectId, "neelkanth@knoldus.com", "ABCD", List(), false, None, None)
    User.createUser(employer)
    val employers = User.findUserByEmail("neelkanth@knoldus.com")
    assert(employers.head.password === "ABCD")
    assert(employers.head.emailId === "neelkanth@knoldus.com")
  }

  test("Find Job Seeker") {
    val jobSeeker = UserEntity(new ObjectId, "neelkanth@knoldus.com", "", List("Scala"), true, None, None)
    User.createUser(jobSeeker)
    val jobseekers = User.findUserById(jobSeeker.id.toString)
    assert(jobseekers.head.jobSeeker === true)
  }

  test("test for Job Seeker Exist") {
    val jobSeeker = UserEntity(new ObjectId, "neelkanth@knoldus.com", "", List("Scala"), true, None, None)
    User.createUser(jobSeeker)
    val jobSeekerExist = User.jobSeekerExist("neelkanth@knoldus.com", List("Scala"))
    assert(jobSeekerExist === true)
  }

  test("Test For User Create Via Social Network") {
    val userId = User.getOrCreateUserBySocialNetwork("neelkanth@knoldus.com", "facebook", "73473957")
    val user: Option[UserEntity] = User.findUserById(userId)
    assert(user.get.emailId === "neelkanth@knoldus.com")
  }

  test("find user register via social network") {
    val userId = User.getOrCreateUserBySocialNetwork("neelkanth@knoldus.com", "facebook", "73473957")
    val users = User.findUserViaSocialNetwork("neelkanth@knoldus.com", "facebook", "73473957")
    assert(users.size === 1)
  }

  after {
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
  }

}