package models
import com.mongodb.casbah.commons.MongoDBObject
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import org.bson.types.ObjectId
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection

case class LogInForm(emailId: String, password: String)

case class EditUserProfileForm(currentPassword: String, newPassword: String, confirmPassword: String)

object LogIn {

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
  
  def findJobSeekers= {
    EmployerDAO.find(MongoDBObject("jobSeeker" -> true)).toList
  }

}