package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.dao.boardDaoImpl;
import kr.or.ddit.board.vo.boardVO;
import kr.or.ddit.board.vo.boardVO;
import kr.or.ddit.board.vo.boardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class boardDaoImpl  implements IboardDao {
	private static IboardDao borDao;
	private SqlMapClient smc;
	private boardDaoImpl() {
		smc= SqlMapClientFactory.getInstance();
	}

	public static IboardDao getInstance() {
		if(borDao == null) {
			borDao = new boardDaoImpl();
		}
		return borDao;
	}
	@Override
	public int insertCon(boardVO mc) throws SQLException {
		int cnt = smc.update("board.insertCon",mc);
		return cnt;
	}
	@Override
	public boolean chkboard(String borWriter) throws SQLException {
		boolean chk = false;
		
		int cnt = (int) smc.queryForObject("board.checkboard",borWriter);

		return chk;
		
	}
	@Override
	public int updateCon(boardVO mv) throws SQLException {
		int cnt=0;
		cnt = smc.update("board.updateCon",mv);
		
		return cnt;
	}
	@Override
	public int deleteCon(String borTit) throws SQLException {
		
		int cnt =0;
		
		cnt = smc.delete("board.deleteCon",borTit);
		return cnt;
	}
	@Override
	public List<boardVO> getAllConList() throws SQLException {
		
		List<boardVO> memList = new ArrayList<boardVO>();
		
		memList = smc.queryForList("board.getboardAll");
		return memList;
	}
	@Override
	public List<boardVO> getSearchCon(boardVO mv) throws SQLException {
		List<boardVO> borList = new ArrayList<>();
		
		borList = smc.queryForList("board.getSearchCon",mv);
		return borList;
	}
	
	
	
}
