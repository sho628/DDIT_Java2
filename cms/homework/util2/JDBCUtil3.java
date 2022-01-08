package util2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import util2.JDBCUtil2;

/**
 * db.properties 파일의 내용으로 DB 정보를 설정하는 방법
 * 방법2) ResourceBundle 객체 이용하기
 * @author pc11
 *
 */
public class JDBCUtil3 extends JDBCUtil2{
	static ResourceBundle bundle; // 선언
	
	
	private JDBCUtil3() {
		
	}
	
	private static JDBCUtil3 instance;
	
	public static JDBCUtil3 getInstance() {
		if(instance == null) {
			instance = new JDBCUtil3();
		}
		return instance;
	}
	
	static {
		bundle = ResourceBundle.getBundle("db"); // 객체 생성
		
		try {
			Class.forName(bundle.getString("driver"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
		} catch (Exception e) {
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
