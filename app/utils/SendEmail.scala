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

object SendEmail {

  val mailServer = "scalajobz@gmail.com"
  val supportMailString = "support@scalajobz.com"
  val serverProtocol = "smtp.gmail.com"
  val email_password = "email_password"
  val protocolName = "smtp"
  val break = "<br/>"

  def sendEmail(jobSeeker: UserEntity, jobs: List[JobEntity]): Unit = {
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

      "Hi <b>ScalaJobz</b> User." + break + break +
        "Here is your account details " + break + break +
        "Email-Id: " + emailId + break +
        "Password: " + password + break +
        break + break + break +
        "Cheers," + break +
        "ScalaJobz" + break, "text/html")

    val transport = session.getTransport(protocolName)
    transport.connect(serverProtocol, mailServer, ConversionUtility.decodeMe(Play.current.configuration.getString(email_password).get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }

}
