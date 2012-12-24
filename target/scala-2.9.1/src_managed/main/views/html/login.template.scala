
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object login extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Alert,Form[LogInForm],String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(alert: Alert,loginForm: Form[LogInForm],userId:String,flag:String):play.api.templates.Html = {
        _display_ {import helper._ 


Seq[Any](format.raw/*1.69*/("""
"""),_display_(Seq[Any](/*3.2*/main("Welcome to Scalajobz.com",userId,alert)/*3.47*/ {_display_(Seq[Any](format.raw/*3.49*/("""

<div id="homewhite">
	<div class="page">
			<div class="border-center">
				<div class="box">
					<div class="bordered">
						<p class="text-info">Please login into <b>scalajobz.com </b>to post a job.
						 If you don't have a login, it's easy to  <a class="menulink" href="/signup/"""),_display_(Seq[Any](/*11.84*/flag)),format.raw/*11.88*/("""">register</a>. </p>
					</div>


				</div>

				<div class="box">
					<div class="bordered">
						<h2>Sign In Here</h2>
						<form id="login-form" action="/login/"""),_display_(Seq[Any](/*20.45*/flag)),format.raw/*20.49*/("""" method="post">
							<div class="control-group">
								<label class="control-label" for="EmailId">Email</label>
								<div class="controls">
									<input id="EmailId" name="EmailId" type="text" placeholder="Email address" class="input-large"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="Password">Password</label>
								<div class="controls">
									<input id="Password" name="Password" type="password" placeholder="Password" class="input-large"/>
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<input type="submit" value="Sign In" class="btn btn-primary"/>
								</div>
							</div>
							<span class="or-label">OR</span>
							<table style="height: 50px;">
							<tr>
							<td>
							<a onclick ="open_win_login('/google/login',800,400)" href="javascript:void(0);">
								<img class="signInImage" src=""""),_display_(Seq[Any](/*44.40*/routes/*44.46*/.Assets.at("images/google_signin.png"))),format.raw/*44.84*/("""" 
								alt="Google" title="SignIn With Google"/>
							</a>
							<a onclick ="open_win_login('/facebook/login',700,500)" href="javascript:void(0);">
								<img class="signInImage" src=""""),_display_(Seq[Any](/*48.40*/routes/*48.46*/.Assets.at("images/facebook_signin.png"))),format.raw/*48.86*/("""" 
								alt="Facebook" title="SignIn With Facebook"/>
							</a>
							<a onclick ="open_win_login('/linkedin/login',600,400)" href="javascript:void(0);">
								<img class="signInImage" src=""""),_display_(Seq[Any](/*52.40*/routes/*52.46*/.Assets.at("images/linkedin_signin.png"))),format.raw/*52.86*/("""" 
								alt="LinkedIn" title="SignIn With LinkedIn"/>
							</a>
						    <a onclick ="open_win_login('/twitter/twitterLogin',600,400)" href="javascript:void(0);">
						    	<img class="signInImage" src=""""),_display_(Seq[Any](/*56.43*/routes/*56.49*/.Assets.at("images/twitter_bg_signin.png"))),format.raw/*56.91*/("""" 
						    	alt="Twitter" title="SignIn With Twitter"/>
						    </a>
						</td>
						</tr>
						</table>
						<div class="separator-line"></div>
						<a href="/forgetPassword"><h2>Forgot Password?</h2></a>
						</form>
					</div>
				</div>
			</div>
	</div>
</div>


""")))})),format.raw/*72.2*/(""" """))}
    }
    
    def render(alert:Alert,loginForm:Form[LogInForm],userId:String,flag:String) = apply(alert,loginForm,userId,flag)
    
    def f:((Alert,Form[LogInForm],String,String) => play.api.templates.Html) = (alert,loginForm,userId,flag) => apply(alert,loginForm,userId,flag)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/login.scala.html
                    HASH: b3d8e5090c59018c6160499182fb468fa64052f3
                    MATRIX: 534->1|695->68|731->88|784->133|823->135|1147->423|1173->427|1378->596|1404->600|2391->1551|2406->1557|2466->1595|2697->1790|2712->1796|2774->1836|3009->2035|3024->2041|3086->2081|3333->2292|3348->2298|3412->2340|3723->2620
                    LINES: 19->1|23->1|24->3|24->3|24->3|32->11|32->11|41->20|41->20|65->44|65->44|65->44|69->48|69->48|69->48|73->52|73->52|73->52|77->56|77->56|77->56|93->72
                    -- GENERATED --
                */
            