package kr.or.ddit.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener, HttpSessionAttributeListener  {

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println("[MyHttpSessionListener] sessionCreated() 호출됨. "
				+ " 세션ID : " + hse.getSession().getId());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		System.out.println("[MyHttpSessionListener] sessionDestroyed() 호출됨. "
				+ " 세션ID : " + hse.getSession().getId());
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeAdded() 호출됨. "
				+ "속성 : " + hsbe.getName() + " 추가됨.");
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeRemoved() 호출됨. "
				+ "속성 : " + hsbe.getName() + " 삭제됨.");
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeReplaced() 호출됨. "
				+ "속성 : " + hsbe.getName() + " 변경됨.");
		
	}

}
