package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {

	public List<BoardVO> SeachBoard(BoardVO bv) throws SQLException;
	
	public int deleteBoard(int no) throws SQLException;
	
	public int updateBoard(BoardVO bv) throws SQLException;
	
	public int newBoard(BoardVO bv) throws SQLException;
	
	public List<BoardVO> listBoard() throws SQLException;
	
	public BoardVO ViewBoard(int no) throws SQLException;
	
}
