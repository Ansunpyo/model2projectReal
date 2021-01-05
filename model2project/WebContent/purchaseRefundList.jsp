<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Pay, vo.Lecture, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	if (loginMember == null || !loginMember.getId().equals("admin")) {
		out.println("<script>location.href='index.do'</script>");
	}
	int nowPageNumber = 1;
	int pageCount = 5;
	int range = 5;
	String prevDisabled = "";
	String nextDisabled = "";
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/sidebar.css">
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
					<li class="nav-item active"><a class="nav-link" href="purchaseAllList.do">주문관리
					</a></li>
					<li class="nav-item"><a class="nav-link" href="members.do">회원관리</a></li>
					<li class="nav-item"><a class="nav-link" href="logout.do">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link" href="review.do">마이페이지</a></li>
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
		int lastPage = 1;
		if (session.getAttribute("lastPage") != null) lastPage = (int) session.getAttribute("lastPage");
		if (request.getParameter("page") != null) nowPageNumber = Integer.parseInt(request.getParameter("page"));
		if (nowPageNumber < 1) nowPageNumber = 1;
		if (nowPageNumber > lastPage) nowPageNumber = lastPage;
		LinkedList[] payList = (LinkedList[])session.getAttribute("payList");
		LinkedList<Pay> pList = payList[0];
		LinkedList<Lecture> lecList = payList[1];
		LinkedList<Member> memList = payList[2];
		int startNumber = (nowPageNumber - 1) / pageCount * range + 1;
		int endNumber = startNumber + range - 1;
		if (nowPageNumber <= 1) {
			prevDisabled = " disabled";
		}
		if (nowPageNumber >= lastPage) {
			nextDisabled = " disabled";
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
						<li><a href="purchaseAllList.do">결제목록</a></li>
						<li><a href="purchaseRefundList.do">결제 취소</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">주문관리</div>
			<div>
				<div class="myPageMenu"><a href="purchaseAllList.do"><img src="images/heart_icon.png">&nbsp;결제목록</a></div>
				<div class="myPageMenu on"><a href="purchaseRefundList.do"><img src="images/x_mark_icon.png">&nbsp;결제 취소</a></div>
			</div>
		</div>
			<div class="contents">
			
				<table class="table table-sm bg-white">
				  <thead>
				    <tr>
				      <th scope="col">BUYER NUM</th>
				      <th scope="col">BUYER ID</th>
				      <th scope="col">LECTURE NUM</th>
				      <th scope="col">LECTURE TITLE</th>
				      <th scope="col">TYPE</th>
				      <th scope="col">PAYCODE</th>
				      <th scope="col">DATE</th>
				      <th scope="col">REFUND</th>
				    </tr>
				  </thead>
				  <tbody>
<%
	if(pList != null) {
	
		for(int i = 0; i < pList.size();i++) {
%>
				    <tr>
				      <td><%=pList.get(i).getNumber() %></td>
				      <td><%=memList.get(i).getId() %></td>
				      <td><%=pList.get(i).getLecture_num() %></td>
				      <td><%=lecList.get(i).getLecture_title() %></td>
				      <td><%=pList.get(i).getType() %></td>
				      <td><%=pList.get(i).getPay_code() %></td>
				      <td><%=pList.get(i).getDate() %></td>
				      <td><input type="button" class="btn btn-danger refundButton" id="<%=pList.get(i).getPay_number() %>" value="환불처리" /></td>
				    </tr>
<%
		}
	}
%>				    
				  </tbody>
				</table>
				
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <li class="page-item<%=prevDisabled %>">
						      <a class="page-link" href="purchase.do?page=<%=nowPageNumber - 1 %>" tabindex="-1">Previous</a>
						    </li>
<%
				for (int i = startNumber; i <= Math.min(endNumber, lastPage); i++) {					    
%>
						    <li class="page-item<% if(nowPageNumber==i){%> active<%} %>"><a class="page-link" href="purchase.do?page=<%=i%>"><%=i %></a></li>
<%
				}
%>
						    <li class="page-item<%=nextDisabled%>">
						      <a class="page-link" href="purchase.do?page=<%=nowPageNumber + 1 %>">Next</a>
						    </li>
						  </ul>
						</nav>
<%
	}
%>
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
		});
	</script>
	<script>
		$(function(){
			$(".refundButton").on("click", function(){
				if(confirm("정말 환불 처리하시겠습니까?")) {
					var pay_number = $(this).attr("id");
					location.href="purchaseDoRefund.do?pay_number=" + pay_number;
				}
			});
		});
	</script>
	<script src="js/sidebar.js"></script>
</body>
</html>