package kr.or.ddit.border;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.board.service.boardServiceImpl;
import kr.or.ddit.board.vo.boardVO;


/*
	위의 테이블을 작성하고 게시판을 관리하는
	다음 기능들을 구현하시오.

	기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

	게시판 테이블 구조 및 시퀀스

	create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
	);
	create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal
);

*/
public class boardmain {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	private IboardService borService;

	public boardmain() {
		borService = new boardServiceImpl();
	}

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글 입력");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 글 출력");
		System.out.println("  5. 글검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 글 입력
				insert();
				break;
			case 2: // 글 삭제
				delete();
				break;
			case 3: // 글 수정
				update();
				break;
			case 4: // 전체 글 출력
				displayAll();
				break;
			case 5: // 검색기능
				search();
				break;
			case 6: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	/**
	 * 회원정보를 삭제하는 메서드 (입력받은 회원ID를 이용하여 삭제한다.)
	 */
	private void delete() {
		System.out.println();
		System.out.println("삭제할 글의 제목을 입력하세요.");
		System.out.println("글제목>>");
		String title = scan.next();

		int cnt = borService.deleteCon(title);

		if (cnt > 0) {
			System.out.println(title + "글삭제 성공!");
		} else {
			System.out.println(title + "글 삭제 실패!!");
		}

	}

	/**
	 * 회원정보를 수정하는 메서드
	 */
	private void update() {
		boolean chk = false;// 회원 중복 여부 체크용
		String writer = "";

		do {
			System.out.println();
			System.out.println("수정할 회원의 아이디를 입력하세요.");
			System.out.println("아이디>>");
			writer = scan.next();

			chk = checkboard(writer);
			if (chk == false) {
				System.out.println(writer + "인 회원은 없는 회원입니다.");
				System.out.println("다시 입력하세요.");
			}

		} while (chk == false);
		System.out.print("새로운 제목 이름>>");
		String title = scan.next();
		System.out.print("새로운 내용 주소>>");
		scan.nextLine();// 입력버퍼 비우기
		String content = scan.nextLine();
		// 커넥션 -> 스테이트먼트 -> 결과값가져오기

		boardVO mv = new boardVO();
		mv.setBorWriter(writer);
		mv.setBorTit(title);
		mv.setBorContent(content);
		
		int cnt = borService.updateCon(mv);

		if (cnt > 0) {
			System.out.println(title + "글 수정완료");
		} else {
			System.out.println(title + "글 수정실패!!");
		}

	}

	private void displayAll() {
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("순번\t제목\t작성자\t날짜\t\t내용");
		System.out.println("-------------------------------");

		List<boardVO> memList = borService.getAllConList();
		for (boardVO mv : memList) {

			System.out.println(mv.getNum()+"\t" +mv.getBorTit() + "\t" + mv.getBorWriter() + "\t"  + "\t" + mv.getBorDate()+ "\t" + mv.getBorContent());
		}

		System.out.println("-------------------------------------------");
		System.out.println("출력 작업 끝...");

	}

	/**
	 * 글 추가하는 메서드
	 */
	private void insert() {

		System.out.println("제목>>");
		String title = scan.next();
		System.out.println("작성자>>");
		String writer = scan.next();
		System.out.println("내용>>");
		scan.nextLine();// 입력버퍼 비우기
		String content = scan.nextLine();
		// 커넥션 -> 스테이트먼트 -> 결과값가져오기
		boardVO mv = new boardVO();
	
		mv.setBorTit(title);
		mv.setBorWriter(writer);
		mv.setBorContent(content);
		int cnt = borService.insertCon(mv);

		if (cnt > 0) {
			System.out.println(title + "글 게시 성공!");
		} else {
			System.out.println(title + "글 게시 실패!!!");
		}
	}

	/**
	 * 회원ID를 이용하여 회원이 있는지 알려주는 메서드
	 * 
	 * @param memId
	 *            회원ID
	 * @return 존재하면 true, 아니면false
	 */
	private boolean checkboard(String writer) {
		boolean chk = false;
		chk = borService.chkboard(writer);

		return chk;
	}

	/**
	 * 회원 정보를 검색하는 메서드
	 */
	public void search() {
		/*
		 	검색할 회원ID, 회원이름, 전화번호, 주소 등을 입력하면 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		 	주소는 입력한 값이 포함만 되어도 검색 되도록 한다.
		 	입력을 하지 않은 자료는 엔터키로 다음 입력으로 넘긴다.
		 */
			scan.nextLine();//입력버퍼 비우기
			
			System.out.println();
			System.out.println("검색할 정보를 입력하세요.");
			System.out.println("제목>>");
			String title = scan.nextLine().trim();//trim은 좌우 공백을 제거해 주기위해서 사용
			
			System.out.println("작성자>>");
			String writer = scan.nextLine().trim();//trim은 좌우 공백을 제거해 주기위해서 사용
			
			System.out.println("내용>>");
			String content = scan.nextLine().trim();//trim은 좌우 공백을 제거해 주기위해서 사용
			
			boardVO mv = new boardVO();
			mv.setBorTit(title);
			mv.setBorWriter(writer);
			mv.setBorContent(content);
			
			
			System.out.println();
			System.out.println("-------------------------------");
			System.out.println("순번\t제목\t내  용\t날짜\t\t내용");
			System.out.println("-------------------------------");

			List<boardVO> borList = borService.getSearchCon(mv);
			for (boardVO mv2 : borList) {

				System.out.println(mv2.getNum()+"\t" +mv2.getBorTit()+ "\t" + mv2.getBorWriter()+ "\t" + "\t" + mv2.getBorDate()+ "\t" + mv2.getBorContent()  );
			}

			System.out.println("-------------------------------------------");
			System.out.println("검색 작업 끝...");
	
	}
	
	public static void main(String[] args) {
		boardmain borObj = new boardmain();
		borObj.start();
	}

}
