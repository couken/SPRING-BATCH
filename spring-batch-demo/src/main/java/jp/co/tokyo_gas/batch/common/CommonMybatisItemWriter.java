package jp.co.tokyo_gas.batch.common;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;

/**
 * 写数据库
 * @author shuliangzhao
 * @Title: CommonMybatisItemWriter
 * @ProjectName spring-boot-learn
 * @Description: TODO
 * @date 2019/9/10 19:18
 */
public class CommonMybatisItemWriter<T> extends MyBatisBatchItemWriter<T> {

    public  CommonMybatisItemWriter(SqlSessionFactory sqlSessionFactory,String name) {
        setSqlSessionFactory(sqlSessionFactory);
        //setStatementId("com.sl.entity." + name + ".insertCat");
        setStatementId("jp.co.tokyo_gas.batch.entity." + name + ".insertCat");
        setAssertUpdates(false);
    }


}
