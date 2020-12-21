<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.One_On_One, java.util.LinkedList" %>
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
	<link rel="stylesheet" href="css/one_on_one.css">
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
		LinkedList[] oneOnOneAdList = (LinkedList[])session.getAttribute("oneOnOneAdList");
		
			
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
				<div class="myPageMenu"><a href="quitters.do"><img src="images/x_mark_icon.png">&nbsp;탈퇴회원 리스트</a></div>
				<div class="myPageMenu"><a href="statistics.do"><img src="images/statistics_icon.png">&nbsp;통계</a></div>
				<div class="myPageMenu on"><a href="one_on_onead.do"><img src="images/mail_icon.png">&nbsp;1:1 문의함</a></div>
			</div>
		</div>
			<div class="contents">
			
<%
		if(oneOnOneAdList != null) {
			
			LinkedList<Member> memList = oneOnOneAdList[0];
			LinkedList<One_On_One> oneList = oneOnOneAdList[1];
			
			if(oneList != null) {	
				
				for(int i = 0; i < oneList.size(); i++) {				    
%>
				    <div class="col-sm-12 bg-white py-3 shadow mb-3">
				    	<h5 class="Q"><%=oneList.get(i).getTitle() %></h5>
<%
				    if(oneList.get(i).getAnswer() != null) {				    	
%>
				    	
								<div class="badge badge-primary float-left">답변 완료</div>
<%
				    }
%>
				    	<div><hr></div>
				    	<div class="popup" style="word-break: break-all;">
							<h6><%=oneList.get(i).getContents() %></h6>
							<span class="float-right">문의자 : <%=memList.get(i).getName() %></span>
							<div><hr/></div>
<%
					if(oneList.get(i).getAnswer() != null) {							
%>
							<div class="content">
								<p><b><%=oneList.get(i).getAnswer() %></b></p>			
							</div>
<%
					} else {
%>
							<div class="form-group">
								<form method="post" action="one_on_one_answer.do">
								    <label for="answerTextarea">ANSWER</label>
								    <textarea class="form-control" name="answer" rows="3"></textarea>
								    <input type="hidden" name="one_on_one_num" value="<%=oneList.get(i).getOne_on_one_num() %>" />
								    <input type="submit" class="btn btn-primary" value="답변하기" />
								</form>
							</div>
<%
					}
%>
						</div>
				    </div>
<%
				}
			}
%>
				
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <li class="page-item<%=prevDisabled %>">
						      <a class="page-link" href="one_on_onead.do?page=<%=nowPageNumber - 1 %>" tabindex="-1">Previous</a>
						    </li>
<%
				for (int i = startNumber; i <= Math.min(endNumber, lastPage); i++) {					    
%>
						    <li class="page-item"><a class="page-link" href="one_on_onead.do?page=<%=i%>"><%=i %></a></li>
<%
				}
%>
						    <li class="page-item<%=nextDisabled%>">
						      <a class="page-link" href="one_on_onead.do?page=<%=nowPageNumber + 1 %>">Next</a>
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
			$(".Q").click(function(){
					$(this).siblings(".popup").slideToggle();
			});
		});
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