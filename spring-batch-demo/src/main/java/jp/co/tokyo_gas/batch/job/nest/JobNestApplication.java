package jp.co.tokyo_gas.batch.job.nest;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class JobNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobNestApplication.class, args);
	}

}