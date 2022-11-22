package jp.co.tokyo_gas.batch.json.reader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class JsonReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonReaderApplication.class, args);
	}

}