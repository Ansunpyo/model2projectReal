<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Lecture, vo.Review, java.util.LinkedList" %>
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
	<link rel="stylesheet" href="css/review.css">
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
					<li class="nav-item active"><a class="nav-link" href="editProfilePage.do">마이페이지
							<span class="sr-only">(current)</span></a></li>
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
				LinkedList[] reviewList = (LinkedList[])session.getAttribute("reviewList");
				int startNumber = (nowPageNumber - 1) / pageCount * range + 1;
				int endNumber = startNumber + range - 1;
				if (nowPageNumber == 1) {
					prevDisabled = " disabled";
				}
				if (nowPageNumber == lastPage) {
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
						<li><a href="editProfilePage.do">개인정보 수정</a></li>
						<li><a href="favorites.do">즐겨찾기 목록</a></li>
						<li><a href="review.do">리뷰 남기기</a></li>
						<li><a href="messenger.do">쪽지함</a></li>
						<li><a href="quit.do">회원 탈퇴</a></li>
						<li><a href="logout.do">로그아웃</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">My Page</div>
			<div>
				<div class="myPageMenu"><a href="editProfilePage.do"><img src="images/user_icon.png">&nbsp;개인정보 수정</a></div>
				<div class="myPageMenu"><a href="favorites.do"><img src="images/heart_icon.png">&nbsp;즐겨찾기 목록</a></div>
				<div class="myPageMenu on"><a href="review.do"><img src="images/star_icon.png">&nbsp;리뷰남기기</a></div>
				<div class="myPageMenu"><a href="messenger.do"><img src="images/mail_icon.png">&nbsp;쪽지함</a></div>
				<div class="myPageMenu"><a href="quit.do"><img src="images/x_mark_icon.png">&nbsp;회원 탈퇴</a></div>
				<div class="myPageMenu"><a href="logout.do"><img src="images/door_icon.png">&nbsp;로그아웃</a></div>
			</div>
		</div>
			<div class="contents">
				<div class="row justify-content-center">
					<div class="col-md-12">
						<div class="bbsWrapper">
							<ul class="bbsWrapperList">
								<li class="bbsHeader">
									<ul class="bbsHeaderContents">
										<li class="bbsTitleHeader">REVIEW</li>
										<li class="bbsLectureHeader">LECTURE</li>
										<li class="bbsWriterHeader">WRITER</li>
									</ul>
								</li>
<%
			if (reviewList == null) {
%>
								<li><h4>리뷰가 없습니다.</h4></li>
<%
			} else {
				
				LinkedList<Lecture> lecList = reviewList[0];
				LinkedList<Member> memList = reviewList[1];
				LinkedList<Review> reList = reviewList[2];
				
				for (int i = 0; i < lecList.size();i++) {
%>
								<li class="bbsBody">
									<ul class="bbsBodyContents">
										<li class="bbsTitle">
											<a href="reviewView.do?page=<%=nowPageNumber %>&review_num=<%=reList.get(i).getReview_num() %>"><%=reList.get(i).getTitle() %></a>
											<div class="bbsTitleDetail"><%=reList.get(i).getContents() %></div>
										</li>
										<li class="bbsLecture"><a href="강의페이지.do?lecture_num=<%=lecList.get(i).getLecture_num() %>"><%=lecList.get(i).getLecture_title() %></a></li>
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
						      <a class="page-link" href="review.do?page=<%=nowPageNumber - 1 %>" tabindex="-1">Previous</a>
						    </li>
<%
				for (int i = startNumber; i <= Math.min(endNumber, lastPage); i++) {					    
%>
						    <li class="page-item<% if(nowPageNumber==i){%> active<%} %>"><a class="page-link" href="review.do?page=<%=i%>"><%=i %></a></li>
<%
				}
%>
						    <li class="page-item<%=nextDisabled%>">
						      <a class="page-link" href="review.do?page=<%=nowPageNumber + 1 %>">Next</a>
						    </li>
						  </ul>
						</nav>
						
				</div>			        
						<div class="float-right">
							<button class="btn btn-primary" onClick="location.href='reviewWritePage.do'">리뷰 작성</button>
							<button class="btn btn-info" onClick="location.href='review.do?page=1'">모두 보기</button>
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