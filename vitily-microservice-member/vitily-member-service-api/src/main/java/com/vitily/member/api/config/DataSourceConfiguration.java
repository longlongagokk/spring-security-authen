package com.vitily.member.api.config;

import com.vitily.jdbc.DataSourceFac;
import com.vitily.jdbc.MultipleDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

////
@Component
@EnableTransactionManagement
@MapperScan(basePackages = {"com.vitily.member.server.mapper","com.vitily.mapper"})
@Slf4j
public class DataSourceConfiguration {
	@Value("${spring.application.name}")
	String jdbcUrl;
	@ConfigurationProperties(prefix = "datasource0.hikari")
	@Bean(name = "datasource0")
	@Primary
	public DataSource datasource0(){
		log.info("jdbcUrl:"+jdbcUrl);
		return new HikariDataSource();
	}
	@Bean(name = "datasource1")
	public DataSource datasource1(@Qualifier("datasource0") DataSource datasource0){
		return datasource0;
	}

	@Bean(name = "multipleDataSource")
	public MultipleDataSource multipleDataSource(
			@Qualifier("datasource0") DataSource datasource0,
			@Qualifier("datasource1") DataSource datasource1){
		MultipleDataSource multipleDataSource = new MultipleDataSource();
		multipleDataSource.setDefaultTargetDataSource(datasource0);
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("datasource0",datasource0);
		targetDataSources.put("datasource1",datasource1);
		multipleDataSource.setTargetDataSources(targetDataSources);
		log.info("......use origin datasource......");
		return multipleDataSource;
	}
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(MultipleDataSource multipleDataSource) {
		return new DataSourceTransactionManager(multipleDataSource);
	}
	@Bean(name="commTransactionInterceptor")
	public TransactionInterceptor commTransactionInterceptor(@Qualifier("transactionManager") PlatformTransactionManager transactionManager,
											@Value("${datasource.rollBackTransactionAttribute}")String rollBackTransactionAttribute,
											@Value("${datasource.readOnlyTransactionAttribute}")String readOnlyTransactionAttribute) {
		return DataSourceFac.getTxAdvice(transactionManager,rollBackTransactionAttribute,readOnlyTransactionAttribute);
	}

	@Bean(name="primaryAdviceAdvisor")
	public Advisor primaryAdviceAdvisor(@Qualifier("commTransactionInterceptor")TransactionInterceptor commTransactionInterceptor) {
		DefaultPointcutAdvisor advisor = DataSourceFac.testAdviceAdvisor(MultipleDataSourceAspectAdvice.COM_SERVICE_EXECU_PATTERN,commTransactionInterceptor);
		advisor.setOrder(2);
		return advisor;
	}
	@Bean(name="testAdviceAdvisor")
	public Advisor testAdviceAdvisor(@Qualifier("commTransactionInterceptor")TransactionInterceptor commTransactionInterceptor) {
		DefaultPointcutAdvisor advisor = DataSourceFac.testAdviceAdvisor(MultipleDataSourceAspectAdvice.TEST_SERVICE_EXECU_PATTERN,commTransactionInterceptor);
		advisor.setOrder(2);
		return advisor;
	}
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("multipleDataSource") MultipleDataSource multipleDataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(multipleDataSource);
		return sessionFactory.getObject();
	}
}
