package util2;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * db.properties 파일의 내용으로 DB정보를 설정하는 방법
 * 방법1) Properties 객체 이용하기
 * @author pc11
 *
 */
public class JDBCUtil2 {
	static Properties prop; //Properties객체 변수 선언
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static JDBCUtil2 instance;
	
	public static JDBCUtil2 getInstance() {
		if(instance == null) {
			instance = new JDBCUtil2();
		}
		return instance;
	}
	
	static {
		prop = new Properties(); //객체생성
		File file = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Connection getConnection() {
		
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
					
		} catch (Exception e) {
			System.out.println("DB 연결실패");
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String, Object> selectOne(String sql) {
		conn = getConnection();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			ResultSetMetaData meta = rs.getMetaData();
			
			while(rs.next()) {
				for(int i = 1; i <= meta.getColumnCount(); i++) {
					map.put(meta.getColumnName(i), rs.getObject(i));
				}
				
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			disConnect(conn, stmt, pstmt, rs);
		}
		
		return map;
	}
	
	public Map<String, Object> selectOne(String sql, List<String> param) {
		conn = getConnection();
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			stmt = conn.prepareStatement(sql);
			
			ResultSetMetaData meta = rs.getMetaData();
			
			while(rs.next()) {
				for(int i = 1; i <= meta.getColumnCount(); i++) {
					map.put(meta.getColumnName(i), rs.getObject(i));
				}
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			disConnect(conn, stmt, pstmt, rs);
		}
		
		return map;
	}
	
	//자원반납
	public void disConnect(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(conn != null) try {conn.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(rs != null) try {rs.close();} catch(SQLException e) {}
	}
}
