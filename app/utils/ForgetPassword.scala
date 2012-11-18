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

object ForgetPassword extends App {
  val ALPHA_NUM = "!1@2aA#Ss3Dd$Ff4gG%Hh5Jj@Kk6Ll&Zz7Xx*Cc8Vv(Bb9Nn)Mm0QqWwEeR-rTtYy+Uu}]IiOo{[Pp";
  def getAlphaNumeric: String = {
    var randomPassword: String = ""
    for (x <- 1 to 5) {
      val ndx = Math.random * ALPHA_NUM.length()
      randomPassword += ndx
    }
    randomPassword
  }

  def sendPassword(emailId: String, password: String) {
    val props = new Properties
    props.setProperty("mail.transport.protocol", "smtp")
    props.setProperty("mail.smtp.starttls.enable", "true")
    props.setProperty("mail.host", "smtp.gmail.com")
    props.setProperty("mail.user", "neelkanth@knoldus.com")
    props.setProperty("mail.password", "9585859381a39295969384a4a2")

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
    transport.connect("smtp.gmail.com", "neelkanth@knoldus.com", ConversionUtility.decodeMe("9585859381a39295969384a4a2"))
    transport.sendMessage(msg, msg.getAllRecipients)
  }


  //  def sendEmail(emailId: String, job: Job) = {
  //    val props = new Properties
  //    props.setProperty("mail.transport.protocol", "smtp");
  //    props.setProperty("mail.smtp.starttls.enable", "true");
  //    props.setProperty("mail.host", "smtp.gmail.com");
  //    props.setProperty("mail.user", "neelkanth@knoldus.com");
  //    props.setProperty("mail.password", "")
  //    val session = Session.getDefaultInstance(props, null);
  //    val msg = new MimeMessage(session)
  //    val recepientAddress = new InternetAddress(emailId)
  //    msg.setFrom(new InternetAddress("beamteam@beamstream.com", "beamteam@beamstream.com"))
  //    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
  //    msg.setSubject("Password Recovery On Scala Jobz");
  //    msg.setContent("Here is the jobs for you from scalajobz.com", "text/html");
  //    val transport = session.getTransport("smtp");
  //    transport.connect("smtp.gmail.com", "neelkanth@knoldus.com", "")
  //    transport.sendMessage(msg, msg.getAllRecipients)
  //  }

}



