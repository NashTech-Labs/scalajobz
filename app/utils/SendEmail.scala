package utils

import javax.mail.internet.MimeMessage
import java.util.Properties
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.Message
import javax.mail.Transport
import org.bson.types.ObjectId
import play.api.Play
import models.Job
import models.Common

object SendEmail extends App {

  def sendEmail(emailId: String, jobs: List[Job]) = {
    val props = new Properties
    props.setProperty("mail.transport.protocol", "smtp");
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.host", "smtp.gmail.com");
    props.setProperty("mail.user", "scalajobz@gmail.com");
    props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString("email_password").get))
    val session = Session.getDefaultInstance(props, null);
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(emailId)
    msg.setFrom(new InternetAddress("support@scalajobz.com", "support@scalajobz.com"))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Job Alert From ScalaJobz.com");
    msg.setContent(Common.setContentForJobAlert(jobs),"text/html")
    val transport = session.getTransport("smtp");
    transport.connect("smtp.gmail.com", "scalajobz@gmail.com", ConversionUtility.decodeMe(Play.current.configuration.getString("email_password").get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }
  
  def sendPassword(emailId: String, password: String) {
    val props = new Properties
    props.setProperty("mail.transport.protocol", "smtp")
    props.setProperty("mail.smtp.starttls.enable", "true")
    props.setProperty("mail.host", "smtp.gmail.com")
    props.setProperty("mail.user", "scalajobz@gmail.com")
     props.setProperty("mail.password", ConversionUtility.decodeMe(Play.current.configuration.getString("email_password").get))

    val session = Session.getDefaultInstance(props, null)
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(emailId)
    msg.setFrom(new InternetAddress("support@scalajobz.com", "support@scalajobz.com"))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Password Recovery On ScalaJobz")

    msg.setContent(

      "Hi <b>ScalaJobz</b> User." + "<br>" + "<br>" +
        "Here is your account details " + "<br>" + "<br>" +
        "Email-Id: " + emailId + "<br>" +
        "Password: " + password + "<br>" +
        "<br>" + "<br>" + "<br>" +
        "Cheers," + "<br>" +
        "ScalaJobz" + "<br>", "text/html")

    val transport = session.getTransport("smtp")
    transport.connect("smtp.gmail.com", "scalajobz@gmail.com", ConversionUtility.decodeMe(Play.current.configuration.getString("email_password").get))
    transport.sendMessage(msg, msg.getAllRecipients)
  }


  }

