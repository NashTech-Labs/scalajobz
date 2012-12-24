
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Alert,String,List[JobEntity],Boolean,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(alert: Alert,userId:String,jobs: List[JobEntity],editFlag:Boolean):play.api.templates.Html = {
        _display_ {import java.text.SimpleDateFormat

def /*4.2*/format_date/*4.13*/(date: java.util.Date) = {{
	new SimpleDateFormat("MMM dd yyyy").format(date)
}};
Seq[Any](format.raw/*1.69*/("""
"""),format.raw/*3.1*/("""
"""),format.raw/*6.2*/("""
		<div id="logos">
			<div style="height: 100px;">
				<form action="" class="form-search" style="margin-top: 10px;">
					<div class="input-append">
						<input id="searchString" type="text" class="input-xxlarge" style="height:50px;font-size: 40px;color:#2884dd;" onKeyUp="changeVal()"
						onmouseover="popover" data-content="Please Insert your search criteria here for daily job alerts(You can cancel email alerts at any time)." data-original-title="Get Job Alert">
						<input id="search" type="submit" value="Search" class="btn btn-primary" style="margin-top: 15px;"/>
					</div>
				</form>
			</div>
		</div>



"""),_display_(Seq[Any](/*21.2*/main("Welcome to Scalajobz.com",userId,alert)/*21.47*/ {_display_(Seq[Any](format.raw/*21.49*/("""
   
    <div id="homewhite">
	<div class="page">
	<div id="alertHold"></div>
	<div id="alert-button" style="padding-bottom: 12px;">
		<button id="job-alert" class="btn btn-primary btn-large" disabled="disabled"  data-toggle="modal" title="Enter Search Criteria For Job Alert">
			Get Job Alert
		</button>
	</div>
	
	<div id="myModal" class="modal hide fade" tabindex="-1">
		
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="myModalLabel">Get Job Alert</h3>
		</div>
		
		<div class="modal-body">
			<div id="msgBody"></div>
				<form id="alert-form">
						<div class="control-group">
							<label class="control-label" for="EmailId">Email:</label>
							<div class="controls">
								<input id="EmailId" name="EmailId" type="text"
									placeholder="Email address" class="input-xlarge"
									style="height: 30px;" />
							</div>
						</div>
				</form>
		</div>
		
		<div class="modal-footer">
			<button  class="btn btn-primary" data-dismiss="modal">Close</button>
			<button id="inform_me" class="btn btn-primary">Inform me</button>
			<p class="pull-left">You can cancel email alerts at any time.</p>
		</div>
	</div>
	
	
	<div id="successModal" class="modal hide fade">
		
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="myModalLabel">Success</h3>
		</div>
		
		<div class="modal-body">
		<div id="errormsg">
		</div>		
		</div>
		
		<div class="modal-footer">
			<button id="Ok" class="btn btn-primary" data-dismiss="modal">OK</button>
			<p class="pull-left">You can cancel email alerts at any time.</p>
		</div>
	</div>
	
	<div id="confirmDeleteModel" class="modal hide fade">
		
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="myModalLabel">Confirm Delete</h3>
		</div>
		
		<div class="modal-body">
		<h3>Do You want to delete this job?</h3>
		</div>		
		
		<div class="modal-footer">
		    <button  class="btn btn-primary" data-dismiss="modal">Cancel</button>
			<button id="delete" class="btn btn-primary" data-dismiss="modal">OK</button>
		</div>
	</div>
	
	
	"""),_display_(Seq[Any](/*97.3*/if(userId != "" && jobs.isEmpty)/*97.35*/{_display_(Seq[Any](format.raw/*97.36*/("""
	<h5>No Job Posted by you. Click <a class="menulink" href="/postAJob">Here</a> to post a job. </h5>
	""")))}/*99.4*/else/*99.9*/{_display_(Seq[Any](format.raw/*99.10*/("""
	
	<div id="search-result">
		<table id="results" class="table table-striped table-bordered table-condensed tablesorter">
			<thead>
				<tr>
					<th>POSITION</th>
					<th>COMPANY</th>
					<th>LOCATION</th>
					<th>TYPE</th>
					<th>POSTED</th>
					
					"""),_display_(Seq[Any](/*111.7*/if(editFlag == true)/*111.27*/{_display_(Seq[Any](format.raw/*111.28*/("""<th>OPTION</th>""")))})),format.raw/*111.44*/("""
				</tr>
				</thead>
			<tbody data-provides="rowlink">
				"""),_display_(Seq[Any](/*115.6*/jobs/*115.10*/.map/*115.14*/ { job =>_display_(Seq[Any](format.raw/*115.23*/("""
				<tr>
					<td><a href="/jobDetail/"""),_display_(Seq[Any](/*117.31*/job/*117.34*/.id)),format.raw/*117.37*/(""""><b>"""),_display_(Seq[Any](/*117.43*/job/*117.46*/.position)),format.raw/*117.55*/("""</b></a>
					"""),_display_(Seq[Any](/*118.7*/if(job.jobBy.toString != "ScalaJobz" && job.jobBy.toString != "SimplyHired")/*118.83*/{_display_(Seq[Any](format.raw/*118.84*/("""
					<div style="text-align: right;">
					<a href="http://www.indeed.com."><img src=""""),_display_(Seq[Any](/*120.50*/routes/*120.56*/.Assets.at("images/" + job.jobBy.toString + ".gif"))),format.raw/*120.107*/("""" alt="Job By """),_display_(Seq[Any](/*120.122*/job/*120.125*/.jobBy.toString)),format.raw/*120.140*/("""" title="Job By """),_display_(Seq[Any](/*120.157*/job/*120.160*/.jobBy.toString)),format.raw/*120.175*/(""""/></a>
					</div>
					""")))})),format.raw/*122.7*/("""
					"""),_display_(Seq[Any](/*123.7*/if(job.jobBy.toString == "SimplyHired")/*123.46*/{_display_(Seq[Any](format.raw/*123.47*/("""
					<div style="text-align: right;">
					<a STYLE="text-decoration:none" href="http://www.simplyhired.com/">
					<span style="color: rgb(0, 0, 0); font-size: 12px;">Jobs</span>
					</a> <span style="font-size: 12px;">by </span>
					<a STYLE="text-decoration:none" href="http://www.simplyhired.com/">
					<span style="color: rgb(0, 159, 223); font-weight: bold;  font-size: 12px;">Simply</span>
					<span style="color: rgb(163, 204, 64); font-weight: bold; font-size: 12px;">Hired</span></a></div>
					""")))})),format.raw/*131.7*/("""
					</td>
					<td>"""),_display_(Seq[Any](/*133.11*/job/*133.14*/.company)),format.raw/*133.22*/("""</td>
					<td>"""),_display_(Seq[Any](/*134.11*/job/*134.14*/.location)),format.raw/*134.23*/("""</td>
					<td>"""),_display_(Seq[Any](/*135.11*/job/*135.14*/.jobType)),format.raw/*135.22*/("""</td>
					<td>"""),_display_(Seq[Any](/*136.11*/format_date(job.datePosted))),format.raw/*136.38*/("""</td>
					"""),_display_(Seq[Any](/*137.7*/if(editFlag == true)/*137.27*/{_display_(Seq[Any](format.raw/*137.28*/("""
					<td class="nolink"><a href="/findJobPostForEdit/"""),_display_(Seq[Any](/*138.55*/job/*138.58*/.id)),format.raw/*138.61*/(""""><i class="icon-pencil" title="Edit"></i> </a>
					&nbsp;<a href="#" onclick="confirmDeleteJob('"""),_display_(Seq[Any](/*139.52*/job/*139.55*/.id)),format.raw/*139.58*/("""');"><i class="icon-remove" title="Delete"></i> </a></td>""")))})),format.raw/*139.116*/("""
				</tr>
				""")))})),format.raw/*141.6*/("""
			</tbody>
			
		</table>
		<div id="myPager" class="pagination pagination-centered"></div>
		</div>
		  

		""")))})),format.raw/*149.4*/("""
	</div>
</div>


 <script  type="text/javascript">
 
 $(document).ready(function() """),format.raw("""{"""),format.raw/*156.32*/("""
	 $("#results").tablesorter();
	 changeVal();
	 showRecords(1,25);
	 window.addEventListener("load", function() """),format.raw("""{"""),format.raw/*160.47*/("""
		 jobPagination();
		"""),format.raw("""}"""),format.raw/*162.4*/(""", false); 
	 $("#searchString").popover("""),format.raw("""{"""),format.raw/*163.31*/("""placement:'bottom'"""),format.raw("""}"""),format.raw/*163.50*/("""); 
	
	"""),format.raw("""}"""),format.raw/*165.3*/(""");
 
 function jobPagination() """),format.raw("""{"""),format.raw/*167.28*/("""
	 var editflag="""),_display_(Seq[Any](/*168.17*/editFlag)),format.raw/*168.25*/("""+""
	    $.ajax("""),format.raw("""{"""),format.raw/*169.14*/("""
	        type : 'GET',
	        url : '"""),_display_(Seq[Any](/*171.18*/routes/*171.24*/.Application.jobPagination("' + editflag + '"))),format.raw/*171.70*/("""',
	        success : function(data) """),format.raw("""{"""),format.raw/*172.36*/("""
	        	 $('#search-result').html(data);
	        """),format.raw("""}"""),format.raw/*174.11*/("""
	    """),format.raw("""}"""),format.raw/*175.7*/(""");
	    return false;
	"""),format.raw("""}"""),format.raw/*177.3*/("""
 
 $('#myPager').pagination("""),_display_(Seq[Any](/*179.28*/jobs/*179.32*/.size)),format.raw/*179.37*/(""","""),format.raw("""{"""),format.raw/*179.39*/("""callback:function(page,component)"""),format.raw("""{"""),format.raw/*179.73*/("""
	 var itemperpage=25;
	 var from=((page + 1) - 1) * itemperpage + 1;
	 var to=from + itemperpage - 1;
	 showRecords(from,to);
	 console.info(page);
	"""),format.raw("""}"""),format.raw("""}"""),format.raw/*185.4*/(""");
	
function showRecords(from, to) """),format.raw("""{"""),format.raw/*187.33*/(""" 
	if(document.getElementById("results") != null)"""),format.raw("""{"""),format.raw/*188.49*/("""
     var rows = document.getElementById("results").rows;  
      // i starts from 1 to skip table header row  
     for (var i = 1; i < rows.length; i++) """),format.raw("""{"""),format.raw/*191.45*/("""  
         if (i < from || i > to)    
             rows[i].style.display = 'none';  
         else  
             rows[i].style.display = '';  
     """),format.raw("""}"""),format.raw/*196.7*/("""  
	"""),format.raw("""}"""),format.raw/*197.3*/("""
   """),format.raw("""}"""),format.raw/*198.5*/("""  
 
 $('#search').click(function(e) """),format.raw("""{"""),format.raw/*200.34*/("""
	 var editflag="""),_display_(Seq[Any](/*201.17*/editFlag)),format.raw/*201.25*/("""+""
	    var idToGet = $("#searchString").val();
	    $.ajax("""),format.raw("""{"""),format.raw/*203.14*/("""
	        type : 'POST',
	        url : '"""),_display_(Seq[Any](/*205.18*/routes/*205.24*/.JobController.findAJob("' + idToGet + '","' + editflag + '"))),format.raw/*205.85*/("""',
	        success : function(data) """),format.raw("""{"""),format.raw/*206.36*/("""
	        	 $('#search-result').html(data);
	        """),format.raw("""}"""),format.raw/*208.11*/("""
	    """),format.raw("""}"""),format.raw/*209.7*/(""");
	    return false;
	"""),format.raw("""}"""),format.raw/*211.3*/(""");
 
 $('#job-alert').click(function(e) """),format.raw("""{"""),format.raw/*213.37*/("""
	 $("#myModal").modal("show");
	 document.getElementById("errormsg").innerHTML="";
	 document.getElementById("alert-form").reset();
	 $('.control-group').removeClass('success');
	 $('.control-group').removeClass('error');
	 document.getElementById("msgBody").innerHTML ='<h3>Get new jobs for '+ $("#searchString").val() +' search by email</h3>'
 """),format.raw("""}"""),format.raw/*220.3*/(""");
 
 $('#inform_me').click(function(e) """),format.raw("""{"""),format.raw/*222.37*/("""
	    var emailId = $("#EmailId").val();
	    var skillsToken=$("#searchString").val();
	    if(skillsToken == "")"""),format.raw("""{"""),format.raw/*225.28*/("""
	   	 document.getElementById("errormsg").innerHTML = '<h3>No Searching Criteria Found !</h3>';
    	 $("#myModal").modal("hide");
    	 $("#successModal").modal("show");

	    """),format.raw("""}"""),format.raw/*230.7*/("""
	    else"""),format.raw("""{"""),format.raw/*231.11*/("""
	    if(!skillsToken == "" && validateEmail(emailId))"""),format.raw("""{"""),format.raw/*232.55*/("""
	    $.ajax("""),format.raw("""{"""),format.raw/*233.14*/("""
	        type : 'POST',
	        url : '"""),_display_(Seq[Any](/*235.18*/routes/*235.24*/.UserController.registerJobSeeker("' + emailId + '","' + skillsToken + '"))),format.raw/*235.98*/("""',
	        success : function(data) """),format.raw("""{"""),format.raw/*236.36*/("""
	        	if (data == "false") """),format.raw("""{"""),format.raw/*237.33*/("""
	        	 		document.getElementById("errormsg").innerHTML = '<h3>You Are Already Enrolled With ScalaJobz for Job Alerts With Same Attributes ( '+skillsToken+' ) !</h3>';
	        	 		$("#myModal").modal("hide");
	        	 		$("#successModal").modal("show");
	        	"""),format.raw("""}"""),format.raw/*241.12*/("""
	        	else
	        		"""),format.raw("""{"""),format.raw/*243.13*/("""
	        			document.getElementById("errormsg").innerHTML = '<h3>You Are Successfully Enrolled With ScalaJobz for Job Alerts !</h3>';
		        	    $("#myModal").modal("hide");
		        	 	$("#successModal").modal("show");
	        		"""),format.raw("""}"""),format.raw/*247.13*/("""
	        	 
	        """),format.raw("""}"""),format.raw/*249.11*/("""
	    """),format.raw("""}"""),format.raw/*250.7*/(""");
	    return false;
	    """),format.raw("""}"""),format.raw/*252.7*/("""
 """),format.raw("""}"""),format.raw/*253.3*/("""
	"""),format.raw("""}"""),format.raw/*254.3*/(""");
 
 function confirmDeleteJob(jobId) """),format.raw("""{"""),format.raw/*256.36*/("""
	 $("#confirmDeleteModel").modal("show");
	 $('#delete').click(function(e) """),format.raw("""{"""),format.raw/*258.35*/(""" deleteJob(jobId);"""),format.raw("""}"""),format.raw/*258.54*/(""");
	"""),format.raw("""}"""),format.raw/*259.3*/("""
 
function deleteJob(jobId) """),format.raw("""{"""),format.raw/*261.28*/("""
	var editflag="""),_display_(Seq[Any](/*262.16*/editFlag)),format.raw/*262.24*/("""+""
	    $.ajax("""),format.raw("""{"""),format.raw/*263.14*/("""
	        type : 'GET',
	        url : '"""),_display_(Seq[Any](/*265.18*/routes/*265.24*/.JobController.deleteJob("' + jobId + '"))),format.raw/*265.65*/("""',
	        success : function(data) """),format.raw("""{"""),format.raw/*266.36*/("""
	        	window.location = "/findJobPostByUserId"
	        """),format.raw("""}"""),format.raw/*268.11*/("""
	    """),format.raw("""}"""),format.raw/*269.7*/(""");
	    return false;
	"""),format.raw("""}"""),format.raw/*271.3*/("""
	

function changeVal() """),format.raw("""{"""),format.raw/*274.23*/("""
	
	if($("#searchString").val().length ==0)
		"""),format.raw("""{"""),format.raw/*277.4*/("""
		document.getElementById("job-alert").innerHTML = 'Get Job Alert';
		document.getElementById("job-alert").disabled=true;
		"""),format.raw("""}"""),format.raw/*280.4*/("""
	else
		"""),format.raw("""{"""),format.raw/*282.4*/("""
		 document.getElementById("job-alert").disabled=false;
    	 document.getElementById("job-alert").innerHTML ='Get Job Alert For&nbsp;' + $("#searchString").val() ;
    	 
    	 """),format.raw("""}"""),format.raw/*286.8*/("""
"""),format.raw("""}"""),format.raw/*287.2*/(""" 
	
 </script>   
    
""")))})))}
    }
    
    def render(alert:Alert,userId:String,jobs:List[JobEntity],editFlag:Boolean) = apply(alert,userId,jobs,editFlag)
    
    def f:((Alert,String,List[JobEntity],Boolean) => play.api.templates.Html) = (alert,userId,jobs,editFlag) => apply(alert,userId,jobs,editFlag)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Dec 24 10:27:09 IST 2012
                    SOURCE: /home/ruchi/knolProj/ScalaJobz/scalajobz-com/app/views/index.scala.html
                    HASH: 7463790176bd42af0e7022402d6944c02470f751
                    MATRIX: 535->1|696->106|715->117|824->68|851->104|878->196|1538->821|1592->866|1632->868|3844->3045|3885->3077|3924->3078|4045->3182|4057->3187|4096->3188|4395->3451|4425->3471|4465->3472|4514->3488|4614->3552|4628->3556|4642->3560|4690->3569|4767->3609|4780->3612|4806->3615|4849->3621|4862->3624|4894->3633|4945->3648|5031->3724|5071->3725|5196->3813|5212->3819|5287->3870|5340->3885|5354->3888|5393->3903|5448->3920|5462->3923|5501->3938|5559->3964|5602->3971|5651->4010|5691->4011|6234->4522|6293->4544|6306->4547|6337->4555|6390->4571|6403->4574|6435->4583|6488->4599|6501->4602|6532->4610|6585->4626|6635->4653|6683->4665|6713->4685|6753->4686|6845->4741|6858->4744|6884->4747|7020->4846|7033->4849|7059->4852|7151->4910|7199->4926|7343->5038|7476->5123|7638->5237|7709->5261|7798->5302|7865->5321|7920->5329|8000->5361|8054->5378|8085->5386|8150->5403|8228->5444|8244->5450|8313->5496|8399->5534|8501->5588|8555->5595|8626->5619|8693->5649|8707->5653|8735->5658|8785->5660|8867->5694|9085->5846|9170->5883|9268->5933|9472->6089|9671->6241|9723->6246|9775->6251|9861->6289|9915->6306|9946->6314|10056->6376|10135->6418|10151->6424|10235->6485|10321->6523|10423->6577|10477->6584|10548->6608|10637->6649|11032->6997|11121->7038|11284->7153|11510->7332|11569->7343|11672->7398|11734->7412|11813->7454|11829->7460|11926->7534|12012->7572|12093->7605|12413->7877|12489->7905|12775->8143|12846->8166|12900->8173|12975->8201|13025->8204|13075->8207|13163->8247|13288->8324|13355->8343|13407->8348|13485->8378|13538->8394|13569->8402|13634->8419|13712->8460|13728->8466|13792->8507|13878->8545|13988->8607|14042->8614|14113->8638|14187->8664|14281->8711|14454->8837|14511->8847|14738->9027|14787->9029
                    LINES: 19->1|22->4|22->4|25->1|26->3|27->6|42->21|42->21|42->21|118->97|118->97|118->97|120->99|120->99|120->99|132->111|132->111|132->111|132->111|136->115|136->115|136->115|136->115|138->117|138->117|138->117|138->117|138->117|138->117|139->118|139->118|139->118|141->120|141->120|141->120|141->120|141->120|141->120|141->120|141->120|141->120|143->122|144->123|144->123|144->123|152->131|154->133|154->133|154->133|155->134|155->134|155->134|156->135|156->135|156->135|157->136|157->136|158->137|158->137|158->137|159->138|159->138|159->138|160->139|160->139|160->139|160->139|162->141|170->149|177->156|181->160|183->162|184->163|184->163|186->165|188->167|189->168|189->168|190->169|192->171|192->171|192->171|193->172|195->174|196->175|198->177|200->179|200->179|200->179|200->179|200->179|206->185|208->187|209->188|212->191|217->196|218->197|219->198|221->200|222->201|222->201|224->203|226->205|226->205|226->205|227->206|229->208|230->209|232->211|234->213|241->220|243->222|246->225|251->230|252->231|253->232|254->233|256->235|256->235|256->235|257->236|258->237|262->241|264->243|268->247|270->249|271->250|273->252|274->253|275->254|277->256|279->258|279->258|280->259|282->261|283->262|283->262|284->263|286->265|286->265|286->265|287->266|289->268|290->269|292->271|295->274|298->277|301->280|303->282|307->286|308->287
                    -- GENERATED --
                */
            