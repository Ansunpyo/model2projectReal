<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Banner, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	if (loginMember == null || !loginMember.getId().equals("admin")) {
		out.println("<script>location.href='index.do'</script>");
	}
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/review.css">
</head>
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
					<li class="nav-item"><a class="nav-link" href="purchaseAllList.do">주문관리
					</a></li>
					<li class="nav-item"><a class="nav-link" href="members.do">회원관리</a></li>
					<li class="nav-item"><a class="nav-link" href="logout.do">로그아웃</a></li>
					<li class="nav-item active"><a class="nav-link" href="review.do">마이페이지
							<span class="sr-only">(current)</span></a></li>
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
		LinkedList<Banner> banList = (LinkedList<Banner>)session.getAttribute("banList");
%>
	<div class="topbar">
		<ul>
			<li>
				<div class="hamburger">
					<div></div>
					<div></div>
					<div></div>
				</div>
			</li>
			<li>
				<div class="topbarMenu">
					<ul>
						<li><a href="review.do">리뷰 관리</a></li>
						<li><a href="faq.do">FAQ 관리</a></li>
						<li><a href="category.do">카테고리 관리</a></li>
						<li><a href="banner.do">메인배너 관리</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">My Page</div>
			<div>
				<div class="myPageMenu"><a href="review.do"><img src="images/star_icon.png">&nbsp;리뷰 관리</a></div>
				<div class="myPageMenu"><a href="faq.do"><img src="images/faq_icon.png">&nbsp;FAQ 관리</a></div>
				<div class="myPageMenu"><a href="category.do"><img src="images/category_icon.png">&nbsp;카테고리 관리</a></div>
				<div class="myPageMenu on"><a href="banner.do"><img src="images/banner_icon.png">&nbsp;메인배너 관리</a></div>
			</div>
		</div>
			<div class="contents">
				<div class="container d-flex align-content-start flex-wrap">
<%
			if (banList != null) {
				for(int i = 0; i < banList.size(); i++) {					
%>
					<div class="card m-2">
					  <div class="card-body">
					    <img src="banner/<%=banList.get(i).getImg() %>" width="120px" height="30px" />
					    <h6 class="card-subtitle mb-2 text-muted">file name:<%=banList.get(i).getImg() %></h6>
					    <span class="float-right"><button class="btn btn-danger deleteBanner" id="<%=banList.get(i).getImg() %>">삭제</button></span>
					  </div>
					</div>
<%
				}					
			}
%>
					
				</div>
				<div class="mt-5 bg-white p-4">
					<form method="post" action="bannerUpload.do" enctype="multipart/form-data">
						<div id="imagePreview"></div> 
						<input type="file" class="form-control-file bannerFile" name="bannerFile" required="required" />
						<input type="submit" class="btn btn-primary mt-3" value="배너 등록" />
						<div><h6>배너 크기는 1200 x 300이 적절하며, jpg(jpeg), png, gif 파일만 등록할 수 있습니다. 10mb까지 업로드 가능합니다.</h6></div>
					</form>
				</div>
			</div>
	</div>
	
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
			
			$(document).on("click", ".deleteBanner", function(){
				if(confirm('정말 삭제하시겠습니까?')) {
					var img = $(this).attr("id");
					location.href="deleteBanner.do?img=" + img;
				}
			});
			$(".bannerFile").change(function(){
				var file_name = $(".bannerFile").val().split('\\').pop();;
				var extension = file_name.split('.').pop().toLowerCase();
				var extArray = ["jpg", "jpeg", "png", "gif"];
				var isExtRight = extArray.indexOf(extension) > -1;
				if(!isExtRight) {
					alert('유효하지 않은 파일 형식입니다.');
					$(".bannerFile").val('');
				}
			});
		});
	</script>
	<script src="js/sidebar.js"></script>
</body>
</html>