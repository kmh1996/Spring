package net.koreate.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectTest {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/smartWeb";
	String username = "smartWeb";
	String password = "12345";
	
	@org.junit.Test
	public void testConnection() {
		System.out.println("connection test");
		Connection conn = null;
		try {
			Class.forName(driver);
			System.out.println("DRIVER 확인 완료");
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("연결 완료 : "+ conn);
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER 확인 실패 찾을 수 없음");
		} catch (SQLException e) {
			System.out.println("DB 연결 정보가 일치하지 않음");
		}finally {
			try {
				if(conn != null)conn.close();
			} catch (SQLException e) {}
		}
	}
	
	
	
}









