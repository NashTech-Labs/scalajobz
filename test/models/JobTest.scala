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

  before {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
  }

  test("Create A Job") {
    val job1 = JobEntity(new ObjectId, Option(new ObjectId), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job3 = JobEntity(new ObjectId, Option(new ObjectId), "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    assert(Job.findAllJobs.size === 3)
  }

  test("Find All Jobs") {
    val job1 = JobEntity(new ObjectId, Option(new ObjectId), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job3 = JobEntity(new ObjectId, Option(new ObjectId), "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    assert(Job.findAllJobs.size === 3)
  }

  test("Find All Jobs Of Last N hours") {
    val job1 = JobEntity(new ObjectId, Option(new ObjectId), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date(), JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job3 = JobEntity(new ObjectId, Option(new ObjectId), "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    assert(Job.findJobsOfLastNHours.size === 3)
  }

  test("Search the Job") {
    val job1 = JobEntity(new ObjectId, Option(new ObjectId), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job3 = JobEntity(new ObjectId, Option(new ObjectId), "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    val jobsFound = Job.searchTheJob("Programmer")
    assert(jobsFound.size === 1)
    val jobsFoundAgain = Job.searchTheJob("Delhi")
    assert(jobsFoundAgain.size === 2)
  }

  test("Find Job Details") {
    val job1 = JobEntity(new ObjectId, Option(new ObjectId), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List(), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    assert(Job.findJobDetail(job1.id).get.emailAddress === "neel@gmail.com")
  }

  test("Find Jobs Posted By A Particular User") {
    val employer = new UserEntity(new ObjectId, "neelkanth@gmail.com", "12345", List(), true, None, None, None)
    val employerId = User.createUser(employer)
    val job1 = JobEntity(new ObjectId, Option(employerId.get), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(employerId.get), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job3 = JobEntity(new ObjectId, Option(new ObjectId), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job4 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    Job.addJob(job4)
    assert(Job.findJobsPostByUserId(employerId.get).size === 2)
  }
  test("Searching the Job by Search Token") {
    val employer = new UserEntity(new ObjectId, "neelkanth@gmail.com", "12345", List(), true, None, None, None)
    val employerId = User.createUser(employer)
    val job1 = JobEntity(new ObjectId, Option(employerId.get), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(employerId.get), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job3 = JobEntity(new ObjectId, Option(new ObjectId), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job4 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Python", "MongoDB"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    Job.addJob(job3)
    Job.addJob(job4)
    val jobsFound = Job.searchJobs(List("Sify"), List(job1, job2, job3, job4))
    assert(jobsFound.size === 2)
  }

  test("Update Job") {
    val job1 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    assert(Job.findJobDetail(job1.id).head.position === "Software Programmer")
    val jobFound = Job.findJobDetail(job1.id)
    val updatedJob = JobEntity(jobFound.get.id, jobFound.get.userId, "Software Developer", jobFound.get.company, jobFound.get.location, jobFound.get.jobType,
      jobFound.get.emailAddress, jobFound.get.skillsRequired, jobFound.get.description, jobFound.get.datePosted, jobFound.get.jobBy, jobFound.get.applyType, jobFound.get.tinyUrl)
    Job.updateJob(updatedJob)
    val updatedJobFound = Job.findJobDetail(job1.id)
    assert(Job.findJobDetail(updatedJobFound.get.id).head.position === "Software Developer")

  }

  test("Delete Job") {
    val job1 = JobEntity(new ObjectId, Option(new ObjectId), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    assert(Job.findJobDetail(job1.id).head.position === "Software Programmer")
    val jobFound = Job.findJobDetail(job1.id)
    Job.deleteJobByJobId(jobFound.get.id)
    assert(Job.findAllJobs.size === 0)
  }

  test("Find Job Post In Last N Hours") {
    val employer = new UserEntity(new ObjectId, "neelkanth@gmail.com", "12345", List(), true, None, None, None)
    val employerId = User.createUser(employer)
    val job1 = JobEntity(new ObjectId, Option(employerId.get), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(employerId.get), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    assert(Job.findJobsOfLastNHours.size === 2)
  }

  test("count number of jobs") {
    val employer = new UserEntity(new ObjectId, "neelkanth@gmail.com", "12345", List(), true, None, None, None)
    val employerId = User.createUser(employer)
    val job1 = JobEntity(new ObjectId, Option(employerId.get), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    val job2 = JobEntity(new ObjectId, Option(employerId.get), "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", List("Scala", "MongoDB"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    Job.addJob(job2)
    assert(Job.countTotalJobs === 2)

  }

  test("find job apply address") {
    val employer = new UserEntity(new ObjectId, "neelkanth@gmail.com", "12345", List(), true, None, None, None)
    val employerId = User.createUser(employer)
    val job1 = JobEntity(new ObjectId, Option(employerId.get), "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", List("Java", "Scala"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("email"), None)
    Job.addJob(job1)
    val jobApplyAddress1 = Job.findJobApplyAdress(Option(job1.emailAddress), None)
    val job2 = JobEntity(new ObjectId, Option(employerId.get), "Software Engineer", "Sify", " New Delhi", "Contract", "http://www.scalajobz.com/", List("Java", "Scala"), "Description", new Date, JobBy.withName("ScalaJobz"), Option("link"), None)
    Job.addJob(job2)
    val jobApplyAddress2 = Job.findJobApplyAdress(None, Option(job2.emailAddress))
    assert(jobApplyAddress1 === "neel@gmail.com")
    assert(job1.applyType.get == "email")
    assert(jobApplyAddress2 === "http://www.scalajobz.com/")
    assert(job2.applyType.get == "link")

  }

  after {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
    UserDAO.remove(MongoDBObject("emailId" -> ".*".r))
  }
}