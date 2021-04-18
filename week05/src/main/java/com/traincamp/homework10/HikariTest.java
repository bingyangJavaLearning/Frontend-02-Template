package com.traincamp.homework10;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zaxxer.hikari.HikariDataSource;


public class HikariTest {
	
	private static boolean useJdbcBatch = false;
	private static boolean haveError = true;
	
	public static void main(String[] args) throws SQLException {
		//使用spring管理数据源，通过配置类 DataSourceConfiguration装配bean
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		HikariDataSource dataSource = ac.getBean(HikariDataSource.class);
		//获取connection
		Connection con = dataSource.getConnection();
		// 开启事务
		con.setAutoCommit(false);
		try {
			// 批量增删改，分为mysql原生sql方法和jdbc batch方法
			if (useJdbcBatch) {
				TransAndBatchJdbcTest.jdbcBatchInsert(con);
				TransAndBatchJdbcTest.jdbcBatchUpdate(con);
				//若执行过程中出现异常，则回滚。
				if (haveError) {
					throw new SQLException("sql error");
				}
				TransAndBatchJdbcTest.jdbcBatchDelete(con);
			} else {
				TransAndBatchJdbcTest.mysqlBatchInsert(con);
				TransAndBatchJdbcTest.mysqlBatchUpdate(con);
				if (haveError) {
					throw new SQLException("sql error");
				}
				TransAndBatchJdbcTest.mysqlBatchDelete(con);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		
		//或使用ORM，使用@Transactional注解配置事务,使用@EnableTransactionManagement 或xml配置  <tx:annotation-driven/> 开启事务管理
		UserService userService = ac.getBean(UserService.class);
		userService.insert(haveError);
	}
	
	
	

}
