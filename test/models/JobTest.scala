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
    val job1 = Job(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", "Description", new Date)
    val job2 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", "Description", new Date)
    val job3 = Job(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", "Description", new Date)
    PostAJob.addJob(job1)
    PostAJob.addJob(job2)
    PostAJob.addJob(job3)
    assert(PostAJob.findAllJobs.size === 3)
  }

  test("Search the Job") {
    val job1 = Job(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", "Description", new Date)
    val job2 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", "Description", new Date)
    val job3 = Job(new ObjectId, new ObjectId, "Consultant", "HCL", " Noida", "Contract", "narender@gmail.com", "Description", new Date)
    PostAJob.addJob(job1)
    PostAJob.addJob(job2)
    PostAJob.addJob(job3)

    val jobsFound = PostAJob.searchTheJob("Programmer")
    assert(jobsFound.size === 1)

    val jobsFoundAgain = PostAJob.searchTheJob("Delhi")
    assert(jobsFoundAgain.size === 2)
  }

  test("Find Job Details") {
    val job1 = Job(new ObjectId, new ObjectId, "Software Developer", "Sify", " New Delhi", "Contract", "neel@gmail.com", "Description", new Date)
    val job2 = Job(new ObjectId, new ObjectId, "Software Programmer", "Knoldus", " New Delhi", "Permanent", "neels@gmail.com", "Description", new Date)
    PostAJob.addJob(job1)
    PostAJob.addJob(job2)
    assert(PostAJob.findJobDetail(job1.id).get.emailAddress === "neel@gmail.com")
  }

  after {
    JobDAO.remove(MongoDBObject("location" -> ".*".r))
  }
}