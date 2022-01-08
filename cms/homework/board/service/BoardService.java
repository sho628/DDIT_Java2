package board.service;

import java.util.ArrayList;
import java.util.List;

import board.dao.BoardDao;
import board.dao.IBoardDao;
import board.vo.BoardVO;

public class BoardService implements IBoardService{
	
	private static IBoardService instance;
	private IBoardDao boardDao;
	
	private BoardService() {
		boardDao = BoardDao.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(instance == null) {
			instance = new BoardService();
		}
		return instance;
	}

	@Override
	public int insert(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = boardDao.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int delete(String no) {
		int cnt = 0;
		
		try {
			cnt = boardDao.delete(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int update(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = boardDao.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> search(BoardVO vo) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			boardList = boardDao.search(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public List<BoardVO> getListAll() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			boardList = boardDao.getListAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public boolean checkMember(String no) {
		boolean chk = false;
		
		try {
			chk = boardDao.checkMember(no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chk;
	}

	

}
