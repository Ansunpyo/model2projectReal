<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Intro, vo.Member, java.util.*, java.text.SimpleDateFormat"  %>
<%
	ArrayList[] articleList = (ArrayList[])session.getAttribute("articleList");
	ArrayList<Intro> intList = articleList[0];
	ArrayList<Member> memList = articleList[1];
	Intro article = intList.get(0);
	Member articlem = memList.get(0);
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
<title>강사소개 Detail</title>
<style>
	body,h1,h2,h3,h4,h5,h6,p,address,header,footer,section,aside,nav,ul,ol,li,dl,dt,dd,input,textarea,select,button {
    	margin: 0;
    	padding: 0;
    	font-family: 'Malgun Gothic',sans-serif;
    	font-size: 14px;
    	color: #222328;
	}
	
	body {
	    background-image: url(https://roman-flossler.github.io/StoryShowGallery/img/bg.png);
	}
	
	li {
   		list-style: none;
	}

	.clearfix {
		*zoom: 1;
	}
	.slider{
		width: 1200px;
		height: 300px; 
		overflow: hidden;
		margin: 0 auto;
	}
	
	h2 {
		text-align: center;
	}
	
	#basicInfoArea {
		height: 40px;
		text-align: center;
	}
	
	#articleContentArea {
		background: orange;
		margin-top: 20px;
		height: 350px;
		text-align: center;
		overflow: auto;
	}
	
	#commandList {
		margin: auto;
		width: 500px;
		text-align: center;
	}
	
	#container {
    width: 1200px;
    margin: 10px auto 0;
}
#container .notice {
    position: relative;
    float: left;
    width: 800px;
    margin-right: 15px;
}
#container .notice1 {
    position: relative;
    float: left;
    width: 355px;
    margin-right: 15px;
}
#container .notice h4 {
    position: relative;
    z-index: 1;
    float: left;
    height: 35px;
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
#container .notice h4.on {
    border-bottom-color: #fff;
    background-color: #fff;
}

#container .notice ul {

}
#container .notice .news ul {
    padding-top: 20px;
}
#container .notice .news ul li {
    overflow: hidden;
    margin: 0 10px;
    border-bottom: 1px solid #dbdbdb;
    line-height: 30px;
}
#container .notice .news ul li a {
    float: left;
}
#container .notice .news ul li span {
    float: right;
    color: #606060;
}
#container .notice .gallery ul {
    display: none;
    padding: 20px 0 0 12px;
}
#container .notice .gallery ul li {
    float: left;
    margin-right: 13px;
}
#container .notice .gallery ul li.last {
    margin-right: 0;
}

#container .notice2 {
    position: relative;
    float: left;
    width: 1170px;
    margin-right: 15px;
    margin-top: 20px;
}
#container .notice2 h4 {
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
#container .notice2 h4.on {
    border-bottom-color: #fff;
    background-color: #fff;
}

#container .notice2 ul {

}
#container .notice2 .news ul {
    padding-top: 30px;
}
#container .notice2 .news ul li {
    overflow: hidden;
    margin: 0 10px;
    border-bottom: 1px solid #dbdbdb;
    line-height: 30px;
}
#container .notice2 .news ul li a {
    float: left;
}
#container .notice2 .news ul li span {
	float: right;
	color: #606060;
}
</style>
</head>
<body>
	<!-- 게시판 수정 -->
  	<section id="container" class="clearfix">
		<section class="notice1">
			<a href ="#"><img src="/upload/<%=article.getImg1() %>" alt="-" 
			style="width : 355px; height:300px; align:center;"/><span></span></a>					
		</section>
		<section class="notice">
			<div class="news">
				<ul>
					<li><a href="#" >name</a><span><%=articlem.getName() %></span></li>
					<li><a href="#">E-mail</a><span><%=articlem.getEmail() %></span></li>
					<li><a href="#">gender</a><span><%=articlem.getGender() %></span></li>
					<li><a href="#">major</a><span><%=articlem.getMajor() %></span></li>
					<li><a href="#">education</a><span><%=articlem.getEducation() %></span></li>
					<li><a href="#">introduce</a><span><%=article.getContents() %></span></li>
				</ul>				
			</div>
		</section>
		<section class="notice2">
			<div class="news">
				<h4 class="on">리뷰</h4>
				<ul>
					<li><a href="#">It was a good class.</a><span>2020.12.01</span></li>
					<li><a href="#">I'd like to hear it again if I have a chance.</a><span>2020.10.17</span></li>
					<li><a href="#">It was fun.</a><span>2020.09.21</span></li>
					<li><a href="#">When is the next class coming?</a><span>2020.09.12</span></li>
				</ul>				
			</div>
	<div id="notice2">
		<div class="mr-auto">
			<button style="margin-top:20px; margin-right:10px; float:right;"
		 	type="button" class="btn btn-success" onclick = "location.href = 'introList.do'">목록으로</button>
		 	<button id="button_event" style="margin-top:20px; margin-right:10px; float:right;"
		 	type="button" class="btn btn-danger" onclick =
			"location.href = 'introDeleteForm.do?intro_num=<%=article.getIntro_num() %>&page=<%=nowPage %>'">삭제하기</button>		 	
			<button style="margin-top:20px; margin-right:10px; float:right;" 
			type="button" class="btn btn-primary" onclick =
			"location.href = 'introModifyForm.do?intro_num=<%=article.getIntro_num() %>&page=<%=nowPage %>'">수정하기</button>
		</div>
	</div>
		</section>
	</section>
</body>
</html>