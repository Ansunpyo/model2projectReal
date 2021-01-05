<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="vo.Lecture_Video, vo.Lecture, vo.Member, java.util.*"%>
<%
	Member loginMember = null;
String classify = null;
LinkedList<Lecture_Video> vidList = (LinkedList<Lecture_Video>) session.getAttribute("vidList");
LinkedList<Member> memList = (LinkedList<Member>) session.getAttribute("memList");
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
<title>강의상세탭</title>
<script src="js/jquery-3.5.1.js"></script>
<style>
@charset "UTF-8";
html, body, div, iframe, h2, p, a, ul, li, form, section{
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

section {
	display: block;
}

body {
	line-height: 1;
	text-decoration: none;
	margin: auto;
}

ul {
	list-style: none;
}

.inputSlot {
	width: 340px;
	height: 20px;
	border: 1px solid #6f6f6f;
	border-radius: 4px;
	font-size: 1.2rem;
	font-weight: 600;
	resize: none;
	margin-right: 2px;
}

textarea:hover {
	box-shadow: 0px 0px 12px gray;
}

textarea:focus {
	outline: none;
}


.sidebar {
	width: 390px;
	height: 720px;
	opacity: 0.9;
	border: 1px solid black;
	float: left;
}

.tabs {
	width: 390px;
	height: 60px;
	display: flex;
}

.listTab {
	flex: auto;
	letter-spacing: 1px;
	cursor: pointer;
	font-weight: 600;
	width: 195px;
	height: 60px;
	line-height: 60px;
	text-align: center;
	color: #8a8a8a;
	background-color: #d7d7d7;
}

.listTab.on {
	background-color: #ffffff;
	border-bottom: 0px;
}

.manageTab {
	flex: auto;
	letter-spacing: 1px;
	cursor: pointer;
	font-weight: 600;
	width: 195px;
	height: 60px;
	line-height: 60px;
	text-align: center;
	color: #8a8a8a;
	background-color: #d7d7d7;
}

.manageTab.on {
	background-color: #ffffff;
	border-bottom: 0px;
}

.lectureList {
	width: 370px;
	height: 660px;
	margin: auto;
	background-color: #E6E6E6;
	position: relative;
	overflow: hidden;
}

.lectureList ul {
	list-style: none;
}

.lectureList ul li {
	width: 370px;
	height: 58px;
	overflow: hidden;
	border-bottom: 1px solid #dbdbdb;
	line-height: 58px;
	text-decoration: none;
}

.manageList {
	width: 370px;
	height: 660px;
	margin: auto;
	background-color: #E6E6E6;
	position: relative;
	overflow: hidden;
}

.manageList ul {
	list-style: none;
}

.manageList ul li {
	width: 370px;
	height: 58px;
	overflow: hidden;
	border-bottom: 1px solid #dbdbdb;
	line-height: 58px;
	text-decoration: none;
}

.op {
	width: 10px;
	height: 722px;
	background-color: #F5DA81;
	font-color: white;
}

.cl {
	width: 10px;
	height: 830px;
	background-color: #F5DA81;
	font-color: white;
}

.lectureList>.up, .lectureList>.down {
	margin: 0 auto;
	width: 370px;
	height: 35px;
	background: #444;
}

.lectureList>.up:hover, .lectureList>.down:hover {
	background: #333;
}

.lectureList>.up>a, .lectureList>.down>a {
	display: block;
	line-height: 35px;
	text-align: center;
	text-decoration: none;
	font-weight: 700;
	color: #8ac007;
}

.manageList>.up, .manageList>.down {
	margin: 0 auto;
	width: 370px;
	height: 35px;
	background: #444;
}

.manageList>.up:hover, .manageList>.down:hover {
	background: #333;
}

.manageList>.up>a, .manageList>.down>a {
	display: block;
	line-height: 35px;
	text-align: center;
	text-decoration: none;
	font-weight: 700;
	color: #8ac007;
}

.lecList {
	position: relative;
	overflow: hidden;
	overflow-y:scroll;
	width: 370px;
	height: 590px;
}

.lecList>ul {
	position: absolute;
	width: 370px;
}

.lecList>ul>li>a {
	display: block;
	width: 370px;
	height: 58px;
	line-height: 58px;
	text-decoration: none;
	font-size: 0.875rem;
	background: rgba(0, 0, 0, 0);
	color: #000;
	border-bottom: 1px solid rgba(0, 0, 0, .1);
	-webkit-transition: all 0.4s;
	transition: all 0.4s;
}

.lecList>ul>li>a.on {
	background: rgba(0, 0, 0, .1);
	color: #f1f1f1;
}
</style>
</head>
<!-- onresize="parent.resizeTo(1698,790)" -->
<body onload="parent.resizeTo(1698,790)"
	style="min-width: 1715px; min-height: 900px; overflow: hidden;">
	<div class="main">
		<section style="float: left;">
			<%
				String video = "";
			if (vidList.get(0).getVideo().indexOf("&") > 0) {
				video = vidList.get(0).getVideo().substring(vidList.get(0).getVideo().indexOf("v=") + 2,
				vidList.get(0).getVideo().indexOf("&"));
			} else {
				video = vidList.get(0).getVideo().substring(vidList.get(0).getVideo().indexOf("v=") + 2);
			}
			%>
			<iframe id="showLecture" width="1280" height="722"
				src="https://www.youtube.com/embed/<%=video%>" frameborder="0"
				allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
				allowfullscreen></iframe>
		</section>
		<a class="op" href="#"
			style="float: left; text-align: center; font-size: 10px; line-height: 650px; text-decoration: none;">▶</a>
		<a class="cl" href="#"
			style="float: left; text-align: center; font-size: 10px; line-height: 650px; text-decoration: none;">◀</a>
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
				<div class="lecList">
					<%
						if (vidList != null) {
					%>
					<ul>
						<%
							for (int i = 0; i < vidList.size(); i++) {
						%>
						<li><a href="#" onclick="return false;"
							style="margin-left: 25px;" class="lec"
							data-url="<%=vidList.get(i).getVideo()%>"><%=vidList.get(i).getChapter_title()%></a></li>
						<%
							}
						%>
					</ul>
					<%
						} else {
					%>
					<h2 style="text-align: center; margin-top: 245px;">등록된 강의가
						없습니다.</h2>
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
				<div class="lecList">
					<%
						if (vidList != null) {
					%>
					<ul>
						<%
							for (int i = 0; i < vidList.size(); i++) {
						%>
						<li><a style="margin-left: 25px;" href="#" onclick="false"><%=vidList.get(i).getChapter_title()%>
								<button
									onclick="location.href ='lectureDetailDelete.do?chapter=<%=vidList.get(i).getChapter()%>&lecture_num=<%=vidList.get(i).getLecture_num()%>'"
									style="float: right; margin-top: 20px; margin-right: 55px;">X</button></a></li>
						<%
							}
						%>
						<li class="lug" style="text-align: center;"><p
								style="display: block; cursor: pointer;">+</p></li>
						<%
							} else {
						%>

						<%
							}
						%>
						<div class="lu">
							<form action="lectureDetailUpload.do" method="post" name="ldinfo">
								<input type="hidden" name="lecture_num"
									value="<%=vidList.get(0).getLecture_num()%>" />
								<textarea style="margin-top: 15px;" class="inputSlot"
									name="chapter_title" placeholder="강의제목" autocomplete="off" required="required"/></textarea>
								<textarea style="margin-top: 5px;" class="inputSlot"
									name="video" placeholder="강의 URL" autocomplete="off" required="required"/></textarea>
								<button
									style="margin-top: 5px; margin-bottom: 5px; margin-left: 113px; height: 30px;">등록하기</button>
								<button type="button" class="back"
									style="margin-top: 5px; margin-bottom: 5px; height: 30px;">뒤로가기</button>
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
	$(".manageList").hide();
	$(".cl").hide();
	$(".lu").hide();
		$(".listTab").click(function(){
			$(this).siblings().removeClass("on");
			$(this).addClass("on");
			$(".lectureList").show();
			$(".manageList").hide();
		});
		$(".manageTab").click(function(){
			$(this).siblings().removeClass("on");
			$(this).addClass("on");
			$(".lectureList").hide();
			$(".manageList").show();
		});	
		$(".op").click(function(){
			window.resizeTo(1698, 898);
			$("#showLecture").attr("width", 1672);
			$("#showLecture").attr("height", 830);
			$(".sidebar").hide();
			$(".cl").show();
			$(".op").hide();
			$(".lu").hide();
		});
		$(".cl").click(function(){
			window.resizeTo(1698, 790); 
			$("#showLecture").attr("width", 1280);
			$("#showLecture").attr("height", 722);
			$(".cl").hide();
			$(".op").show();
			$(".sidebar").show();
		});	
		$(".lug").click(function(){
			$(".lug").hide();
			$(".lu").show();
		});	
		$(".back").click(function(){
			$(".lu").hide();
			$(".lug").show();
		});	
	});

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