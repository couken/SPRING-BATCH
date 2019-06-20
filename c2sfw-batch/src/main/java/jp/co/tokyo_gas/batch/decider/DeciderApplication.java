package jp.co.tokyo_gas.batch.decider;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DeciderApplication {

	//决策器
	public static void main(String[] args) {
		SpringApplication.run(DeciderApplication.class, args);
	}

}