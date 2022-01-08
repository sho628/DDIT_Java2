package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.basic.MyHttpServletRequest;

public class CharacterEncodingFilter implements Filter {
	
	private String encoding; // 인코딩 정보
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("인코딩 정보 설정 : " + encoding);
		
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		if(config.getInitParameter("encoding") == null) {
			this.encoding = "utf-8"; // 기본 인코딩 값 설정
		}else {
			this.encoding = config.getInitParameter("encoding");
		}
	}

}
