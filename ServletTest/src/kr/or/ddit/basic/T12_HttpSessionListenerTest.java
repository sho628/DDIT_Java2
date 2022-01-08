package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.listener.MyHttpSessionBindingListener;

@WebServlet("/T12_HttpSessionListenerTest")
public class T12_HttpSessionListenerTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// HttpSession 객체 생성 및 소멸 관련 테스트
		HttpSession httpSession = req.getSession();
		httpSession.invalidate();
		
		req.getSession().setAttribute("ATTR1", "속성1");
		req.getSession().setAttribute("ATTR1", "속성11");
		req.getSession().setAttribute("ATTR2", "속성2");
		req.getSession().removeAttribute("ATTR1");
		
		// HttpSession 영역내에서 HttpSessionBindingListener를 구현한
		// 객체 바인딩 테스트
		MyHttpSessionBindingListener bindingListener
			= new MyHttpSessionBindingListener();
		// 바인딩
		req.getSession().setAttribute("obj", bindingListener);
		// 언바인딩
		req.getSession().removeAttribute("obj");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
