package io.github.scordio.playground;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableBatchProcessing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	Job job1(JobBuilderFactory factory, Step step1) {
		return factory.get("job1").start(step1).build();
	}

	@Bean
	Step step1(StepBuilderFactory factory) {
		return factory.get("step1")
			.<Item, Item>chunk(10)
			.reader(new ListItemReader<>(List.of(new Item("Bob"), new Item("John"))))
			.writer(new ListItemWriter<>())
			.build();
	}

	@Bean
	Job job2(JobBuilderFactory factory, Step step2) {
		return factory.get("job2").start(step2).build();
	}

	@Bean
	Step step2(StepBuilderFactory factory) {
		return factory.get("step2")
			.<Item, Item>chunk(10)
			.reader(new ListItemReader<>(List.of(new Item("Alice"), new Item("Marge"))))
			.writer(new ListItemWriter<>())
			.build();
	}

	record Item(String name) {
	}

}
