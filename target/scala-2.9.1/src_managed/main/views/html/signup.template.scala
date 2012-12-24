
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
object signup extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Alert,Form[SignUpForm],String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(alert: Alert,signUpForm: Form[SignUpForm],userId:String,flag:String):play.api.templates.Html = {
        _display_ {import helper._ 


Seq[Any](format.raw/*1.71*/(""" 
"""),_display_(Seq[Any](/*3.2*/main("Welcome to ScalaJobz.com",userId,alert)/*3.47*/ {_display_(Seq[Any](format.raw/*3.49*/("""


<div id="homewhite">
	<div class="page">
		<div class="border-center">
			<div class="box">
				<div class="bordered">
					<p class="text-info">
						It's very easy to register with <b>scalajobz.com </b>
					</p>
				</div>
			</div>

			<div class="box">
				<div class="bordered">
					<h2 class="box-heading">New User</h2>
					<form id="signup-form" action="/signup/"""),_display_(Seq[Any](/*20.46*/flag)),format.raw/*20.50*/("""" method="post">
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
							<label class="control-label" for="ConfirmPassword">Confirm Password</label>
							<div class="controls">
								<input name="ConfirmPassword" type="password" id="ConfirmPassword"	placeholder="Confirm Password" class="input-large"/>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="submit" value="Sign Up" class="btn btn-primary" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

""")))})),format.raw/*51.2*/("""
"""))}
    }
    
    def render(alert:Alert,signUpForm:Form[SignUpForm],userId:String,flag:String) = apply(alert,signUpForm,userId,flag)
    
    def f:((Alert,Form[SignUpForm],String,String) => play.api.templates.Html) = (alert,signUpForm,userId,flag) => apply(alert,signUpForm,userId,flag)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/signup.scala.html
                    HASH: 777e7e95958c878945d7c7f024861c4f89f238ba
                    MATRIX: 536->1|699->70|736->91|789->136|828->138|1241->515|1267->519|2365->1586
                    LINES: 19->1|23->1|24->3|24->3|24->3|41->20|41->20|72->51
                    -- GENERATED --
                */
            