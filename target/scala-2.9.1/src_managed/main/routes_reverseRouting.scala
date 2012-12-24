// @SOURCE:/home/ruchi/knolProj/ScalaJobz/scalajobz-com/conf/routes
// @HASH:8279556617b33cbd8da431885bcc325946852e94
// @DATE:Sun Dec 23 18:48:21 IST 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:89
// @LINE:86
// @LINE:82
// @LINE:81
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:55
// @LINE:54
// @LINE:50
// @LINE:48
// @LINE:44
// @LINE:42
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:89
class ReverseAssets {
    


 
// @LINE:89
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:65
// @LINE:64
// @LINE:63
class ReverseRESTServiceController {
    


 
// @LINE:65
def processGetJobListForSingleRequest(code:String) = {
   Call("GET", "/getJobsForACriteria/" + implicitly[PathBindable[String]].unbind("code", code))
}
                                                        
 
// @LINE:63
def restApi() = {
   Call("GET", "/restApi")
}
                                                        
 
// @LINE:64
def processGetAllJobsList() = {
   Call("GET", "/getAllJobs")
}
                                                        

                      
    
}
                            

// @LINE:73
// @LINE:72
class ReverseLinkedInController {
    


 
// @LINE:73
def linkedinCallback() = {
   Call("GET", "/linkedin/callback")
}
                                                        
 
// @LINE:72
def linkedinLogin() = {
   Call("GET", "/linkedin/login")
}
                                                        

                      
    
}
                            

// @LINE:75
// @LINE:74
class ReverseFacebookController {
    


 
// @LINE:74
def facebookLogin() = {
   Call("GET", "/facebook/login")
}
                                                        
 
// @LINE:75
def facebookCallback() = {
   Call("GET", "/facebook/callback")
}
                                                        

                      
    
}
                            

// @LINE:77
// @LINE:76
class ReverseGoogleController {
    


 
// @LINE:77
def googleCallback() = {
   Call("GET", "/google/callback")
}
                                                        
 
// @LINE:76
def googleLogin() = {
   Call("GET", "/google/login")
}
                                                        

                      
    
}
                            

// @LINE:71
// @LINE:70
class ReverseTwitterController {
    


 
// @LINE:71
def twitterCallBack() = {
   Call("GET", "/twitter/callback")
}
                                                        
 
// @LINE:70
def twitterLogin() = {
   Call("GET", "/twitter/twitterLogin")
}
                                                        

                      
    
}
                            

// @LINE:50
// @LINE:48
// @LINE:44
// @LINE:42
// @LINE:32
// @LINE:30
// @LINE:28
class ReverseUserController {
    


 
// @LINE:48
def forgetPassword() = {
   Call("GET", "/forgetPassword")
}
                                                        
 
// @LINE:30
def findJobPostByUserId() = {
   Call("GET", "/findJobPostByUserId")
}
                                                        
 
// @LINE:42
def registerJobSeeker(emailId:String, skillsToken:String) = {
   Call("POST", "/registerJobSeeker/" + implicitly[PathBindable[String]].unbind("emailId", emailId) + "/" + implicitly[PathBindable[String]].unbind("skillsToken", skillsToken))
}
                                                        
 
// @LINE:32
def updateUserProfile() = {
   Call("POST", "/updateUserProfile")
}
                                                        
 
// @LINE:44
def unSubscribeJobSeeker(userId:String) = {
   Call("GET", "/unSubscribeJobSeeker/" + implicitly[PathBindable[String]].unbind("userId", userId))
}
                                                        
 
// @LINE:28
def editUserProfile() = {
   Call("GET", "/editUserProfile")
}
                                                        
 
// @LINE:50
def sendForgetPassword(emailId:String) = {
   Call("POST", "/sendForgetPassword/" + implicitly[PathBindable[String]].unbind("emailId", emailId))
}
                                                        

                      
    
}
                            

// @LINE:86
// @LINE:82
// @LINE:81
// @LINE:55
// @LINE:54
// @LINE:20
// @LINE:18
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    


 
// @LINE:12
def loginOnScalaJobz() = {
   Call("GET", "/login")
}
                                                        
 
// @LINE:81
def errorPage() = {
   Call("GET", "/errorPage")
}
                                                        
 
// @LINE:54
def contactUs() = {
   Call("GET", "/contactUs")
}
                                                        
 
// @LINE:82
def loginFailureViaSocialNetworks() = {
   Call("GET", "/loginFailure")
}
                                                        
 
// @LINE:10
def newUser(flag:String) = {
   Call("POST", "/signup/" + implicitly[PathBindable[String]].unbind("flag", flag))
}
                                                        
 
// @LINE:13
def logIn(flag:String) = {
   Call("POST", "/login/" + implicitly[PathBindable[String]].unbind("flag", flag))
}
                                                        
 
// @LINE:18
// @LINE:6
def index() = {
   () match {
// @LINE:6
case () if true => Call("GET", "/")
                                                                
// @LINE:18
case () if true => Call("GET", "/findAllJobs")
                                                                    
   }
}
                                                        
 
// @LINE:9
def signUpOnScalaJobz(flag:String) = {
   Call("GET", "/signup/" + implicitly[PathBindable[String]].unbind("flag", flag))
}
                                                        
 
// @LINE:86
def jobPagination(editFlag:String) = {
   Call("GET", "/jobPagination/" + implicitly[PathBindable[String]].unbind("editFlag", editFlag))
}
                                                        
 
// @LINE:55
def contactUsEmail() = {
   Call("POST", "/contactUsEmail")
}
                                                        
 
// @LINE:20
def logOutFromScalaJobz() = {
   Call("GET", "/logout")
}
                                                        

                      
    
}
                            

// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:24
// @LINE:22
// @LINE:16
// @LINE:15
class ReverseJobController {
    


 
// @LINE:34
def findJobPostForEdit(jobId:String) = {
   Call("GET", "/findJobPostForEdit/" + implicitly[PathBindable[String]].unbind("jobId", jobId))
}
                                                        
 
// @LINE:38
def deleteJob(jobId:String) = {
   Call("GET", "/deleteJob/" + implicitly[PathBindable[String]].unbind("jobId", jobId))
}
                                                        
 
// @LINE:36
def editJob(jobId:String) = {
   Call("POST", "/editJob/" + implicitly[PathBindable[String]].unbind("jobId", jobId))
}
                                                        
 
// @LINE:16
def newJob() = {
   Call("POST", "/postAJob")
}
                                                        
 
// @LINE:22
def findAJob(searchString:String, editFlag:String) = {
   Call("POST", "/findAJob/" + implicitly[PathBindable[String]].unbind("searchString", searchString) + "/" + implicitly[PathBindable[String]].unbind("editFlag", editFlag))
}
                                                        
 
// @LINE:24
def findJobDetail(jobId:String) = {
   Call("GET", "/jobDetail/" + implicitly[PathBindable[String]].unbind("jobId", jobId))
}
                                                        
 
// @LINE:15
def postAJob() = {
   Call("GET", "/postAJob")
}
                                                        

                      
    
}
                            
}
                    


// @LINE:89
// @LINE:86
// @LINE:82
// @LINE:81
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:55
// @LINE:54
// @LINE:50
// @LINE:48
// @LINE:44
// @LINE:42
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:89
class ReverseAssets {
    


 
// @LINE:89
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:65
// @LINE:64
// @LINE:63
class ReverseRESTServiceController {
    


 
// @LINE:65
def processGetJobListForSingleRequest = JavascriptReverseRoute(
   "controllers.RESTServiceController.processGetJobListForSingleRequest",
   """
      function(code) {
      return _wA({method:"GET", url:"/getJobsForACriteria/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("code", code)})
      }
   """
)
                                                        
 
// @LINE:63
def restApi = JavascriptReverseRoute(
   "controllers.RESTServiceController.restApi",
   """
      function() {
      return _wA({method:"GET", url:"/restApi"})
      }
   """
)
                                                        
 
// @LINE:64
def processGetAllJobsList = JavascriptReverseRoute(
   "controllers.RESTServiceController.processGetAllJobsList",
   """
      function() {
      return _wA({method:"GET", url:"/getAllJobs"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:73
// @LINE:72
class ReverseLinkedInController {
    


 
// @LINE:73
def linkedinCallback = JavascriptReverseRoute(
   "controllers.LinkedInController.linkedinCallback",
   """
      function() {
      return _wA({method:"GET", url:"/linkedin/callback"})
      }
   """
)
                                                        
 
// @LINE:72
def linkedinLogin = JavascriptReverseRoute(
   "controllers.LinkedInController.linkedinLogin",
   """
      function() {
      return _wA({method:"GET", url:"/linkedin/login"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:75
// @LINE:74
class ReverseFacebookController {
    


 
// @LINE:74
def facebookLogin = JavascriptReverseRoute(
   "controllers.FacebookController.facebookLogin",
   """
      function() {
      return _wA({method:"GET", url:"/facebook/login"})
      }
   """
)
                                                        
 
// @LINE:75
def facebookCallback = JavascriptReverseRoute(
   "controllers.FacebookController.facebookCallback",
   """
      function() {
      return _wA({method:"GET", url:"/facebook/callback"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:77
// @LINE:76
class ReverseGoogleController {
    


 
// @LINE:77
def googleCallback = JavascriptReverseRoute(
   "controllers.GoogleController.googleCallback",
   """
      function() {
      return _wA({method:"GET", url:"/google/callback"})
      }
   """
)
                                                        
 
// @LINE:76
def googleLogin = JavascriptReverseRoute(
   "controllers.GoogleController.googleLogin",
   """
      function() {
      return _wA({method:"GET", url:"/google/login"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:71
// @LINE:70
class ReverseTwitterController {
    


 
// @LINE:71
def twitterCallBack = JavascriptReverseRoute(
   "controllers.TwitterController.twitterCallBack",
   """
      function() {
      return _wA({method:"GET", url:"/twitter/callback"})
      }
   """
)
                                                        
 
// @LINE:70
def twitterLogin = JavascriptReverseRoute(
   "controllers.TwitterController.twitterLogin",
   """
      function() {
      return _wA({method:"GET", url:"/twitter/twitterLogin"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:50
// @LINE:48
// @LINE:44
// @LINE:42
// @LINE:32
// @LINE:30
// @LINE:28
class ReverseUserController {
    


 
// @LINE:48
def forgetPassword = JavascriptReverseRoute(
   "controllers.UserController.forgetPassword",
   """
      function() {
      return _wA({method:"GET", url:"/forgetPassword"})
      }
   """
)
                                                        
 
// @LINE:30
def findJobPostByUserId = JavascriptReverseRoute(
   "controllers.UserController.findJobPostByUserId",
   """
      function() {
      return _wA({method:"GET", url:"/findJobPostByUserId"})
      }
   """
)
                                                        
 
// @LINE:42
def registerJobSeeker = JavascriptReverseRoute(
   "controllers.UserController.registerJobSeeker",
   """
      function(emailId,skillsToken) {
      return _wA({method:"POST", url:"/registerJobSeeker/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("emailId", emailId) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("skillsToken", skillsToken)})
      }
   """
)
                                                        
 
// @LINE:32
def updateUserProfile = JavascriptReverseRoute(
   "controllers.UserController.updateUserProfile",
   """
      function() {
      return _wA({method:"POST", url:"/updateUserProfile"})
      }
   """
)
                                                        
 
// @LINE:44
def unSubscribeJobSeeker = JavascriptReverseRoute(
   "controllers.UserController.unSubscribeJobSeeker",
   """
      function(userId) {
      return _wA({method:"GET", url:"/unSubscribeJobSeeker/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                                                        
 
// @LINE:28
def editUserProfile = JavascriptReverseRoute(
   "controllers.UserController.editUserProfile",
   """
      function() {
      return _wA({method:"GET", url:"/editUserProfile"})
      }
   """
)
                                                        
 
// @LINE:50
def sendForgetPassword = JavascriptReverseRoute(
   "controllers.UserController.sendForgetPassword",
   """
      function(emailId) {
      return _wA({method:"POST", url:"/sendForgetPassword/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("emailId", emailId)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:86
// @LINE:82
// @LINE:81
// @LINE:55
// @LINE:54
// @LINE:20
// @LINE:18
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    


 
// @LINE:12
def loginOnScalaJobz = JavascriptReverseRoute(
   "controllers.Application.loginOnScalaJobz",
   """
      function() {
      return _wA({method:"GET", url:"/login"})
      }
   """
)
                                                        
 
// @LINE:81
def errorPage = JavascriptReverseRoute(
   "controllers.Application.errorPage",
   """
      function() {
      return _wA({method:"GET", url:"/errorPage"})
      }
   """
)
                                                        
 
// @LINE:54
def contactUs = JavascriptReverseRoute(
   "controllers.Application.contactUs",
   """
      function() {
      return _wA({method:"GET", url:"/contactUs"})
      }
   """
)
                                                        
 
// @LINE:82
def loginFailureViaSocialNetworks = JavascriptReverseRoute(
   "controllers.Application.loginFailureViaSocialNetworks",
   """
      function() {
      return _wA({method:"GET", url:"/loginFailure"})
      }
   """
)
                                                        
 
// @LINE:10
def newUser = JavascriptReverseRoute(
   "controllers.Application.newUser",
   """
      function(flag) {
      return _wA({method:"POST", url:"/signup/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("flag", flag)})
      }
   """
)
                                                        
 
// @LINE:13
def logIn = JavascriptReverseRoute(
   "controllers.Application.logIn",
   """
      function(flag) {
      return _wA({method:"POST", url:"/login/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("flag", flag)})
      }
   """
)
                                                        
 
// @LINE:18
// @LINE:6
def index = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      if (true) {
      return _wA({method:"GET", url:"/"})
      }
      if (true) {
      return _wA({method:"GET", url:"/findAllJobs"})
      }
      }
   """
)
                                                        
 
// @LINE:9
def signUpOnScalaJobz = JavascriptReverseRoute(
   "controllers.Application.signUpOnScalaJobz",
   """
      function(flag) {
      return _wA({method:"GET", url:"/signup/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("flag", flag)})
      }
   """
)
                                                        
 
// @LINE:86
def jobPagination = JavascriptReverseRoute(
   "controllers.Application.jobPagination",
   """
      function(editFlag) {
      return _wA({method:"GET", url:"/jobPagination/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("editFlag", editFlag)})
      }
   """
)
                                                        
 
// @LINE:55
def contactUsEmail = JavascriptReverseRoute(
   "controllers.Application.contactUsEmail",
   """
      function() {
      return _wA({method:"POST", url:"/contactUsEmail"})
      }
   """
)
                                                        
 
// @LINE:20
def logOutFromScalaJobz = JavascriptReverseRoute(
   "controllers.Application.logOutFromScalaJobz",
   """
      function() {
      return _wA({method:"GET", url:"/logout"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:24
// @LINE:22
// @LINE:16
// @LINE:15
class ReverseJobController {
    


 
// @LINE:34
def findJobPostForEdit = JavascriptReverseRoute(
   "controllers.JobController.findJobPostForEdit",
   """
      function(jobId) {
      return _wA({method:"GET", url:"/findJobPostForEdit/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("jobId", jobId)})
      }
   """
)
                                                        
 
// @LINE:38
def deleteJob = JavascriptReverseRoute(
   "controllers.JobController.deleteJob",
   """
      function(jobId) {
      return _wA({method:"GET", url:"/deleteJob/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("jobId", jobId)})
      }
   """
)
                                                        
 
// @LINE:36
def editJob = JavascriptReverseRoute(
   "controllers.JobController.editJob",
   """
      function(jobId) {
      return _wA({method:"POST", url:"/editJob/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("jobId", jobId)})
      }
   """
)
                                                        
 
// @LINE:16
def newJob = JavascriptReverseRoute(
   "controllers.JobController.newJob",
   """
      function() {
      return _wA({method:"POST", url:"/postAJob"})
      }
   """
)
                                                        
 
// @LINE:22
def findAJob = JavascriptReverseRoute(
   "controllers.JobController.findAJob",
   """
      function(searchString,editFlag) {
      return _wA({method:"POST", url:"/findAJob/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("searchString", searchString) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("editFlag", editFlag)})
      }
   """
)
                                                        
 
// @LINE:24
def findJobDetail = JavascriptReverseRoute(
   "controllers.JobController.findJobDetail",
   """
      function(jobId) {
      return _wA({method:"GET", url:"/jobDetail/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("jobId", jobId)})
      }
   """
)
                                                        
 
// @LINE:15
def postAJob = JavascriptReverseRoute(
   "controllers.JobController.postAJob",
   """
      function() {
      return _wA({method:"GET", url:"/postAJob"})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:89
// @LINE:86
// @LINE:82
// @LINE:81
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:55
// @LINE:54
// @LINE:50
// @LINE:48
// @LINE:44
// @LINE:42
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {

// @LINE:89
class ReverseAssets {
    


 
// @LINE:89
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:65
// @LINE:64
// @LINE:63
class ReverseRESTServiceController {
    


 
// @LINE:65
def processGetJobListForSingleRequest(code:String) = new play.api.mvc.HandlerRef(
   controllers.RESTServiceController.processGetJobListForSingleRequest(code), HandlerDef(this, "controllers.RESTServiceController", "processGetJobListForSingleRequest", Seq(classOf[String]))
)
                              
 
// @LINE:63
def restApi() = new play.api.mvc.HandlerRef(
   controllers.RESTServiceController.restApi(), HandlerDef(this, "controllers.RESTServiceController", "restApi", Seq())
)
                              
 
// @LINE:64
def processGetAllJobsList() = new play.api.mvc.HandlerRef(
   controllers.RESTServiceController.processGetAllJobsList(), HandlerDef(this, "controllers.RESTServiceController", "processGetAllJobsList", Seq())
)
                              

                      
    
}
                            

// @LINE:73
// @LINE:72
class ReverseLinkedInController {
    


 
// @LINE:73
def linkedinCallback() = new play.api.mvc.HandlerRef(
   controllers.LinkedInController.linkedinCallback(), HandlerDef(this, "controllers.LinkedInController", "linkedinCallback", Seq())
)
                              
 
// @LINE:72
def linkedinLogin() = new play.api.mvc.HandlerRef(
   controllers.LinkedInController.linkedinLogin(), HandlerDef(this, "controllers.LinkedInController", "linkedinLogin", Seq())
)
                              

                      
    
}
                            

// @LINE:75
// @LINE:74
class ReverseFacebookController {
    


 
// @LINE:74
def facebookLogin() = new play.api.mvc.HandlerRef(
   controllers.FacebookController.facebookLogin(), HandlerDef(this, "controllers.FacebookController", "facebookLogin", Seq())
)
                              
 
// @LINE:75
def facebookCallback() = new play.api.mvc.HandlerRef(
   controllers.FacebookController.facebookCallback(), HandlerDef(this, "controllers.FacebookController", "facebookCallback", Seq())
)
                              

                      
    
}
                            

// @LINE:77
// @LINE:76
class ReverseGoogleController {
    


 
// @LINE:77
def googleCallback() = new play.api.mvc.HandlerRef(
   controllers.GoogleController.googleCallback(), HandlerDef(this, "controllers.GoogleController", "googleCallback", Seq())
)
                              
 
// @LINE:76
def googleLogin() = new play.api.mvc.HandlerRef(
   controllers.GoogleController.googleLogin(), HandlerDef(this, "controllers.GoogleController", "googleLogin", Seq())
)
                              

                      
    
}
                            

// @LINE:71
// @LINE:70
class ReverseTwitterController {
    


 
// @LINE:71
def twitterCallBack() = new play.api.mvc.HandlerRef(
   controllers.TwitterController.twitterCallBack(), HandlerDef(this, "controllers.TwitterController", "twitterCallBack", Seq())
)
                              
 
// @LINE:70
def twitterLogin() = new play.api.mvc.HandlerRef(
   controllers.TwitterController.twitterLogin(), HandlerDef(this, "controllers.TwitterController", "twitterLogin", Seq())
)
                              

                      
    
}
                            

// @LINE:50
// @LINE:48
// @LINE:44
// @LINE:42
// @LINE:32
// @LINE:30
// @LINE:28
class ReverseUserController {
    


 
// @LINE:48
def forgetPassword() = new play.api.mvc.HandlerRef(
   controllers.UserController.forgetPassword(), HandlerDef(this, "controllers.UserController", "forgetPassword", Seq())
)
                              
 
// @LINE:30
def findJobPostByUserId() = new play.api.mvc.HandlerRef(
   controllers.UserController.findJobPostByUserId(), HandlerDef(this, "controllers.UserController", "findJobPostByUserId", Seq())
)
                              
 
// @LINE:42
def registerJobSeeker(emailId:String, skillsToken:String) = new play.api.mvc.HandlerRef(
   controllers.UserController.registerJobSeeker(emailId, skillsToken), HandlerDef(this, "controllers.UserController", "registerJobSeeker", Seq(classOf[String], classOf[String]))
)
                              
 
// @LINE:32
def updateUserProfile() = new play.api.mvc.HandlerRef(
   controllers.UserController.updateUserProfile(), HandlerDef(this, "controllers.UserController", "updateUserProfile", Seq())
)
                              
 
// @LINE:44
def unSubscribeJobSeeker(userId:String) = new play.api.mvc.HandlerRef(
   controllers.UserController.unSubscribeJobSeeker(userId), HandlerDef(this, "controllers.UserController", "unSubscribeJobSeeker", Seq(classOf[String]))
)
                              
 
// @LINE:28
def editUserProfile() = new play.api.mvc.HandlerRef(
   controllers.UserController.editUserProfile(), HandlerDef(this, "controllers.UserController", "editUserProfile", Seq())
)
                              
 
// @LINE:50
def sendForgetPassword(emailId:String) = new play.api.mvc.HandlerRef(
   controllers.UserController.sendForgetPassword(emailId), HandlerDef(this, "controllers.UserController", "sendForgetPassword", Seq(classOf[String]))
)
                              

                      
    
}
                            

// @LINE:86
// @LINE:82
// @LINE:81
// @LINE:55
// @LINE:54
// @LINE:20
// @LINE:18
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    


 
// @LINE:12
def loginOnScalaJobz() = new play.api.mvc.HandlerRef(
   controllers.Application.loginOnScalaJobz(), HandlerDef(this, "controllers.Application", "loginOnScalaJobz", Seq())
)
                              
 
// @LINE:81
def errorPage() = new play.api.mvc.HandlerRef(
   controllers.Application.errorPage(), HandlerDef(this, "controllers.Application", "errorPage", Seq())
)
                              
 
// @LINE:54
def contactUs() = new play.api.mvc.HandlerRef(
   controllers.Application.contactUs(), HandlerDef(this, "controllers.Application", "contactUs", Seq())
)
                              
 
// @LINE:82
def loginFailureViaSocialNetworks() = new play.api.mvc.HandlerRef(
   controllers.Application.loginFailureViaSocialNetworks(), HandlerDef(this, "controllers.Application", "loginFailureViaSocialNetworks", Seq())
)
                              
 
// @LINE:10
def newUser(flag:String) = new play.api.mvc.HandlerRef(
   controllers.Application.newUser(flag), HandlerDef(this, "controllers.Application", "newUser", Seq(classOf[String]))
)
                              
 
// @LINE:13
def logIn(flag:String) = new play.api.mvc.HandlerRef(
   controllers.Application.logIn(flag), HandlerDef(this, "controllers.Application", "logIn", Seq(classOf[String]))
)
                              
 
// @LINE:6
def index() = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq())
)
                              
 
// @LINE:9
def signUpOnScalaJobz(flag:String) = new play.api.mvc.HandlerRef(
   controllers.Application.signUpOnScalaJobz(flag), HandlerDef(this, "controllers.Application", "signUpOnScalaJobz", Seq(classOf[String]))
)
                              
 
// @LINE:86
def jobPagination(editFlag:String) = new play.api.mvc.HandlerRef(
   controllers.Application.jobPagination(editFlag), HandlerDef(this, "controllers.Application", "jobPagination", Seq(classOf[String]))
)
                              
 
// @LINE:55
def contactUsEmail() = new play.api.mvc.HandlerRef(
   controllers.Application.contactUsEmail(), HandlerDef(this, "controllers.Application", "contactUsEmail", Seq())
)
                              
 
// @LINE:20
def logOutFromScalaJobz() = new play.api.mvc.HandlerRef(
   controllers.Application.logOutFromScalaJobz(), HandlerDef(this, "controllers.Application", "logOutFromScalaJobz", Seq())
)
                              

                      
    
}
                            

// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:24
// @LINE:22
// @LINE:16
// @LINE:15
class ReverseJobController {
    


 
// @LINE:34
def findJobPostForEdit(jobId:String) = new play.api.mvc.HandlerRef(
   controllers.JobController.findJobPostForEdit(jobId), HandlerDef(this, "controllers.JobController", "findJobPostForEdit", Seq(classOf[String]))
)
                              
 
// @LINE:38
def deleteJob(jobId:String) = new play.api.mvc.HandlerRef(
   controllers.JobController.deleteJob(jobId), HandlerDef(this, "controllers.JobController", "deleteJob", Seq(classOf[String]))
)
                              
 
// @LINE:36
def editJob(jobId:String) = new play.api.mvc.HandlerRef(
   controllers.JobController.editJob(jobId), HandlerDef(this, "controllers.JobController", "editJob", Seq(classOf[String]))
)
                              
 
// @LINE:16
def newJob() = new play.api.mvc.HandlerRef(
   controllers.JobController.newJob(), HandlerDef(this, "controllers.JobController", "newJob", Seq())
)
                              
 
// @LINE:22
def findAJob(searchString:String, editFlag:String) = new play.api.mvc.HandlerRef(
   controllers.JobController.findAJob(searchString, editFlag), HandlerDef(this, "controllers.JobController", "findAJob", Seq(classOf[String], classOf[String]))
)
                              
 
// @LINE:24
def findJobDetail(jobId:String) = new play.api.mvc.HandlerRef(
   controllers.JobController.findJobDetail(jobId), HandlerDef(this, "controllers.JobController", "findJobDetail", Seq(classOf[String]))
)
                              
 
// @LINE:15
def postAJob() = new play.api.mvc.HandlerRef(
   controllers.JobController.postAJob(), HandlerDef(this, "controllers.JobController", "postAJob", Seq())
)
                              

                      
    
}
                            
}
                    
                