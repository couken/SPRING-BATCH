package jp.co.tokyo_gas.batch.split;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SplitApplication {

	//split实现并发执行
	public static void main(String[] args) {
		SpringApplication.run(SplitApplication.class, args);
	}

}