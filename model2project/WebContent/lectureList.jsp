<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Lecture, vo.Subject, vo.Lecture_Video, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	int nowPageNumber = 1;
	int pageCount = 8;
	int range = 5;
	String prevDisabled = "";
	String nextDisabled = "";
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
				LinkedList<Subject> subjectList = (LinkedList<Subject>)session.getAttribute("subjectList");
				LinkedList[] lectureList = (LinkedList[])session.getAttribute("lectureList");
				LinkedList<Lecture> lecList = lectureList[0];
				LinkedList<Member> memList = lectureList[1];
				LinkedList<Subject> subList = lectureList[2];
				LinkedList<Lecture_Video> vidList = lectureList[3];
				int startNumber = (nowPageNumber - 1) / pageCount * range + 1;
				int endNumber = startNumber + range - 1;
				if (nowPageNumber <= 1) {
					prevDisabled = " disabled";
				}
				if (nowPageNumber >= lastPage) {
					nextDisabled = " disabled";
				}
%>
	<div class="container mb-5">
		<div class="p-3 mb-2 d-flex flex-wrap bg-secondary text-light position-relative">
			<form method="post" action="lectureListChecked.do">
<%
		for(int i = 0; i < subjectList.size(); i++){
%>
				<label><input type="checkbox" name="subject" class="subject" value="<%=subjectList.get(i).getCode() %>" /> <%=subjectList.get(i).getSubject_name() %></label>&nbsp;<% if (i != 0 && i % 4 == 0) { %><br /> <% } %>
<%
		}
%>
				<div class="position-absolute" style="top:10px; right:10px;">
					<input type="submit" value="검색" class="btn btn-info" />
				</div>
			</form>
		</div>
		
		<div class="row">
			<div class="col-12 d-flex flex-wrap <% if(lecList.size() == 8) { %>justify-content-between<% } else { %>justify-content-start<% } %>">
<%
			if(lecList != null) {
				for(int i = 0; i < lecList.size(); i++) {
					String video = "";
					if(vidList.get(i).getVideo().indexOf("&") > 0) {
						video = vidList.get(i).getVideo().substring(vidList.get(i).getVideo().indexOf("v=") + 2, vidList.get(i).getVideo().indexOf("&"));
					} else {
						video = vidList.get(i).getVideo().substring(vidList.get(i).getVideo().indexOf("v=")+2);
					}
					String lecUrl = "";
					if(lecList.get(i).getPrice() == 0) {
						lecUrl = "lectureDetail.do?lecture_num=" + lecList.get(i).getLecture_num();
					} else {
						lecUrl = "결제페이지.do?lecture_num=" + lecList.get(i).getLecture_num();
					}
%>
				<div class="card my-2" style="width: 15rem;">
				  <a href="<%=lecUrl %>"><img src="https://img.youtube.com/vi/<%=video %>/0.jpg" class="card-img-top" alt="..."></a>
				  <div class="card-body">
				    <h5 class="card-title"><a href="<%=lecUrl %>"><%=lecList.get(i).getLecture_title() %></a></h5>
				    <h6 class="card-text"><%if(lecList.get(i).getPrice() == 0) { %><span class="badge badge-pill badge-primary">FREE</span><%} else { %><span class="badge badge-pill badge-danger"><%=lecList.get(i).getPrice() %>원</span><%} %></h6>
				    <p class="card-text"><span class="badge badge-pill badge-info"><%=subList.get(i).getSubject_name() %></span></p>
				    <h5 class="card-text float-right"><%=memList.get(i).getName() %> 선생님</h5>
				    <input type="hidden" name="subject"/>
				  </div>
				</div>
<%
				}
			}
%>				
			</div>
		</div>
	</div>
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <li class="page-item<%=prevDisabled %>">
						      <a class="page-link" href="lectureList.do?page=<%=nowPageNumber - 1 %>" tabindex="-1">Previous</a>
						    </li>
<%
				for (int i = startNumber; i <= Math.min(endNumber, lastPage); i++) {					    
%>
						    <li class="page-item<% if(nowPageNumber==i){%> active<%} %>"><a class="page-link" href="lectureList.do?page=<%=i%>"><%=i %></a></li>
<%
				}
%>
						    <li class="page-item<%=nextDisabled%>">
						      <a class="page-link" href="lectureList.do?page=<%=nowPageNumber + 1 %>">Next</a>
						    </li>
						  </ul>
						</nav>
<%
			if(loginMember.getClassify().equals("교사")) {	
%>
				<div class="float-right">
					<input type="button" class="btn btn-primary uploadLecture" value="강의 올리기" />
					<input type="button" class="btn btn-info myLecture" value="내 강의" />
					<input type="button" class="btn btn-info allLecture" value="전체 강의" />
				</div>
<%
			}
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
			$(".uploadLecture").click(function(){
				location.href="lectureUploadPage.do";
			});
			$(".myLecture").click(function(){
				location.href="myLecture.do?page=1";
			});
			$(".allLecture").click(function(){
				location.href="lectureList.do?page=1";
			});
		});
	</script>
</body>
</html>