
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
object editJob extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[JobEntity,Form[PostAJobForm],String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(job:JobEntity,postAJobForm: Form[PostAJobForm],userId:String):play.api.templates.Html = {
        _display_ {import helper._

import models.Alert


Seq[Any](format.raw/*1.64*/(""" 
"""),_display_(Seq[Any](/*4.2*/main("Welcome to ScalaJobz.com",userId,Alert("",""))/*4.54*/ {_display_(Seq[Any](format.raw/*4.56*/("""

<div id="homewhite">
	<div class="page">
		<h1>Edit Job</h1>
		<div class="border-center">
			<div class="box-post">
				<div class="bordered">
					<form id="jobpost-form" class="form-horizontal" action="/editJob/"""),_display_(Seq[Any](/*12.72*/job/*12.75*/.id)),format.raw/*12.78*/("""" method="post">
						<table>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Position">Position</label>
										<div class="controls">
											<input id="Position" name="Position" type="text" value=""""),_display_(Seq[Any](/*19.69*/job/*19.72*/.position)),format.raw/*19.81*/("""" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Company"> Company
											Name</label>
										<div class="controls">
											<input id="Company" name="Company" type="text" value=""""),_display_(Seq[Any](/*30.67*/job/*30.70*/.company)),format.raw/*30.78*/("""" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Location"> Job
											Location</label>
										<div class="controls">
											<input id="Location" name="Location" type="text" value=""""),_display_(Seq[Any](/*41.69*/job/*41.72*/.location)),format.raw/*41.81*/("""" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="JobType"> Job Type</label>
										<div class="controls">
											<select id="JobType" name="JobType" class="input-large">
												<option value="">-- Select Job Type --</option>
												"""),_display_(Seq[Any](/*53.14*/Job/*53.17*/.jobType.map/*53.29*/{case(key, value)=>_display_(Seq[Any](format.raw/*53.48*/("""
												"""),_display_(Seq[Any](/*54.14*/if(job.jobType == value)/*54.38*/{_display_(Seq[Any](format.raw/*54.39*/("""
												<option value="""),_display_(Seq[Any](/*55.28*/value)),format.raw/*55.33*/(""" selected="selected">"""),_display_(Seq[Any](/*55.55*/key)),format.raw/*55.58*/("""</option>
												""")))}/*56.15*/else/*56.20*/{_display_(Seq[Any](format.raw/*56.21*/("""
												<option value="""),_display_(Seq[Any](/*57.28*/value)),format.raw/*57.33*/(""">"""),_display_(Seq[Any](/*57.35*/key)),format.raw/*57.38*/("""</option>
												""")))})),format.raw/*58.14*/("""
												 """)))})),format.raw/*59.15*/("""
											</select>


										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Email_Addrss_To_Apply_To">Email
											Address</label>
										<div class="controls">
											<input id="Email_Addrss_To_Apply_To" name="Email_Addrss_To_Apply_To" type="text"
												value=""""),_display_(Seq[Any](/*74.21*/job/*74.24*/.emailAddress)),format.raw/*74.37*/("""" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Skills">Skills
											Required</label>
										<div class="controls">
											<input id="Skills" name="Skills" type="text"
												value=""""),_display_(Seq[Any](/*86.21*/job/*86.24*/.skillsRequired.mkString(","))),format.raw/*86.53*/("""" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Description">Job
											Description</label>
										<div class="controls">
											<textarea id="Description" name="Description" rows="6"  style="resize: none;" 
												 class="input-tlarge">"""),_display_(Seq[Any](/*98.36*/job/*98.39*/.description)),format.raw/*98.51*/("""</textarea>
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
""")))})),format.raw/*124.2*/("""
"""))}
    }
    
    def render(job:JobEntity,postAJobForm:Form[PostAJobForm],userId:String) = apply(job,postAJobForm,userId)
    
    def f:((JobEntity,Form[PostAJobForm],String) => play.api.templates.Html) = (job,postAJobForm,userId) => apply(job,postAJobForm,userId)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/editJob.scala.html
                    HASH: 0af2be69d260ce01422db7ebf5fbc770d949d94e
                    MATRIX: 536->1|712->63|749->104|809->156|848->158|1101->375|1113->378|1138->381|1439->646|1451->649|1482->658|1849->989|1861->992|1891->1000|2261->1334|2273->1337|2304->1346|2731->1737|2743->1740|2764->1752|2821->1771|2871->1785|2904->1809|2943->1810|3007->1838|3034->1843|3092->1865|3117->1868|3159->1892|3172->1897|3211->1898|3275->1926|3302->1931|3340->1933|3365->1936|3420->1959|3467->1974|3897->2368|3909->2371|3944->2384|4322->2726|4334->2729|4385->2758|4817->3154|4829->3157|4863->3169|5381->3655
                    LINES: 19->1|25->1|26->4|26->4|26->4|34->12|34->12|34->12|41->19|41->19|41->19|52->30|52->30|52->30|63->41|63->41|63->41|75->53|75->53|75->53|75->53|76->54|76->54|76->54|77->55|77->55|77->55|77->55|78->56|78->56|78->56|79->57|79->57|79->57|79->57|80->58|81->59|96->74|96->74|96->74|108->86|108->86|108->86|120->98|120->98|120->98|146->124
                    -- GENERATED --
                */
            