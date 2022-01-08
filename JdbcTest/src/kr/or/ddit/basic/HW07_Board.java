package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.ddit.util.JdbcUtil;
import kr.or.ddit.util.ScanUtil;

public class HW07_Board {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public static void main(String[] args) {
		
		new HW07_Board().start();
		
		
	}

	public void start() {
		while(true){
		System.out.println("-----------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성일");
		System.out.println("-----------------------------------------");
		
		try {
		conn = JdbcUtil.getConnection();
		
		String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE"
				+" FROM JDBC_BOARD";
		
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery(sql);
		
		while(rs.next()) {
			String boardNo = rs.getString("BOARD_NO");
			String title = rs.getString("BOARD_TITLE");
			String writer = rs.getString("BOARD_WRITER");
			String date = rs.getString("BOARD_DATE");
			
			System.out.println(boardNo+ "\t" + title + "\t" + writer + "\t" + date);
			System.out.println("-----------------------------------------");
		}
		
		} catch (SQLException e) {
			System.out.println("게시판 조회 실패!");
			e.printStackTrace();
		} finally {
			JdbcUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
		System.out.println("1.조회\t2.등록\t0.종료");
		System.out.print("입력>");
		int input = ScanUtil.nextInt();
		
		switch (input) {
		case 1:
			select();
			break;
		case 2:
			insert();
			break;
		case 0:
			System.out.println("프로그램이 종료되었습니다.");
			System.exit(0);
			break;
		}
	}
	}
	private void delete(int boardNo) {
		System.out.print("정말 삭제 하시겠습니까?(Y/N)>");
		String input = ScanUtil.nextLine();
		
		if(input.equals("Y")){
			try {
				conn = JdbcUtil.getConnection();
				
				String sql = "DELETE FROM JDBC_BOARD"
						+ " WHERE BOARD_NO = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				
				int result = pstmt.executeUpdate();
				
				if(0 < result){
					System.out.println("삭제가 완료되었습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
			}
		}
	}

	private void update(int boardNo) {
		System.out.print("제목>");
		String title = ScanUtil.nextLine();
		System.out.print("내용>");
		String content = ScanUtil.nextLine();
		
		try {
			conn = JdbcUtil.getConnection();
			
			String sql = "UPDATE JDBC_BOARD"
					+ " SET TITLE = ?"
					+ " , CONTENT = ?"
					+ " WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardNo);
			
			int result = pstmt.executeUpdate();
			if(0 < result){
				System.out.println("수정이 완료되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	//게시판 등록
	private void insert() {
		
		System.out.print("제목>");
		String title = ScanUtil.nextLine();
		System.out.print("내용>");
		String content = ScanUtil.nextLine();
		System.out.print("작성자>");
		String memId = ScanUtil.nextLine();
		
		try {
			conn = JdbcUtil.getConnection();
			
			String sql = "INSERT INTO JDBC_BOARD VALUES ("
					+" board_seq.nextVal, ?, ?,SYSDATE, ?)";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, memId);
			pstmt.setString(3, content);
			
			int result = pstmt.executeUpdate();
			
			if(0 < result) {
				System.out.println("등록 완료");
			}else{
				System.out.println("등록 실패");
			}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		JdbcUtil.disConnect(conn, stmt, pstmt, rs);
	}
	}

	//게시판 조회	
	private void select() {
		System.out.println("조회할 게시물 번호 >");
		int input = ScanUtil.nextInt();
		
		try {
			conn = JdbcUtil.getConnection();
			
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, BOARD_DATE"
					+" FROM JDBC_BOARD"
					+" WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				System.out.println("번호\t: " + rs.getInt("BOARD_NO"));
				System.out.println("작성자\t: " + rs.getString("BOARD_WRITER"));
				System.out.println("작성일\t: " + rs.getString("BOARD_DATE"));
				System.out.println("제목\t: " + rs.getString("BOARD_TITLE"));
				System.out.println("내용\t: " + rs.getString("BOARD_CONTENT"));
				
				System.out.println("1.수정\t2.삭제\t0.목록");
				System.out.print("입력>");
				input = ScanUtil.nextInt();
				
				switch (input) {
				case 1:
					update(boardNo);
					break;
				case 2:
					delete(boardNo);
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}
}
