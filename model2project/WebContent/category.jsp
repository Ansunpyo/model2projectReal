<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Subject, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	if (loginMember == null || !loginMember.getId().equals("admin")) {
		out.println("<script>location.href='index.do'</script>");
	}
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
					<li class="nav-item"><a class="nav-link" href="#">주문관리
					</a></li>
					<li class="nav-item"><a class="nav-link" href="members.do">회원관리</a></li>
					<li class="nav-item"><a class="nav-link" href="logout.do">로그아웃</a></li>
					<li class="nav-item active"><a class="nav-link" href="review.do">마이페이지
							<span class="sr-only">(current)</span></a></li>
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
		LinkedList<Subject> subList = (LinkedList<Subject>)session.getAttribute("subList");
%>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">My Page</div>
			<div>
				<div class="myPageMenu"><a href="review.do"><img src="images/star_icon.png">&nbsp;리뷰 관리</a></div>
				<div class="myPageMenu"><a href="faq.do"><img src="images/faq_icon.png">&nbsp;FAQ 관리</a></div>
				<div class="myPageMenu on"><a href="category.do"><img src="images/category_icon.png">&nbsp;카테고리 관리</a></div>
				<div class="myPageMenu"><a href="banner.do"><img src="images/banner_icon.png">&nbsp;메인배너 관리</a></div>
			</div>
		</div>
			<div class="contents">
				<div class="container d-flex align-content-start flex-wrap">
<%
			if (subList != null) {
				for(int i = 0; i < subList.size(); i++) {					
%>
					<div class="card m-2">
					  <div class="card-body">
					    <h5 class="card-title"><%=subList.get(i).getSubject_name() %></h5>
					    <h6 class="card-subtitle mb-2 text-muted">subject code:<%=subList.get(i).getCode() %></h6>
					    <span class="float-right"><button class="btn btn-danger deleteCategory" id="<%=subList.get(i).getCode() %>">삭제</button></span>
					  </div>
					</div>
<%
				}					
			}
%>
					
				</div>
				
				<div class="input-group mb-3">
				  <input type="text" class="form-control" id="subject_name" placeholder="과목명" />
				  <div class="input-group-append">
				    <button class="btn btn-outline-light bg-dark insertCategory" type="button">추가</button>
				  </div>
				</div>
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
			
			$(document).on("click", ".deleteCategory", function(){
				if (window.confirm("정말 삭제하시겠습니까?\n해당 과목의 모든 강의가 삭제됩니다!")) {
					var code = $(this).attr("id");
					location.href="deleteCategory.do?code=" + code;
			    }
			});
			
			$(document).on("click", ".insertCategory", function(){
				if ($("#subject_name").val() == "") {
					alert('과목명을 입력하세요!');
				} else {
					var subject_name = $("#subject_name").val();
					location.href="insertCategory.do?subject_name=" + subject_name;
				}
			});
		});
	</script>
</body>
</html>