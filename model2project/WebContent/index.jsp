<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
	if(loginMember != null && loginMember.getId().equals("admin")) {
		out.println("<script>location.href='indexad.do'</script>");
	}
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
				<h1 class="logo"><a href="#"><img src="./images/logo.png" alt="2LW"></a></h1>
				<nav class="nav">
					<h2 class="blind">메인메뉴</h2>
					<ul>
						<li>
							<a href="introList.do">강사소개</a>
						</li>
						<li>
							<a href="#">강의목록</a>
						</li>
						<li>
							<a href="#">마이페이지</a>
							<ul>
								<li><a href="editProfilePage.do">개인정보 수정</a></li>
								<li><a href="favorites.do">즐겨찾기 목록</a></li>
								<li><a href="review.do">리뷰남기기</a></li>
								<li><a href="messenger.do">쪽지함</a></li>
								<li><a href="quit.do">회원탈퇴</a></li>
<%
						if (loginMember != null) {
%>
								<li><a href="logout.do">로그아웃</a></li>
<%
						}
%>
							</ul>
						</li>
						<li>
							<a href="#">고객센터</a>
							<ul>
								<li><a href="faq.do">자주묻는 질문</a></li>
								<li><a href="one_on_one.do">1:1문의하기</a></li>
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
				<li><a href="#"><img src="./images/land1.jpg" alt="Landscape Photo 1"></a></li>
				<li><a href="#"><img src="./images/land2.jpg" alt="Landscape Photo 2"></a></li>
				<li><a href="#"><img src="./images/land3.jpg" alt="Landscape Photo 3"></a></li>
			</ul>
		</div>
		<!-- //slider -->
		<!-- container -->
		<section id="container" class="clearfix">
			<h3 class="blind">공지사항, 갤러리, 배너, 링크 영역</h3>
			<section class="notice">
				<div class="news">
					<h4 class="on">Notice</h4>
					<ul>
						<li><a href="#" title="팝업레이어 띄우기" class="popupWindow">Site Usage Rules</a> <span>2020.12.01</span></li>
						<li><a href="#">We're looking for a new teacher with 2LW!</a> <span>2020.10.17</span></li>
						<li><a href="#">Site Changes in September</a> <span>2020.09.21</span></li>
						<li><a href="#">information on enrollment</a> <span>2020.09.12</span></li>
					</ul>				
				</div>
				<div class="gallery">
					<h4>Event</h4>
					<ul>
						<li><a href="#"><img src="./images/thumb1.png" alt="thumbnail photo 1"></a></li>
						<li><a href="#"><img src="./images/thumb2.png" alt="thumbnail photo 2"></a></li>
						<li class="last"><a href="#"><img src="./images/thumb3.png" alt="thumbnail photo 3"></a></li>
					</ul>				
				</div>
			</section>
			<section class="notice">
				<div class="news">
					<h4 class="on"><a href="freeBoard.do">Community</a></h4>
					<ul>
						<li><a href="#" title="팝업레이어 띄우기" class="popupWindow">Professor Peterson's class review</a> <span>2020.12.02</span></li>
						<li><a href="#">Professor Jenson's class review</a> <span>2020.11.27</span></li>
						<li><a href="#">How do I sign up for classes?</a> <span>2020.11.25</span></li>
						<li><a href="#">Can I participate in volunteer work?</a> <span>2020.11.24</span></li>
					</ul>				
				</div>		
			</section>
			<section class="quick">
				<h4>news</h4>
				<p><a href="#"><img src="./images/news.png" alt="악세사리 바로가기"></a></p>
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
				<dd><a href="#"><img src="./images/ico_in.png" alt="인스타그램으로 이동"></a></dd>
				<dd><a href="#"><img src="./images/ico_tw.png" alt="트위터로 이동"></a></dd>
				<dd><a href="#"><img src="./images/ico_fb.png" alt="페이스북으로 이동"></a></dd>
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
</body>
</html>