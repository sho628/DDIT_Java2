package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.vo.PagingVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/list.do")
public class ListMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 페이지 번호
		int pageNo = req.getParameter("pageNo") == null ?
				1 : Integer.parseInt(req.getParameter("pageNo"));
		
		// 1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 페이징 객체 생성
		int totalCnt = memService.getAllMemberCount(); 
		
		PagingVO pagingVO = new PagingVO();
		pagingVO.setCountPerPage(10);
		pagingVO.setPageSize(5);
		pagingVO.setTotalCount(totalCnt);
		pagingVO.setCurrentPageNo(pageNo);
		
		req.setAttribute("pagingVO", pagingVO);
		
		
		// 3. 회원정보 조회
		List<MemberVO> memList = memService.getAllMemberList(pagingVO);
		
		req.setAttribute("memList", memList);
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/view/member/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
