package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;


public interface BoardDao {

	public int insertBoard(BoardVO mv) throws SQLException; //파라미터로 멤버VO를 던졌을때 받아서 int값 리턴

	public boolean chkBoard(String boardNo) throws SQLException;
	
	public int updateBoard(BoardVO mv) throws SQLException;
	
	public int deleteBoard(String boardNo) throws SQLException;

	public List<BoardVO> getAllBoardList() throws SQLException;
	
	public List<BoardVO> getSearchBoard(BoardVO mv) throws SQLException; //예외를 던질 경우가 없다 호출하는 사람이 예외를 잘 처리해라
	
	
	
}
