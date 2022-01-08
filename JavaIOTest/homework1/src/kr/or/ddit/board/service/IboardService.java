package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.boardVO;

/**
 * 우리가 만들어야하는 기능!!
 * Service객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 자료를 Controller에서 보내주는 역할을 수행한다.
 * 보통DAO의 메서드와 같은 구조를 갖는다.
 * 
 * @author pc17
 *
 */
public interface IboardService {
	

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성한 후 Service에 전달하는 
 * DAO의 Interface
 * @author pc17
 *
 */

	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1 이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	
	public int insertCon(boardVO mv) ;
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 검색할 회원ID
	 * @return	해당회원 ID가 있으면 true,없으면 false
	 */
	
	public boolean chkboard(String writer) ;
	
	/**
	 * 하나의MemberVO자료를  이용하여 DB를 update하는 메서드
	 * @param mv update할 회원정보가 들어있는 MemberVO객체
	 * @return 작업성공:1, 작업실패:0
	 */
	public int updateCon(boardVO mv) ;
	
	/**
	 * 회원 ID를 매개변수로 받아서 그 회원정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공:1, 작업실패:0
	 */
	public int deleteCon(String Writer) ;
	/**
	 * mymeber 테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고있는 List객체
	 */
	public List<boardVO> getAllConList() ;
	
	public List<boardVO> getSearchCon(boardVO mv);//throws는 예외처리할 자신이 없을때 사용
	
}
