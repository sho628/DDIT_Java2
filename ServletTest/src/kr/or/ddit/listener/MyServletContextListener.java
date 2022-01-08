package kr.or.ddit.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 서블릿 컨텍스트 관련 이벤트 리스너
 * @author sem
 *
 */
public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] contextDestroyed() 호출됨 ");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] contextInitialized() 호출됨 ");
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] attributeAdded() 호출됨 "
				+ "속성값이 추가되었음 : " + scae.getName());
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] attributeRemoved() 호출됨 "
				+ "속성값이 삭제되었음 : " + scae.getName());
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] attributeReplaced() 호출됨 "
				+ "속성값이 변경되었음 : " + scae.getName());
		
	}

}
