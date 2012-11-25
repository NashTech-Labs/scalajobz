package models

case class Alert(alertType: String, message: String)

object Common {

  var alert: Alert = new Alert(null, null)
  def setAlert(alert: Alert) = this.alert = alert

  def setContentForJobAlert(jobs: List[Job]): String = {

    var message = "<b>Job Alert from scalajobz.com</b>" +
      "<br/> <br/>" + "<b>Your Job Details</b>" + "<br/> <br/>"
    for (job <- jobs) {
      message += "<b><u>" + job.position + "</u></b>" + "<br/>"
      message += job.company + "<br/>"
      message += job.location + "<br/>"
    }
    message
  }

}
