<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Lecture_Video, vo.Lecture, vo.Member, java.util.*" %>
<%
	Member loginMember = null;
	String classify = null;
	LinkedList<Lecture_Video> vidList = (LinkedList<Lecture_Video>)session.getAttribute("vidList");
	LinkedList<Member> memList = (LinkedList<Member>)session.getAttribute("memList");
	if(session.getAttribute("loginMember") != null) {
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
<title>강의상세탭</title>
<link rel="stylesheet" href="css/Lecture_Detail.css"/>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/Lecture_Detail.js"></script>
</head>
<body onresize="parent.resizeTo(1340,730)" onload="parent.resizeTo(1340,730)">
<div class="main">
<section style="float: left;">
<%
	String video = "";
	if(vidList.get(0).getVideo().indexOf("&") > 0) {
		video = vidList.get(0).getVideo().substring(vidList.get(0).getVideo().indexOf("v=") + 2, vidList.get(0).getVideo().indexOf("&"));
	} else {
		video = vidList.get(0).getVideo().substring(vidList.get(0).getVideo().indexOf("v=")+2);
	}
%>
	<iframe width="900" height="650" src="https://www.youtube.com/embed/<%=video %>" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>	
</section>
<a class="op" style="float: left; text-align: center; font-size: 10px; line-height: 650px;">▶</a>
<a class="cl" style="float: left; text-align: center; font-size: 10px; line-height: 650px;">◀</a>
<section class="sidebar">
<!-- 여기서부터 메인바 -->
	<div class="tabs">
		<div class="listTab on">List</div>
		<div class="manageTab">강의관리</div>
	</div>
<!-- 여기서부터 학생들에게 보여지는 List -->
	<div class="lectureList">
		<div class="up">
			<a href="#">▲</a>
		</div>
		<div class="photoList">
<% 
	if(vidList != null) {
%>		
			<ul>
<%

	for(int i=0;i<vidList.size();i++) {
%>				
				<li><a href="vidList.get(i).getVideo()"><%=vidList.get(i).getChapter_title() %></a></li>
<%					
	}
%>				
			</ul>
<%
	} else {
%>			
					<h2 style="text-align: center; margin-top: 245px;">등록된 강의가 없습니다.</h2>
<%
	}
%>
		</div>
		<div class="down">
			<a href="#">▼</a>
		</div>
	</div>
	<div class="manageList">
		<div class="up">
			<a href="#">▲</a>
		</div>
		<div class="photoList">
			<ul>
				<li>강의1</li>
				<li>강의2</li>
				<li>강의3</li>
				<li>강의4</li>
				<li>강의5</li>
				<li>강의6</li>
				<li>강의7</li>
				<li>강의8</li>
				<li>강의9</li>
				<li class="lug" style="text-align: center;"><p style="display:block; cursor:pointer;">+</p></li>
				<div class="lu">
					<form action="lectureDetailUpload.do" method="post" name="ldinfo">
						<input type="hidden" name="lecture_num" value="<%=vidList.get(0).getLecture_num() %>"/>		
						<textarea style="margin-top: 15px;" class="inputSlot" name="chapter_title" placeholder="강의제목" autocomplete="off" /></textarea>
						<textarea style="margin-top: 5px;" class="inputSlot" name="video" placeholder="강의 URL" autocomplete="off" /></textarea>
						<button style="margin-top: 5px; margin-left: 113px; height: 30px;">등록하기</button>
						<button class="back" style="margin-top: 5px; height: 30px;">뒤로가기</button>
					</form>
				</div>
			</ul>
		</div>
		<div class="down">
			<a href="#">▼</a>
		</div>
	</div>
</section>
</div>
</body>
</html>