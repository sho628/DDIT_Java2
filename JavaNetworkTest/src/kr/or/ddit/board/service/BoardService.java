package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardService implements IBoardService {

	private static IBoardService boardService;
	private IBoardDao boardDao;
	private BoardService(){
		boardDao = BoardDao.getInstance();
	}
	public static IBoardService getInstance(){
		if(boardService == null) {
			boardService = new BoardService();
		}
		return boardService;
	}
	
	@Override
	public List<BoardVO> SeachBoard(BoardVO bv) {
		List<BoardVO> bv2 = new ArrayList<BoardVO>();
		try {
			bv2 = boardDao.SeachBoard(bv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bv2;
	}
	
	@Override
	public int deleteBoard(int no) {
		int cnt = 0;
		try {
			cnt = boardDao.deleteBoard(no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = boardDao.updateBoard(bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int newBoard(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = boardDao.newBoard(bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public List<BoardVO> listBoard() {
		List<BoardVO> bv = new ArrayList<BoardVO>();
		try {
			bv = boardDao.listBoard();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bv;
	}
	@Override
	public BoardVO ViewBoard(int no) {
		BoardVO bv = new BoardVO();
		try {
			bv = boardDao.ViewBoard(no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bv;
	}
}
