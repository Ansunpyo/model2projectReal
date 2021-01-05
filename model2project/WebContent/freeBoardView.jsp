<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Free, vo.Free_Comment, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	int nowPage = 1;
	String opt = "";
	int free_num = Integer.parseInt(request.getParameter("free_num"));
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/freeBoard.css">
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
					<li class="nav-item"><a class="nav-link" href="introList.do">강사소개
					</a></li>
					<li class="nav-item"><a class="nav-link" href="lectureList.do">강의목록</a></li>
					<li class="nav-item"><a class="nav-link" href="editProfilePage.do">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="faq.do">고객센터</a></li>
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
				LinkedList<Object> freeViewList = (LinkedList<Object>)session.getAttribute("freeViewList");
				if(request.getParameter("page") != null) nowPage = Integer.parseInt(request.getParameter("page"));
				Free fr = (Free) freeViewList.get(0);
				Member mem = (Member) freeViewList.get(1);
				session.setAttribute("free_num", fr.getFree_num());
				if(!mem.getName().equals(loginMember.getName())) opt = " invisible";
				
				LinkedList[] freeComList = (LinkedList[])session.getAttribute("freeComList");
				LinkedList<Member> cMemList = null;
				LinkedList<Free_Comment> fCList = null;
				if (freeComList != null) {
					cMemList = freeComList[0];
					fCList = freeComList[1];
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
						<li><a href="notice.do">공지사항</a></li>
						<li><a href="freeBoard.do">자유게시판</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">Board</div>
			<div>
				<div class="myPageMenu"><a href="notice.do"><img src="images/user_icon.png">&nbsp;공지사항</a></div>
				<div class="myPageMenu on"><a href="freeBoard.do"><img src="images/heart_icon.png">&nbsp;자유게시판</a></div>
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
										<li class="bbsViewWriterName"><%=mem.getName() %></li>
									</ul>
								</li>
								<li class="bbsViewTitle">
									<ul>
										<li class="bbsViewTitleHeader">TITLE</li>
										<li class="bbsViewTitleText"><%=fr.getTitle() %></li>
									</ul>
								</li>
								<li class="bbsViewBody" style="border-bottom:1px solid gray;">
									<div>
										<%=fr.getContents() %>
									</div>
								</li>
								<li class="comments" style="width:100%;text-align:left;background:white;">
									<form action="freeCommentWrite.do?page=<%=nowPage %>&free_num=<%=free_num %>" method="post">
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
								if(freeComList != null) {
									for(int i=0; i < cMemList.size(); i++){
%>
									<div id="IAMCOMMENT_<%=fCList.get(i).getComment_num() %>">
										<div id="com<%=fCList.get(i).getComment_num() %>">
											<span class="float-right"><%=fCList.get(i).getTime() %></span>
											<h6><b><%=cMemList.get(i).getName() %></b></h6>
											<button id="commentButton<%=fCList.get(i).getComment_num() %>" class="btn btn-primary float-right" onclick="$(this).parent().next('form').toggle()">+</button>
											<%if(loginMember.getId().equals("admin") || loginMember.getNumber() == cMemList.get(i).getNumber()) { %><button class="btn btn-danger float-right" onclick="location.href='deleteFreeComment.do?page=<%=nowPage %>&free_num=<%=free_num%>&comment_num=<%=fCList.get(i).getComment_num() %>';">X</button><% } %>
											<div style="border-bottom:1px solid gray;min-height:50px;"><%=fCList.get(i).getContents() %></div>
										</div>
										<form action="freeExtraComment.do" method="post" style="display: none;">
											<input type="hidden" name="page" value="<%=nowPage %>" />
											<input type="hidden" name="free_num" value="<%=free_num %>" />
											<input type="hidden" name="parent" value="<%=fCList.get(i).getComment_num() %>" />
											<textarea class="form-control" name="contents" style="resize: none;" required="required"></textarea>
											<input type="submit" class="btn btn-secondary" value="작성하기" />
										</form>
										<div id="comCont<%=fCList.get(i).getComment_num() %>">
											
										</div>
									</div>
									<script>
										comment_step(<%=fCList.get(i).getParent() %>,<%=fCList.get(i).getComment_num() %>,<%=fCList.get(i).getStep() %>);
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
							<button class="btn btn-info" onClick="location.href='freeBoard.do?page=<%=nowPage %>'">목록</button>
							<button class="btn btn-primary<%=opt %>" onClick="location.href='freeUpdatePage.do?page=<%=nowPage%>'">수정</button>
							<button class="btn btn-danger<%=opt %>" onClick="confirmDelete()">삭제</button>
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
          location.href="freeDelete.do";
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