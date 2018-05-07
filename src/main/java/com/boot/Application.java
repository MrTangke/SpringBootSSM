package com.boot;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
/**
 * 
 * @项目名称：spring-boot-ssm
 *
 * @author ： YXM
 *
 * @date : 2018年5月5日 下午12:59:07
 */
@SpringBootApplication
public class Application {
	
	//配置链接数据库
	@Bean
	@ConfigurationProperties(prefix="db")
	public DataSource dateSource(){
		return new DataSource() ;
	}

	//通过工程创建sqlSession
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dateSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	//配置事物的管理器
	@Bean
	public PlatformTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dateSource());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
