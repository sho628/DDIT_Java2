package kr.or.ddit.comm.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.comm.dao.AtchFileDaoImpl;
import kr.or.ddit.comm.dao.IAtchFileDao;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class AtchFileServiceImpl implements IAtchFileService {
	
	private static IAtchFileService fileService;
	private IAtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		
		return fileService;
	}
	
	@Override
	public AtchFileVO saveAtchFileList(Map<String, Object[]> fileItemMap) {
		
		// 파일 기본정보 저장하기
		AtchFileVO atchFileVO = new AtchFileVO();
		
		try {
			// 기본 파일정보 저장(VO에 atchFileId가 저장된다.)
			fileDao.insertAtchFile(atchFileVO);
			
			for(Object[] objArr : fileItemMap.values()) {
				for(Object obj : objArr) {
					
					FileItem item = (FileItem) obj;
					
					File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
					if(!uploadDir.exists()) {
						uploadDir.mkdir();
					}
					
					String orignFileName = 
							new File(item.getName()).getName();
					long fileSize = item.getSize(); // 파일 사이즈 가져오기
					String storeFileName = "";
					String filePath = "";
					File storeFile = null;
					
					do {
						// 저장 파일명 추출
						storeFileName = UUID.randomUUID()
								.toString().replace("-", "");
						filePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY 
								+ File.separator
								+ storeFileName;
						storeFile = new File(filePath);
					}while(storeFile.exists()); // 파일명이 중복되지 않을때까지
					
					// 확장명 추출
					String fileExtension = orignFileName
										.lastIndexOf(".") < 0 ?
										"" 
										: orignFileName.substring(
										  orignFileName
										  .lastIndexOf(".") + 1);
					
					item.write(storeFile);// 파일 업로드
					
					atchFileVO.setStreFileNm(storeFileName);
					atchFileVO.setFileSize(fileSize);
					atchFileVO.setOrignlFileNm(orignFileName);
					atchFileVO.setFileStreCours(filePath);
					atchFileVO.setFileExtsn(fileExtension);
					
					fileDao.insertAtchFileDetail(atchFileVO);
					
					item.delete(); // 임시 업로드 파일 삭제하기
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		
		List<AtchFileVO> atchFileList = null;
		
		try {
			atchFileList = fileDao.getAtchFileList(atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		AtchFileVO fileVO = null;
		
		try {
			fileVO = fileDao.getAtchFileDetail(atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fileVO;
	}

}
