package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 서블릿 3부터 지원하는 Part 인터페이스를이용한 파일 업로드 예제
 */


@WebServlet("/upload3")
@MultipartConfig(maxFileSize=40000000, 
	maxRequestSize=50000000, fileSizeThreshold=3000000)
public class UploadServlet2 extends HttpServlet {
	
	private static final String UPLOAD_DIR = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 웹 애플리케이션 루트 디렉토리  기준 업로드 경로 설정하기
		String uploadPath = req.getServletContext()
							.getRealPath("") + File.separator
							+ UPLOAD_DIR;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			for(Part part : req.getParts()) {
				// Part로부터 파일명 추출하기
				fileName = getFileName(part);
				
				if(fileName != null && !fileName.equals("")) {
					//폼필드가 아니거나 파일이 비어있지 않은 경우...
					
					part.write(uploadPath + File.separator 
								+ fileName);
					System.out.println("파일명 : " + fileName 
							+ "업로드 완료!!!");
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("파라미터값 : " + req.getParameter("sender"));
		
		resp.setContentType("text/html");
		resp.getWriter().print("업로드 완료...!!!");
	}
	
	
	/**
	 * Part객체 파싱하여 파일이름 추출하기
	 * @param part
	 * @return 파일명 : 파일명이 존재하지 않으면 null리턴함(폼필드)
	 */
	private String getFileName(Part part) {
	/**
	 * multipart body를 위한 헤더 정보로 사용되는 경우... ex) 파일 업로드
	 * 
	 * Content-Disposition: form-data
	 * Content-Disposition: form-data; name="fieldName"
	 * Content-Disposition: form-data; name="fieldName"; filename="filename.jpg"
	 */
		for(String content : part.getHeader("Content-Disposition")
								.split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=")+1)
						.trim().replace("\"", "");
			}
		}
		return null;
	}
}
