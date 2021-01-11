<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Notice, vo.Free, vo.Banner, vo.Lecture_Video, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
	if(loginMember != null && loginMember.getId().equals("admin")) {
		out.println("<script>location.href='indexad.do'</script>");
	}
	LinkedList<Notice> notList = (LinkedList<Notice>) session.getAttribute("notList");
	LinkedList<Free> freeList = (LinkedList<Free>) session.getAttribute("freeList");
	LinkedList<Banner> banList = (LinkedList<Banner>) session.getAttribute("banList");
	LinkedList<Lecture_Video> lecList = (LinkedList<Lecture_Video>) session.getAttribute("lecList");
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
				<h1 class="logo"><a href="index.do"><img src="./images/main.png"  alt="2LW"></a></h1>
				<nav class="nav">
					<h2 class="blind">메인메뉴</h2>
					<ul>
						<li>
							<a href="introList.do">강사소개</a>
						</li>
						<li>
							<a href="lectureList.do">강의목록</a>
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
								<li><a href="purchaseList.do">구매내역</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</header>
		<!-- //header -->
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
							<li><a href="introList.do">강사 소개</a></li>
							<li><a href="lectureList.do">강의 목록</a></li>
							<li><a href="editProfilePage.do">마이페이지</a></li>
							<li><a href="faq.do">고객센터</a></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
		<!-- slider -->
		<div class="slider">
			<ul class="clearfix">
<%
			if(banList != null) {
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
				<h4>news</h4>
				<p><a href="#"><img src="./images/news.png" alt="악세사리 바로가기"></a></p>
			</section>
		</section>
<%
			if(lecList != null) {
%>
		<section>
			<section id="container" class="clearfix">
				<ul>
					<li>
<%
					for(int i = 0; i < Math.min(4, lecList.size()); i++) {
						String video = "";
						if(lecList.get(i).getVideo().indexOf("&") > 0) {
							video = lecList.get(i).getVideo().substring(lecList.get(i).getVideo().indexOf("v=") + 2, lecList.get(i).getVideo().indexOf("&"));
						} else {
							video = lecList.get(i).getVideo().substring(lecList.get(i).getVideo().indexOf("v=")+2);
						}
						String lecUrl = "lectureDetail.do?lecture_num=" + lecList.get(i).getLecture_num();
%>
						<%if(loginMember != null) { %><a href="<%=lecUrl %>" onClick="window.open(this.href, '', 'resizable=no width=1340, height=730'); return false;"><% } %><img src="https://img.youtube.com/vi/<%=video %>/0.jpg" alt="thumb<%=i%>" width="296" height="200"><%if(loginMember != null) { %></a><% } %>
<%
					}
%>
					</li>
				</ul>
			</section>
		</section>
		<section>
			<section id="container" class="clearfix">
				<ul>
					<li>
<%
					for(int i = 4; i < Math.min(8, lecList.size()); i++) {
						String video = "";
						if(lecList.get(i).getVideo().indexOf("&") > 0) {
							video = lecList.get(i).getVideo().substring(lecList.get(i).getVideo().indexOf("v=") + 2, lecList.get(i).getVideo().indexOf("&"));
						} else {
							video = lecList.get(i).getVideo().substring(lecList.get(i).getVideo().indexOf("v=")+2);
						}
						String lecUrl = "";
						lecUrl = "lectureDetail.do?lecture_num=" + lecList.get(i).getLecture_num();
%>
						<%if(loginMember != null) { %><a href="<%=lecUrl %>" onClick="window.open(this.href, '', 'resizable=no width=1340, height=730'); return false;"><% } %><img src="https://img.youtube.com/vi/<%=video %>/0.jpg" alt="thumb<%=i%>" width="296" height="200"><%if(loginMember != null) { %></a><% } %>
<%
					}
%>
					</li>
				</ul>
			</section>
		</section>		
<%
			}
%>						
		<!-- //container -->
		<!-- footer -->
		<footer id="footer" class="clearfix">
			<p class="btm_logo"><img src="./images/btm.png" alt="2LW"></p>
			<dl class="btm_menu">
				<dt class="blind"><strong>바닥메뉴</strong></dt>
				<dd><a href="https://www.instagram.com/" target="_blank"><img src="./images/ico_in.png" alt="인스타그램으로 이동"></a></dd>
				<dd><a href="https://twitter.com/" target="_blank"><img src="./images/ico_tw.png" alt="트위터로 이동"></a></dd>
				<dd><a href="https://www.facebook.com/" target="_blank"><img src="./images/ico_fb.png" alt="페이스북으로 이동"></a></dd>
			</dl>
			<p class="copy">copyright &copy; 2LW, All rights reserved.</p>
		</footer>
		<!-- //footer -->
	</div>
	
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
			$(".hamburger").click(function(){
				$(".topbarMenu").stop().slideToggle();
			});
		});
	</script>
</body>
</html>