
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
object ajax_result extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[List[JobEntity],Boolean,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(result: List[JobEntity],editFlag:Boolean):play.api.templates.Html = {
        _display_ {import java.text.SimpleDateFormat

def /*3.2*/format_date/*3.13*/(date: java.util.Date) = {{ new SimpleDateFormat("MMM dd yyyy").format(date) }};
Seq[Any](format.raw/*1.44*/(""" 
"""),format.raw/*3.91*/(""" 
"""),_display_(Seq[Any](/*4.2*/if(!result.isEmpty)/*4.21*/{_display_(Seq[Any](format.raw/*4.22*/("""

<table id="results" class="table table-striped table-bordered table-condensed tablesorter">
	<thead>
		<tr>
			<th>POSITION</th>
			<th>COMPANY</th>
			<th>LOCATION</th>
			<th>TYPE</th>
			<th>POSTED</th> """),_display_(Seq[Any](/*13.21*/if(editFlag == true)/*13.41*/{_display_(Seq[Any](format.raw/*13.42*/("""
			<th>OPTION</th>""")))})),format.raw/*14.20*/("""
		</tr>
	</thead>
	<tbody data-provides="rowlink">
		"""),_display_(Seq[Any](/*18.4*/result/*18.10*/.map/*18.14*/ { job =>_display_(Seq[Any](format.raw/*18.23*/("""
		<tr>
			<td><a href="/jobDetail/"""),_display_(Seq[Any](/*20.29*/job/*20.32*/.id)),format.raw/*20.35*/(""""><b>"""),_display_(Seq[Any](/*20.41*/job/*20.44*/.position)),format.raw/*20.53*/("""</b></a>
					"""),_display_(Seq[Any](/*21.7*/if(job.jobBy.toString != "ScalaJobz" && job.jobBy.toString != "SimplyHired")/*21.83*/{_display_(Seq[Any](format.raw/*21.84*/("""
					<div style="text-align: right;">
					<a href="http://www.indeed.com."><img src=""""),_display_(Seq[Any](/*23.50*/routes/*23.56*/.Assets.at("images/" + job.jobBy.toString + ".gif"))),format.raw/*23.107*/("""" alt="Job By """),_display_(Seq[Any](/*23.122*/job/*23.125*/.jobBy.toString)),format.raw/*23.140*/("""" title="Job By """),_display_(Seq[Any](/*23.157*/job/*23.160*/.jobBy.toString)),format.raw/*23.175*/(""""/></a>
					</div>
					""")))})),format.raw/*25.7*/("""
					"""),_display_(Seq[Any](/*26.7*/if(job.jobBy.toString == "SimplyHired")/*26.46*/{_display_(Seq[Any](format.raw/*26.47*/("""
					<div style="text-align: right;">
					<a STYLE="text-decoration:none" href="http://www.simplyhired.com/">
					<span style="color: rgb(0, 0, 0); font-size: 12px;">Jobs</span>
					</a> <span style="font-size: 12px;">by </span>
					<a STYLE="text-decoration:none" href="http://www.simplyhired.com/">
					<span style="color: rgb(0, 159, 223); font-weight: bold;  font-size: 12px;">Simply</span>
					<span style="color: rgb(163, 204, 64); font-weight: bold; font-size: 12px;">Hired</span></a></div>
					""")))})),format.raw/*34.7*/("""
			</td>
			<td>"""),_display_(Seq[Any](/*36.9*/job/*36.12*/.company)),format.raw/*36.20*/("""</td>
			<td>"""),_display_(Seq[Any](/*37.9*/job/*37.12*/.location)),format.raw/*37.21*/("""</td>
			<td>"""),_display_(Seq[Any](/*38.9*/job/*38.12*/.jobType)),format.raw/*38.20*/("""</td>
			<td>"""),_display_(Seq[Any](/*39.9*/format_date(job.datePosted))),format.raw/*39.36*/("""</td> """),_display_(Seq[Any](/*39.43*/if(editFlag == true)/*39.63*/{_display_(Seq[Any](format.raw/*39.64*/("""
			<td class="nolink"><a href="/findJobPostForEdit/"""),_display_(Seq[Any](/*40.53*/job/*40.56*/.id)),format.raw/*40.59*/(""""><i
					class="icon-pencil" title="Edit"></i> </a> &nbsp;<a href="#"
				onclick="confirmDeleteJob('"""),_display_(Seq[Any](/*42.33*/job/*42.36*/.id)),format.raw/*42.39*/("""');"><i class="icon-remove"
					title="Delete"></i> </a></td>""")))})),format.raw/*43.36*/("""
		</tr>
		""")))})),format.raw/*45.4*/("""
	</tbody>

</table>

""")))}/*50.3*/else/*50.8*/{_display_(Seq[Any](format.raw/*50.9*/("""
<h1>No results found.</h1>
""")))})),format.raw/*52.2*/("""
<div id="myPager" class="pagination pagination-centered"></div>
<script src=""""),_display_(Seq[Any](/*54.15*/routes/*54.21*/.Assets.at("javascripts/bootstrap-rowlink.js"))),format.raw/*54.67*/("""" type="text/javascript"></script>
<script src=""""),_display_(Seq[Any](/*55.15*/routes/*55.21*/.Assets.at("javascripts/bootstrap.min.js"))),format.raw/*55.63*/("""" type="text/javascript"></script>
<script type="text/javascript">
 

 $(document).ready(function() """),format.raw("""{"""),format.raw/*59.32*/("""
	 $("#results").tablesorter();
	 showRecords(1,25);
	"""),format.raw("""}"""),format.raw/*62.3*/(""");
 
 $('#myPager').pagination("""),_display_(Seq[Any](/*64.28*/result/*64.34*/.size)),format.raw/*64.39*/(""","""),format.raw("""{"""),format.raw/*64.41*/("""callback:function(page,component)"""),format.raw("""{"""),format.raw/*64.75*/("""
	 var itemperpage=25;
	 var from=((page + 1) - 1) * itemperpage + 1;
	 var to=from + itemperpage - 1;
	 showRecords(from,to);
	 console.info(page);
	"""),format.raw("""}"""),format.raw("""}"""),format.raw/*70.4*/(""");
	
function showRecords(from, to) """),format.raw("""{"""),format.raw/*72.33*/("""          
     var rows = document.getElementById("results").rows;  
      // i starts from 1 to skip table header row  
     for (var i = 1; i < rows.length; i++) """),format.raw("""{"""),format.raw/*75.45*/("""  
         if (i < from || i > to)    
             rows[i].style.display = 'none';  
         else  
             rows[i].style.display = '';  
     """),format.raw("""}"""),format.raw/*80.7*/("""  
   """),format.raw("""}"""),format.raw/*81.5*/("""  
	
 </script>
"""))}
    }
    
    def render(result:List[JobEntity],editFlag:Boolean) = apply(result,editFlag)
    
    def f:((List[JobEntity],Boolean) => play.api.templates.Html) = (result,editFlag) => apply(result,editFlag)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Dec 24 10:27:08 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/ajax_result.scala.html
                    HASH: 54cec1f65c03eefe954f2e5387b2eff635ad68fa
                    MATRIX: 528->1|664->81|683->92|791->43|820->170|857->173|884->192|922->193|1167->402|1196->422|1235->423|1287->443|1377->498|1392->504|1405->508|1452->517|1524->553|1536->556|1561->559|1603->565|1615->568|1646->577|1696->592|1781->668|1820->669|1944->757|1959->763|2033->814|2085->829|2098->832|2136->847|2190->864|2203->867|2241->882|2298->908|2340->915|2388->954|2427->955|2969->1466|3022->1484|3034->1487|3064->1495|3113->1509|3125->1512|3156->1521|3205->1535|3217->1538|3247->1546|3296->1560|3345->1587|3388->1594|3417->1614|3456->1615|3545->1668|3557->1671|3582->1674|3721->1777|3733->1780|3758->1783|3853->1846|3896->1858|3937->1882|3949->1887|3987->1888|4047->1917|4162->1996|4177->2002|4245->2048|4330->2097|4345->2103|4409->2145|4557->2246|4658->2301|4726->2333|4741->2339|4768->2344|4817->2346|4898->2380|5115->2532|5199->2569|5412->2735|5610->2887|5663->2894
                    LINES: 19->1|22->3|22->3|23->1|24->3|25->4|25->4|25->4|34->13|34->13|34->13|35->14|39->18|39->18|39->18|39->18|41->20|41->20|41->20|41->20|41->20|41->20|42->21|42->21|42->21|44->23|44->23|44->23|44->23|44->23|44->23|44->23|44->23|44->23|46->25|47->26|47->26|47->26|55->34|57->36|57->36|57->36|58->37|58->37|58->37|59->38|59->38|59->38|60->39|60->39|60->39|60->39|60->39|61->40|61->40|61->40|63->42|63->42|63->42|64->43|66->45|71->50|71->50|71->50|73->52|75->54|75->54|75->54|76->55|76->55|76->55|80->59|83->62|85->64|85->64|85->64|85->64|85->64|91->70|93->72|96->75|101->80|102->81
                    -- GENERATED --
                */
            