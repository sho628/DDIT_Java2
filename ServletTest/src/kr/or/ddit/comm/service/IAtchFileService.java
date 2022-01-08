package kr.or.ddit.comm.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.comm.vo.AtchFileVO;

/**
 * 첨부파일 처리를 위한 공통 서비스용 인터페이스
 */
public interface IAtchFileService {
	/**
	 * 첨부파일 목록을 저장하기 위한 메서드
	 * @param fileItemMap 저장할 FileItem을 담은 맵객체
	 * @return 저장한 첨부파일 정보
	 */
	public AtchFileVO saveAtchFileList(Map<String, Object[]> fileItemMap);
	
	/**
	 * 첨부파일 목록 조회
	 * @param atchFileVO
	 * @return
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @param atchFileVO
	 * @return
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO);
}
