<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.One_On_One, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/one_on_one.css">
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
					<li class="nav-item"><a class="nav-link" href="introList.do">강사소개
					</a></li>
					<li class="nav-item"><a class="nav-link" href="lectureList.do">강의목록</a></li>
					<li class="nav-item"><a class="nav-link" href="editProfilePage.do">마이페이지</a></li>
					<li class="nav-item active"><a class="nav-link" href="faq.do">고객센터
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
		LinkedList<One_On_One> oneList = (LinkedList<One_On_One>)session.getAttribute("oneOnOneList");
%>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">고객센터</div>
			<div>
				<div class="myPageMenu"><a href="faq.do"><img src="images/faq_icon.png">&nbsp;자주묻는 질문</a></div>
				<div class="myPageMenu on"><a href="one_on_one.do"><img src="images/oneonone_icon.png">&nbsp;1:1 문의하기</a></div>
				<div class="myPageMenu"><a href="purchaseList.do"><img src="images/event_icon.png">&nbsp;구매내역</a></div>
			</div>
		</div>
			<div class="contents justify-content-center">
				<div class="container">
				  <div class="row">
				    <div class="col-sm">
				    	<h1><span class="badge bg-light text-dark shadow p-3 mb-5 bg-white rounded">1:1 문의하기</span></h1>
				    </div>
				  </div>
				</div>
				<div class="formWrap mb-5">
			        <form method="post" action="one_on_one_submit.do">
				        <ul class="writeWrap">
				        	<li class="titleBigWrap">
				        		<ul class="titleWrap">
				        			<li class="titleHeader"><h4><span class="badge rounded-pill bg-success text-light">TITLE</span></h4></li>
				        			<li class="titleInput"><div><input type="text" class="form-control" name="title" autocomplete="off" required="required" placeholder="1:1 문의는 삭제할 수 없습니다!" /></div></li>
				        		</ul>
				        	</li>
				        	<li class="contentsBigWrap">
				        		<ul class="contentsWrap">
				        			<li class="contentsHeader"><h4><span class="badge rounded-pill bg-success text-light">CONTENTS</span></h4></li>
				        			<li class="contentsInput"><div><textarea rows="10" class="form-control" style="resize: none;" name="contents" placeholder="500자까지 적을 수 있습니다." required="required"></textarea></div></li>
				        		</ul>
				        	</li>
				        </ul>
				        <input type="submit" class="btn btn-primary float-right" value="문의하기" />
			        </form>
				</div>
				<div><hr/></div>
				<div class="container">
				  <div class="row justify-content-center mx-0">
				    <div class="col-sm-12 alert alert-info shadow">
				      <h3>문의 내역</h3>
				    </div>
				    
<%
			if(oneList != null) {	
				
				for(int i = 0; i < oneList.size(); i++) {				    
%>
				    <div class="col-sm-12 bg-white py-3 shadow mb-3">
				    	<h5 class="Q"><%=oneList.get(i).getTitle() %></h5>
<%
				    if(oneList.get(i).getAnswer() != null) {				    	
%>
				    	
								<div class="badge badge-primary float-left">답변 완료</div>
<%
				    }
%>
				    	<div><hr></div>
				    	<div class="popup" style="word-break: break-all;">
							<%=oneList.get(i).getContents() %>
							<div><hr/></div>
<%
					if(oneList.get(i).getAnswer() != null) {							
%>
							<div class="content">
								<p><b><%=oneList.get(i).getAnswer() %></b></p>			
							</div>
<%
					}
%>
						</div>
				    </div>
<%
				}
			}
%>
				    
				  </div>
				</div>
			</div>
	</div>
<%			
	}
%>
</div>
	<script src="./js/jquery-1.12.4.min.js"></script>
	<!-- Optional JavaScript; -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
	<script>
		$(function(){
			$(".Q").click(function(){
					$(this).siblings(".popup").slideToggle();
			});
		});
		$(function(){
			$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			$(window).resize(function(){
				$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			});
		});
	</script>
</body>
</html>