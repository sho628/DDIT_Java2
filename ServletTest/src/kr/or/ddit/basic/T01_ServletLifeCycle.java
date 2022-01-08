package kr.or.ddit.basic;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿의 라이프 사이클을 확인하기 위한 예제
 * @author sem
 *
 */
public class T01_ServletLifeCycle extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// 초기화 코드 작성...
		System.out.println("init() 호출됨...");
	}
	
	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		// 실제적인 작업수행이 시작되는 지점...(자바의 메인 메서드 역할)
		System.out.println("service() 호출됨...");
		
		super.service(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServerException {
		// 메서드 방식이 GET 인 경우 호출됨...
		System.out.println("doGet() 호출됨...");
		
		throw new ServerException("심각한 예외 발생!!!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 post인 경우 호출됨...
		System.out.println("doPost() 호출됨...");
	}
	
	@Override
	public void destroy() {
		// 객체 소멸시(컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성...
		System.out.println("destroy() 호출됨...");
	}
}
