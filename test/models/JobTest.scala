package models

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfter
import org.bson.types.ObjectId
import java.util.Date
import com.mongodb.casbah.commons.MongoDBObject

@RunWith(classOf[JUnitRunner])
class JobTest extends FunSuite with BeforeAndAfter {

  test("Create A Job") {
    val job1 = Job(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date)
    val job2 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date)
    val job3 = Job(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date)
    PostAJob.addJob(job1)
    PostAJob.addJob(job2)
    PostAJob.addJob(job3)
    assert(PostAJob.findAllJobs.size === 3)
  }

  test("Search the Job") {
    val job1 = Job(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date)
    val job2 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date)
    val job3 = Job(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date)
    PostAJob.addJob(job1)
    PostAJob.addJob(job2)
    PostAJob.addJob(job3)

    val jobsFound = PostAJob.searchTheJob("Programmer")
    assert(jobsFound.size === 1)

    val jobsFoundAgain = PostAJob.searchTheJob("Delhi")
    assert(jobsFoundAgain.size === 2)
  }

  test("Find Job Details") {
    val job1 = Job(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date)
    val job2 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date)
    PostAJob.addJob(job1)
    PostAJob.addJob(job2)
    assert(PostAJob.findJobDetail(job1.id).get.emailAddress === "neel@gmail.com")
  }
  
  test("Search Job By Key skills") {
    val job1 = Job(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java","Scala"), "Description", new Date)
    val job2 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala","MongoDB"), "Description", new Date)
    PostAJob.addJob(job1)
    PostAJob.addJob(job2)
    val jobsMatchingUserProfile=PostAJob.findJobMatchingUserKeySkills(List("Scala","PHP"))
    assert(jobsMatchingUserProfile.size===2)
    val jobsMatchingUserProfileAgain=PostAJob.findJobMatchingUserKeySkills(List("Java","PHP"))
    assert(jobsMatchingUserProfileAgain(0).company==="Sify")
  }
  
  
  test("Find Jobs Posted By A Particular User"){
    val employer=new Employer(new ObjectId,"neelkanth@gmail.com","12345",List(),true)
    val employerId=SignUp.createUser(employer)
    val job1 = Job(new ObjectId, employerId.get, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java","Scala"), "Description", new Date)
    val job2 = Job(new ObjectId, employerId.get, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala","MongoDB"), "Description", new Date)
    val job3 = Job(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java","Scala"), "Description", new Date)
    val job4 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala","MongoDB"), "Description", new Date)
    PostAJob.addJob(job1)
    PostAJob.addJob(job2)
    PostAJob.addJob(job3)
    PostAJob.addJob(job4)
    assert(PostAJob.findJobsPostByUserId(employerId.get).size===2)
  }
  
//  test("Update Job"){
//    val job1 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala","MongoDB"), "Description", new Date)
//    PostAJob.addJob(job1)
//    assert(PostAJob.findJobDetail(job1.id).head.position==="Software Programmer")
//    val newJob = Job(new ObjectId, new ObjectId, "Software Developer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala","MongoDB"), "Description", new Date)
//    PostAJob.updateJob(newJob)
//  }

  after {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
  }
}