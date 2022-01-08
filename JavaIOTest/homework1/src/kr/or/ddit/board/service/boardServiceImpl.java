package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.dao.boardDaoImpl;
import kr.or.ddit.board.vo.boardVO;

public class boardServiceImpl implements IboardService{

	private IboardDao memDao;
	
	public boardServiceImpl() {
		memDao =  boardDaoImpl.getInstance();
	}
	
	
	
	@Override
	public int insertCon(boardVO mv) {
		
		int cnt = 0;
		
		try {
			cnt = memDao.insertCon(mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public boolean chkboard(String writer) {
		boolean chk = false;
		
		try {
			chk = memDao.chkboard(writer);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public int updateCon(boardVO mv) {
		int cnt=0;
		try {
			cnt = memDao.updateCon(mv);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteCon(String writer) {
		int cnt = 0;
		try {
			cnt = memDao.deleteCon(writer);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<boardVO> getAllConList() {
		List<boardVO> memList = new ArrayList<boardVO>();
		 try {
			memList = memDao.getAllConList();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return memList;
	}



	@Override
	public List<boardVO> getSearchCon(boardVO mv) {
		List<boardVO> memList = new ArrayList<>();
		try {
			memList = memDao.getSearchCon(mv);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return memList;
	}

}
