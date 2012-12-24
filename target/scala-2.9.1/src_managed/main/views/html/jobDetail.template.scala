
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
object jobDetail extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[JobEntity,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(job:JobEntity,userId:String):play.api.templates.Html = {
        _display_ {import java.text.SimpleDateFormat

import models.Alert

def /*5.2*/format_date/*5.13*/(date: java.util.Date) = {{
	new SimpleDateFormat("MMM dd yyyy").format(date)
}};
Seq[Any](format.raw/*1.31*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*7.2*/("""
		
"""),_display_(Seq[Any](/*9.2*/main("Welcome to Scalajobz.com",userId,Alert("",""))/*9.54*/ {_display_(Seq[Any](format.raw/*9.56*/("""
   
    <div id="homewhite">
	<div class="page">
	<div id="jobheading">"""),_display_(Seq[Any](/*13.24*/job/*13.27*/.position)),format.raw/*13.36*/(""" - """),_display_(Seq[Any](/*13.40*/job/*13.43*/.location)),format.raw/*13.52*/("""</div>
				
					<Strong>Company:</Strong>
				
					<h3>"""),_display_(Seq[Any](/*17.11*/job/*17.14*/.company)),format.raw/*17.22*/("""</h3>
				
					<Strong>JobType:</Strong>
				
					<h3>"""),_display_(Seq[Any](/*21.11*/job/*21.14*/.jobType)),format.raw/*21.22*/("""</h3>
					
					<Strong>Date Posted:</Strong>
				
					<h3>"""),_display_(Seq[Any](/*25.11*/format_date(job.datePosted))),format.raw/*25.38*/("""</h3>
					
					"""),_display_(Seq[Any](/*27.7*/if(job.userId == None)/*27.29*/{_display_(Seq[Any](format.raw/*27.30*/("""
					
						<Strong>Link To Apply:</Strong>
						<a href=""""),_display_(Seq[Any](/*30.17*/job/*30.20*/.emailAddress)),format.raw/*30.33*/(""""><h5><u>"""),_display_(Seq[Any](/*30.43*/job/*30.46*/.emailAddress)),format.raw/*30.59*/("""</u></h5></a>
					
					""")))}/*32.7*/else/*32.11*/{_display_(Seq[Any](format.raw/*32.12*/("""
				
						<Strong>Email Address To Apply:</Strong>
						<a href="mailto:"""),_display_(Seq[Any](/*35.24*/job/*35.27*/.emailAddress)),format.raw/*35.40*/(""""><h3><u>"""),_display_(Seq[Any](/*35.50*/job/*35.53*/.emailAddress)),format.raw/*35.66*/("""</u></h3></a>
					""")))})),format.raw/*36.7*/("""
				
			<div class="separator-line"></div>
			<p>	"""),_display_(Seq[Any](/*39.9*/job/*39.12*/.description)),format.raw/*39.24*/("""</p>
				
		
	</div>
</div>
    
    
""")))})))}
    }
    
    def render(job:JobEntity,userId:String) = apply(job,userId)
    
    def f:((JobEntity,String) => play.api.templates.Html) = (job,userId) => apply(job,userId)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/jobDetail.scala.html
                    HASH: f539c8caf352babdd81d3586edd705efe4f553e1
                    MATRIX: 519->1|663->89|682->100|791->30|818->87|845->179|884->184|944->236|983->238|1092->311|1104->314|1135->323|1175->327|1187->330|1218->339|1312->397|1324->400|1354->408|1447->465|1459->468|1489->476|1587->538|1636->565|1689->583|1720->605|1759->606|1856->667|1868->670|1903->683|1949->693|1961->696|1996->709|2040->735|2053->739|2092->740|2204->816|2216->819|2251->832|2297->842|2309->845|2344->858|2395->878|2482->930|2494->933|2528->945
                    LINES: 19->1|24->5|24->5|27->1|28->4|29->7|31->9|31->9|31->9|35->13|35->13|35->13|35->13|35->13|35->13|39->17|39->17|39->17|43->21|43->21|43->21|47->25|47->25|49->27|49->27|49->27|52->30|52->30|52->30|52->30|52->30|52->30|54->32|54->32|54->32|57->35|57->35|57->35|57->35|57->35|57->35|58->36|61->39|61->39|61->39
                    -- GENERATED --
                */
            