package jp.co.tokyo_gas.batch.json.writer;

import jp.co.tokyo_gas.batch.common.CommonFileItemReader;
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
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * @author shuliangzhao
 * @Title: CafeCatConfiguration
 * @ProjectName spring-boot-learn
 * @Description: TODO
 * @date 2019/9/10 19:31
 */
@Configuration
@EnableBatchProcessing
public class CafeCatConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CatProcessor catProcessor;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    public Job cafeCatJob() {
        return jobBuilderFactory.get("cafeCatJob")
                .incrementer(new RunIdIncrementer())
                .start(cafeCatStep())
                .build();
    }

    @Bean
    public Step cafeCatStep() {
        return stepBuilderFactory.get("cafeCatStep")
                .<CafeCat, Cat>chunk(10000)
                .reader(commonJsonItemReader())
                .processor(catProcessor)
                .writer(catCommonMybatisItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public CommonJsonItemReader<CafeCat> commonJsonItemReader() {
        FileSystemResource fileSystemResource = new FileSystemResource("D:\\myspace\\java\\github\\SPRING-BATCH\\spring-batch-demo\\data\\springbatch.cat.json");
        return new CommonJsonItemReader(fileSystemResource,new JacksonJsonObjectReader<>(CafeCat.class));
    }

    @Bean
    @StepScope
    public CommonFileItemReader<CafeCat> cafeCatCommonFileItemReader() {
        return new CommonFileItemReader<>(CafeCat.class);
    }


    @Bean
    @StepScope
    public CommonMybatisItemWriter<Cat> catCommonMybatisItemWriter() {
         return new CommonMybatisItemWriter<>(sqlSessionFactory, Cat.class.getSimpleName());
    }
}
