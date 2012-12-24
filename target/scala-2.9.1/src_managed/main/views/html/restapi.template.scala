
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
object restapi extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(userId:String):play.api.templates.Html = {
        _display_ {import models.Alert 

import models.Common


Seq[Any](format.raw/*1.17*/(""" 
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/main("Welcome to Scalajobz.com",userId,Alert("",""))/*5.54*/ {_display_(Seq[Any](format.raw/*5.56*/("""

<div id="homewhite">
	<div class="page">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Summary</th>
					<th>Search Jobs</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2"><h2 style="text-align: center;">To Get
							All The Jobs</h2></td>
				</tr>
				<tr>
					<td>REST Endpoint(URL)</td>
					<td>http://Host:Port/URL</td>
				</tr>
				<tr>
					<td>HTTP Method</td>
					<td>GET</td>
				</tr>
				<tr>
					<td>Request Example</td>
					<td><a href="http://"""),_display_(Seq[Any](/*31.27*/Common/*31.33*/.getContextUrl)),format.raw/*31.47*/("""/getAllJobs"><u>http://"""),_display_(Seq[Any](/*31.71*/Common/*31.77*/.getContextUrl)),format.raw/*31.91*/("""/getAllJobs</u></a></td>
				</tr>
				<tr>
					<td>Response Example</td>
					<td>["""),format.raw("""{"""),format.raw/*35.12*/("""<br /> "id":"""),format.raw("""{"""),format.raw/*35.25*/(""""id":"50b2403884ae8fe92ed4474a""""),format.raw("""}"""),format.raw/*35.57*/(""",<br />
						"userId":"""),format.raw("""{"""),format.raw/*36.17*/(""""id":"50b23fd984ae8fe92ed44748""""),format.raw("""}"""),format.raw/*36.49*/(""",<br /> "position":"Java
						Developer",<br /> "company":"Knoldus Software",<br />
						"location":"New Delhi",<br /> "jobType":"Permanent",<br />
						"emailAddress":"hr[at]knoldus.com",<br />
						"skillsRequired":["Scala","Java"],<br /> "description":"Need Of
						Java Developer",<br /> "datePosted":"2012-11-25T15:58:48Z"<br />
						"""),format.raw("""}"""),format.raw/*42.8*/(""","""),format.raw("""{"""),format.raw/*42.10*/("""<br /> "id":"""),format.raw("""{"""),format.raw/*42.23*/(""""id":"50b2401084ae8fe92ed44749""""),format.raw("""}"""),format.raw/*42.55*/(""",<br />
						"userId":"""),format.raw("""{"""),format.raw/*43.17*/(""""id":"50b23fd984ae8fe92ed44748""""),format.raw("""}"""),format.raw/*43.49*/(""",<br />
						"position":"Scala Developer",<br /> "company":"Knoldus Software",<br />
						"location":"New Delhi",<br /> "jobType":"Contract",<br />
						"emailAddress":"hr[at]knoldus.com",<br />
						"skillsRequired":["Scala","Akka"],<br /> "description":"Need Scala
						Developer",<br /> "datePosted":"2012-11-25T15:58:08Z"<br /> """),format.raw("""}"""),format.raw/*48.68*/("""]
					</td>
				</tr>
				<tr>
					<td>Error Response</td>
					<td>"""),format.raw("""{"""),format.raw/*53.11*/("""<br /> "alertType":"No Result Found",<br /> "message":"No
						Job Exist"<br /> """),format.raw("""}"""),format.raw/*54.25*/("""
					</td>
				</tr>
				<tr>
					<td>Authentication Required</td>
					<td>No</td>
				</tr>

				<tr>
					<td colspan="2"><h2 style="text-align: center;">To Get
							The Jobs For a Particular Searching Criteria</h2></td>
				</tr>
				<tr>
					<td>REST Endpoint(URL)</td>
					<td>http://Host:Port/URL/search_string</td>
				</tr>
				<tr>
					<td>HTTP Method</td>
					<td>GET</td>
				</tr>
				<tr>
					<td>Request Example</td>
					<td><a
						href="http://"""),_display_(Seq[Any](/*77.21*/Common/*77.27*/.getContextUrl)),format.raw/*77.41*/("""/getJobsForACriteria/scala+knoldus"><u>http://"""),_display_(Seq[Any](/*77.88*/Common/*77.94*/.getContextUrl)),format.raw/*77.108*/("""/getJobsForACriteria/scala+knoldus</u></a></td>
				</tr>
				<tr>
					<td>Response Example</td>
					<td>["""),format.raw("""{"""),format.raw/*81.12*/("""<br /> "id":"""),format.raw("""{"""),format.raw/*81.25*/(""""id":"50b24c2284ae6aa539512c60""""),format.raw("""}"""),format.raw/*81.57*/(""",<br />
						"userId":"""),format.raw("""{"""),format.raw/*82.17*/(""""id":"50b23fd984ae8fe92ed44748""""),format.raw("""}"""),format.raw/*82.49*/(""",<br />
						"position":"Scala Developer",<br /> "company":"Knoldus Software",<br />
						"location":"New Delhi",<br /> "jobType":"Contract",<br />
						"emailAddress":"hr[at]knoldus.com",<br />
						"skillsRequired":["Scala","Akka"],<br /> "description":"Need For
						Scala Developer",<br /> "datePosted":"2012-11-25T16:49:38Z"<br />
						"""),format.raw("""}"""),format.raw/*88.8*/(""","""),format.raw("""{"""),format.raw/*88.10*/("""<br /> "id":"""),format.raw("""{"""),format.raw/*88.23*/(""""id":"50b24c4484ae6aa539512c61""""),format.raw("""}"""),format.raw/*88.55*/(""",<br />
						"userId":"""),format.raw("""{"""),format.raw/*89.17*/(""""id":"50b23fd984ae8fe92ed44748""""),format.raw("""}"""),format.raw/*89.49*/(""",<br /> "position":"Java
						Developer",<br /> "company":"Knoldus Software",<br />
						"location":"New Delhi",<br /> "jobType":"Contract",<br />
						"emailAddress":"hr[at]knoldus.com",<br />
						"skillsRequired":["Java","JPA"],<br /> "description":"Need For
						Java Developer",<br /> "datePosted":"2012-11-25T16:50:12Z"<br />
						"""),format.raw("""}"""),format.raw/*95.8*/("""]
					</td>
				</tr>
				<tr>
					<td>Error Response</td>
					<td>"""),format.raw("""{"""),format.raw/*100.11*/("""<br /> "alertType":"No Result Found",<br />
						"message":"Your search did not match with any exist job."<br /> """),format.raw("""}"""),format.raw/*101.72*/("""
					</td>
				</tr>
				<tr>
					<td>Authentication Required</td>
					<td>No</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
""")))})),format.raw/*112.2*/("""
"""))}
    }
    
    def render(userId:String) = apply(userId)
    
    def f:((String) => play.api.templates.Html) = (userId) => apply(userId)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/restapi.scala.html
                    HASH: 39b9da82ab902f0b1908b53ecad849fe09f00539
                    MATRIX: 507->1|642->16|670->62|706->64|766->116|805->118|1362->639|1377->645|1413->659|1473->683|1488->689|1524->703|1657->789|1717->802|1796->834|1867->858|1946->890|2338->1236|2387->1238|2447->1251|2526->1283|2597->1307|2676->1339|3061->1677|3179->1748|3308->1830|3817->2303|3832->2309|3868->2323|3951->2370|3966->2376|4003->2390|4159->2499|4219->2512|4298->2544|4369->2568|4448->2600|4842->2948|4891->2950|4951->2963|5030->2995|5101->3019|5180->3051|5570->3395|5689->3466|5852->3581|6019->3716
                    LINES: 19->1|25->1|26->4|27->5|27->5|27->5|53->31|53->31|53->31|53->31|53->31|53->31|57->35|57->35|57->35|58->36|58->36|64->42|64->42|64->42|64->42|65->43|65->43|70->48|75->53|76->54|99->77|99->77|99->77|99->77|99->77|99->77|103->81|103->81|103->81|104->82|104->82|110->88|110->88|110->88|110->88|111->89|111->89|117->95|122->100|123->101|134->112
                    -- GENERATED --
                */
            