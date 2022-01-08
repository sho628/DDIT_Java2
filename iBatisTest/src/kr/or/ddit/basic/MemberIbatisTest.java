package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberIbatisTest {
	public static void main(String[] args) {
		
		// iBatis 를 이용하여 DB자료를 처리하는 순서
		// 1. ibatis의 환경설정 파일을 읽어와 실행시킨다.
		try {
			// 1-1. xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8");//설정파일의 인코딩 설정
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			
			// 2-1. insert연습
			System.out.println("insert 작업 시작...");

			// 1) 저장할  데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMemId("b003");
			mv.setMemName("강감찬3");
			mv.setMemTel("010-2222-3333");
			mv.setMemAddr("경상남도 진주시");
			
			
			// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			//  형식) smc.insert("namespace값.id값", 파라미터객체);
			//   반환값 : 성공하면 null이 반환된다.
			/*Object obj = smc.insert("memberTest.insertMember", mv);
			if(obj == null) {
				System.out.println("insert 작업 성공");
			}else {
				System.out.println("insert 작업 실패!!!");
			}
			
			int cnt = smc.update("memberTest.insertMember", mv);
			if(cnt > 0) {
				System.out.println("insert 작업 성공");
			}else {
				System.out.println("insert 작업 실패!!!");
			}
			
			System.out.println("--------------------------------");
			*/
			// 2-2. update작업 연습
			System.out.println("update작업 시작...");
			
			MemberVO mv2 = new MemberVO();
			mv2.setMemId("b001");
			mv2.setMemName("강인혜");
			mv2.setMemTel("2222-2222");
			mv2.setMemAddr("대전시 서구 ");
			
			// update()의 반환값은 성공한 레코드 수 이다.
			int cnt = smc.update("memberTest.updateMember", mv2);
			
			if(cnt > 0)	{
				System.out.println("update작업 성공.");
			}else {
				System.out.println("update작업 실패!!!");
			}
			System.out.println("----------------------------------");
			
			// 2-3. delete 연습
			System.out.println("delete 작업 시작...");
			
			// delete메서드의 반환값 : 성공한 레코드 수
			
			int cnt2 = smc.delete("memberTest.deleteMember", "b001");
			if(cnt2 > 0) {
				System.out.println("delete작업 성공");
			}else {
				System.out.println("delete작업 실패!!!");
			}
			System.out.println("-----------------------------------");
			
			// 2-4. select 연습
			// 1) 응답의 결과가 여러개일 경우
			/*System.out.println("select연습 (결과가 여러개 일 경우)...");
			List<MemberVO> memList;
			
			// 응답 결과가 여러개 일 경우에는 queryForList메서드를 사용한다.
			// 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를
			// List에 추가해 주는 작업을 자동으로 수행한다.
			memList = smc.queryForList("memberTest.getMemberAll");
			for(MemberVO memVO : memList) {
				System.out.println("ID : " + memVO.getMemId());
				System.out.println("이름 : " + memVO.getMemName());
				System.out.println("전화 : " + memVO.getMemTel());
				System.out.println("주소 : " + memVO.getMemAddr());
				System.out.println("============================");
			}
			System.out.println("출력 끝...");
			*/
			
			// 2) 응답이 1개일 경우
			System.out.println("select 연습 시작(결과가 1개일 경우)...");
			
			// 응답 결과가 1개가 확실할 경우에는 queryForObject메서드를 사용한다.
			MemberVO mv3 =
			(MemberVO) smc.queryForObject("memberTest.getMember", "b002");
			System.out.println("ID : " + mv3.getMemId());
			System.out.println("이름 : " + mv3.getMemName());
			System.out.println("전화 : " + mv3.getMemTel());
			System.out.println("주소 : " + mv3.getMemAddr());
			
			System.out.println("출력 끝...");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}
}
