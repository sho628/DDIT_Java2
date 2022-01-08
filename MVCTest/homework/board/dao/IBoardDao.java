package board.dao;

import java.sql.SQLException;
import java.util.List;

import board.vo.BoardVO;

public interface IBoardDao {
	
	public int insert(BoardVO vo) throws SQLException;
	
	public int delete(String no) throws SQLException;
	
	public int update(BoardVO vo) throws SQLException;
	
	public List<BoardVO> search(BoardVO vo) throws SQLException;
	
	public List<BoardVO> getListAll() throws SQLException;
	
	public boolean checkMember(String no) throws SQLException;
	
	
		
}
