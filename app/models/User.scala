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
 * @param socialNetworkChannel the social network channel through which user registered
 * @param socailNetworkId is the unique id provided by the network channel to identify user
 * @param jobSeekerActivate is the flag to identify whether jobSeeker is active or not
 */
case class UserEntity(@Key("_id") id: ObjectId,
  emailId: String,
  password: String,
  skills: List[String],
  jobSeeker: Boolean,
  socialNetworkChannel: Option[String],
  socailNetworkId: Option[String],
  jobSeekerActivate: Option[Boolean])

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
    UserDAO.update(MongoDBObject("_id" -> employer.id), new UserEntity(employer.id, employer.emailId, password, employer.skills, employer.jobSeeker, employer.socialNetworkChannel, employer.socailNetworkId, None), false, false, new WriteConcern)
  }
  /**
   * Finding the job seekers
   */
  def findJobSeekers: List[UserEntity] = {
    UserDAO.find(MongoDBObject("jobSeeker" -> true, "jobSeekerActivate" -> Option(true))).toList
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
   *  Find Users By Email Registered Through ScalaJobz
   *  @param emailId is the emailId of jobseeker to be searched
   */

  def findUserRegisteredWithScalaJobzViaEmailId(emailId: String): List[UserEntity] = {
    UserDAO.find(MongoDBObject("emailId" -> emailId, "jobSeeker" -> false, "socialNetworkChannel" -> None)).toList
  }

  /**
   *  Find Users Registered Through ScalaJobz
   */

  def findUsersRegisteredWithScalaJobz: List[UserEntity] = {
    UserDAO.find(MongoDBObject("jobSeeker" -> false, "socialNetworkChannel" -> None)).toList
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
        Job.findJobseekerForJobMailAlert(new ObjectId(userId)) match {
          case None => //do nothing
          case Some(jobMailAlertEntity: JobMailAlertEntity) => JobMailAlertDAO.remove(jobMailAlertEntity) //remove user from job mail alert to stop sending email
        }
        true
    }
  }

  /**
   *  Find User Via Social Networks
   *  @param emailId is the emailId of user to be searched
   *  @param socialMediumChannel is the network channel
   *  @param socialMediumId is the network channel Id
   */

  def findUserViaSocialNetwork(emailId: String, socialNetworkChannel: String, socailNetworkId: String): List[UserEntity] = {
    UserDAO.find(MongoDBObject("emailId" -> emailId, "socialNetworkChannel" -> Option(socialNetworkChannel), "socailNetworkId" -> Option(socailNetworkId))).toList
  }

  /**
   * Subscribe Through Social Network
   * @param userName is the name of the user to be subscribed through Social Networks
   */

  def getOrCreateUserBySocialNetwork(userName: String, socialNetworkChannel: String, socailNetworkId: String): String = {
    val userList = User.findUserViaSocialNetwork(userName, socialNetworkChannel, socailNetworkId)
    if (userList.isEmpty) {
      val newUser = UserEntity(new ObjectId, userName, "", List(), false, Option(socialNetworkChannel), Option(socailNetworkId), None)
      createUser(newUser).get.toString
    } else {
      userList(0).id.toString
    }

  }

  /**
   * Check For Existing Job Seeker
   * @param emailId is the emailId of jobseeker
   * @param skills is user searching token
   */

  def jobSeekerExist(emailId: String, skills: List[String]): Boolean = {
    val jobSeekers = UserDAO.find(MongoDBObject("emailId" -> emailId, "skills" -> skills)).toList
    jobSeekers.isEmpty match {
      case true => false
      case false => true
    }

  }

  /**
   * To Give the permission to the scala jobz user to edit their profile
   * @param userId is the userId Of User
   */

  def isUserLoginViaScalaJobz(userId: String): Boolean = {
    User.findUserById(userId) match {
      case None => false
      case Some(user: UserEntity) =>
        if (user.socialNetworkChannel == None)
          true
        else
          false
    }
  }

  /**
   * Activate Job Seeker For Job Alert Mail
   * @param jobSeeker is jobSeeker object to be updated
   */
  def activateJobSeeker(jobSeeker: UserEntity): Unit = {
    UserDAO.update(MongoDBObject("_id" -> jobSeeker.id), new UserEntity(jobSeeker.id, jobSeeker.emailId, "", jobSeeker.skills, jobSeeker.jobSeeker, None, None, Option(true)), false, false, new WriteConcern)
  }

}
/**
 * Instantiate UserDAO
 * Access the settings from MongoHQConfig file
 */
object UserDAO extends SalatDAO[UserEntity, ObjectId](collection = MongoHQConfig.mongoDB("user"))
