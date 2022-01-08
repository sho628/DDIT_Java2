package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {


	public List<BoardVO> SeachBoard(BoardVO bv);
	
	public int deleteBoard(int no);
	
	public int updateBoard(BoardVO bv);
	
	public int newBoard(BoardVO bv);
	
	public List<BoardVO> listBoard();

	public BoardVO ViewBoard(int no);
}
