package kr.or.ddit.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener] requestDestroyed() 호출됨 ");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener] requestInitialized() 호출됨 ");
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] attributeAdded() 호출됨 "
				 + " 추가된 속성명 : " + srae.getName());
		
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] attributeRemoved() 호출됨 "
				 + " 삭제된 속성명 : " + srae.getName());
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] attributeReplaced() 호출됨 "
				 + " 변경된 속성명 : " + srae.getName());
		
	}

}
