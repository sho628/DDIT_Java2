package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;


public interface BoardService {

	
	public int insertboard(BoardVO mv); //파라미터로 멤버VO를 던졌을때 받아서 int값 리턴
	

	public boolean chkboard(String boardNo);
	
	
	public int updateboard(BoardVO mv);
	
	public int deleteboard(String boardNo);
	
	public List<BoardVO> getAllboardList();
	
	public List<BoardVO> getSearchboard(BoardVO mv);
	

}
