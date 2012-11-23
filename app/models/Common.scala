package models

case class Alert(alertType: String, message: String)

object Common {

  var alert:Alert= new Alert(null,null)
  def setAlert(alert:Alert)=this.alert=alert
  
}
