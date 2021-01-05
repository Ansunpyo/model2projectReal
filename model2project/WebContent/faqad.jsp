<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Faq, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/sidebar.css">
	<style>
		ul {
			list-style: none;
		}
		ul, li {
			padding-left: 0px;
		}
	</style>
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
	if(loginMember == null){
		out.println("<script>alert('로그인이 필요합니다.');location.href='loginPage.do';</script>");
	} else {
				LinkedList<Faq> faqList = (LinkedList<Faq>)session.getAttribute("faqList");
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
				<div class="myPageMenu on"><a href="faq.do"><img src="images/faq_icon.png">&nbsp;FAQ 관리</a></div>
				<div class="myPageMenu"><a href="category.do"><img src="images/category_icon.png">&nbsp;카테고리 관리</a></div>
				<div class="myPageMenu"><a href="banner.do"><img src="images/banner_icon.png">&nbsp;메인배너 관리</a></div>
			</div>
		</div>
			<div class="contents justify-content-center">
				<div class="container">
				  <div class="row">
				    <div class="col-sm">
				    	<h1><span class="badge bg-light text-dark shadow p-3 mb-5 bg-white rounded">FAQ</span></h1>
				    </div>
				  </div>
				</div>
<%
	if(faqList == null) {
%>
			<div>
				<h2>자주 묻는 질문이 없습니다.</h2>
			</div>
<%
	} else {
%>
			<div class="contents">
<%
				for (int i = 0; i<faqList.size();i++) {
%>
			        <div id="accordion<%=i %>">
					  <div class="card">
					    <div class="card-header">
					      <h5>
					        <button class="btn btn-link" data-toggle="collapse" data-target="#collapse<%=i %>">
					          <span class="badge bg-info text-light">Q</span> <span class="text-muted"><%=faqList.get(i).getQuestion() %></span>
					        </button>
					          <span class="float-right"><button class="btn btn-danger delButton" id="<%=faqList.get(i).getFaq_num() %>">삭제</button></span>
					      </h5>
					    </div>
					    <div id="collapse<%=i %>" class="collapse" data-parent="#accordion<%=i %>">
					      <div class="card-body">
					        <span class="badge bg-primary text-light">A</span> <%=faqList.get(i).getAnswer() %>
					      </div>
					    </div>
					  </div>
					</div>
<%
				}	
%>
			       <div><hr /></div>
			       <form method="post" action="faqWrite.do">
				        <ul class="writeWrap">
				        	<li class="titleBigWrap">
				        		<ul class="titleWrap">
				        			<li class="titleHeader"><h4><span class="badge rounded-pill bg-success text-light">QUESTION</span></h4></li>
				        			<li class="titleInput"><div><input type="text" class="form-control" name="question" autocomplete="off" required="required" placeholder="질문" /></div></li>
				        		</ul>
				        	</li>
				        	<li class="contentsBigWrap">
				        		<ul class="contentsWrap">
				        			<li class="contentsHeader"><h4><span class="badge rounded-pill bg-success text-light">ANSWER</span></h4></li>
				        			<li class="contentsInput"><div><textarea rows="10" class="form-control" style="resize: none;" name="answer" placeholder="답변" required="required"></textarea></div></li>
				        		</ul>
				        	</li>
				        </ul>
				        <input type="submit" class="btn btn-primary float-right" value="등록" />
			        </form>
			        
			</div>
<%
	}
%>
	</div>
<%			
	}
%>
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
			$(".delButton").click(function(){
				if (window.confirm("정말 삭제하시겠습니까?")) {
					var click_id = $(this).attr('id');
			    	location.href="faqDelete.do?faqId="+click_id;
			    }
			});
		});
	</script>
	<script src="js/sidebar.js"></script>
</body>
</html>