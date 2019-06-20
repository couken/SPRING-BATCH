package jp.co.tokyo_gas.batch.flow;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowDemo {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step flowDemoStep1() {
		return stepBuilderFactory.get("flowDemoStep1").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("flowDemoStep1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public Step flowDemoStep2() {
		return stepBuilderFactory.get("flowDemoStep2").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("flowDemoStep2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public Step flowDemoStep3() {
		return stepBuilderFactory.get("flowDemoStep3").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("flowDemoStep3");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public Flow flowDemoFlow() {
		return new FlowBuilder<Flow>("flowDemoFlow")
				.start(flowDemoStep1())
				.next(flowDemoStep2())
				.build();
	}
	
	@Bean
	public Job flowDemoJob(){
		return jobBuilderFactory.get("flowDemoJob")
				.start(flowDemoFlow())
				.next(flowDemoStep3())
				.end()
				.build();
	}
}
