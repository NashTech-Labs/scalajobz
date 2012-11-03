package models


case class PostAJobForm(position: String, company: String, location: String,jobType:String, emailAddress: String, description: String)
object PostAJob {

  /*
   * Job Type
   */
  def jobType: Seq[(String, String)] = {
   Seq("Contract"->"Contract","Permanent"->"Permanent")
  }
  
}