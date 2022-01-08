package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T04_ErrorHandler extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 예외 객체
		Throwable throwable = (Throwable) 
				req.getAttribute("javax.servlet.error.exception");
		// 에러 상태 코드
		Integer statusCode = (Integer)
				req.getAttribute("javax.servlet.error.status_code");
		// 에러 메시지
		String message = (String)
				req.getAttribute("javax.servlet.error.message");
		// 에러발생 서블릿 이름
		String servletName = (String)
				req.getAttribute("javax.servlet.error.servlet_name");
		
		req.setAttribute("javax.servlet.error.servlet_name", "안녕하세요..ㅋㅋ");
		
		if(servletName == null) {
			servletName = "알수없는 서블릿";
		}
		// 에러발생 uri 정보
		String reqUri = (String) 
				req.getAttribute("javax.servlet.error.request_uri");
		
		if(reqUri == null) {
			reqUri = "알수없는 URI";
		}
		
		// 응답헤더 정보 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "에러/예외 정보";
		
		out.println("<html><head><title>" + title
					+ "</title></head>\n" + "<body>\n");
		if(throwable == null && statusCode == null) {
			out.println("<h2>에러정보 없음</h2>");
		}else {
			out.println("<h2>예외/에러 정보</h2>");
			out.println("상태코드 : " + statusCode + "<br><br>");
			out.println("에러메시지 : " + message + "<br><br>");
			out.println("서블릿이름 : " + servletName + "<br><br>");
			out.println("요청 URI : " + reqUri + "<br><br>");
			if(statusCode != null) {
				out.println("예외 타입 : " + throwable.getClass().getName()
									+ "<br><br>");
				out.println("예외 메시지 : " + throwable.getMessage());
			}
		}
		out.println("</body>");
		out.println("</html>");
		
		
		
		
		
		
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
