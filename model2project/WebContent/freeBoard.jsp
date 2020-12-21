<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Free, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
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
	<link rel="stylesheet" href="css/freeBoard.css">
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
					<li class="nav-item"><a class="nav-link" href="introList.do">강사소개
					</a></li>
					<li class="nav-item"><a class="nav-link" href="lectureList.do">강의목록</a></li>
					<li class="nav-item"><a class="nav-link" href="editProfilePage.do">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="faq.do">고객센터</a></li>
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
	if(loginMember == null){
		out.println("<script>alert('로그인이 필요합니다.');location.href='loginPage.do';</script>");
	} else {
				int lastPage = 1;
				if (session.getAttribute("lastPage") != null) lastPage = (int) session.getAttribute("lastPage");
				if (request.getParameter("page") != null) nowPageNumber = Integer.parseInt(request.getParameter("page"));
				if (nowPageNumber < 1) nowPageNumber = 1;
				if (nowPageNumber > lastPage) nowPageNumber = lastPage;
				LinkedList[] freeList = (LinkedList[])session.getAttribute("freeList");
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
						<li><a href="notice.do">공지사항</a></li>
						<li><a href="freeBoard.do">자유게시판</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">Board</div>
			<div>
				<div class="myPageMenu"><a href="notice.do"><img src="images/user_icon.png">&nbsp;공지사항</a></div>
				<div class="myPageMenu on"><a href="freeBoard.do"><img src="images/heart_icon.png">&nbsp;자유게시판</a></div>
			</div>
		</div>
			<div class="contents">
				<div class="row justify-content-center">
					<div class="col-md-12 mb-5">
						<div class="bbsWrapper">
							<ul class="bbsWrapperList">
								<li class="bbsHeader">
									<ul class="bbsHeaderContents">
										<li class="bbsTitleHeader">TITLE</li>
										<li class="bbsWriterHeader">WRITER</li>
									</ul>
								</li>
<%
			if (freeList == null) {
%>
								<li><h4>글이 없습니다.</h4></li>
<%
			} else {
				
				LinkedList<Free> frList = freeList[0];
				LinkedList<Member> memList = freeList[1];
				
				for (int i = 0; i < frList.size();i++) {
%>
								<li class="bbsBody">
									<ul class="bbsBodyContents">
										<li class="bbsTitle">
											<a href="freeView.do?page=<%=nowPageNumber %>&free_num=<%=frList.get(i).getFree_num() %>"><%=frList.get(i).getTitle() %></a>
											<div class="bbsTitleDetail"><%=frList.get(i).getContents() %></div>
										</li>
										<li class="bbsWriter"><%=memList.get(i).getName() %></li>
									</ul>
								</li>
								
<%
				}
			}
%>
							</ul>
						</div>
					</div>
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <li class="page-item<%=prevDisabled %>">
						      <a class="page-link" href="freeBoard.do?page=<%=nowPageNumber - 1 %>" tabindex="-1">Previous</a>
						    </li>
<%
				for (int i = startNumber; i <= Math.min(endNumber, lastPage); i++) {					    
%>
						    <li class="page-item<% if(nowPageNumber==i){%> active<%} %>"><a class="page-link" href="freeBoard.do?page=<%=i%>"><%=i %></a></li>
<%
				}
%>
						    <li class="page-item<%=nextDisabled%>">
						      <a class="page-link" href="freeBoard.do?page=<%=nowPageNumber + 1 %>">Next</a>
						    </li>
						  </ul>
						</nav>
						
				</div>			        
						<div class="float-right">
							<button class="btn btn-primary" onClick="location.href='freeWritePage.do'">글쓰기</button>
							<button class="btn btn-info" onClick="location.href='myFree.do?page=1'">내가 쓴 글</button>
						</div>
			</div>
	</div>
	
<%
	}
%>
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