package board;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import board.service.BoardService;
import board.service.IBoardService;
import board.vo.BoardVO;
import util2.ScanUtil;

public class BoardMain {
	
	private static IBoardService boardService;
	
	public BoardMain() {
		boardService = BoardService.getInstance();
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2.  글  삭제");
		System.out.println("  3.  글  수정");
		System.out.println("  4.  글  검색");		
		System.out.println("  5. 전체 목록 출력");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = ScanUtil.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 작성
					insert();
					break;
				case 2 :  // 삭제
					delete();
					break;
				case 3 :  // 수정
					update();
					break;
				case 4 :  // 검색
					search();			
					break;
				case 5 :	// 전체 목록
					getListAll();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	
	
	private void delete() {
		boolean chk = false; 
		String num = "";
		do {
			System.out.println();
			System.out.println("삭제할 게시글 번호를 입력하세요.");
			System.out.print("게시글 번호 >");
			num = ScanUtil.nextLine();
			
			chk = checkMember(num);
			
			if(chk == false) {
				System.out.println(num + " 은 없는 게시글입니다.");
				System.out.println("다시 입력하세요");
			}
			
		}while(chk == false);
		
		int cnt = boardService.delete(num);
		
		if(cnt > 0) {
			System.out.println("삭제 성공");
		} else System.out.println("삭제 실패");
		
	}

	private void update() {
		boolean chk = false; 
		String num = "";
		do {
			System.out.println();
			System.out.println("수정할 게시글 번호를 입력하세요.");
			System.out.print("게시글 번호 >");
			num = ScanUtil.nextLine();
			
			chk = checkMember(num);
			
			if(chk == false) {
				System.out.println(num + " 은 없는 게시글입니다.");
				System.out.println("다시 입력하세요");
			}
			
		}while(chk == false);
		
		System.out.print("새로운 게시글 제목 입력 >");
		String title = ScanUtil.nextLine();
		
		System.out.print("새로운 작성자 입력 >");
		String name = ScanUtil.nextLine();
		
		System.out.print("새로운 게시글 내용 입력 >");
		String content = ScanUtil.nextLine();
		
		BoardVO vo = new BoardVO();
		vo.setNo(num);
		vo.setTitle(title);
		vo.setName(name);
		vo.setContent(content);
		
		int cnt = boardService.update(vo);
		
		if(cnt > 0) {
			System.out.println("업데이트 성공");
		} else System.out.println("업데이트 실패");
		
		
	}

	private void getListAll() {
		List<BoardVO> boardList = new ArrayList<>();
		
		System.out.println("번호\t제목\t작성자\t작성일\t내용");
		System.out.println();
		
		boardList = boardService.getListAll();
		
		for(BoardVO vo : boardList) {
			System.out.println(vo.getNo() + "\t" + vo.getTitle() 
						+ "\t" + vo.getName() + "\t" + vo.getDate()
						+ "\t" + vo.getContent());
		}
		
		System.out.println("전체 출력 완료");

	}

	private void search() {
		List<BoardVO> boardList = new ArrayList<>();
		System.out.println();
		System.out.print("검색할 게시판 번호 입력 >");

		String num = ScanUtil.nextLine();


		

		System.out.print("검색할 게시판 제목 입력>");
		String title = ScanUtil.nextLine();

		System.out.print("검색할 게시판 작성자 입력>");
		String name = ScanUtil.nextLine();
		
		System.out.println("검색할 게시판 작성일 입력");
		System.out.print("YYYY/MM/DD 형식 입력 >");
		String date = ScanUtil.nextLine();


		System.out.print("검색할 게시판 내용 입력>");
		String content = ScanUtil.nextLine();
		
		System.out.println();
		
		BoardVO vo = new BoardVO();
		
		vo.setNo(num);
		vo.setTitle(title);
		vo.setName(name);
		vo.setDate(date);
		vo.setContent(content);

		boardList = boardService.search(vo);
		
		System.out.println("번호\t제목\t작성자\t작성일\t내용");
		System.out.println();
		
		for(BoardVO vo2 : boardList) {
			System.out.println(vo2.getNo() + "\t" + vo2.getTitle()
			+ "\t" + vo2.getName() + "\t" + vo2.getDate()
			+ "\t" + vo2.getContent());
		}

		System.out.println();
		System.out.println("검색 작업 완료");



		
	}

	private void insert() {
		
		System.out.print("게시글 제목 입력 >");
		String title = ScanUtil.nextLine();
		
		System.out.print("작성자 입력 >");
		String name = ScanUtil.nextLine();
		
		System.out.print("게시글 내용 입력 >");
		String content = ScanUtil.nextLine();
		
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setName(name);
		vo.setContent(content);
		
		int cnt = boardService.insert(vo);
		
		if(cnt > 0) {
			System.out.println("게시글 추가 성공");
		} else System.out.println("게시글 추가 실패");
		
	}
	
	private boolean checkMember(String num) {
		boolean chk = false;
		
		chk = boardService.checkMember(num);
		return chk;
	}

	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();
	}

}






