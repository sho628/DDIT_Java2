package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class BoardDao implements IBoardDao {
	private static IBoardDao boardDao;
	private SqlMapClient smc;

	private BoardDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDao();
		}
		return boardDao;
	}

	@Override
	public List<BoardVO> SeachBoard(BoardVO bv) throws SQLException {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		boardList = smc.queryForList("board.searchBoard", bv);
		return boardList;
	}

	@Override
	public int deleteBoard(int no) throws SQLException {
		int cnt = smc.delete("board.deleteBoard", no);
		return cnt;
	}		


	@Override
	public int updateBoard(BoardVO bv) throws SQLException {
		int cnt = smc.update("board.updateBoard", bv);
		return cnt;
	}

	@Override
	public int newBoard(BoardVO bv) throws SQLException {
		int cnt = smc.update("board.newBoard", bv);
		return cnt;
	}

	@Override
	public List<BoardVO> listBoard() throws SQLException {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		boardList = smc.queryForList("board.listBoard");
		return boardList; 
	}
	@Override
	public BoardVO ViewBoard(int no) throws SQLException {
		BoardVO bv = new BoardVO();
		bv = (BoardVO)smc.queryForObject("board.viewBoard", no);
		return bv;
	}
}
