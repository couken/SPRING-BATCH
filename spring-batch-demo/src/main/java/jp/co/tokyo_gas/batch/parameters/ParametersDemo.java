package jp.co.tokyo_gas.batch.parameters;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParametersDemo implements StepExecutionListener {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  private Map<String, JobParameter> parameters;

  @Bean
  public Job parameterJob() {
    return jobBuilderFactory.get("parameterJob")
        .incrementer(new RunIdIncrementer())
        .start(parameterStep())
        .build();
  }

  @Bean
  public Step parameterStep() {
    return stepBuilderFactory.get("parameterStep").listener(this).tasklet(new Tasklet() {

      @Override
      public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
          throws Exception {
        System.out.println(parameters.get("info").getValue());
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
