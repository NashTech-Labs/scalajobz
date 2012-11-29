package models
import com.mongodb.casbah.commons.MongoDBObject
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global.ctx
import com.mongodb.casbah.Imports.WriteConcern
import com.mongodb.casbah.MongoConnection
import com.novus.salat.annotations.Key
import utils.MongoHQConfig

/**
 * class for creating LoginForm
 */
case class LogInForm(emailId: String,
  password: String)

/**
 * class for creating Edit User Profile Form
 */
case class EditUserProfileForm(currentPassword: String,
  newPassword: String,
  confirmPassword: String)

/**
 * class for creating Sign up Form
 */
case class SignUpForm(emailId: String,
  password: String,
  confirmPassword: String)

/**
 * User Entity
 * @param id the user's id
 * @param emailId the user's emailId
 * @param password the user's emailId
 * @param skills the user's skills
 * @param jobSeeker the user's jobSeeker
 */
case class UserEntity(@Key("_id") id: ObjectId,
  emailId: String,
  password: String,
  skills: List[String],
  jobSeeker: Boolean)

/** Factory for [[models.UserEntity]] instances. */
object User {

  /**
   * Authenticate User By Credentials Provided
   *
   * @param emailId is emailId of user to be searched
   * @param password is password of user to be searched
   */
  def findUser(emailId: String, password: String): List[UserEntity] = {
    UserDAO.find(MongoDBObject("emailId" -> emailId, "password" -> password)).toList
  }

  /**
   * Update user Profile
   * @param employer is employer object to be updated
   * @param password is password of user to be updated
   */
  def updateUser(employer: UserEntity, password: String): Unit = {
    UserDAO.update(MongoDBObject("_id" -> employer.id), new UserEntity(employer.id, employer.emailId, password, employer.skills, employer.jobSeeker), false, false, new WriteConcern)
  }
  /**
   * Finding the job seekers
   */
  def findJobSeekers: List[UserEntity] = {
    UserDAO.find(MongoDBObject("jobSeeker" -> true)).toList
  }

  /**
   * Create New User
   * @param employer is the employer object to be created
   */
  def createUser(employer: UserEntity): Option[ObjectId] = {
    UserDAO.insert(employer)
  }

  /**
   *  Find User By Email But This Will Match With Employer Not with JobSeeker
   *  @param emailId is the emailId of jobseeker to be searched
   */

  def findUserByEmail(emailId: String): List[UserEntity] = {
    UserDAO.find(MongoDBObject("emailId" -> emailId, "jobSeeker" -> false)).toList
  }

  /**
   * Find User By User Id
   * @param userId is the id of the user to be searched
   */

  def findUserById(userId: String): Option[UserEntity] = {
    val userFound = UserDAO.find(MongoDBObject("_id" -> new ObjectId(userId))).toList
    (userFound.isEmpty) match {
      case true => None
      case false => Option(userFound.toList(0))
    }

  }

  /**
   * UnSubscribe From Job Alerts By Using JobSeeker Id(UserId)
   * @param userId is the id of the user to be unsubscribed
   */

  def unSubscribeJobSeeker(userId: String): Boolean = {
    User.findUserById(userId) match {
      case None => false
      case Some(jobSeeker: UserEntity) =>
        UserDAO.remove(jobSeeker)
        true
    }

  }

}
/**
 * Instantiate UserDAO
 * Access the settings from MongoHQConfig file
 */
object UserDAO extends SalatDAO[UserEntity, ObjectId](collection = MongoHQConfig.mongoDB("user"))
