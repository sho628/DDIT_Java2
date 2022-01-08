package board.service;

import java.util.List;

import board.vo.BoardVO;

public interface IBoardService {

	public int insert(BoardVO vo);

	public int delete(String no);

	public int update(BoardVO vo);

	public List<BoardVO> search(BoardVO vo);

	public List<BoardVO> getListAll();
	
	public boolean checkMember(String no);
}
