package kr.or.ddit.board;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;


public class BoardMain {
	
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	private BoardService boardService; //생성자에 만드는 방법 
	
	public BoardMain() {
		boardService = BoardServiceImpl.getInstance();
		//싱글톤때문에 getinstance 해줘야함
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시판 등록 ");
		System.out.println("  2. 게시판 삭제");
		System.out.println("  3. 게시판  수정");
		System.out.println("  4. 전체 게시판 목록");
		System.out.println("  5. 게시판 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 * @throws SQLException 
	 */
	public void start() throws SQLException{
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertBoard();
					break;
				case 2 :  // 자료 삭제
					deleteBoard();
					break;
				case 3 :  // 자료 수정
					updateBoard();
					break;
				case 4 :  // 전체 자료 출력
					displayBoardAll();
					break;
				case 5 :  // 자료검색
					searchBoard();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	
	//회원정보를 삭제하는 메서드
	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시판 번호를  입력하세요.");
		System.out.println("게시판 번호  >> ");
		String boardNo = scan.next();
		
		
		
		int cnt = boardService.deleteboard(boardNo);
			if(cnt > 0) {
				System.out.println(boardNo + "게시판 삭제 성공!");
			}else {
				System.out.println(boardNo + "게시판 삭제 실패 !");
			}
		
	}
	//회원 정보를 수정하는 메서드
	private void updateBoard() throws SQLException {
		
		boolean chk = false; //회원 중복 여부 체크용
		String boardNo = "";
		
		do {
			System.out.println();
			System.out.println("수정할 게시판 번호를 입력하세요.");
			System.out.println("게시판 번호 >> ");
			boardNo = scan.next();
			
			chk = checkBoard(boardNo);
			
			if(chk == false) {
				System.out.println(boardNo + "번인 게시판은 존재하지 않습니다.");
				System.out.println("다시 입력하세요.");
			}
			
		}while(chk == false);
		
		System.out.print("새로운 게시판 제목 >> ");
		String boardTitle = scan.next();
		
		System.out.print("새로운 게시판 내용 >> ");
		String boardContent = scan.next();
		
		System.out.print("새로운 작성자 >> ");
		scan.nextLine(); //입력버퍼 비우기
		String boardWriter = scan.nextLine();
		
		
		
		BoardVO mv = new BoardVO();
		mv.setBoardNo(boardNo);
		mv.setBoardTitle(boardTitle);
		mv.setBoardContent(boardContent);
		mv.setBoardWriter(boardWriter);
		
		int cnt = boardService.updateboard(mv);
		
		
		
		if(cnt > 0) {
			System.out.println(boardNo + "회원 정보를 수정 완료");
		}else {
			System.out.println(boardNo + "회원 정보 수정실패");
		}
	}

	/*
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayBoardAll() throws SQLException {
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("글번호\t제목\t내용\t작성자  ");
		System.out.println("-------------------------------------------------");
		
		List<BoardVO> boardList = boardService.getAllboardList();
		
		for(BoardVO mv : boardList) {
			System.out.println(mv.getBoardNo() + "\t" + mv.getBoardTitle() + "\t" + mv.getBoardContent() + "\t" + mv.getBoardWriter());
		}
		
		
		System.out.println("---------------------------------");
		System.out.println("출력 작업 끝..");
	}

	private void insertBoard() {

		
		System.out.print("게시판 글 번호 >> ");
		String boardNo = scan.next();
		
		System.out.print("게시판 제목 >> ");
		String boardTitle = scan.next();
		
		System.out.print("게시판 내용 >> ");
		String boardContent = scan.next();
		
		System.out.print("작성자 >> ");
		scan.nextLine(); //입력버퍼 비우기
		String boardWriter = scan.nextLine();

		BoardVO mv = new BoardVO();
		mv.setBoardNo(boardNo);
		mv.setBoardTitle(boardTitle);
		mv.setBoardContent(boardContent);
		mv.setBoardWriter(boardWriter);
		
		
		int cnt = boardService.insertboard(mv);
		
		if(cnt > 0) {
			System.out.println(boardNo + "게시판 추가 성공!");
		}else {
			System.out.println(boardNo + "게시판 추가 실패 !");
		}
		
	}

	/*
	 * 회원ID를 이용하여 회원이 있는지 알려주는 메서드
	 * @param boardId 회원ID
	 * @return 존재하면 true, 아니면 false
	 */
	private boolean checkBoard(String boardNo){
		boolean chk = false;
		
		chk = boardService.chkboard(boardNo);
		
		return chk;
	}
	
	/**
	 * 회원 정보를 검색하는 메서드 
	 */
	public void searchBoard() {
		/*
		  검색할 회원ID, 회원이름, 전화번호, 주소 등을 입력하면, 입력한 정보만 사용하여 검색하는 기능을 구현하시오
		  주소는 입력한 값이 포함만 되어도 검색 되도록 한다. 
		  입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		 */
		
		scan.nextLine(); //입력버퍼 지우기
		
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.println("게시판 번호 > ");
		String boardNo = scan.nextLine().trim(); //혹시모를 공백 방지
		
		System.out.println("게시판 제목 > ");
		String boardTitle = scan.nextLine().trim();
		
		
		BoardVO mv = new BoardVO();
		mv.setBoardNo(boardNo);
		mv.setBoardTitle(boardTitle);
		
		
		List<BoardVO> boardList = boardService.getSearchboard(mv);
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("글번호\t제목\t내용\t작성자 ");
		System.out.println("-------------------------------------------------");
		
		for(BoardVO mv2 : boardList) {
			System.out.println(mv2.getBoardNo() + "\t" + mv2.getBoardTitle() + "\t" + mv2.getBoardContent() + "\t" + mv2.getBoardWriter());
		}
		
		
		System.out.println("---------------------------------");
		System.out.println("검색 작업 끝..");
	}
	
	public static void main(String[] args) throws SQLException{
		BoardMain boardObj = new BoardMain();
		boardObj.start();
	}

}






