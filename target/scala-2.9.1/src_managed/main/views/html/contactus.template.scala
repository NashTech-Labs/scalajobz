
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
object contactus extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[ContactUsForm],String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(contactUsForm: Form[ContactUsForm],userId:String):play.api.templates.Html = {
        _display_ {import models.Alert 


Seq[Any](format.raw/*1.52*/(""" 
"""),_display_(Seq[Any](/*3.2*/main("Welcome to Scalajobz.com",userId,Alert("",""))/*3.54*/ {_display_(Seq[Any](format.raw/*3.56*/("""

<div id="homewhite">
	<div class="page">
		<h1>Lets get in touch </h1>
		<div class="border-center">
			<div class="box-post">
				<div class="bordered">
					<form id="contactus-form" class="form-horizontal" action="/contactUsEmail" method="post">
						<table>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Name">Name</label>
										<div class="controls">
											<input id="Name" name="Name" type="text" placeholder="Name" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
						
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="EmailAddress">Email Address</label>
										<div class="controls">
											<input id="EmailAddress" name="EmailAddress" type="text"
												placeholder="Email Address" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Subject">Subject</label>
										<div class="controls">
											<input id="Subject" name="Subject" type="text"
												placeholder="Subject" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Message">Message</label>
										<div class="controls">
											<textarea id="Message" name="Message" rows="6"  style="resize: none;"
												placeholder="Message" class="input-tlarge"></textarea>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<div class="controls">
											<input type="submit" value="Send Message" class="btn btn-primary" />
											<img id="ajaxImageCtrl" src=""""),_display_(Seq[Any](/*62.42*/routes/*62.48*/.Assets.at("/images/load.gif"))),format.raw/*62.78*/("""" style="display: none;" />
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

<script type="text/javascript">
	$('#contactus-form').submit(function(e) """),format.raw("""{"""),format.raw/*76.43*/("""
		var name = $("#Name").val();
		var emailAddress = $("#EmailAddress").val();
		var message = $("#Message").val();
		var subject = $("#Subject").val();
		if (!name == "" && !emailAddress == ""&& !message == "" && !subject == "" && validateEmail(emailAddress)) """),format.raw("""{"""),format.raw/*81.110*/("""
			document.getElementById("ajaxImageCtrl").style.display = 'inline'	
		"""),format.raw("""}"""),format.raw/*83.4*/("""
	"""),format.raw("""}"""),format.raw/*84.3*/(""");
	</script>
""")))})),format.raw/*86.2*/("""
"""))}
    }
    
    def render(contactUsForm:Form[ContactUsForm],userId:String) = apply(contactUsForm,userId)
    
    def f:((Form[ContactUsForm],String) => play.api.templates.Html) = (contactUsForm,userId) => apply(contactUsForm,userId)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/contactus.scala.html
                    HASH: 76f377c0fc9ce628ab97680c7cba739884ba223e
                    MATRIX: 529->1|677->51|714->76|774->128|813->130|2723->2004|2738->2010|2790->2040|3073->2276|3383->2538|3503->2612|3552->2615|3598->2630
                    LINES: 19->1|23->1|24->3|24->3|24->3|83->62|83->62|83->62|97->76|102->81|104->83|105->84|107->86
                    -- GENERATED --
                */
            