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

object SendEmail extends App {

  def sendEmail(emailId: String, job: Job) = {
    val props = new Properties
    props.setProperty("mail.transport.protocol", "smtp");
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.host", "smtp.gmail.com");
    props.setProperty("mail.user", "neelkanth@knoldus.com");
    props.setProperty("mail.password", "")
    val session = Session.getDefaultInstance(props, null);
    val msg = new MimeMessage(session)
    val recepientAddress = new InternetAddress(emailId)
    msg.setFrom(new InternetAddress("support@scalajobz.com", "support@scalajobz.com"))
    msg.addRecipient(Message.RecipientType.TO, recepientAddress);
    msg.setSubject("Job Alert From ScalaJobz.com");
    msg.setContent("Here is the jobs for you from scalajobz.com"
        + "<br>" + "Your Job Details"+ "<br>" + "<br>" +
        job.company+"<br>" +
        job.location+"<br>"
        ,"text/html")
    val transport = session.getTransport("smtp");
    transport.connect("smtp.gmail.com", "neelkanth@knoldus.com", "")
    transport.sendMessage(msg, msg.getAllRecipients)
  }

  }

