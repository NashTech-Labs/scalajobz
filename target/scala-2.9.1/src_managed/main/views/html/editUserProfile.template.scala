
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
object editUserProfile extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Alert,UserEntity,Form[EditUserProfileForm],String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(alert: Alert,userProfile:UserEntity, editUserProfileForm: Form[EditUserProfileForm], userId:String):play.api.templates.Html = {
        _display_ {import helper._


Seq[Any](format.raw/*1.102*/(""" 
"""),_display_(Seq[Any](/*3.2*/main("Welcome to ScalaJobz.com",userId,alert)/*3.47*/ {_display_(Seq[Any](format.raw/*3.49*/("""

<div id="homewhite">
	<div class="page">
		<h1>Edit Profile</h1>
		<div class="border-center">
			<div class="box-post">
				<div class="bordered">
					<form id="editProfile-form" class="form-horizontal" action="/updateUserProfile" method="post">
						<table>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Email">Email</label>
										<div class="controls">
											<input id="Email" readonly="readonly" name="Email" type="text" value=""""),_display_(Seq[Any](/*18.83*/userProfile/*18.94*/.emailId)),format.raw/*18.102*/("""" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="CurrentPassword"> Current Password
											</label>
										<div class="controls">
											<input id="CurrentPassword" name="CurrentPassword" type="password" placeholder="Current Password" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="NewPassword"> New Password
											</label>
										<div class="controls">
											<input id="NewPassword" name="NewPassword" type="password" placeholder="New Password" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="ConfirmPassword"> Confirm Password
											</label>
										<div class="controls">
											<input id="ConfirmPassword" name="ConfirmPassword" type="password" placeholder="Confirm Password" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							
							<tr>
								<td>
									<div class="control-group">
										<div class="controls">
											<input type="submit" value="Save" class="btn btn-primary" />
											<a href="/findJobPostByUserId"><input type="button" value="Cancel" class="btn btn-primary"/> </a>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>


			</div>


		</div>
	</div>
</div>
""")))})),format.raw/*78.2*/("""
"""))}
    }
    
    def render(alert:Alert,userProfile:UserEntity,editUserProfileForm:Form[EditUserProfileForm],userId:String) = apply(alert,userProfile,editUserProfileForm,userId)
    
    def f:((Alert,UserEntity,Form[EditUserProfileForm],String) => play.api.templates.Html) = (alert,userProfile,editUserProfileForm,userId) => apply(alert,userProfile,editUserProfileForm,userId)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/editUserProfile.scala.html
                    HASH: c73ba1d15761c0bdd91092d2708e346ddfe54919
                    MATRIX: 558->1|752->101|789->121|842->166|881->168|1423->674|1443->685|1474->693|3146->2334
                    LINES: 19->1|23->1|24->3|24->3|24->3|39->18|39->18|39->18|99->78
                    -- GENERATED --
                */
            