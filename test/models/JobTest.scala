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
    val job1 = JobEntity(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date)
    val job2 = JobEntity(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date)
    val job3 = JobEntity(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    assert(Job.findAllJobs.size === 3)
  }
  
  test("Find All Jobs"){
    val job1 = JobEntity(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date)
    val job2 = JobEntity(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date)
    val job3 = JobEntity(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    assert(Job.findAllJobs.size === 3)
  }
  
  test("Find All Jobs Of Last N hours"){
    val job1 = JobEntity(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date())
    val job2 = JobEntity(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date)
    val job3 = JobEntity(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    assert(Job.findJobsOfLastNHours.size === 3)
  }

  test("Search the Job") {
    val job1 = JobEntity(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date)
    val job2 = JobEntity(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date)
    val job3 = JobEntity(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)

    val jobsFound = Job.searchTheJob("Programmer")
    assert(jobsFound.size === 1)

    val jobsFoundAgain = Job.searchTheJob("Delhi")
    assert(jobsFoundAgain.size === 2)
  }

  test("Find Job Details") {
    val job1 = JobEntity(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date)
    val job2 = JobEntity(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date)
    Job.addJob(job1)
    Job.addJob(job2)
    assert(Job.findJobDetail(job1.id).get.emailAddress === "neel@gmail.com")
  }

  test("Find Jobs Posted By A Particular User") {
    val employer = new Employer(new ObjectId, "neelkanth@gmail.com", "12345", List(), true)
    val employerId = User.createUser(employer)
    val job1 = JobEntity(new ObjectId, employerId.get, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date)
    val job2 = JobEntity(new ObjectId, employerId.get, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date)
    val job3 = JobEntity(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date)
    val job4 = JobEntity(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    Job.addJob(job4)
    assert(Job.findJobsPostByUserId(employerId.get).size === 2)
  }

  //  test("Update Job"){
  //    val job1 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala","MongoDB"), "Description", new Date)
  //    Job.addJob(job1)
  //    assert(Job.findJobDetail(job1.id).head.position==="Software Programmer")
  //    val newJob = Job(new ObjectId, new ObjectId, "Software Developer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala","MongoDB"), "Description", new Date)
  //    Job.updateJob(newJob)
  //  }

  after {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
  }
}