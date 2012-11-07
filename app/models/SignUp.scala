package models

import com.novus.salat.global._
import com.novus.salat.annotations._
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.MongoHQConfig
import com.mongodb.casbah.commons.MongoDBObject

case class SignUpForm(emailId: String, password: String, confirmPassword: String)
case class Employer(@Key("_id") id: ObjectId, emailId: String, password: String)
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
}

object EmployerDAO extends SalatDAO[Employer, ObjectId](collection = MongoHQConfig.mongoDB("user"))