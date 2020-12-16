<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의상세탭</title>
<script src="js/jquery-3.5.1.js"></script>
<script>
$(function(){
$(".dataList").hide();
$(".manageList").hide();
	$(".listTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".lectureList").show();
		$(".dataList").hide();
		$(".manageList").hide();
	});
	$(".dataTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".lectureList").hide();
		$(".dataList").show();
		$(".manageList").hide();
	});
	$(".manageTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".lectureList").hide();
		$(".dataList").hide();
		$(".manageList").show();
	});	
});
</script>
<style>
body {
	text-decoration: none;
}

.main {
	top: 0px;
	right: 0px;
}
.sidebar {
	width: 390px;
	height: 650px;
	opacity: 0.9;
	border: 1px solid black;
	float: left;
	margin-left: 10px;
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
	width: 130px;
	height: 60px;
	line-height: 60px;
	text-align: center;
	color: #8a8a8a;
	background-color: #d7d7d7;
}

.listTab.on {
	background-color: #ffffff;
	border: 1px solid #bcbcbc;
	border-bottom: 0px;
}

.dataTab {
	flex: auto;
	letter-spacing: 1px;
	cursor: pointer;
	font-weight: 600;
	width: 130px;
	height: 60px;
	line-height: 60px;
	text-align: center;
	color: #8a8a8a;
	background-color: #d7d7d7;
}

.dataTab.on {
	background-color: #ffffff;
	border: 1px solid #bcbcbc;
	border-bottom: 0px;
}

.manageTab {
	flex: auto;
	letter-spacing: 1px;
	cursor: pointer;
	font-weight: 600;
	width: 130px;
	height: 60px;
	line-height: 60px;
	text-align: center;
	color: #8a8a8a;
	background-color: #d7d7d7;
}

.manageTab.on {
	background-color: #ffffff;
	border: 1px solid #bcbcbc;
	border-bottom: 0px;
}

.lectureList {
	width: 370px;
	height: 550px;
	margin: auto;
	background-color: #E6E6E6;
}

.lectureList ul {
	list-style: none;
}

.lectureList ul li {
	overflow: hidden;
    border-bottom: 1px solid #dbdbdb;
    line-height: 50px;
}

.dataList {
	width: 370px;
	height: 550px;
	margin: auto;
	background-color: #E6E6E6;
}

.dataList ul {
	list-style: none;
}

.dataList ul li {
	overflow: hidden;
    border-bottom: 1px solid #dbdbdb;
    line-height: 50px;
}

.manageList {
	width: 370px;
	height: 320px;
	margin: auto;
	background-color: #E6E6E6;
}

.dataList ul {
	list-style: none;
}

.manageList ul li {
	overflow: hidden;
    border-bottom: 1px solid #dbdbdb;
    line-height: 30px;
}

.upload {
	width: 370px;
	height: 15px;
	background-color: #E6E6E6;
	margin: auto;
	text-align: center;
	overflow: hidden;
    border-bottom: 1px solid #dbdbdb;
    line-height: 15px;
}

.btni {
	width: 370px;
	height: 215px;
	margin: auto;
	background-color: #E6E6E6;
}
</style>
</head>
<body>
<div class="main">
<section style="float: left;">
	<iframe width="900" height="650" src="https://www.youtube.com/embed/ClabczduRiE" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>	
</section>
<section class="sidebar">
<!-- 여기서부터 메인바 -->
	<div class="tabs">
		<div class="listTab">
			List
		</div>
		<div class="dataTab">
			자료실
		</div>
		<div class="manageTab">
			강의관리
		</div>
	</div>
<!-- 여기서부터 학생들에게 보여지는 List -->
	<div class="lectureList">
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
		</ul>
	</div>
<!-- 여기서부터 강의에 필요한 자료를 업로드 할 수 있는 자료실 -->
	<div class="dataList">
		<ul>
			<li>강의자료1</li>
			<li>강의자료2</li>
			<li>강의자료3</li>
			<li>강의자료4</li>
			<li>강의자료5</li>
			<li>강의자료6</li>
			<li>강의자료7</li>
			<li>강의자료8</li>
			<li>강의자료9</li>
			<li>강의자료10</li>
		</ul>
	</div>
<!-- 여기서부터 강사만 볼 수 있는 강의 관리 Tab(if classify == 학생, 권한 없음) -->
	<div class="manageList">
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
		</ul>	
	<div class="upload">	<!-- 강의 업로드 버튼 -->
		<a>+</a>
	</div>
	<div class="btni"><!-- 저장하기/뒤로가기 -->
		<button style="margin-left: 100px; margin-top: 100px;">저장하기</button>
		<button style="margin-left: 10px; margin-top: 100px;">뒤로가기</button>
	</div>
	</div>
</section>
</div>
<!-- 여기서부터 메인바 축소 확대
<a>▶</a>  축소 
<a>◀</a>  확대 -->
</body>
</html>