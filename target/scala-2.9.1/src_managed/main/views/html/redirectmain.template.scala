
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
object redirectmain extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(userId:String ,status:String):play.api.templates.Html = {
        _display_ {import models.Alert 


Seq[Any](format.raw/*1.32*/(""" 
"""),_display_(Seq[Any](/*3.2*/main("Welcome to Scalajobz.com",userId,Alert("",""))/*3.54*/ {_display_(Seq[Any](format.raw/*3.56*/("""

<body onload="redirect('"""),_display_(Seq[Any](/*5.26*/status)),format.raw/*5.32*/("""');"></body>

<script type="text/javascript">
	function redirect(status) """),format.raw("""{"""),format.raw/*8.29*/("""
		if (status == "success")
			"""),format.raw("""{"""),format.raw/*10.5*/("""
			close_win('http://localhost:9000/findAllJobs');
			"""),format.raw("""}"""),format.raw/*12.5*/("""
		else
			"""),format.raw("""{"""),format.raw/*14.5*/("""
			close_win('http://localhost:9000/loginFailure');
			"""),format.raw("""}"""),format.raw/*16.5*/("""
	"""),format.raw("""}"""),format.raw/*17.3*/("""
</script>
""")))})),format.raw/*19.2*/("""
"""))}
    }
    
    def render(userId:String,status:String) = apply(userId,status)
    
    def f:((String,String) => play.api.templates.Html) = (userId,status) => apply(userId,status)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Dec 24 11:21:11 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/redirectmain.scala.html
                    HASH: 7ba442ac2bda45f6c93923016759f07e1c4ade6c
                    MATRIX: 519->1|647->31|684->56|744->108|783->110|845->137|872->143|992->217|1070->249|1172->305|1230->317|1333->374|1382->377|1425->389
                    LINES: 19->1|23->1|24->3|24->3|24->3|26->5|26->5|29->8|31->10|33->12|35->14|37->16|38->17|40->19
                    -- GENERATED --
                */
            