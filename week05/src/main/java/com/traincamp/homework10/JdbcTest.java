package com.traincamp.homework10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {
	
	public static final String HOST = "jdbc:mysql://123.56.168.54:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String USERNAME = "root";
	public static final String PASSWORD="abcd1234";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		//获取连接
		Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
		//增删改查
		insert(con);
		update(con);
		List<User> list = select(con);
		System.out.println("查询结果：");
		list.forEach(System.out::println);
		delete(con);
		
		con.close();
	}
	
	private static void insert(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("insert into table_user(uid,username,password) values(?,?,?)");
		ps.setInt(1, 2);
		ps.setString(2, "user2");
		ps.setString(3, "password2");
		ps.execute();
		ps.close();
	}
	
	private static void update(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("update table_user set username = ?, password = ? where uid = ?");
		ps.setString(1, "user2");
		ps.setString(2, "password2");
		ps.setInt(3, 1);
		ps.executeUpdate();
		ps.close();
	}
	
	private static List<User> select(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("select uid,username,password from table_user where uid = ?");
		ps.setInt(1, 1);
		ResultSet set = ps.executeQuery();
		List<User> users = new ArrayList<User>();
		while (set.next()) {
			User user = new User();
			user.setUid(set.getInt(1));
			user.setUsername(set.getString(2));
			user.setPassword(set.getString(3));
			users.add(user);
		}
		ps.close();
		return users;
	}
	
	private static void delete(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("delete from table_user where uid = ?");
		ps.setInt(1, 1);
		ps.execute();
		ps.close();
	}

}
