package com.traincamp.homework10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransAndBatchJdbcTest {

	private static List<User> userDatas = new ArrayList<User>();
	private static boolean useJdbcBatch = false;
	private static boolean haveError = false;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		userDatas.add(new User(4, "user4", "password4"));
		userDatas.add(new User(5, "user5", "password5"));
		userDatas.add(new User(6, "user6", "password6"));

	}

	public static void main(String[] args) throws SQLException {
		// 获取连接
		Connection con = DriverManager.getConnection(JdbcTest.HOST, JdbcTest.USERNAME, JdbcTest.PASSWORD);
		// 开启事务
		con.setAutoCommit(false);
		try {
			// 批量增删改，分为mysql原生sql方法和jdbc batch方法
			if (useJdbcBatch) {
				jdbcBatchInsert(con);
				jdbcBatchUpdate(con);
				//若执行过程中出现异常，则回滚。
				if (haveError) {
					throw new SQLException("sql error");
				}
				jdbcBatchDelete(con);
			} else {
				mysqlBatchInsert(con);
				mysqlBatchUpdate(con);
				if (haveError) {
					throw new SQLException("sql error");
				}
				mysqlBatchDelete(con);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		con.close();
	}

	static void jdbcBatchInsert(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("insert into table_user(uid,username,password) values(?,?,?)");
		for (User user : userDatas) {
			ps.setInt(1, user.getUid());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}

	public static void mysqlBatchInsert(Connection con) throws SQLException {
		StringBuffer sql = new StringBuffer("insert into table_user(uid,username,password) values");
		for (int i = 0; i < userDatas.size(); i++) {
			User user = userDatas.get(i);
			sql.append("(" + user.getUid() + ",\"" + user.getUsername() + "\",\"" + user.getPassword() + "\")");
			if (i != userDatas.size() - 1) {
				sql.append(",");
			}
		}
		System.out.println("执行sql：" + sql.toString());
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ps.execute();
		ps.close();
	}

	public static void jdbcBatchUpdate(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("update table_user set username = ? , password = ? where uid = ?");
		for (User user : userDatas) {
			ps.setString(1, user.getUsername() + "1");
			ps.setString(2, user.getPassword() + "2");
			ps.setInt(3, user.getUid());
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}

	public static void mysqlBatchUpdate(Connection con) throws SQLException {
		StringBuffer sql = new StringBuffer("update table_user set username = CASE uid ");
		for (User user : userDatas) {
			sql.append("WHEN " + user.getUid() + " THEN \"" + user.getUsername()+"1\"");
		}
		sql.append(" END where uid in ("+userDatas.stream().map(u -> u.getUid()+"").collect(Collectors.joining(","))+")");
		System.out.println("执行sql："+sql.toString());
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ps.execute();
		ps.close();
	}

	public static void jdbcBatchDelete(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("delete from table_user where uid = ?");
		for (User user : userDatas) {
			ps.setInt(1, user.getUid());
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}

	public static void mysqlBatchDelete(Connection con) throws SQLException {
		StringBuffer sql = new StringBuffer("delete from table_user where uid in ("+userDatas.stream().map(u -> u.getUid()+"").collect(Collectors.joining(","))+")");
		System.out.println("执行sql："+sql.toString());
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ps.execute();
		ps.close();
	}
}
