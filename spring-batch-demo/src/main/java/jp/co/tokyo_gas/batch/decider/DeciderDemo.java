package jp.co.tokyo_gas.batch.decider;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeciderDemo {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step deciderDemoStep1() {
		return stepBuilderFactory.get("deciderDemoStep1").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("deciderDemoStep1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public Step deciderDemoStep2() {
		return stepBuilderFactory.get("deciderDemoStep2").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("even");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step deciderDemoStep3() {
		return stepBuilderFactory.get("deciderDemoStep3").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("odd");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public JobExecutionDecider myDecider() {
		return new MyDecider();
	}
	
	@Bean
	public Job deciderDemoJob() {
		return jobBuilderFactory.get("deciderDemoJob")
				.start(deciderDemoStep1())
				.next(myDecider())
				.from(myDecider()).on("even").to(deciderDemoStep2())
				.from(myDecider()).on("odd").to(deciderDemoStep3())
				.from(deciderDemoStep3()).on("*").to(myDecider())
				.end()
				.build();
	}
}
