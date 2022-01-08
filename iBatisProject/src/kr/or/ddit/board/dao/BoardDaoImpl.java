package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class BoardDaoImpl implements BoardDao{
	
	//싱글톤 - 자신을 담을 변수 설정 , Interface기반으로 선언(객체 지향성), 
	private static BoardDao boardDao; 
	
	private SqlMapClient smc;
	
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	public static BoardDao getInstance() {
		
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	public int insertBoard(BoardVO mv) throws SQLException {
		
		int cnt = smc.update("Board.insertBoard", mv);
		
		return cnt;
	}

	public boolean chkBoard(String boardNo) throws SQLException {
		boolean chk = false; //중복여부 확인용 플래그
		
		int cnt = (int) smc.queryForObject("Board.checkBoard", boardNo);
		
		if(cnt > 0) {
			chk = true;
		}
		
		return chk;
	}


	public int updateBoard(BoardVO mv) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.update("Board.updateBoard", mv);
		
		return cnt;
	}

	public int deleteBoard(String boardNo) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.delete("Board.deleteBoard", boardNo);
		return cnt;
	}

	public List<BoardVO> getAllBoardList() throws SQLException {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		boardList = smc.queryForList("Board.getAllBoardList");
		
		return boardList;
	
	}


	@Override
	public List<BoardVO> getSearchBoard(BoardVO mv) throws SQLException {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		boardList = smc.queryForList("Board.getSearchBoard");
		
		return boardList;
	}
	
	

}
