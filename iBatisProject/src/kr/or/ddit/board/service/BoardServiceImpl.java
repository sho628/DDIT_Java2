package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO;


public class BoardServiceImpl implements BoardService{

	private BoardDao boardDao;
	
	
	private BoardServiceImpl() {
	      boardDao = BoardDaoImpl.getInstance();
	    		  
	   }
	
	//서비스 객체를 담기위한 멤버변수 선언
	
	private static BoardService boardService;
	
	public static BoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	
	
	

	@Override
	public int insertboard(BoardVO mv) {
		  int cnt = 0;
	      
	      try {
	         cnt = boardDao.insertBoard(mv);
	      } catch (SQLException e) {
	         
	         e.printStackTrace();
	      }
	      return cnt;
	   }

	

	@Override
	public boolean chkboard(String boardNo) {
 
		boolean chk = false;
         
         try {
            chk = boardDao.chkBoard(boardNo);
         } catch (SQLException e) {
           
            e.printStackTrace();
         }
         
         return chk;

   }

	

	@Override
	public int updateboard(BoardVO mv) {
	     int cnt = 0;
	      
	      try {
	         cnt = boardDao.updateBoard(mv);
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return cnt;
	   }

	

	@Override
	public int deleteboard(String boardNo) {
		  int cnt = 0;
	      
	      try {
	         cnt = boardDao.deleteBoard(boardNo);
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return cnt;
	   }

	

	@Override
	public List<BoardVO> getAllboardList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
	      
	      try {
	         boardList = boardDao.getAllBoardList();
	      }catch(SQLException ex) {
	         ex.printStackTrace();
	      }
	      return boardList;
	   }

	@Override
	public List<BoardVO> getSearchboard(BoardVO mv) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = boardDao.getSearchBoard(mv);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return boardList;
	}


	

	
}