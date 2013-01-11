#Project Description 

-----------------------------------------------
What the project does, the problem it solves :-
-----------------------------------------------

Scalajobz is a community driven job portal for Scala jobs.  It is specifically focussed on the Scala ecosystem. It allows employment opportunities to be listed on the site with details so that Job seekers can search and apply for the relevant jobs. It also allows job seekers to enroll for email alerts which would allow them to get a daily email for new jobs which match their search criteria. 
External systems can fetch all job details and search for specific jobs using the REST Api.

------------------------------------------------
Functionality Of Scalajobz:-
------------------------------------------------

Scalajobz is a community driven job portal for Scala jobs.  It is specifically focussed on the Scala ecosystem.

Roles on Scala Jobz

1)Recruiter
2)Job Seeker
3)Publishers
4)Aggregator

1) Recruiter:-
_______________


To Post a job, Recruiter have to login In Scalajobz through easy registration process on Scalajobz Or

login through various Social Networks like Google,Face book,Twitter & Linked in.


Recruiter have the right to post job(s) by mentioning following details:-


Position
Company Name
Location
Skills Required
Job type(Contract/Permanent)
Email Address/Link to apply for job
Description(Job details with required experience)

Recruiter can edit and delete the job as well

Recruiters can edit their profile also.They have the right to change their password any time and can get the forget password mail on request.

2) Job Seeker:-
_______________


Job Seeker can search jobs on Scalajobz by using any searching criteria like(Scala Jobs,India).
Job Seeker can get daily job mail alert from Scalajobz by en-roll themselves.
Job Seeker can apply for the desired job by sending mail to the recruiter mentioned in job details or by using the link mentioned.

3) Publisher:-
_______________


External systems can fetch all jobs List  as json response and search for specific jobs using the REST API  posted in last 24 hrs.
The details of REST API is mentioned on the site footer.

 
4) Aggregator:-
__________________

Scalajobz Aggregate from various Job Portals like Indeed, Simply Hired ,Career Builder and so on to get the collection of relevant Scala jobs.


------------------------------------------------
Why it does it this way, benefits and drawbacks:-
------------------------------------------------

Scalajobz uses a lot of inherent Play 2.0 functionality to drive the job flow. Actors are used to send out daily alerts when matching jobs are found for all enrolled job seekers.

------------------------------------
High-level design and architecture :-
------------------------------------

Scalajobz is using the latest Typesafe 2.0.2 stack which includes

Twitter Bootstrap 2.2.1
Play 2.0.2
Scala 2.9.1
Sbt 0.11.3
Akka 2.0.1
MongoDB  2.0.4
org.scalatest
org.specs2
Verified with scalastyle guide (No serious warnings)

The product follows a standard MVC architecture. The request handling is done in the following path
HttpRequest -> Router -> Controller -> Model -> Database, the rendering of HttpResponse is done via HTML
Actor is used to asynchronously process new jobs and match search criteria for enrolled job seekers

-----------------------------------------------------------------------
Instructions that detail how the project is compiled, deployed and used :-
-----------------------------------------------------------------------

The live application is currently hosted at :  http://www.scalajobz.com
The Gihub code for the project is at    :  https://github.com/knoldus/scalajobz
* Clone the project into local system
* install sbt 0.11.3  if you do not have it already. You can get it from here: https://github.com/harrah/xsbt/wiki/Getting-Started-Setup
* execute `sbt compile` to build the product
* execute `sbt run' to execute the product
* Scalajobz should now be accessible at localhost:9000

How to run tests :

In order to run all the tests for controllers,templates,routers,models(DB Calls),server

* execute  `sbt test` 

