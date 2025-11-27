package io.github.scordio.playground;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.as;
import static org.assertj.core.api.BDDAssertions.from;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.iterable;

@SpringBootTest
@SpringBatchTest
class JobTests {

	@Autowired
	private JobLauncherTestUtils jobLauncher;

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
			.returns("step", from(StepExecution::getStepName))
			.returns(BatchStatus.COMPLETED, from(StepExecution::getStatus));

		// assertions on job side effects
		// e.g., DB insert/update, produced files, command line invocations
	}

}
