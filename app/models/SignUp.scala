package models

import com.novus.salat.global._
import com.novus.salat.annotations._
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.MongoHQConfig
import com.mongodb.casbah.commons.MongoDBObject

case class JobAlertForm(emailId: String, password: String, confirmPassword: String,keySkills:String)
case class SignUpForm(emailId: String, password: String, confirmPassword: String)
case class Employer(@Key("_id") id: ObjectId, emailId: String, password: String, skills: List[String], jobSeeker: Boolean)
object SignUp {

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

  def findUserById(userId: String): Option[Employer] = {
    val userFound = EmployerDAO.find(MongoDBObject("_id" -> new ObjectId(userId))).toList
    (userFound.isEmpty) match {
      case true => None
      case false => Option(userFound.toList(0))
    }

  }
  
  def registerJobSeeker(employer: Employer)={
    EmployerDAO.insert(employer)
    
  }
}

object EmployerDAO extends SalatDAO[Employer, ObjectId](collection = MongoHQConfig.mongoDB("user"))