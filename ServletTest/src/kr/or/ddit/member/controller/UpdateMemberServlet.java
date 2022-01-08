package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

@WebServlet("/member/update.do")
public class UpdateMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회
		MemberVO mv = memService.getMember(memId);
		
		if(mv.getAtchFileId() > 0) { // 첨부파일이 존재하면...
			// 2-1. 첨부파일 정보 조회
			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileId(mv.getAtchFileId());
			
			IAtchFileService fileService = 
					AtchFileServiceImpl.getInstance();
			List<AtchFileVO> atchFileList =
					fileService.getAtchFileList(fileVO);
			
			req.setAttribute("atchFileList", atchFileList);
		}
		
		req.setAttribute("mv", mv);
		
		// 3. view 화면으로 이동
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/view/member/updateForm.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		AtchFileVO atchFileVO = new AtchFileVO();
		
		if(((FileUploadRequestWrapper)req).isMultipartContent()) {
			
			IAtchFileService fileService = 
					AtchFileServiceImpl.getInstance();
			
			Map<String, Object[]> fileItemMap =
				((FileUploadRequestWrapper)req).getFileItemMap();
			
			if(fileItemMap.size() > 0) { // 파일이 존재하는 경우..
				atchFileVO = fileService.saveAtchFileList(fileItemMap);
			}
		}
		
		
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 3. 회원 정보 업데이트
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		mv.setAtchFileId(atchFileVO.getAtchFileId());
		
		int cnt = memService.updateMember(mv);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		// 4. 목록 조회화면으로 이동
		/*req.getRequestDispatcher("/member/list.do?msg=" 
						+ URLEncoder.encode(msg, "utf-8"))
				.forward(req, resp);*/
		String redirectUrl = req.getContextPath() 
					+ "/member/list.do?msg=" 
				    + URLEncoder.encode(msg, "utf-8");
		
		resp.sendRedirect(redirectUrl); //목록 조회화면으로 리다이렉트 하기
				
		
	}
}
