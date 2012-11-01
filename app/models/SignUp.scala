package models


case class SignUpForm(emailId: String, password: String,confirmPassword:String)

object SignUp {

  def createUser(emailId: String,password:String) {
    println(emailId,password)
  }
  
}