package jp.co.tokyo_gas.batch.itemreader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ItemReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(jp.co.tokyo_gas.batch.json.reader.JsonReaderApplication.class, args);
	}

}