package jp.co.tokyo_gas.batch.json.reader;

import jp.co.tokyo_gas.batch.common.CommonFileItemWriter;
import jp.co.tokyo_gas.batch.common.CommonJsonItemReader;
import jp.co.tokyo_gas.batch.common.CommonMybatisItemWriter;
import jp.co.tokyo_gas.batch.entity.CafeCat;
import jp.co.tokyo_gas.batch.entity.Cat;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class CatJsonConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CatJsonProcessor catJsonProcessor;
    
    @Bean
    public Job catJsonJob() {
        return jobBuilderFactory.get("catJsonJob")
                .start(catJsonStep())
                .build();
    }

    @Bean
    public Step catJsonStep() {
        return stepBuilderFactory.get("catJsonStep")
                .<Cat, CafeCat>chunk(10)
                .reader(commonJsonItemReader())
                .processor(catJsonProcessor)
                .writer(cafeCatJsonCommonFileItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public CommonJsonItemReader<Cat> commonJsonItemReader() {
        FileSystemResource fileSystemResource = new FileSystemResource("D:\\myspace\\java\\github\\SPRING-BATCH\\spring-batch-demo\\data\\"+Cat.class.getSimpleName()+".json");
        return new CommonJsonItemReader(fileSystemResource,new JacksonJsonObjectReader<>(Cat.class));
    }

    @Bean
    @StepScope
    public CommonFileItemWriter<CafeCat> cafeCatJsonCommonFileItemWriter() {
        return new CommonFileItemWriter<>(CafeCat.class);
    }



}
