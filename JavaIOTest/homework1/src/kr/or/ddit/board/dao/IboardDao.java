package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.boardVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성한 후 Service에 전달하는 
 * DAO의 Interface
 * @author pc17
 *
 */
public interface IboardDao {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1 이상의 값이 반환되고, 실패하면 0이 반환된다.
	 * @throws SQLException JDBC관련 예외 객체
	 */
	public int insertCon(boardVO mv) throws SQLException;
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 검색할 회원ID
	 * @return	해당회원 ID가 있으면 true,없으면 false
	 * @throws SQLException JDBC관련 예외 객체
	 */
	public boolean chkboard(String writer) throws SQLException;
	
	/**
	 * 하나의MemberVO자료를  이용하여 DB를 update하는 메서드
	 * @param mv update할 회원정보가 들어있는 MemberVO객체
	 * @return 작업성공:1, 작업실패:0
	 * @throws SQLException JDBC관련 예외 객체
	 */
	public int updateCon(boardVO mv) throws SQLException;
	
	/**
	 * 회원 ID를 매개변수로 받아서 그 회원정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공:1, 작업실패:0
	 * @throws SQLException JDBC관련 예외 객체
	 */
	public int deleteCon(String memId) throws SQLException;
	/**
	 * mymeber 테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고있는 List객체
	 * @throws SQLException JDBC관련 예외 객체
	 */
	public List<boardVO> getAllConList() throws SQLException;
	
	/**
	 * MemberVO 에 담ㄷ긴 자료를 이용하여 회원을 검색하는 메서드
	 * @param mv 검색할 자료가 들어있는 MemBerVO객체
	 * @return 검색된 결과를 담은 List
	 * @throws SQLException JDBC관련 예외 객체
	 */
	public List<boardVO> getSearchCon(boardVO mv) throws SQLException;
	
}
