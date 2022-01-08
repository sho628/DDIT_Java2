package kr.or.ddit.basic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequest extends HttpServletRequestWrapper {

	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
	}

}
