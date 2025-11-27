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
	Job job(JobBuilderFactory factory, Step step) {
		return factory.get("job").start(step).build();
	}

	@Bean
	Step step(StepBuilderFactory factory) {
		return factory.get("step")
			.<Item, Item>chunk(10)
			.reader(new ListItemReader<>(List.of(new Item("Bob"), new Item("John"))))
			.writer(new ListItemWriter<>())
			.build();
	}

	record Item(String name) {
	}

}
