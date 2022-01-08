package util2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC드라이버를 로딩하고 Connection 객체 생성하기 위한 유틸 클래스
public class JDBCUtil {
	
	static JDBCUtil instance;
	public static JDBCUtil getInstance() {
		if(instance == null) {
			instance = new JDBCUtil();
		}
		return instance;
	}
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "cms95", "java");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
			return null;
		}
	}
	
	//자원반납
	public void disConnect(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(conn != null) try {conn.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(rs != null) try {rs.close();} catch(SQLException e) {}
	}
}
