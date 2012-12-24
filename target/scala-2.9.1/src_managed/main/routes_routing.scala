// @SOURCE:/home/ruchi/knolProj/ScalaJobz/scalajobz-com/conf/routes
// @HASH:8279556617b33cbd8da431885bcc325946852e94
// @DATE:Sun Dec 23 18:48:21 IST 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:9
val controllers_Application_signUpOnScalaJobz1 = Route("GET", PathPattern(List(StaticPart("/signup/"),DynamicPart("flag", """[^/]+"""))))
                    

// @LINE:10
val controllers_Application_newUser2 = Route("POST", PathPattern(List(StaticPart("/signup/"),DynamicPart("flag", """[^/]+"""))))
                    

// @LINE:12
val controllers_Application_loginOnScalaJobz3 = Route("GET", PathPattern(List(StaticPart("/login"))))
                    

// @LINE:13
val controllers_Application_logIn4 = Route("POST", PathPattern(List(StaticPart("/login/"),DynamicPart("flag", """[^/]+"""))))
                    

// @LINE:15
val controllers_JobController_postAJob5 = Route("GET", PathPattern(List(StaticPart("/postAJob"))))
                    

// @LINE:16
val controllers_JobController_newJob6 = Route("POST", PathPattern(List(StaticPart("/postAJob"))))
                    

// @LINE:18
val controllers_Application_index7 = Route("GET", PathPattern(List(StaticPart("/findAllJobs"))))
                    

// @LINE:20
val controllers_Application_logOutFromScalaJobz8 = Route("GET", PathPattern(List(StaticPart("/logout"))))
                    

// @LINE:22
val controllers_JobController_findAJob9 = Route("POST", PathPattern(List(StaticPart("/findAJob/"),DynamicPart("searchString", """[^/]+"""),StaticPart("/"),DynamicPart("editFlag", """[^/]+"""))))
                    

// @LINE:24
val controllers_JobController_findJobDetail10 = Route("GET", PathPattern(List(StaticPart("/jobDetail/"),DynamicPart("jobId", """[^/]+"""))))
                    

// @LINE:28
val controllers_UserController_editUserProfile11 = Route("GET", PathPattern(List(StaticPart("/editUserProfile"))))
                    

// @LINE:30
val controllers_UserController_findJobPostByUserId12 = Route("GET", PathPattern(List(StaticPart("/findJobPostByUserId"))))
                    

// @LINE:32
val controllers_UserController_updateUserProfile13 = Route("POST", PathPattern(List(StaticPart("/updateUserProfile"))))
                    

// @LINE:34
val controllers_JobController_findJobPostForEdit14 = Route("GET", PathPattern(List(StaticPart("/findJobPostForEdit/"),DynamicPart("jobId", """[^/]+"""))))
                    

// @LINE:36
val controllers_JobController_editJob15 = Route("POST", PathPattern(List(StaticPart("/editJob/"),DynamicPart("jobId", """[^/]+"""))))
                    

// @LINE:38
val controllers_JobController_deleteJob16 = Route("GET", PathPattern(List(StaticPart("/deleteJob/"),DynamicPart("jobId", """[^/]+"""))))
                    

// @LINE:42
val controllers_UserController_registerJobSeeker17 = Route("POST", PathPattern(List(StaticPart("/registerJobSeeker/"),DynamicPart("emailId", """[^/]+"""),StaticPart("/"),DynamicPart("skillsToken", """[^/]+"""))))
                    

// @LINE:44
val controllers_UserController_unSubscribeJobSeeker18 = Route("GET", PathPattern(List(StaticPart("/unSubscribeJobSeeker/"),DynamicPart("userId", """[^/]+"""))))
                    

// @LINE:48
val controllers_UserController_forgetPassword19 = Route("GET", PathPattern(List(StaticPart("/forgetPassword"))))
                    

// @LINE:50
val controllers_UserController_sendForgetPassword20 = Route("POST", PathPattern(List(StaticPart("/sendForgetPassword/"),DynamicPart("emailId", """[^/]+"""))))
                    

// @LINE:54
val controllers_Application_contactUs21 = Route("GET", PathPattern(List(StaticPart("/contactUs"))))
                    

// @LINE:55
val controllers_Application_contactUsEmail22 = Route("POST", PathPattern(List(StaticPart("/contactUsEmail"))))
                    

// @LINE:63
val controllers_RESTServiceController_restApi23 = Route("GET", PathPattern(List(StaticPart("/restApi"))))
                    

// @LINE:64
val controllers_RESTServiceController_processGetAllJobsList24 = Route("GET", PathPattern(List(StaticPart("/getAllJobs"))))
                    

// @LINE:65
val controllers_RESTServiceController_processGetJobListForSingleRequest25 = Route("GET", PathPattern(List(StaticPart("/getJobsForACriteria/"),DynamicPart("code", """[^/]+"""))))
                    

// @LINE:70
val controllers_TwitterController_twitterLogin26 = Route("GET", PathPattern(List(StaticPart("/twitter/twitterLogin"))))
                    

// @LINE:71
val controllers_TwitterController_twitterCallBack27 = Route("GET", PathPattern(List(StaticPart("/twitter/callback"))))
                    

// @LINE:72
val controllers_LinkedInController_linkedinLogin28 = Route("GET", PathPattern(List(StaticPart("/linkedin/login"))))
                    

// @LINE:73
val controllers_LinkedInController_linkedinCallback29 = Route("GET", PathPattern(List(StaticPart("/linkedin/callback"))))
                    

// @LINE:74
val controllers_FacebookController_facebookLogin30 = Route("GET", PathPattern(List(StaticPart("/facebook/login"))))
                    

// @LINE:75
val controllers_FacebookController_facebookCallback31 = Route("GET", PathPattern(List(StaticPart("/facebook/callback"))))
                    

// @LINE:76
val controllers_GoogleController_googleLogin32 = Route("GET", PathPattern(List(StaticPart("/google/login"))))
                    

// @LINE:77
val controllers_GoogleController_googleCallback33 = Route("GET", PathPattern(List(StaticPart("/google/callback"))))
                    

// @LINE:81
val controllers_Application_errorPage34 = Route("GET", PathPattern(List(StaticPart("/errorPage"))))
                    

// @LINE:82
val controllers_Application_loginFailureViaSocialNetworks35 = Route("GET", PathPattern(List(StaticPart("/loginFailure"))))
                    

// @LINE:86
val controllers_Application_jobPagination36 = Route("GET", PathPattern(List(StaticPart("/jobPagination/"),DynamicPart("editFlag", """[^/]+"""))))
                    

// @LINE:89
val controllers_Assets_at37 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Application.index"""),("""GET""","""/signup/$flag<[^/]+>""","""controllers.Application.signUpOnScalaJobz(flag:String)"""),("""POST""","""/signup/$flag<[^/]+>""","""controllers.Application.newUser(flag:String)"""),("""GET""","""/login""","""controllers.Application.loginOnScalaJobz"""),("""POST""","""/login/$flag<[^/]+>""","""controllers.Application.logIn(flag:String)"""),("""GET""","""/postAJob""","""controllers.JobController.postAJob"""),("""POST""","""/postAJob""","""controllers.JobController.newJob"""),("""GET""","""/findAllJobs""","""controllers.Application.index"""),("""GET""","""/logout""","""controllers.Application.logOutFromScalaJobz"""),("""POST""","""/findAJob/$searchString<[^/]+>/$editFlag<[^/]+>""","""controllers.JobController.findAJob(searchString:String, editFlag:String)"""),("""GET""","""/jobDetail/$jobId<[^/]+>""","""controllers.JobController.findJobDetail(jobId:String)"""),("""GET""","""/editUserProfile""","""controllers.UserController.editUserProfile"""),("""GET""","""/findJobPostByUserId""","""controllers.UserController.findJobPostByUserId"""),("""POST""","""/updateUserProfile""","""controllers.UserController.updateUserProfile"""),("""GET""","""/findJobPostForEdit/$jobId<[^/]+>""","""controllers.JobController.findJobPostForEdit(jobId:String)"""),("""POST""","""/editJob/$jobId<[^/]+>""","""controllers.JobController.editJob(jobId:String)"""),("""GET""","""/deleteJob/$jobId<[^/]+>""","""controllers.JobController.deleteJob(jobId:String)"""),("""POST""","""/registerJobSeeker/$emailId<[^/]+>/$skillsToken<[^/]+>""","""controllers.UserController.registerJobSeeker(emailId:String, skillsToken:String)"""),("""GET""","""/unSubscribeJobSeeker/$userId<[^/]+>""","""controllers.UserController.unSubscribeJobSeeker(userId:String)"""),("""GET""","""/forgetPassword""","""controllers.UserController.forgetPassword"""),("""POST""","""/sendForgetPassword/$emailId<[^/]+>""","""controllers.UserController.sendForgetPassword(emailId:String)"""),("""GET""","""/contactUs""","""controllers.Application.contactUs"""),("""POST""","""/contactUsEmail""","""controllers.Application.contactUsEmail"""),("""GET""","""/restApi""","""controllers.RESTServiceController.restApi"""),("""GET""","""/getAllJobs""","""controllers.RESTServiceController.processGetAllJobsList"""),("""GET""","""/getJobsForACriteria/$code<[^/]+>""","""controllers.RESTServiceController.processGetJobListForSingleRequest(code:String)"""),("""GET""","""/twitter/twitterLogin""","""controllers.TwitterController.twitterLogin"""),("""GET""","""/twitter/callback""","""controllers.TwitterController.twitterCallBack"""),("""GET""","""/linkedin/login""","""controllers.LinkedInController.linkedinLogin"""),("""GET""","""/linkedin/callback""","""controllers.LinkedInController.linkedinCallback"""),("""GET""","""/facebook/login""","""controllers.FacebookController.facebookLogin"""),("""GET""","""/facebook/callback""","""controllers.FacebookController.facebookCallback"""),("""GET""","""/google/login""","""controllers.GoogleController.googleLogin"""),("""GET""","""/google/callback""","""controllers.GoogleController.googleCallback"""),("""GET""","""/errorPage""","""controllers.Application.errorPage"""),("""GET""","""/loginFailure""","""controllers.Application.loginFailureViaSocialNetworks"""),("""GET""","""/jobPagination/$editFlag<[^/]+>""","""controllers.Application.jobPagination(editFlag:String)"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil))
   }
}
                    

// @LINE:9
case controllers_Application_signUpOnScalaJobz1(params) => {
   call(params.fromPath[String]("flag", None)) { (flag) =>
        invokeHandler(_root_.controllers.Application.signUpOnScalaJobz(flag), HandlerDef(this, "controllers.Application", "signUpOnScalaJobz", Seq(classOf[String])))
   }
}
                    

// @LINE:10
case controllers_Application_newUser2(params) => {
   call(params.fromPath[String]("flag", None)) { (flag) =>
        invokeHandler(_root_.controllers.Application.newUser(flag), HandlerDef(this, "controllers.Application", "newUser", Seq(classOf[String])))
   }
}
                    

// @LINE:12
case controllers_Application_loginOnScalaJobz3(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.loginOnScalaJobz, HandlerDef(this, "controllers.Application", "loginOnScalaJobz", Nil))
   }
}
                    

// @LINE:13
case controllers_Application_logIn4(params) => {
   call(params.fromPath[String]("flag", None)) { (flag) =>
        invokeHandler(_root_.controllers.Application.logIn(flag), HandlerDef(this, "controllers.Application", "logIn", Seq(classOf[String])))
   }
}
                    

// @LINE:15
case controllers_JobController_postAJob5(params) => {
   call { 
        invokeHandler(_root_.controllers.JobController.postAJob, HandlerDef(this, "controllers.JobController", "postAJob", Nil))
   }
}
                    

// @LINE:16
case controllers_JobController_newJob6(params) => {
   call { 
        invokeHandler(_root_.controllers.JobController.newJob, HandlerDef(this, "controllers.JobController", "newJob", Nil))
   }
}
                    

// @LINE:18
case controllers_Application_index7(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil))
   }
}
                    

// @LINE:20
case controllers_Application_logOutFromScalaJobz8(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.logOutFromScalaJobz, HandlerDef(this, "controllers.Application", "logOutFromScalaJobz", Nil))
   }
}
                    

// @LINE:22
case controllers_JobController_findAJob9(params) => {
   call(params.fromPath[String]("searchString", None), params.fromPath[String]("editFlag", None)) { (searchString, editFlag) =>
        invokeHandler(_root_.controllers.JobController.findAJob(searchString, editFlag), HandlerDef(this, "controllers.JobController", "findAJob", Seq(classOf[String], classOf[String])))
   }
}
                    

// @LINE:24
case controllers_JobController_findJobDetail10(params) => {
   call(params.fromPath[String]("jobId", None)) { (jobId) =>
        invokeHandler(_root_.controllers.JobController.findJobDetail(jobId), HandlerDef(this, "controllers.JobController", "findJobDetail", Seq(classOf[String])))
   }
}
                    

// @LINE:28
case controllers_UserController_editUserProfile11(params) => {
   call { 
        invokeHandler(_root_.controllers.UserController.editUserProfile, HandlerDef(this, "controllers.UserController", "editUserProfile", Nil))
   }
}
                    

// @LINE:30
case controllers_UserController_findJobPostByUserId12(params) => {
   call { 
        invokeHandler(_root_.controllers.UserController.findJobPostByUserId, HandlerDef(this, "controllers.UserController", "findJobPostByUserId", Nil))
   }
}
                    

// @LINE:32
case controllers_UserController_updateUserProfile13(params) => {
   call { 
        invokeHandler(_root_.controllers.UserController.updateUserProfile, HandlerDef(this, "controllers.UserController", "updateUserProfile", Nil))
   }
}
                    

// @LINE:34
case controllers_JobController_findJobPostForEdit14(params) => {
   call(params.fromPath[String]("jobId", None)) { (jobId) =>
        invokeHandler(_root_.controllers.JobController.findJobPostForEdit(jobId), HandlerDef(this, "controllers.JobController", "findJobPostForEdit", Seq(classOf[String])))
   }
}
                    

// @LINE:36
case controllers_JobController_editJob15(params) => {
   call(params.fromPath[String]("jobId", None)) { (jobId) =>
        invokeHandler(_root_.controllers.JobController.editJob(jobId), HandlerDef(this, "controllers.JobController", "editJob", Seq(classOf[String])))
   }
}
                    

// @LINE:38
case controllers_JobController_deleteJob16(params) => {
   call(params.fromPath[String]("jobId", None)) { (jobId) =>
        invokeHandler(_root_.controllers.JobController.deleteJob(jobId), HandlerDef(this, "controllers.JobController", "deleteJob", Seq(classOf[String])))
   }
}
                    

// @LINE:42
case controllers_UserController_registerJobSeeker17(params) => {
   call(params.fromPath[String]("emailId", None), params.fromPath[String]("skillsToken", None)) { (emailId, skillsToken) =>
        invokeHandler(_root_.controllers.UserController.registerJobSeeker(emailId, skillsToken), HandlerDef(this, "controllers.UserController", "registerJobSeeker", Seq(classOf[String], classOf[String])))
   }
}
                    

// @LINE:44
case controllers_UserController_unSubscribeJobSeeker18(params) => {
   call(params.fromPath[String]("userId", None)) { (userId) =>
        invokeHandler(_root_.controllers.UserController.unSubscribeJobSeeker(userId), HandlerDef(this, "controllers.UserController", "unSubscribeJobSeeker", Seq(classOf[String])))
   }
}
                    

// @LINE:48
case controllers_UserController_forgetPassword19(params) => {
   call { 
        invokeHandler(_root_.controllers.UserController.forgetPassword, HandlerDef(this, "controllers.UserController", "forgetPassword", Nil))
   }
}
                    

// @LINE:50
case controllers_UserController_sendForgetPassword20(params) => {
   call(params.fromPath[String]("emailId", None)) { (emailId) =>
        invokeHandler(_root_.controllers.UserController.sendForgetPassword(emailId), HandlerDef(this, "controllers.UserController", "sendForgetPassword", Seq(classOf[String])))
   }
}
                    

// @LINE:54
case controllers_Application_contactUs21(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.contactUs, HandlerDef(this, "controllers.Application", "contactUs", Nil))
   }
}
                    

// @LINE:55
case controllers_Application_contactUsEmail22(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.contactUsEmail, HandlerDef(this, "controllers.Application", "contactUsEmail", Nil))
   }
}
                    

// @LINE:63
case controllers_RESTServiceController_restApi23(params) => {
   call { 
        invokeHandler(_root_.controllers.RESTServiceController.restApi, HandlerDef(this, "controllers.RESTServiceController", "restApi", Nil))
   }
}
                    

// @LINE:64
case controllers_RESTServiceController_processGetAllJobsList24(params) => {
   call { 
        invokeHandler(_root_.controllers.RESTServiceController.processGetAllJobsList, HandlerDef(this, "controllers.RESTServiceController", "processGetAllJobsList", Nil))
   }
}
                    

// @LINE:65
case controllers_RESTServiceController_processGetJobListForSingleRequest25(params) => {
   call(params.fromPath[String]("code", None)) { (code) =>
        invokeHandler(_root_.controllers.RESTServiceController.processGetJobListForSingleRequest(code), HandlerDef(this, "controllers.RESTServiceController", "processGetJobListForSingleRequest", Seq(classOf[String])))
   }
}
                    

// @LINE:70
case controllers_TwitterController_twitterLogin26(params) => {
   call { 
        invokeHandler(_root_.controllers.TwitterController.twitterLogin, HandlerDef(this, "controllers.TwitterController", "twitterLogin", Nil))
   }
}
                    

// @LINE:71
case controllers_TwitterController_twitterCallBack27(params) => {
   call { 
        invokeHandler(_root_.controllers.TwitterController.twitterCallBack, HandlerDef(this, "controllers.TwitterController", "twitterCallBack", Nil))
   }
}
                    

// @LINE:72
case controllers_LinkedInController_linkedinLogin28(params) => {
   call { 
        invokeHandler(_root_.controllers.LinkedInController.linkedinLogin, HandlerDef(this, "controllers.LinkedInController", "linkedinLogin", Nil))
   }
}
                    

// @LINE:73
case controllers_LinkedInController_linkedinCallback29(params) => {
   call { 
        invokeHandler(_root_.controllers.LinkedInController.linkedinCallback, HandlerDef(this, "controllers.LinkedInController", "linkedinCallback", Nil))
   }
}
                    

// @LINE:74
case controllers_FacebookController_facebookLogin30(params) => {
   call { 
        invokeHandler(_root_.controllers.FacebookController.facebookLogin, HandlerDef(this, "controllers.FacebookController", "facebookLogin", Nil))
   }
}
                    

// @LINE:75
case controllers_FacebookController_facebookCallback31(params) => {
   call { 
        invokeHandler(_root_.controllers.FacebookController.facebookCallback, HandlerDef(this, "controllers.FacebookController", "facebookCallback", Nil))
   }
}
                    

// @LINE:76
case controllers_GoogleController_googleLogin32(params) => {
   call { 
        invokeHandler(_root_.controllers.GoogleController.googleLogin, HandlerDef(this, "controllers.GoogleController", "googleLogin", Nil))
   }
}
                    

// @LINE:77
case controllers_GoogleController_googleCallback33(params) => {
   call { 
        invokeHandler(_root_.controllers.GoogleController.googleCallback, HandlerDef(this, "controllers.GoogleController", "googleCallback", Nil))
   }
}
                    

// @LINE:81
case controllers_Application_errorPage34(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.errorPage, HandlerDef(this, "controllers.Application", "errorPage", Nil))
   }
}
                    

// @LINE:82
case controllers_Application_loginFailureViaSocialNetworks35(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.loginFailureViaSocialNetworks, HandlerDef(this, "controllers.Application", "loginFailureViaSocialNetworks", Nil))
   }
}
                    

// @LINE:86
case controllers_Application_jobPagination36(params) => {
   call(params.fromPath[String]("editFlag", None)) { (editFlag) =>
        invokeHandler(_root_.controllers.Application.jobPagination(editFlag), HandlerDef(this, "controllers.Application", "jobPagination", Seq(classOf[String])))
   }
}
                    

// @LINE:89
case controllers_Assets_at37(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                