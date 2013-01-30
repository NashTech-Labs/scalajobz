package utils

import javax.mail.internet.MimeMessage
import java.util.Properties
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.Message
import javax.mail.Transport
import org.bson.types.ObjectId
import play.api.Play
import models.JobEntity
import models.Common
import models.UserEntity
import models.UserEntity
import models.UserEntity
import play.api.Logger

object MailUtility {

  val mailServer = "scalajobz@gmail.com"
  val supportMailString = "support@scalajobz.com"
  val serverProtocol = "smtp.gmail.com"
  val email_password = "email_password"
  val protocolName = "smtp"
  val break = "<br/>"

  /**
   * Send mail to user for job alerts
   * @param jobSeeker is the mail receiver
   * @param jobs are the jobs matching the seekers skills
   */
  def sendDailyJobAlertMail(jobSeeker: UserEntity, jobs: List[JobEntity]): Unit = {
    val props = new Properties
    props.setProperty("mail.transport.protocol", protocolName)
    props.setProperty("mail.smtp.starttls.enable", "true")
    props.setProperty("mail.host", serverProtocol)
    props.setProperty("mail.user", mailServer)
    props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    val session = Session.getDefaultInstance(props, null);
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(jobSeeker.emailId)
    msg.setFrom(new InternetAddress(supportMailString, supportMailString))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Job Alert From ScalaJobz.com");
    msg.setContent(Common.setContentForJobAlert(jobs, jobSeeker), "text/html")
    val transport = session.getTransport(protocolName);
    transport.connect(serverProtocol, mailServer, ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }

  /**
   * Forgot password functionality
   * @param emailId is the mailId f password
   * @param password is the password of that user
   *
   */
  def sendPassword(emailId: String, password: String) {
    val props = new Properties
    props.setProperty("mail.transport.protocol", protocolName)
    props.setProperty("mail.smtp.starttls.enable", "true")
    props.setProperty("mail.host", serverProtocol)
    props.setProperty("mail.user", mailServer)
    props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))

    val session = Session.getDefaultInstance(props, null)
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(emailId)
    msg.setFrom(new InternetAddress(supportMailString, supportMailString))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Password Recovery On ScalaJobz")

    msg.setContent(

      "Hi <b>ScalaJobz</b> User," + break + break +
        "Here is your account details " + break + break +
        "Email-Id: " + emailId + break +
        "Password: <b>" + password + "</b>" + break + break +
        "This is system generated password, please change your password from profile settings." +
        break + break + break +
        "Thanks & Regards" + break +
        "ScalaJobz Support Team" + break, "text/html")

    val transport = session.getTransport(protocolName)
    transport.connect(serverProtocol, mailServer, ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }

  /**
   * Sent mail to Scalajobz when anyone make a suggestion on contact us page
   * @param name is mail sender name
   * @param emailAddress is sender email id
   * @param subject is mail subject
   * @param message is sender message
   */
  def sendEmailToScalaJobzFromContactUs(name: String, senderEmailAddress: String, subject: String, message: String): Unit = {
    val props = new Properties
    props.setProperty("mail.transport.protocol", protocolName)
    props.setProperty("mail.smtp.starttls.enable", "true")
    props.setProperty("mail.host", serverProtocol)
    props.setProperty("mail.user", mailServer)
    props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    val session = Session.getDefaultInstance(props, null);
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(mailServer)
    msg.setFrom(new InternetAddress(supportMailString, supportMailString))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Contact Us ScalaJobz : " + subject);
    msg.setContent(Common.setContentForContactUsMail(name, senderEmailAddress, subject, message), "text/html")
    val transport = session.getTransport(protocolName);
    transport.connect(serverProtocol, mailServer, ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }

  /**
   * Acknowledgement Mail
   * @param jobSeeker is the mail receiver
   * @param jobs are the jobs matching the seekers skills
   */
  def acknowledgementMail(name: String, senderEmailAddress: String): Unit = {
    val props = new Properties
    props.setProperty("mail.transport.protocol", protocolName)
    props.setProperty("mail.smtp.starttls.enable", "true")
    props.setProperty("mail.host", serverProtocol)
    props.setProperty("mail.user", mailServer)
    props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    val session = Session.getDefaultInstance(props, null);
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(senderEmailAddress)
    msg.setFrom(new InternetAddress(supportMailString, supportMailString))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Thanks For Mailing Us ScalaJobz");
    msg.setContent(Common.setContentForAcknowledgementMail(name), "text/html")
    val transport = session.getTransport(protocolName);
    transport.connect(serverProtocol, mailServer, ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }

  /**
   * Send Email Id Verification Mail To Job Seeker To Verify Email Id Or Activate Job Alert Mail
   * @param jobSeeker is the mail receiver
   */
  def verificationMailToJobSeeker(jobSeeker: UserEntity): Unit = {
    val props = new Properties
    props.setProperty("mail.transport.protocol", protocolName)
    props.setProperty("mail.smtp.starttls.enable", "true")
    props.setProperty("mail.host", serverProtocol)
    props.setProperty("mail.user", mailServer)
    props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    val session = Session.getDefaultInstance(props, null);
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(jobSeeker.emailId)
    msg.setFrom(new InternetAddress(supportMailString, supportMailString))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject(" Activate your job alert : Scalajobz");
    msg.setContent(Common.setContentForJobSeekerVerificationMail(jobSeeker), "text/html")
    val transport = session.getTransport(protocolName);
    transport.connect(serverProtocol, mailServer, ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }

  /**
   * Send Job Detail Email Via Job Id to the email Id provided by Rest Api Call
   * @param emailId is the mail receiver
   * @param jobId is the Job Id
   */
  def sendMailForJobDetail(emailId: String, jobId: String): Boolean = {
    try {
      val props = new Properties
      props.setProperty("mail.transport.protocol", protocolName)
      props.setProperty("mail.smtp.starttls.enable", "true")
      props.setProperty("mail.host", serverProtocol)
      props.setProperty("mail.user", mailServer)
      props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
      val session = Session.getDefaultInstance(props, null);
      val msg = new MimeMessage(session)
      val recepientAddress = new InternetAddress(emailId)
      msg.setFrom(new InternetAddress(supportMailString, supportMailString))
      msg.addRecipient(Message.RecipientType.TO, recepientAddress);
      msg.setSubject(" Job Details : Scalajobz");
      msg.setContent(Common.setContentForJobDetailMail(emailId, jobId), "text/html")
      val transport = session.getTransport(protocolName);
      transport.connect(serverProtocol, mailServer, ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
      transport.sendMessage(msg, msg.getAllRecipients)
      true
    } catch {
      case ex =>
        Logger.error("Error occurred While Sending Mail For Rest Api Call : ", ex)
        false
    }
  }

}