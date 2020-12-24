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
<script src="js/LD.js"></script>
</head>
<body onresize="parent.resizeTo(1715,790)" onload="parent.resizeTo(1715,790)">
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
	<iframe id="showLecture" width="1280" height="720" src="https://www.youtube.com/embed/<%=video %>" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>	
</section>
	<a class="op" href="#" style="float: left; text-align: center; font-size: 10px; line-height: 650px; text-decoration: none;">▶</a>
	<a class="cl" href="#" style="float: left; text-align: center; font-size: 10px; line-height: 650px; text-decoration: none;">◀</a>
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
				<li><a href="#" onclick="return false;" class="lec" style="text-align: center;" data-url="<%=vidList.get(i).getVideo()%>"><%=vidList.get(i).getChapter_title() %></a></li>
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
<% 
	if(vidList != null) {
%>		
			<ul>
<%

	for(int i=0;i<vidList.size();i++) {
%>					
				<li><a style="margin-left: 25px;" href="#" onclick="false" ><%=vidList.get(i).getChapter_title() %>
				<button onclick = "location.href ='lectureDetailDelete.do?chapter=<%=vidList.get(i).getChapter() %>&lecture_num=<%=vidList.get(i).getLecture_num() %>'" 
				style="float: right; margin-top: 15px; margin-right: 40px;">X</button></a></li>
<%					
	}
%>				
				<li class="lug" style="text-align: center;"><p style="display:block; cursor:pointer;">+</p></li>
<%
	} else {
%>		
	
<%
	}
%>				
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		$(".lec").click(function(){
			let nowUrl = $(this).attr("data-url");
			if(nowUrl.indexOf("&") > 0) {
				nowUrl = nowUrl.substring(nowUrl.indexOf("v=") + 2, nowUrl.indexOf("&"));
			} else {
				nowUrl = nowUrl.substring(nowUrl.indexOf("v=")+2);
			}

			let showUrl = "https://www.youtube.com/embed/" + nowUrl;
			console.log(showUrl);
			$("#showLecture").attr("src", showUrl);
		});
	});
	

</script>
</body>
</html>