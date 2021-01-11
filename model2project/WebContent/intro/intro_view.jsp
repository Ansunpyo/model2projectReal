<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="vo.Intro, vo.Member, vo.Lecture, vo.Review, java.util.*, java.text.SimpleDateFormat"%>
<%
	String Number = null;
	String Intro_num = null;
	String Lecture_num = null;
	Member loginMember = null;
	String classify = null;
	ArrayList[] articleList = (ArrayList[]) session.getAttribute("articleList");
	ArrayList<Intro> intList = articleList[0];
	ArrayList<Member> memList = articleList[1];
	ArrayList<Lecture> lecList = articleList[2];
	ArrayList<Review> revList = articleList[3];
	Intro article = intList.get(0);
	Member articlem = memList.get(0);

	if (lecList.size() != 0) {
		Lecture articlel = lecList.get(0);
	}
	
	if (revList.size() != 0) {
		Review articler = revList.get(0);
	}

	String nowPage = (String) request.getAttribute("page");
	if (session.getAttribute("loginMember") != null) {
		loginMember = (Member) session.getAttribute("loginMember");
		classify = loginMember.getClassify();
	} else {
		out.println("<script>alert('로그인이 필요합니다.');location.href='login.jsp';</script>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
<title>강사소개</title>
<style>
body, h1, h2, h3, h4, h5, h6, p, address, header, footer, section, aside,
	nav, ul, li, dl, dt, dd, input, textarea, select, button {
	margin: 0;
	padding: 0;
	font-family: 'Malgun Gothic', sans-serif;
	font-size: 14px;
	color: #222328;
}

body {
	background-image:
		url(https://roman-flossler.github.io/StoryShowGallery/img/bg.png);
}

li {
	list-style: none;
}

h2 {
	text-align: center;
}

button {
	margin-top: 20px;
	margin-right: 10px;
	float: right;
}

.img {
	float: left;
	width: 355px;
	margin-right: 15px;
}

#container {
	width: 1200px;
	margin: 10px auto 0;
}

.introInfo {
	position: relative;
	float: left;
	width: 800px;
	margin-right: 15px;
}

.introInfo .infoList ul li {
	overflow: hidden;
	margin: 0 10px;
	border-bottom: 1px solid #dbdbdb;
	line-height: 30px;
}

.introInfo .infoList ul li a {
	float: left;
}

.introInfo .infoList ul li span {
	float: right;
	color: #606060;
}

.lecrev {
	position: relative;
	float: left;
	width: 1170px;
	margin-right: 15px;
	margin-top: 20px;
}

.rev {
	position: relative;
	z-index: 1;
	float: left;
	height: 30px;
	padding: 0 12px;
	border: 1px solid #ccc;
	background-color: #d7d7d7;
	font-size: 16px;
	font-weight: bold;
	color: #606060;
	line-height: 35px;
	cursor: pointer;
	box-sizing: border-box;
}

.lec {
	position: relative;
	z-index: 1;
	float: left;
	height: 30px;
	padding: 0 12px;
	border: 1px solid #ccc;
	background-color: #d7d7d7;
	font-size: 16px;
	font-weight: bold;
	color: #606060;
	line-height: 35px;
	cursor: pointer;
	box-sizing: border-box;
}

.lecList ul, .revList ul {
	padding-top: 30px;
}

.lecList ul li, .revList ul li {
	overflow: hidden;
	border-bottom: 1px solid #dbdbdb;
	line-height: 30px;
}

.lecList ul li a, .revList ul li a {
	float: left;
}

.lecList ul li span, .revList ul li span  {
	float: right;
	color: #606060;
}


rev .on {
	border-bottom-color: #fff;
	background-color: #fff;
}

lec .on {
	border-bottom-color: #fff;
	background-color: #fff;
}
</style>
</head>
<body>
<section id="container">
	<section class="img">
		<img src="/upload/<%=article.getImg1()%>" alt="-" style="width: 355px; height: 410px;;" />
	</section>
	<section class="introInfo">
		<div class="infoList">
			<ul>
				<li><a>name</a><span><%=articlem.getName()%></span></li>
				<li><a>E-mail</a><span><%=articlem.getEmail()%></span></li>
				<li><a>gender</a><span><%=articlem.getGender()%></span></li>
				<li><a>major</a><span><%=articlem.getMajor()%></span></li>
				<li><a>education</a><span><%=articlem.getEducation()%></span></li>
				<li><a>introduce</a><span><%=article.getContents()%></span></li>
			</ul>
		</div>
	</section>
	<section class="lecrev" style="margin-bottom: 30px;">
		<div class="tabs">
			<div class="rev on">리뷰</div>
			<div class="lec">강의</div>
		</div>
			<div class="revList">
<% if (revList != null) { %>
					<ul>
<% for (int j = 0; j < revList.size(); j++) { %>
						<li><a href="review.do?page=<%=revList.get(j).getReview_num()%>"><%=revList.get(j).getTitle()%></a></li>
<% } %>
					</ul>
<% } else { %>
						<h2>등록된 리뷰가 없습니다.</h2>
<% } %>
			</div>
			<div class="lecList">
<% if (lecList != null) { %>
					<ul>
<% for (int k = 0; k < lecList.size(); k++) { %>
						<li><a href="lectureDetail.do?lecture_num=<%=lecList.get(k).getLecture_num()%>"><%=lecList.get(k).getLecture_title()%></a></li>
<% } %>
					</ul>
<% } else { %>
						<h2>등록된 강의가 없습니다.</h2>
<% } %>
			</div>
		</div>
		<div class="mr-auto">
			<button class="btn btn-success" onclick="location.href = 'introList.do'">목록으로</button>
<% if (loginMember.getNumber() == article.getIntro_num()) { %>
			<button class="btn btn-danger" onclick="location.href = 'introDeletePro.do?intro_num=<%=article.getIntro_num()%>&page=<%=nowPage%>'">삭제하기</button>
			<button class="btn btn-primary" onclick="location.href = 'introModifyForm.do?intro_num=<%=article.getIntro_num()%>&page=<%=nowPage%>'">수정하기</button>
<% } %>
		</div>
	</section>
</section>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(function(){
	$(".lecList").hide();
		$(".rev").click(function(){
			$(this).siblings().removeClass("on");
			$(this).addClass("on");
			$(".revList").show();
			$(".lecList").hide();
		});
		$(".lec").click(function(){
			$(this).siblings().removeClass("on");
			$(this).addClass("on");
			$(".lecList").show();
			$(".revList").hide();
		});
});	
</script>
</body>
</html>