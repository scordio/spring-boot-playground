package io.github.scordio.playground;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.as;
import static org.assertj.core.api.BDDAssertions.from;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.iterable;

@SpringBootTest
class Job1Tests {

	private final JobLauncherTestUtils jobLauncher;

	Job1Tests(@Autowired JobLauncher jobLauncher, @Autowired JobRepository jobRepository, @Autowired Job job1) {
		this.jobLauncher = new JobLauncherTestUtils();
		this.jobLauncher.setJobLauncher(jobLauncher);
		this.jobLauncher.setJobRepository(jobRepository);
		this.jobLauncher.setJob(job1);
	}

	@Test
	void test() throws Exception {
		// Given

		// set up prerequisites, e.g., DB data, mock downstream dependencies

		// When
		JobExecution jobExecution = jobLauncher.launchJob();

		// Then
		then(jobExecution) //
			.returns(ExitStatus.COMPLETED, from(JobExecution::getExitStatus))
			.returns(BatchStatus.COMPLETED, from(JobExecution::getStatus))
			.extracting(JobExecution::getStepExecutions, as(iterable(StepExecution.class)))
			.singleElement() // often replaced with assertions on multiple steps
			.returns("step1", from(StepExecution::getStepName))
			.returns(BatchStatus.COMPLETED, from(StepExecution::getStatus));

		// assertions on job side effects
		// e.g., DB insert/update, produced files, command line invocations
	}

}
