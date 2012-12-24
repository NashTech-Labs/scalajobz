
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
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[String,String,Alert,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String,userId:String,alert: Alert)(content: Html):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.59*/("""

<!DOCTYPE html>
<html lang="en">
<head>
<title>"""),_display_(Seq[Any](/*6.9*/title)),format.raw/*6.14*/("""</title>


<link rel="stylesheet" href=""""),_display_(Seq[Any](/*9.31*/routes/*9.37*/.Assets.at("stylesheets/main.css"))),format.raw/*9.71*/("""" />
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*10.31*/routes/*10.37*/.Assets.at("stylesheets/font.css"))),format.raw/*10.71*/("""" />
<link rel="shortcut icon" href=""""),_display_(Seq[Any](/*11.34*/routes/*11.40*/.Assets.at("images/favicon.ico"))),format.raw/*11.72*/("""" type="image/x-icon" />

<script src=""""),_display_(Seq[Any](/*13.15*/routes/*13.21*/.Assets.at("javascripts/jquery-1.7.1.min.js"))),format.raw/*13.66*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*14.15*/routes/*14.21*/.Assets.at("javascripts/jquery.rotator.js"))),format.raw/*14.64*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*15.15*/routes/*15.21*/.Assets.at("javascripts/jquery.validate.js"))),format.raw/*15.65*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*16.15*/routes/*16.21*/.Assets.at("javascripts/bootstrap-rowlink.js"))),format.raw/*16.67*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*17.15*/routes/*17.21*/.Assets.at("javascripts/validationScript.js"))),format.raw/*17.66*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*18.15*/routes/*18.21*/.Assets.at("javascripts/bootstrap-alert.js"))),format.raw/*18.65*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*19.15*/routes/*19.21*/.Assets.at("javascripts/bootstrap-dropdown.js"))),format.raw/*19.68*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*20.15*/routes/*20.21*/.Assets.at("javascripts/pagination.js"))),format.raw/*20.60*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*21.15*/routes/*21.21*/.Assets.at("javascripts/bootbox.js"))),format.raw/*21.57*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*22.15*/routes/*22.21*/.Assets.at("javascripts/bootstrap-tooltip.js"))),format.raw/*22.67*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*23.15*/routes/*23.21*/.Assets.at("javascripts/bootstrap-popover.js"))),format.raw/*23.67*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*24.15*/routes/*24.21*/.Assets.at("javascripts/jquery.tablesorter.js"))),format.raw/*24.68*/("""" type="text/javascript"></script>

<script type="text/javascript">
window.setTimeout(function() """),format.raw("""{"""),format.raw/*27.31*/(""" $(".alert").alert('close'); """),format.raw("""}"""),format.raw/*27.61*/(""", 5000);
function open_win(url)
"""),format.raw("""{"""),format.raw/*29.2*/("""
	window.open(url)
	
"""),format.raw("""}"""),format.raw/*32.2*/("""
function open_win_login(url,w,h)
"""),format.raw("""{"""),format.raw/*34.2*/("""
	          wleft = (screen.width - w) / 2;
	 	      wtop = (screen.height - h) / 2;
	 	      window.open(url, name,
	                               'width=' + w + ', height=' + h + ', ' +
	 	                           'left=' + wleft + ', top=' + wtop + ', ' +
		                           'location=no, menubar=no, ' +
	 	                           'status=no, toolbar=no, scrollbars=no, resizable=no');
	 	     						window.resizeTo(w, h);
	 	    						window.moveTo(wleft, wtop);
	 	  							window.focus();
	
"""),format.raw("""}"""),format.raw/*46.2*/("""
function close_win(redirectUrl)
"""),format.raw("""{"""),format.raw/*48.2*/("""
	window.opener.location=redirectUrl
	window.opener.focus();
	window.close();
"""),format.raw("""}"""),format.raw/*52.2*/("""
</script>


</head>
<body>

	<div class="wrap">

		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">

					<div class="pull-right">
						<ul class="nav">
							<li><a class="menulink" href="/findAllJobs">All Jobs</a></li>
							<li><a class="menulink" href="/postAJob">Post a job</a></li>
							"""),_display_(Seq[Any](/*69.9*/if(userId != "")/*69.25*/ {_display_(Seq[Any](format.raw/*69.27*/("""
						
							<li class="dropdown">  
          						<a class="menulink" data-toggle="dropdown" href="#">Edit<b class="caret"></b></a>  
          						<ul class="dropdown-menu">  
            						<li><a href="/editUserProfile" onclick="return """),_display_(Seq[Any](/*74.67*/User/*74.71*/.isUserLoginViaScalaJobz(userId))),format.raw/*74.103*/("""">Edit Your Profile</a></li>  
            						<li style="margin-top: -10px;"><a href="/findJobPostByUserId">Edit Your Jobs</a></li>  
         					 	</ul>  
        					</li>
								<li><a class="menulink" href="/logout ">Sign Out&nbsp;<span style="text-transform:lowercase;">("""),_display_(Seq[Any](/*78.105*/User/*78.109*/.findUserById(userId).get.emailId)),format.raw/*78.142*/(""")</span></a></li>
							""")))}/*79.10*/else/*79.14*/{_display_(Seq[Any](format.raw/*79.15*/("""
							<li><a class="menulink" href="/login">Sign In</a></li> 
							""")))})),format.raw/*81.9*/("""

						</ul>
					</div>
					<div class="pull-left">
						<a href="/"><img alt="Scala Jobz" 
									src="http://knoldus.files.wordpress.com/2012/11/scjobs.png"/></a>
					</div>
				</div>
			</div>
		</div>




		<div id="main" class="container">
		"""),_display_(Seq[Any](/*97.4*/if(alert.alertType != "")/*97.29*/{_display_(Seq[Any](format.raw/*97.30*/("""
   		<div class="alert alert-"""),_display_(Seq[Any](/*98.31*/alert/*98.36*/.alertType)),format.raw/*98.46*/("""">  
  			<a class="close" data-dismiss="alert">×</a>  
  			<strong style="text-transform: capitalize;">"""),_display_(Seq[Any](/*100.51*/alert/*100.56*/.alertType)),format.raw/*100.66*/(""" ! </strong>"""),_display_(Seq[Any](/*100.79*/alert/*100.84*/.message)),format.raw/*100.92*/("""  
		</div> 
		""")))})),format.raw/*102.4*/("""
		
		"""),_display_(Seq[Any](/*104.4*/content)),format.raw/*104.11*/("""</div>

		<div id="footer">
			<div id="logos">
			<div class="pull-left">
					  <a onclick=open_win('http://www.facebook.com/JobzInScala') href="#"><img src="http://knoldus.files.wordpress.com/2012/12/facebook.png"  alt="facebook" title="facebook"/></a>
                      <a onclick=open_win('http://twitter.com/scalajobz') href="#"><img src="http://knoldus.files.wordpress.com/2012/12/twitter.png"  alt="twitter" title="twitter"/></a>
                      <a onclick=open_win('http://in.linkedin.com/in/scalajobz') href="#"><img src="http://knoldus.files.wordpress.com/2012/12/linkedin.png"  alt="linkedin" title="linkedin"/></a>
			</div>
				<div class="pull-right">
					<a onclick=open_win('http://typesafe.com/') href="#">Powered 0n &nbsp;
					<img src=""""),_display_(Seq[Any](/*115.17*/routes/*115.23*/.Assets.at("images/typesafe.png"))),format.raw/*115.56*/("""" style="margin-top: -1px;"
								alt="Typesafe Stack" title="Typesafe Stack"/>
					</a>
					<a href="/restApi">API</a>
					<a onclick=open_win('http://www.knoldus.com') href="#">Community Initiative by <b>Knoldus</b></a>
					<a href="/contactUs">Contact Us </a>
				</div>
			</div>
		</div>

	</div>

</body>
</html>
"""))}
    }
    
    def render(title:String,userId:String,alert:Alert,content:Html) = apply(title,userId,alert)(content)
    
    def f:((String,String,Alert) => (Html) => play.api.templates.Html) = (title,userId,alert) => (content) => apply(title,userId,alert)(content)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Dec 24 11:18:28 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/main.scala.html
                    HASH: aa54aa45cbe289e39ee8a41ea51e768457e9c712
                    MATRIX: 522->1|656->58|740->108|766->113|842->154|856->160|911->194|982->229|997->235|1053->269|1127->307|1142->313|1196->345|1272->385|1287->391|1354->436|1439->485|1454->491|1519->534|1604->583|1619->589|1685->633|1770->682|1785->688|1853->734|1938->783|1953->789|2020->834|2105->883|2120->889|2186->933|2271->982|2286->988|2355->1035|2440->1084|2455->1090|2516->1129|2601->1178|2616->1184|2674->1220|2759->1269|2774->1275|2842->1321|2927->1370|2942->1376|3010->1422|3095->1471|3110->1477|3179->1524|3324->1622|3401->1652|3480->1685|3548->1707|3629->1742|4190->2257|4270->2291|4395->2370|4777->2717|4802->2733|4842->2735|5128->2985|5141->2989|5196->3021|5517->3305|5531->3309|5587->3342|5632->3369|5645->3373|5684->3374|5787->3446|6078->3702|6112->3727|6151->3728|6218->3759|6232->3764|6264->3774|6407->3880|6422->3885|6455->3895|6505->3908|6520->3913|6551->3921|6599->3937|6642->3944|6672->3951|7478->4720|7494->4726|7550->4759
                    LINES: 19->1|22->1|27->6|27->6|30->9|30->9|30->9|31->10|31->10|31->10|32->11|32->11|32->11|34->13|34->13|34->13|35->14|35->14|35->14|36->15|36->15|36->15|37->16|37->16|37->16|38->17|38->17|38->17|39->18|39->18|39->18|40->19|40->19|40->19|41->20|41->20|41->20|42->21|42->21|42->21|43->22|43->22|43->22|44->23|44->23|44->23|45->24|45->24|45->24|48->27|48->27|50->29|53->32|55->34|67->46|69->48|73->52|90->69|90->69|90->69|95->74|95->74|95->74|99->78|99->78|99->78|100->79|100->79|100->79|102->81|118->97|118->97|118->97|119->98|119->98|119->98|121->100|121->100|121->100|121->100|121->100|121->100|123->102|125->104|125->104|136->115|136->115|136->115
                    -- GENERATED --
                */
            