<%@page import="kr.or.ddit.comm.vo.PagingVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	List<MemberVO> memList = 
				(List<MemberVO>)request.getAttribute("memList");
	String msg = request.getParameter("msg") == null ?
					"" : request.getParameter("msg");
	
	PagingVO pagingVO = (PagingVO) request.getAttribute("pagingVO");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원목록</title>
<script>

/* 회원 목록 조회 */
function fn_selectList(pageNo){
	
	const listForm = document.listForm;
	
	// 페이지번호 설정
	pageNo ? listForm.pageNo.value = pageNo 
			: listForm.pageNo.value = 1;
	
	listForm.action = "list.do";
	listForm.method = "post";
	listForm.submit();
}

</script>
</head>
<body>
<form name="listForm">
<input type="hidden" name="pageNo">

<table border="1">
	<tr>
		<td>ID</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>주소</td>
	</tr>
	
<%
	int memSize = memList.size();
	if(memSize > 0){
		for(int i=0; i<memSize; i++){
%>
	<tr>
		<td><%=memList.get(i).getMemId() %></td>
		<td><a href="select.do?memId=<%=memList.get(i).getMemId() %>"><%=memList.get(i).getMemName() %></a></td>
		<td><%=memList.get(i).getMemTel() %></td>
		<td><%=memList.get(i).getMemAddr() %></td>
	</tr>

<% 			
		}
	}
%>
<tr align="center">
	<td colspan="4"><a href="insert.do">[회원 등록]</a></td>
</tr>	
	
<%if(pagingVO.getTotalPageCount() > 0){
%>

	<tr>
		<td colspan="4" align="center">
			<%if(pagingVO.getFirstPageNo() > pagingVO.getPageSize()){ %>
				<a href="javascript:fn_selectList(<%=pagingVO.getFirstPageNo() - pagingVO.getPageSize() %>)">[이전]</a>
			<%} %>
			<%for(int pNo = pagingVO.getFirstPageNo(); pNo <= pagingVO.getLastPageNo(); pNo++){ %>
				<a <%if(pNo == pagingVO.getCurrentPageNo()){ %> style="color:orange;"<%} %>  href="javascript:fn_selectList(<%=pNo %>)">[<%=pNo %>]</a>
			<%} %>			
			<%if(pagingVO.getLastPageNo() < pagingVO.getTotalPageCount()){ %>
				<a href="javascript:fn_selectList(<%=pagingVO.getFirstPageNo() + pagingVO.getPageSize() %>)">[다음]</a>
			<%} %>
		
		</td>
	</tr>



<%
   }
%>
</table>

</form>


<%
	if(msg.equals("성공")	){// 성공 메시지 전달된 경우...
%>
		<script>
			alert('정상적으로 처리 되었습니다.');
		</script>
<%		
	}
%>


</body>
</html>