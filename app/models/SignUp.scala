package models

import com.novus.salat.global._
import com.novus.salat.annotations._
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.MongoHQConfig

case class SignUpForm(emailId: String, password: String,confirmPassword:String)
case class User(@Key("_id") id: ObjectId,emailId:String,password:String)
object SignUp {

  /**
 * Create New User 
 */
  def createUser(emailId: String,password:String)= {
    val newUser=User(new ObjectId,emailId,password)
    UserDAO.insert(newUser)
  }
}


object UserDAO extends SalatDAO[User, ObjectId](collection = MongoHQConfig.mongoDB("user"))