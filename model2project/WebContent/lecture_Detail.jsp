<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Lecture_Video, vo.Lecture, java.util.LinkedList" %>

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
	<iframe width="900" height="650" src="https://www.youtube.com/embed/ClabczduRiE" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>	
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
				<li>강의10</li>
				<li>강의11</li>
				<li>강의12</li>
				<li>강의13</li>
			</ul>
		</div>
		<div class="down">
			<a href="#">▼</a>
		</div>
	</div>
<!-- 여기서부터 강사만 볼 수 있는 강의 관리 Tab(if classify == 학생, 권한 없음) -->
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
					<form>		
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