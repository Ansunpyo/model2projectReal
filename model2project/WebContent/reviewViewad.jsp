<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Lecture, vo.Review, vo.Review_Comment, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	int nowPage = 1;
	int review_num = Integer.parseInt(request.getParameter("review_num"));
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/review.css">
	<style>
		.depth1, .depth2, .depth3, .depth4, .depth5, .depth6, .depth7, .depth8, .depth9, .depth10 {
		    padding-left: 2%;
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
				LinkedList<Object> reviewViewList = (LinkedList<Object>)session.getAttribute("reviewViewList");
				if(request.getParameter("page") != null) nowPage = Integer.parseInt(request.getParameter("page"));
				Review re = (Review) reviewViewList.get(0);
				Member mem = (Member) reviewViewList.get(1);
				session.setAttribute("review_num", re.getReview_num());
				
				LinkedList[] reviewComList = (LinkedList[])session.getAttribute("reviewComList");
				LinkedList<Member> cMemList = null;
				LinkedList<Review_Comment> rCList = null;
				if (reviewComList != null) {
					cMemList = reviewComList[0];
					rCList = reviewComList[1];
				}
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
						<li><a href="editProfilePage.do">개인정보 수정</a></li>
						<li><a href="favorites.do">즐겨찾기 목록</a></li>
						<li><a href="review.do">리뷰 남기기</a></li>
						<li><a href="messenger.do">쪽지함</a></li>
						<li><a href="quit.do">회원 탈퇴</a></li>
						<li><a href="logout.do">로그아웃</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">My Page</div>
			<div>
				<div class="myPageMenu on"><a href="review.do"><img src="images/star_icon.png">&nbsp;리뷰 관리</a></div>
				<div class="myPageMenu"><a href="faq.do"><img src="images/faq_icon.png">&nbsp;FAQ 관리</a></div>
				<div class="myPageMenu"><a href="category.do"><img src="images/category_icon.png">&nbsp;카테고리 관리</a></div>
				<div class="myPageMenu"><a href="banner.do"><img src="images/banner_icon.png">&nbsp;메인배너 관리</a></div>
			</div>
		</div>
			<div class="contents">
				<div class="row justify-content-center mb-5">
					<div class="col-md-12">
						<div class="bbsWrapper">
							<ul class="bbsViewWrapperList">
								<li class="bbsViewWriter">
									<ul>
										<li class="bbsViewWriterHeader">WRITER</li>
										<li class="bbsViewWriterName"><%=mem.getName() %>(<%=mem.getId() %>)</li>
									</ul>
								</li>
								<li class="bbsViewTitle">
									<ul>
										<li class="bbsViewTitleHeader">TITLE</li>
										<li class="bbsViewTitleText"><%=re.getTitle() %></li>
									</ul>
								</li>
								<li class="bbsViewBody">
									<div>
										<%=re.getContents() %>
									</div>
								</li>
								<li class="comments" style="width:100%;text-align:left;background:white;">
									<form action="reviewCommentWrite.do?page=<%=nowPage %>&review_num=<%=review_num %>" method="post">
										<div style="height:5px; botder-top:2px solid gray;"></div>
										<textarea class="form-control" name="contents" style="resize: none;" required="required"></textarea>
										<input type="submit" value="작성하기" class="btn btn-secondary" />
									</form>
									<script>
										function comment_step(parent, me, step) {
											var oCur = document.getElementById("IAMCOMMENT_"+me);
											if(parent>0 && step>0) {
												var oOrg = document.getElementById("comCont" + parent);
												var oCom = document.getElementById("com" + me);
												oOrg.className = "depth"+step;
												oOrg.innerHTML += oCur.innerHTML;
												oCur.parentNode.removeChild(oCur);
											} else {
												oCur.style.display="";
											}
										}
									</script>
<%
								if(reviewComList != null) {
									for(int i=0; i < cMemList.size(); i++){
%>
									<div id="IAMCOMMENT_<%=rCList.get(i).getComment_num() %>">
										<div id="com<%=rCList.get(i).getComment_num() %>">
											<span class="float-right"><%=rCList.get(i).getTime() %></span>
											<h6><%if(cMemList.get(i).getId().equals("admin")) { %><b>admin</b><% } else if (cMemList.get(i).getName().equals("<탈퇴한 회원>")) { %><b><탈퇴한 회원></b><% } else { %><b><%=cMemList.get(i).getName() %>(<%=cMemList.get(i).getId() %>)</b><% } %></h6>
											<button id="commentButton<%=rCList.get(i).getComment_num() %>" class="btn btn-primary float-right" onclick="$(this).parent().next('form').toggle()">+</button>
											<%if(loginMember.getId().equals("admin") || loginMember.getNumber() == cMemList.get(i).getNumber()) { %><button class="btn btn-danger float-right" onclick="location.href='deleteReviewComment.do?page=<%=nowPage %>&review_num=<%=review_num%>&comment_num=<%=rCList.get(i).getComment_num() %>';">X</button><% } %>
											<div style="border-bottom:1px solid gray;min-height:50px;"><%=rCList.get(i).getContents() %></div>
										</div>
										<form action="reviewExtraComment.do" method="post" style="display: none;">
											<input type="hidden" name="page" value="<%=nowPage %>" />
											<input type="hidden" name="review_num" value="<%=review_num %>" />
											<input type="hidden" name="parent" value="<%=rCList.get(i).getComment_num() %>" />
											<textarea class="form-control" name="contents" style="resize: none;" required="required"></textarea>
											<input type="submit" class="btn btn-secondary" value="작성하기" />
										</form>
										<div id="comCont<%=rCList.get(i).getComment_num() %>">
											
										</div>
									</div>
									<script>
										comment_step(<%=rCList.get(i).getParent() %>,<%=rCList.get(i).getComment_num() %>,<%=rCList.get(i).getStep() %>);
									</script>
<%
									}
								}
%>
										
								</li>
							</ul>
						</div>
					</div>
						
						
				</div>			        
						<div class="float-right">
							<button class="btn btn-info" onClick="location.href='review.do?page=<%=nowPage %>'">목록</button>
							<button class="btn btn-danger" onClick="confirmDelete()">삭제</button>
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
      function confirmDelete() {
        if (window.confirm("정말 삭제하시겠습니까?")) {
          location.href="reviewDelete.do";
        }
      }
    </script>
	<script>
		$(function(){
			$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			$(window).resize(function(){
				$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			});
		});
	</script>
	<script src="js/sidebar.js"></script>
</body>
</html>