<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Lecture, vo.Review, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	if (loginMember == null || !loginMember.getId().equals("admin")) {
		out.println("<script>location.href='index.do'</script>");
	}
	int nowPageNumber = 1;
	int pageCount = 5;
	int range = 5;
	String prevDisabled = "";
	String nextDisabled = "";
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
			<div class="container">
				<a class="navbar-brand" href="index.jsp">2LW</a>	
			</div>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
	
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="purchaseAllList.do">주문관리
					</a></li>
					<li class="nav-item active"><a class="nav-link" href="members.do">회원관리
							<span class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="logout.do">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link" href="review.do">마이페이지</a></li>
				</ul>
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>
		<!-- //header -->
		<div class="container" id="main">
<%
		int lastPage = 1;
		if (session.getAttribute("lastPage") != null) lastPage = (int) session.getAttribute("lastPage");
		if (request.getParameter("page") != null) nowPageNumber = Integer.parseInt(request.getParameter("page"));
		if (nowPageNumber < 1) nowPageNumber = 1;
		if (nowPageNumber > lastPage) nowPageNumber = lastPage;
		LinkedList<Member> memList = (LinkedList<Member>)session.getAttribute("memList");
		int startNumber = (nowPageNumber - 1) / pageCount * range + 1;
		int endNumber = startNumber + range - 1;
		if (nowPageNumber <= 1) {
			prevDisabled = " disabled";
		}
		if (nowPageNumber >= lastPage) {
			nextDisabled = " disabled";
		}
%>
	<div class="topbar">
		<ul>
			<li>
				<div class="hamburger">
					<div></div>
					<div></div>
					<div></div>
				</div>
			</li>
			<li>
				<div class="topbarMenu">
					<ul>
						<li><a href="members.do">회원 리스트</a></li>
						<li><a href="quitters.do">탈퇴회원 리스트</a></li>
						<li><a href="statistics.do">통계</a></li>
						<li><a href="one_on_onead.do">1:1 문의함</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">회원관리</div>
			<div>
				<div class="myPageMenu"><a href="members.do"><img src="images/members_icon.png">&nbsp;회원 리스트</a></div>
				<div class="myPageMenu on"><a href="quitters.do"><img src="images/x_mark_icon.png">&nbsp;탈퇴회원 리스트</a></div>
				<div class="myPageMenu"><a href="statistics.do"><img src="images/statistics_icon.png">&nbsp;통계</a></div>
				<div class="myPageMenu"><a href="one_on_onead.do"><img src="images/mail_icon.png">&nbsp;1:1 문의함</a></div>
			</div>
		</div>
			<div class="contents">
			
				<table class="table table-sm bg-white">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">E-MAIL</th>
				      <th scope="col">NUMBER</th>
				    </tr>
				  </thead>
				  <tbody>
<%
	if(memList != null) {
	
		for(int i = 0; i < memList.size();i++) {
%>
				    <tr>
				      <td><%=memList.get(i).getId() %></td>
				      <td><%=memList.get(i).getEmail() %></td>
				      <td><%=memList.get(i).getNumber() %></td>
				    </tr>
<%
		}
%>				    
				  </tbody>
				</table>
				
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <li class="page-item<%=prevDisabled %>">
						      <a class="page-link" href="quitters.do?page=<%=nowPageNumber - 1 %>" tabindex="-1">Previous</a>
						    </li>
<%
				for (int i = startNumber; i <= Math.min(endNumber, lastPage); i++) {					    
%>
						    <li class="page-item<% if(nowPageNumber==i){%> active<%} %>"><a class="page-link" href="quitters.do?page=<%=i%>"><%=i %></a></li>
<%
				}
%>
						    <li class="page-item<%=nextDisabled%>">
						      <a class="page-link" href="quitters.do?page=<%=nowPageNumber + 1 %>">Next</a>
						    </li>
						  </ul>
						</nav>
<%
	}
%>
			</div>
	</div>
	
</div>
	<script src="./js/jquery-1.12.4.min.js"></script>
	<!-- Optional JavaScript; -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
	<script>
		$(function(){
			$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			$(window).resize(function(){
				$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			});
		});
	</script>
	<script src="js/sidebar.js"></script>
</body>
</html>