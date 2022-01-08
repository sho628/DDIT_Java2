package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import board.vo.BoardVO;
import util2.JDBCUtil3;

public class BoardDao implements IBoardDao{
	
	private static IBoardDao instance;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private JDBCUtil3 jdbc;
	
	private BoardDao() {
		jdbc = JDBCUtil3.getInstance();
	}
	
	public static IBoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	@Override
	public int insert(BoardVO vo) throws SQLException {
		
		conn = jdbc.getConnection();
		
		String sql = "INSERT INTO JDBC_BOARD VALUES(BOARD_SEQ.nextVal, ?, ?, SYSDATE, ?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getName());
		pstmt.setString(3, vo.getContent());
		
		int cnt = pstmt.executeUpdate();
		
		jdbc.disConnect(conn, stmt, pstmt, rs);
		
		return cnt;
		
	}

	@Override
	public int delete(String no) throws SQLException {
		conn = jdbc.getConnection();
		
		String sql = "DELETE JDBC_BOARD WHERE BOARD_NO = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(no));
		
		int cnt = pstmt.executeUpdate();
		jdbc.disConnect(conn, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public int update(BoardVO vo) throws SQLException {
		conn = jdbc.getConnection();
		
		String sql = "UPDATE JDBC_BOARD SET BOARD_TITLE = ?, "
				+ " BOARD_WRITER = ?, BOARD_DATE = SYSDATE, "
				+ " BOARD_CONTENT = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getName());
		pstmt.setString(3, vo.getContent());
		
		int cnt = pstmt.executeUpdate();
		jdbc.disConnect(conn, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public List<BoardVO> search(BoardVO vo) throws SQLException {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = jdbc.getConnection();


			String sql = "SELECT * FROM JDBC_BOARD WHERE 1=1 ";
			
			if(String.valueOf(vo.getNo()) != null && !String.valueOf(vo.getNo()).equals("")) 
				sql += " AND BOARD_NO = ? ";

			if(vo.getTitle() != null && !vo.getTitle().equals("")) 
				sql += " AND BOARD_TITLE = ? ";
			
			if(vo.getName() != null && !vo.getName().equals("")) 
				sql += " AND BOARD_WRITER = ? ";
			
			if(vo.getDate() != null && !vo.getDate().equals(""))
				sql += " AND TO_CHAR(BOARD_DATE, 'YYYY/MM/dd') = ? ";
			
			if(vo.getContent() != null && !vo.getContent().equals("")) 
				sql += " AND BOARD_CONTENT LIKE '%' || ? || '%'";

			pstmt = conn.prepareStatement(sql);
	
			
			int index = 1;
			if(vo.getNo() != null && !vo.getNo().equals(""))
				pstmt.setInt(index++, Integer.parseInt(vo.getNo()));
			
			if(vo.getTitle() != null && !vo.getTitle().equals(""))
				pstmt.setString(index++, vo.getTitle());
			
			if(vo.getName() != null && !vo.getName().equals("")) 
				pstmt.setString(index++, vo.getName());
			
			if(vo.getDate() != null && !vo.getDate().equals(""))
				pstmt.setString(index++, vo.getDate());
			
			if(vo.getContent() != null && !vo.getContent().equals("")) 
				pstmt.setString(index++, vo.getContent());

			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");

			while(rs.next()) {
				
				BoardVO vo2 = new BoardVO();
				vo2.setNo(String.valueOf(rs.getInt("BOARD_NO")));
				vo2.setTitle(rs.getString("BOARD_TITLE"));
				vo2.setName(rs.getString("BOARD_WRITER"));
				vo2.setDate(sdf.format(rs.getDate("BOARD_DATE")));;
				vo2.setContent(rs.getString("BOARD_CONTENT"));

				boardList.add(vo2);
			}
		}finally {
			jdbc.disConnect(conn, stmt, pstmt, rs);
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> getListAll() throws SQLException {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = jdbc.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
			
			while(rs.next()) {
				
				BoardVO vo = new BoardVO();
				vo.setNo(String.valueOf(rs.getInt("BOARD_NO")));
				vo.setTitle(rs.getString("BOARD_TITLE"));
				vo.setName(rs.getString("BOARD_WRITER"));
				vo.setDate(sdf.format(rs.getDate("BOARD_DATE")));;
				vo.setContent(rs.getString("BOARD_CONTENT"));

				boardList.add(vo);
			}
			
		} finally {
			jdbc.disConnect(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

	@Override
	public boolean checkMember(String no) throws SQLException {
		boolean chk = false;
		
		try {
			conn = jdbc.getConnection();
			
			String sql = "SELECT count(*) as cnt FROM JDBC_BOARD "
					+ " WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(no));
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true; // 중복
			}
			
		} finally {
			jdbc.disConnect(conn, stmt, pstmt, rs);
		}
		return chk;
	}

}
