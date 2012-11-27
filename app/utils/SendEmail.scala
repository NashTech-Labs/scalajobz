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

  val duplicateSenderName = "support@scalajobz.com"
  val mailAddress = "scalajobz@gmail.com"
  val mailProtocol = "smtp.gmail.com"
  val transportProtocaolName="smtp"
  val emailPassword="email_password"
  val htmlBreak="<br>"
  def sendEmail(jobSeeker: UserEntity, jobs: List[JobEntity]): Unit = {
    val props = new Properties
    props.setProperty("mail.transport.protocol", transportProtocaolName);
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.host", mailProtocol);
    props.setProperty("mail.user", mailAddress);
    props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString(emailPassword).get))
    val session = Session.getDefaultInstance(props, null);
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(jobSeeker.emailId)
    msg.setFrom(new InternetAddress(duplicateSenderName, duplicateSenderName))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Job Alert From ScalaJobz.com");
    msg.setContent(Common.setContentForJobAlert(jobs, jobSeeker), "text/html")
    val transport = session.getTransport(transportProtocaolName);
    transport.connect(mailProtocol, mailAddress, ConversionUtility.decodeMe(Play.current.configuration.getString(emailPassword).get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }

  def sendPassword(emailId: String, password: String): Unit = {
    val props = new Properties
    props.setProperty("mail.transport.protocol", transportProtocaolName)
    props.setProperty("mail.smtp.starttls.enable", "true")
    props.setProperty("mail.host", mailProtocol)
    props.setProperty("mail.user", mailAddress)
    props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString(emailPassword).get))

    val session = Session.getDefaultInstance(props, null)
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(emailId)
    msg.setFrom(new InternetAddress(duplicateSenderName, duplicateSenderName))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Password Recovery On ScalaJobz")

    msg.setContent(

      "Hi <b>ScalaJobz</b> User." + htmlBreak + htmlBreak +
        "Here is your account details " + htmlBreak + htmlBreak +
        "Email-Id: " + emailId + htmlBreak +
        "Password: " + password + htmlBreak +
        htmlBreak + htmlBreak + htmlBreak +
        "Cheers," + htmlBreak +
        "ScalaJobz" + htmlBreak, "text/html")

    val transport = session.getTransport(transportProtocaolName)
    transport.connect(mailProtocol, mailAddress, ConversionUtility.decodeMe(Play.current.configuration.getString(emailPassword).get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }

}

