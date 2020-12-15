<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Intro, vo.Member, java.util.*, java.text.SimpleDateFormat" %>
<%
	String nowPage = request.getParameter("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
<title>강사소개 작성하기</title>
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
</style>
</head>
<body>
<section id="container" class="clearfix">
	<form action="introWritePro.do" method="post" enctype="multipart/form-data" name="introform">
	<input type="hidden" name="page" value="<%=nowPage %>"/>
	<section class="notice1">
			<ul>
				<li><input name="img1" type="file" id="img1" required="required" /></li>
				<li><input name="img2" type="file" id="img2"/></li>
				<li><input name="img3" type="file" id="img3"/></li>
				<li><input name="img4" type="file" id="img4"/></li>
				<li><input name="img5" type="file" id="img5"/></li>
				<li><input name="img6" type="file" id="img6"/></li>
			</ul>
			<ul>
				<li><input type="text" class="inputSlot" name="imgex1" placeholder="사진 소개1" required="required"/>
				<input type="text" class="inputSlot" name="imgex2" placeholder="사진 소개2"/>
				<input type="text" class="inputSlot" name="imgex3" placeholder="사진 소개3"/>
				<input type="text" class="inputSlot" name="imgex4" placeholder="사진 소개4"/>		
				<input type="text" class="inputSlot" name="imgex5" placeholder="사진 소개5"/>			
				<input type="text" class="inputSlot" name="imgex6" placeholder="사진 소개6"/></li>
			</ul>
		</section>
		<section class="notice">
			<div class="news">
				<ul>
					<li><a>introduce</a><span><textarea id="contents" name="contents" cols="50" rows="1"
					 required="required"></textarea></span></li>
				</ul>				
			</div>
		</section>
	<div>
		<div>
			<button style="margin-top:20px; margin-right:40px; float:right;"
		 	type="button" class="btn btn-success" onclick="history.back(-1);">돌아가기</button>
			<input type="submit" style="margin-top:20px; margin-right:20px; float:right;" 
			class="btn btn-primary" value="작성하기"/>
		</div>	
	</div>
	</form>
</section>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
<!--<
<body>
<div class="introCont">
	<div class="loginFormDiv">
		<div class="loginFormDiv2">		
			<form action="introWritePro.do" method="post" enctype="multipart/form-data" name="boardform">
			<div>
				<input type="text" class="inputSlot" name="contents" placeholder="소개" required="required"/> 		
			</div>
			<div>
				<input name="img1" type="file" id="img1" required="required" />
				<input name="img2" type="file" id="img2"/>
				<input name="img3" type="file" id="img3"/>
				<input name="img4" type="file" id="img4"/>
				<input name="img5" type="file" id="img5"/>
				<input name="img6" type="file" id="img6"/>
				<input type="text" class="inputSlot" name="imgex1" placeholder="사진 소개1" required="required"/>
				<input type="text" class="inputSlot" name="imgex2" placeholder="사진 소개2"/>
				<input type="text" class="inputSlot" name="imgex3" placeholder="사진 소개3"/>
				<input type="text" class="inputSlot" name="imgex4" placeholder="사진 소개4"/>				
				<input type="text" class="inputSlot" name="imgex5" placeholder="사진 소개5"/>				
				<input type="text" class="inputSlot" name="imgex6" placeholder="사진 소개6"/>				
			</div>
			<section id="commandCell" style="margin-top: 120px;">
				<button type="submit" class="btn btn-primary" onclick="submit">등록하기</button>
				<button type="button" class="btn btn-success" onclick="history.back(-1);">목록으로</button>
			</section>
		</form>
		</div>
	</div>
</div>
-->