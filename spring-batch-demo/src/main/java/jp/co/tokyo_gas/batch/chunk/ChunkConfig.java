package jp.co.tokyo_gas.batch.chunk;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jp.co.tokyo_gas.batch.chunk.entity.Student;
import jp.co.tokyo_gas.batch.chunk.item.StudentProcessor;
import jp.co.tokyo_gas.batch.chunk.item.StudentReader;
import jp.co.tokyo_gas.batch.chunk.item.StudentWriter;

@Configuration
@EnableBatchProcessing
public class ChunkConfig {
  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private StudentReader studentReader;

  @Autowired
  private StudentProcessor studentProcessor;

  @Autowired
  private StudentWriter studentWriter;
  
  @Bean
  public Job doJob() {
    return jobBuilderFactory.get("doJob")
        .incrementer(new RunIdIncrementer())
        .start(lineStep())
        .build();
  }

  @Bean
  public Step lineStep() {
    return stepBuilderFactory.get("lineStep")
        .<Student,Student>chunk(10)
        .reader(studentReader)
        .processor(studentProcessor)
        .writer(studentWriter)
        .build();
  }

}
