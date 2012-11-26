package models

import com.mongodb.casbah.commons.MongoDBObject
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import com.novus.salat.annotations._
import utils.MongoHQConfig

case class LogInForm(emailId: String,
  password: String)

case class EditUserProfileForm(currentPassword: String,
  newPassword: String,
  confirmPassword: String)

case class SignUpForm(emailId: String,
  password: String,
  confirmPassword: String)
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
    EmployerDAO.find(MongoDBObject("emailId" -> emailId, "password" -> password)).toList
  }

  /**
   * Update user Profile
   */
  def updateUser(employer: Employer, password: String) {
    EmployerDAO.update(MongoDBObject("_id" -> employer.id), new Employer(employer.id, employer.emailId, password, employer.skills, employer.jobSeeker), false, false, new WriteConcern)
  }

  def findJobSeekers = {
    EmployerDAO.find(MongoDBObject("jobSeeker" -> true)).toList
  }

  /**
   * Create New User
   */
  def createUser(employer: Employer) = {
    EmployerDAO.insert(employer)
  }

  /**
   *  Find User By Email
   */

  def findUserByEmail(emailId: String) = {
    EmployerDAO.find(MongoDBObject("emailId" -> emailId)).toList
  }

  /**
   * Find User By User Id
   */

  def findUserById(userId: String): Option[Employer] = {
    val userFound = EmployerDAO.find(MongoDBObject("_id" -> new ObjectId(userId))).toList
    (userFound.isEmpty) match {
      case true => None
      case false => Option(userFound.toList(0))
    }

  }

  /**
   *  Register Job Seeker
   */

  def registerJobSeeker(employer: Employer) = {
    EmployerDAO.insert(employer)

  }

}

object EmployerDAO extends SalatDAO[Employer, ObjectId](collection = MongoHQConfig.mongoDB("user"))