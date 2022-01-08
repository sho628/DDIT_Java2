package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JdbcUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	@Override
	public int insertMember(MemberVO mv) throws SQLException {
		
		int cnt = 0;
		
		try {
			conn = JdbcUtil3.getConnection();
			String sql = " insert into mymember "
					    + " (mem_id, mem_name, mem_tel, mem_addr) "
					    + " values "
					    + " (?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public boolean chkMember(String memId) throws SQLException {
		
		boolean chk = false;
		
		try {
			conn = JdbcUtil3.getConnection();
			
			String sql = "select count(*) cnt from mymember "
					   + " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int  cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true; // 중복임.
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		return chk;
	}

	@Override
	public int updateMember(MemberVO mv) throws SQLException {
		int cnt = 0;
		
		try {
			conn = JdbcUtil3.getConnection();
			
			String sql = " update mymember " +
					     " set mem_name = ?, " + 
					     "    mem_tel = ?, " +
					     "    mem_addr = ? " +
					     " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) throws SQLException {
		
		int cnt = 0;
		
		try {
			conn = JdbcUtil3.getConnection();
			
			String sql = "delete from mymember "
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() throws SQLException {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JdbcUtil3.getConnection();
			
			String sql = "select * from mymember ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setMemId(rs.getString("mem_id"));
				mv.setMemName(rs.getString("mem_name"));
				mv.setMemTel(rs.getString("mem_tel"));
				mv.setMemAddr(rs.getString("mem_addr"));
				
				memList.add(mv);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = JdbcUtil3.getConnection();
			
			String sql = "select * from mymember where 1=1 ";
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += " and mem_id = ? ";
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++, mv.getMemTel());
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mv2 = new MemberVO();
				mv2.setMemId(rs.getString("mem_id"));
				mv2.setMemName(rs.getString("mem_name"));
				mv2.setMemTel(rs.getString("mem_tel"));
				mv2.setMemAddr(rs.getString("mem_addr"));
				
				memList.add(mv2);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

}
