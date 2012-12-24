
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
object forgetPassword extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Alert,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(alert: Alert):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.16*/(""" 
"""),_display_(Seq[Any](/*2.2*/main("Welcome to ScalaJobz.com","",alert)/*2.43*/ {_display_(Seq[Any](format.raw/*2.45*/("""

<div id="homewhite">
	<div class="page">
		<h1>Recover Password</h1>
		<div class="border-center">
			<div class="box-post">
				<div class="bordered">
					<form id="forgetPassword_form" class="form-inline">
						<div class="control-group">
							<label class="control-label" for="EmailId">Email:</label>
							<div class="controls">
								<input id="EmailId" name="EmailId" type="text"
									placeholder="Email address" class="input-xlarge" />
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button id="recoverpassword" type="submit"
									class="btn btn-primary">Recover Password</button>
								<img id="ajaxImageCtrl" src=""""),_display_(Seq[Any](/*22.39*/routes/*22.45*/.Assets.at("/images/load.gif"))),format.raw/*22.75*/("""" style="display: none;" />
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="modal" class="modal hide fade">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<div id="headerMsg"></div>
	</div>

	<div class="modal-body">
		<div id="msg"></div>
	</div>

	<div class="modal-footer">
	    <input type="hidden" id="sendPassword" value="false">
		<button id="Ok" class="btn btn-primary" data-dismiss="modal">OK</button>
	</div>
</div>

<script src=""""),_display_(Seq[Any](/*50.15*/routes/*50.21*/.Assets.at("javascripts/bootstrap.min.js"))),format.raw/*50.63*/("""" type="text/javascript"></script>
<script type="text/javascript">
	$('#forgetPassword_form').submit(function(e) """),format.raw("""{"""),format.raw/*52.48*/("""
						var idToGet = $("#EmailId").val();
						if (!idToGet == "" && validateEmail(idToGet)) """),format.raw("""{"""),format.raw/*54.54*/("""
							document.getElementById("ajaxImageCtrl").style.display = 'inline'
							$
									.ajax("""),format.raw("""{"""),format.raw/*57.17*/("""
										type : 'POST',
										url : '"""),_display_(Seq[Any](/*59.19*/routes/*59.25*/.UserController.sendForgetPassword("' + idToGet + '"))),format.raw/*59.78*/("""',
										success : function(data) """),format.raw("""{"""),format.raw/*60.37*/("""
											if (data == "false") """),format.raw("""{"""),format.raw/*61.34*/("""
												document.getElementById("ajaxImageCtrl").style.display = 'none'
												document.getElementById("headerMsg").innerHTML = '<h3 id="myModalLabel" class="alert-error">Failure</h3>'
												document.getElementById("msg").innerHTML = '<h3>No User Found with this EmailId !</h3>'
												$("#modal").modal("show");
											"""),format.raw("""}"""),format.raw/*66.13*/(""" else """),format.raw("""{"""),format.raw/*66.20*/("""
												document.getElementById("sendPassword").value = 'true';
												document.getElementById("headerMsg").innerHTML = '<h3 id="myModalLabel" class="alert-success">Success</h3>'
												document.getElementById("msg").innerHTML = '<h3>Password has been sent to your emailId !</h3>'
											    $("#modal").modal("show");
												
											"""),format.raw("""}"""),format.raw/*72.13*/("""
										"""),format.raw("""}"""),format.raw/*73.12*/("""
									"""),format.raw("""}"""),format.raw/*74.11*/(""");
							return false;
						"""),format.raw("""}"""),format.raw/*76.8*/("""
					"""),format.raw("""}"""),format.raw/*77.7*/(""");
	
	$('#Ok').click(function(e) """),format.raw("""{"""),format.raw/*79.30*/("""
		var flag = $("#sendPassword").val();
		if(flag == "true")
			window.location = "/login"
	"""),format.raw("""}"""),format.raw/*83.3*/(""");
</script>
""")))})),format.raw/*85.2*/("""
"""))}
    }
    
    def render(alert:Alert) = apply(alert)
    
    def f:((Alert) => play.api.templates.Html) = (alert) => apply(alert)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/forgetPassword.scala.html
                    HASH: 61e243f66ce0280fd922b716ec8417f1571113ae
                    MATRIX: 513->1|604->15|641->18|690->59|729->61|1460->756|1475->762|1527->792|2106->1335|2121->1341|2185->1383|2346->1497|2488->1592|2634->1691|2714->1735|2729->1741|2804->1794|2890->1833|2971->1867|3364->2213|3418->2220|3828->2583|3887->2595|3945->2606|4022->2637|4075->2644|4156->2678|4295->2771|4340->2785
                    LINES: 19->1|22->1|23->2|23->2|23->2|43->22|43->22|43->22|71->50|71->50|71->50|73->52|75->54|78->57|80->59|80->59|80->59|81->60|82->61|87->66|87->66|93->72|94->73|95->74|97->76|98->77|100->79|104->83|106->85
                    -- GENERATED --
                */
            