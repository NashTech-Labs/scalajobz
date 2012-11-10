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
   * Find User By Id
   */
  def findUserProfile(userId: String)= {
    val userList = EmployerDAO.find(MongoDBObject("_id" -> new ObjectId(userId))).toList
    (userList.isEmpty) match {
      case true => None
      case false => Option(userList.toList(0))
    }
  }

  /**
   * Update user Profile
   */
  def updateUser(employer: Employer, password: String){
    EmployerDAO.update(MongoDBObject("_id" -> employer.id), new Employer(employer.id, employer.emailId, password, List(), false), false, false, new WriteConcern)
  }

}