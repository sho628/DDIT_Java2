package kr.or.ddit.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MyHttpSessionBindingListener implements HttpSessionBindingListener {
	
	/**
	 * Http세션 영역내에 HttpSessionBindingListener를 구현한 객체가 바인딩 되면
	 * 호출됨
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent hsbe) {
		String attrName = hsbe.getName();
		System.out.println("[MyHttpSessionBindingListener]"
				+ this.hashCode() + " 객체가 " + attrName 
				+ "으로 바인딩 되었음.");
		
	}
	
	/**
	 * Http세션 영역내에 HttpSessionBindingListener를 구현한 객체가 언바인딩 되면
	 * 호출됨
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent hsbe) {
		String attrName = hsbe.getName();
		System.out.println("[MyHttpSessionBindingListener]"
				+ this.hashCode() + " 객체가 " + attrName 
				+ "으로 언바인딩 되었음.");
		
	}

}
