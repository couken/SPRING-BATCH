package jp.co.tokyo_gas.batch.json.writer;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class JsonWriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonWriteApplication.class, args);
	}

}