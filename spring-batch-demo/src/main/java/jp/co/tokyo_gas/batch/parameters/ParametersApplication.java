package jp.co.tokyo_gas.batch.parameters;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ParametersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParametersApplication.class, args);
	}

}