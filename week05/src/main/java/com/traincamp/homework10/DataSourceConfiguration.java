package com.traincamp.homework10;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {
	
	//配置数据源
	@Bean(name="dataSource")
	public HikariDataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(JdbcTest.HOST);
		config.setUsername(JdbcTest.USERNAME);
		config.setPassword(JdbcTest.PASSWORD);
		return new HikariDataSource(config);
	}
	//配置事务
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	//配置sqlSessionFactory
	@Bean(name="sqlSessionFactory")
	public MybatisSqlSessionFactoryBean sqlSessionFactory() {
		MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		return bean;
	}
	//配置mybatis包扫描路径，将mybatis接口加入Spring容器
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("com.traincamp");
		return msc;
	}

}
