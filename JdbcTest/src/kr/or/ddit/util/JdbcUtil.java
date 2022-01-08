package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC드라이버를 로딩하고 Connection 객체를 생성하기 위한 유틸클래스
 */
public class JdbcUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!!");
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"sem",
					"java");
		}catch(SQLException ex) {
			System.out.println("DB연결 실패!!!");
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 자원반납
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void disConnect(
			Connection conn,
			Statement stmt,
			PreparedStatement pstmt,
			ResultSet rs
			) {
		if(rs != null) try {rs.close();} catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
		if(conn != null) try {conn.close();} catch(SQLException ex) {}
	}
	
	
	
}
