package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.vo.BoardVO;


public class BoardIbatisTest {
	public static void main(String[] args) {
		// iBatis 를 이용하여 DB자료를 처리하는 순서
		// 1. ibatis의 환경설정 파일을 읽어와 실행시킨다.
		
		try {
			
			// 1-1 xml문서 읽어오기 - txt보다 xml을 쓰는 이유 : 정확한 데이터를 표현하는 공식적인 문서 
			Charset charset = Charset.forName("UTF-8"); // 설정파일의 인코딩 설정
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml"); //설정파일을 읽을 수 있는 reader만들고 
			
			//1-2 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); //Reader객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			
			// 2-1. insert연습
			
			//1) 저장할 데이터를 VO에 담는다.
			System.out.println("insert 작업 시작...");
			BoardVO mv = new BoardVO();
			mv.setBoardNo("2");
			mv.setBoardTitle("안녕하세요");
			mv.setBoardContent("반갑습니다");
			mv.setBoardWriter("전희");
			
			
			
			// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식 ) smc.insert("Titlespace값.No값", 파라미터객체);
			// 반환값 : 성공하면  null이반환된다.
			
			Object obj = smc.insert("BoardTest.insertBoard", mv); //object로 리턴 
			if(obj == null) {
				System.out.println("insert 작업 성공");
				
			}else {
				System.out.println("insert 작업 실패 ");
			}
			
			
			int cnt = smc.update("BoardTest.insertBoard", mv);
			if(cnt > 0) {
				System.out.println("insert 작업 성공");
				
			}else {
				System.out.println("insert 작업 실패 ");
			}

			System.out.println("----------------------------------------");
			
			//2-2. update작업 연습
			
			System.out.println("update작업 시작...");
			
			BoardVO mv2 = new BoardVO(); //업데이트 할 내용 설정 
			mv2.setBoardNo("3");
			mv2.setBoardTitle("안녕하십니까");
			mv2.setBoardContent("반갑습니다요");
			mv2.setBoardWriter("정경민");
			
			//update()의 반환값은 성공한 레코드 수 이다. 
			
			cnt = smc.update("BoardberTest.updateBoard", mv2);
			
			if(cnt > 0) {
				System.out.println("업데이트 성공");
			}else {
				System.out.println("업데이트 실패 ");
			}
			
			//2-3. delete 작업 연습 
			System.out.println("delete 작업 시작...");
			
			//delete메서드의 반환값 : 성공한 레코드 수 
			
			int cnt2 = smc.delete("BoardberTest.deleteBoard", "b001");
			if(cnt2 > 0) {
				System.out.println("delete작업 성공");
			}else {
				System.out.println("delete작업 실패");
				
			}
			
			//2-4. select 연습
			//1)응답의 결과가 여러개일 경우 
			System.out.println("select연습(결과가 여러개 일 경우)...");
			List<BoardVO> BoardList;
			
			//응답 결과가 여러개 일 경우에는 queryForList메서드를 사용한다.
			//이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 추가해주는 작업을 자동으로 수행한다. 
			
			BoardList = smc.queryForList("BoardberTest.getAllBoardber"); //파라미터가 있다면 컴마 후 파라미터 넣어주기 
			
			for(BoardVO BoardVO : BoardList) {
				System.out.println("No : " + BoardVO.getBoardNo());
				System.out.println("이름 : " + BoardVO.getBoardTitle());
				System.out.println("전화 : " + BoardVO.getBoardContent());
				System.out.println("주소 : " + BoardVO.getBoardWriter());
				System.out.println("===================================");
			}
			System.out.println("출력끝...");
			
			
			//2)응답이 1개일 경우 
			System.out.println("select 연습 시작(결과가 1개일 경우)...");
			
			//응답 결과가 1개가 확실할 경우에는 queryForObject메서드를 사용한다. 
			
			BoardVO mv3 = (BoardVO) smc.queryForObject("BoardberTest.getBoard","a123");
			System.out.println("No : " + mv3.getBoardNo());
			System.out.println("이름 : " + mv3.getBoardTitle());
			System.out.println("전화 : " + mv3.getBoardContent());
			System.out.println("주소 : " + mv3.getBoardWriter());
			
			System.out.println("출력 끝 ...");
			
					
		}catch(IOException ex) {
			ex.printStackTrace();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			
		
	}
}
