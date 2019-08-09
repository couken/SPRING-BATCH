package jp.co.tokyo_gas.batch.qualifier;

import java.util.Map;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.Sleeper;

@Configuration
public class QualifierDemo implements StepExecutionListener {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  private Map<String, JobParameter> parameters;

  @Bean
  public Job doJob(@Qualifier("stepOne") Step step1,@Qualifier("stepTwo") Step step2) {
    return jobBuilderFactory.get("doJob")
        .incrementer(new RunIdIncrementer())
        .start(step1)
        .next(step2)
        .build();
  }

  @Bean
  public Step stepOne() {
    return stepBuilderFactory.get("stepOne").listener(this).tasklet(new Tasklet() {

      @Override
      public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
          throws Exception {
        System.out.println(parameters.get("info").getValue() + "one");
        return RepeatStatus.FINISHED;
      }
    }).build();
  }
  @Bean
  public Step stepTwo() {
    return stepBuilderFactory.get("stepTwo")
        .listener(this)
        .tasklet(new Tasklet() {
      
      @Override
      public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
          throws Exception {
        System.out.println(parameters.get("info").getValue() +"two");
        Thread.sleep(1000000);
        return RepeatStatus.FINISHED;
      }
    }).build();
  }

  @Override
  public void beforeStep(StepExecution stepExecution) {
    parameters = stepExecution.getJobParameters().getParameters();

  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    return null;
  }


}
