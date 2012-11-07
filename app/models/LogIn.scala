package models
import com.mongodb.casbah.commons.MongoDBObject

case class LogInForm(emailId: String, password: String)
object LogIn {
  
 def findUser(emailId:String,password:String)={
   EmployerDAO.find(MongoDBObject("emailId"-> emailId,"password"->password)).toList
   
 }

}