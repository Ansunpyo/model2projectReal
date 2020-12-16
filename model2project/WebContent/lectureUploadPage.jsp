<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Subject, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	LinkedList<Subject> subList = (LinkedList<Subject>) session.getAttribute("subList");
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
</head>
<style>
a {
	text-decoration: none;
	color: black;
}
a:hover {
	text-decoration: none;
	color: #d16328;
}
</style>
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
					<li class="nav-item active"><a class="nav-link" href="lectureList.do">강의목록</a></li>
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
<%
	if(loginMember == null){
		out.println("<script>alert('로그인이 필요합니다.');location.href='loginPage.do';</script>");
	} else {
%>
<div class="container" id="main">
	
	<div class="container mb-5" style="width:1200px;">
		<div class="p-3 mb-2 d-flex flex-wrap bg-secondary text-light position-relative">
			<form method="post" action="lectureUpload.do" style="margin:auto;">
				<div class="bg-light p-2">
					<input type="text" class="form-control" name="lecture_title" placeholder="LECTURE TITLE" required="required" autocomplete="off" />
				</div>
				<div class="bg-light p-2">
					<select class="form-control" name="subject">
<%
					for(int i = 0; i < subList.size(); i++) {
%>
						<option value="<%=subList.get(i).getCode() %>"><%=subList.get(i).getSubject_name() %></option>
<%
					}	
%>
					</select>
				</div>
				<div class="bg-light p-2">
					<input type="text" class="form-control" name="video" placeholder="LECTURE URL(Youtube 주소만 입력해주세요!)" required="required" autocomplete="off" />
				</div>
				<div class="bg-light text-dark p-2">
					<h6>CHAPTER 2 이후의 강의 영상은 해당 강의 페이지에서 추가하실 수 있습니다.</h6>
				</div>
				<div class="bg-light p-2">
					<input type="number" class="form-control" name="price" placeholder="PRICE" min="0" required="required" autocomplete="off" />
				</div>
				<div style="text-align:center;">
					<input type="submit" class="btn btn-primary" disabled value="강의 개설" />
				</div>
			</form>
		</div>
	</div>
	
</div>
<%
	}
%>

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
			$("input[name=video]").on("input",function(){
				var str = $(this).val()
				var index = str.indexOf("youtube.com/watch?v=");
				if(index >= 0) {
					var extra = str.substring(str.indexOf("=") + 1, str.length);
					if(extra != "") {
						$("input[type=submit]").attr("disabled", false);
					} else {
						$("input[type=submit]").attr("disabled", true);
					}
				} else {
					$("input[type=submit]").attr("disabled", true);
				}
			});
		});
	</script>
</body>
</html>