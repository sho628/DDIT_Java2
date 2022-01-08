package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import kr.or.ddit.util.FileUploadRequestWrapper;

/**
 * 멀티파트 요청메시지 파싱을 위한 필터
 * @author macween
 *
 */
public class MultipartParser implements Filter {

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40; // 파일 1개당 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 요청 파일 최대 크기

	private static Logger logger = Logger.getLogger(MultipartParser.class);

	@Override
	public void destroy() {
		logger.info("소멸 메서드 실행");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			// 리퀘스트래퍼 객체생성
			FileUploadRequestWrapper requestWrapper =
					new FileUploadRequestWrapper((HttpServletRequest) req, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE, "");

			// 래퍼 객체 적용
			chain.doFilter(requestWrapper, resp);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("MultipartParser 초기화 메서드 실행.");
	}

}
