<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Notice, vo.Free, vo.Banner, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
	if(loginMember == null || !loginMember.getId().equals("admin")) {
		out.println("<script>location.href='index.do'</script>");
	}
	LinkedList<Notice> notList = (LinkedList<Notice>) session.getAttribute("notList");
	LinkedList<Free> freeList = (LinkedList<Free>) session.getAttribute("freeList");
	LinkedList<Banner> banList = (LinkedList<Banner>) session.getAttribute("banList");
%>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>2LW</title>
	<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div class="wrap">
		<!-- header -->
		<header id="header">
			<div class="inner clearfix">
				<h1 class="logo"><a href="index.do"><img src="./images/logo.png" alt="2LW"></a></h1>
				<nav class="nav">
					<h2 class="blind">메인메뉴</h2>
					<ul>
						<li>
							<a href="#">주문관리</a>
							<ul>
								<li><a href="#">결제목록</a></li>
								<li><a href="#">결제 취소</a></li>
							</ul>	
						</li>
						<li>
							<a href="#">회원관리</a>
							<ul>
								<li><a href="members.do">회원 리스트</a></li>
								<li><a href="quitters.do">탈퇴 회원 리스트</a></li>
								<li><a href="statistics.do">방문자/가입자/뷰 통계</a></li>
								<li><a href="one_on_onead.do">1:1문의함</a></li>
							</ul>
						</li>
						<li>
							<a href="logout.do">로그아웃</a>
						</li>
						<li>
							<a href="#">마이페이지</a>
							<ul>
								<li><a href="review.do">리뷰 관리</a></li>
								<li><a href="faq.do">자주하는질문 관리</a></li>
								<li><a href="category.do">강의 카테고리 관리</a></li>
								<li><a href="banner.do">메인배너 관리</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</header>
		<!-- //header -->
		<!-- slider -->
		<div class="slider">
			<ul class="clearfix">
<%
			if(banList != null) {
				int bannerNum = banList.size();
				for (int i = 0; i < banList.size(); i++) {
%>
				<li><a href="#"><img src="./banner/<%=banList.get(i).getImg() %>" width="1200px" height="300px" alt="<%=banList.get(i).getImg() %>"></a></li>
<%
				}
			}				
%>
			</ul>
		</div>
		<!-- //slider -->
		<!-- container -->
		<section id="container" class="clearfix">
			<h3 class="blind">공지사항, 갤러리, 배너, 링크 영역</h3>
			<section class="notice">
				<div class="news">
					<h4 class="on"><a href="notice.do">Notice</a></h4>
										<ul>
<%
				if(notList != null) {
					for (int i = 0;i<notList.size();i++) {
%>
						<li class="<%=notList.get(i).getNotice_num() %>"><a style="cursor:pointer;" class="noticeView"><%=notList.get(i).getTitle() %> </a></li>
<%
					}
				}
%>
					</ul>				
				</div>
			</section>
			<section class="notice">
				<div class="news">
					<h4 class="on"><a href="freeBoard.do">Community</a></h4>
					<ul>
<%
				if(freeList != null) {
					for (int i = 0;i<freeList.size();i++) {
%>
						<li class="<%=freeList.get(i).getFree_num() %>"><a style="cursor:pointer;" class="freeView"><%=freeList.get(i).getTitle() %> </a></li>
<%
					}
				}
%>						
					</ul>			
				</div>		
			</section>
			<section class="quick">
				<h4>News</h4>
				<p><a href="#"><img src="./images/news.png" alt="뉴스 바로가기"></a></p>
			</section>
		</section>
		<section>
			<section id="container" class="clearfix">
				<ul>
					<li>
						<iframe width="296" height="200" src="https://www.youtube.com/embed/kBdfcR-8hEY" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						<iframe width="296" height="200" src="https://www.youtube.com/embed/6Ql5mQdxeWk" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						<iframe width="296" height="200" src="https://www.youtube.com/embed/BDqvzFY72mg" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						<iframe width="296" height="200" src="https://www.youtube.com/embed/wLn28DrSF68" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
					</li>
				</ul>
			</section>
		</section>
		<section>
			<section id="container" class="clearfix">
				<ul>
					<li>
						<iframe width="296" height="200" src="https://www.youtube.com/embed/0JUN9aDxVmI" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						<iframe width="296" height="200" src="https://www.youtube.com/embed/p2J7wSuFRl8" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						<iframe width="296" height="200" src="https://www.youtube.com/embed/3QsjdLlWHxU" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						<iframe width="296" height="200" src="https://www.youtube.com/embed/r_w7pfulsn8" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
					</li>
				</ul>
			</section>
		</section>		
						
		<!-- //container -->
		<!-- footer -->
		<footer id="footer" class="clearfix">
			<p class="btm_logo"><img src="./images/logo_btm.png" alt="JUST쇼핑몰"></p>
			<dl class="btm_menu">
				<dt class="blind"><strong>바닥메뉴</strong></dt>
				<dd><a href="https://www.instagram.com/"><img src="./images/ico_in.png" alt="인스타그램으로 이동"></a></dd>
				<dd><a href="https://twitter.com/"><img src="./images/ico_tw.png" alt="트위터로 이동"></a></dd>
				<dd><a href="https://www.facebook.com/"><img src="./images/ico_fb.png" alt="페이스북으로 이동"></a></dd>
			</dl>
			<p class="copy">copyright &copy; 2LW, All rights reserved.</p>
		</footer>
		<!-- //footer -->
	</div>
	<!-- Popup -->
	<div class="popup">
		<h3>POPUP Title</h3>
		<div class="content">
			<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt iste dolore dicta placeat ipsam beatae, esse dignissimos eum ea, aperiam odio, perspiciatis praesentium ullam. Laudantium vitae quas minus nihil repellendus.</p>			
		</div>
		<p class="close"><button type="button" title="팝업레이어 닫기">X</button></p>
	</div>
	
	<!-- //Popup -->
	
	<script src="./js/jquery-1.12.4.min.js"></script>
	<script src="./js/common.js"></script>
	<script>
		$(function(){
			$(".noticeView").click(function(e){
				e.preventDefault();
				var notice_num = $(this).parent().attr("class");
				location.href="noticeView.do?page=1&notice_num=" + notice_num;
			});
			$(".freeView").click(function(e){
				e.preventDefault();
				var notice_num = $(this).parent().attr("class");
				location.href="freeView.do?page=1&free_num=" + notice_num;
			});
			// slider
			var $images = <%=banList.size() %> - 1;
			var $now = 0;
			var $height = $('.slider ul li').height();
			setInterval(function(){
				if($now == $images) {
					$now = 0;
					$('.slider ul').animate({
						top: $now * -$height
					});
				}
				else {
					$now += 1; 
					$('.slider ul').animate({
						top: $now * -$height
					});
				}
			}, 2500);
		});
	</script>
</body>
</html>