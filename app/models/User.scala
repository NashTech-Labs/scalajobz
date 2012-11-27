package models

import com.mongodb.casbah.commons.MongoDBObject
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import com.novus.salat.annotations._
import utils.MongoHQConfig

//LoginForm
case class LogInForm(emailId: String,
  password: String)

//Edit User Profile Form
case class EditUserProfileForm(currentPassword: String,
  newPassword: String,
  confirmPassword: String)

//Sign up Form
case class SignUpForm(emailId: String,
  password: String,
  confirmPassword: String)

//Add User(Employer/JobSeeker) Form
case class Employer(@Key("_id") id: ObjectId,
  emailId: String,
  password: String,
  skills: List[String],
  jobSeeker: Boolean)

object User {

  /**
   * Authenticate User By Credentials Provided
   */
  def findUser(emailId: String, password: String) = {
    UserDAO.find(MongoDBObject("emailId" -> emailId, "password" -> password)).toList
  }

  /**
   * Update user Profile
   */
  def updateUser(employer: Employer, password: String) {
    UserDAO.update(MongoDBObject("_id" -> employer.id), new Employer(employer.id, employer.emailId, password, employer.skills, employer.jobSeeker), false, false, new WriteConcern)
  }

  def findJobSeekers = {
    UserDAO.find(MongoDBObject("jobSeeker" -> true)).toList
  }

  /**
   * Create New User
   */
  def createUser(employer: Employer) = {
    UserDAO.insert(employer)
  }

  /**
   *  Find User By Email But This Will Match With Employer Not with JobSeeker
   */

  def findUserByEmail(emailId: String) = {
    UserDAO.find(MongoDBObject("emailId" -> emailId, "jobSeeker" -> false)).toList
  }

  /**
   * Find User By User Id
   */

  def findUserById(userId: String): Option[Employer] = {
    val userFound = UserDAO.find(MongoDBObject("_id" -> new ObjectId(userId))).toList
    (userFound.isEmpty) match {
      case true => None
      case false => Option(userFound.toList(0))
    }

  }

 

  /**
   * UnSubscribe From Job Alerts By Using JobSeeker Id(UserId)
   */

  def unSubscribeJobSeeker(userId: String) = {
    User.findUserById(userId) match {
      case None => false
      case Some(jobSeeker) =>
        UserDAO.remove(jobSeeker)
        true
    }

  }

}

object UserDAO extends SalatDAO[Employer, ObjectId](collection = MongoHQConfig.mongoDB("user"))