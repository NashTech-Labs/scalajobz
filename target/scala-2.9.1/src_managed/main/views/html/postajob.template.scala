
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
object postajob extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[PostAJobForm],String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(postAJobForm: Form[PostAJobForm],userId:String):play.api.templates.Html = {
        _display_ {import helper._

import models.Alert


Seq[Any](format.raw/*1.50*/(""" 
"""),_display_(Seq[Any](/*4.2*/main("Welcome to ScalaJobz.com",userId,Alert("",""))/*4.54*/ {_display_(Seq[Any](format.raw/*4.56*/("""

<div id="homewhite">
	<div class="page">
		<h1>Post a Job </h1>
		<div class="border-center">
			<div class="box-post">
				<div class="bordered">
					<form id="jobpost-form" class="form-horizontal" action="/postAJob" method="post">
						<table>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="Position">Position</label>
										<div class="controls">
											<input id="Position" name="Position" type="text" placeholder="Position" class="input-large"/>
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
											<input id="Company" name="Company" type="text" placeholder="Company" class="input-large"/>
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
											<input id="Location" name="Location" type="text" placeholder="Location" class="input-large"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="JobType"> Job Type</label>
										<div class="controls">
											<select id="JobType" name="JobType" class="input-slarge">
												<option value="">-- Select Job Type --</option>
												"""),_display_(Seq[Any](/*53.14*/Job/*53.17*/.jobType.map/*53.29*/{case(key, value)=>_display_(Seq[Any](format.raw/*53.48*/("""
												<option value="""),_display_(Seq[Any](/*54.28*/value)),format.raw/*54.33*/(""">"""),_display_(Seq[Any](/*54.35*/key)),format.raw/*54.38*/("""</option> """)))})),format.raw/*54.49*/("""
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
												placeholder="Email Address To Apply To" class="input-large"/>
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
												placeholder="Skills (Separated By Comma)" class="input-large"/>
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
												placeholder="Description" class="input-tlarge"></textarea>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
										<div class="controls">
											<input type="submit" value="Post Job" class="btn btn-primary" />
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
""")))})),format.raw/*119.2*/("""
"""))}
    }
    
    def render(postAJobForm:Form[PostAJobForm],userId:String) = apply(postAJobForm,userId)
    
    def f:((Form[PostAJobForm],String) => play.api.templates.Html) = (postAJobForm,userId) => apply(postAJobForm,userId)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 23 18:48:24 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/postajob.scala.html
                    HASH: 39d3f38df9091e9df4170cab40ef700388a07014
                    MATRIX: 527->1|689->49|726->90|786->142|825->144|2440->1723|2452->1726|2473->1738|2530->1757|2594->1785|2621->1790|2659->1792|2684->1795|2727->1806|4466->3513
                    LINES: 19->1|25->1|26->4|26->4|26->4|75->53|75->53|75->53|75->53|76->54|76->54|76->54|76->54|76->54|141->119
                    -- GENERATED --
                */
            