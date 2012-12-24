
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
object errorPage extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message:String):play.api.templates.Html = {
        _display_ {import models.Alert 


Seq[Any](format.raw/*1.18*/(""" 
"""),_display_(Seq[Any](/*3.2*/main("Welcome to Scalajobz.com","",Alert("",""))/*3.50*/ {_display_(Seq[Any](format.raw/*3.52*/("""

<div id="homewhite">
	<div class="page">
		<h1>Error 404</h1>
		<!--	<h3 class="alert-error">"""),_display_(Seq[Any](/*8.33*/message)),format.raw/*8.40*/("""</h3> -->

	</div>
	<span style=""><img src=""""),_display_(Seq[Any](/*11.28*/routes/*11.34*/.Assets.at("404.jpg"))),format.raw/*11.55*/("""" alt="There Is Some Error"/></span>
	<br/><br/>
	
	<a href="/findAllJobs" class="offset1">Back To Home Page</a>
	 
</div>


""")))})),format.raw/*19.2*/("""
"""))}
    }
    
    def render(message:String) = apply(message)
    
    def f:((String) => play.api.templates.Html) = (message) => apply(message)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/errorPage.scala.html
                    HASH: 921bee8f02d00e92a87a96373b94ce83e2718cb4
                    MATRIX: 509->1|623->17|660->42|716->90|755->92|886->188|914->195|996->241|1011->247|1054->268|1211->394
                    LINES: 19->1|23->1|24->3|24->3|24->3|29->8|29->8|32->11|32->11|32->11|40->19
                    -- GENERATED --
                */
            