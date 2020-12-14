<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Lecture, vo.Subject, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	int nowPageNumber = 1;
	int pageCount = 8;
	int range = 5;
	String prevDisabled = "";
	String nextDisabled = "";
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
</head>
<style>
a {
	text-decoration: none;
	color: black;
}
a:hover {
	text-decoration: none;
	color: #d16328;
}
</style>
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
					<li class="nav-item active"><a class="nav-link" href="lectureList.do">강의목록</a></li>
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
				int lastPage = 1;
				if (session.getAttribute("lastPage") != null) lastPage = (int) session.getAttribute("lastPage");
				if (request.getParameter("page") != null) nowPageNumber = Integer.parseInt(request.getParameter("page"));
				if (nowPageNumber < 1) nowPageNumber = 1;
				if (nowPageNumber > lastPage) nowPageNumber = lastPage;
				LinkedList<Subject> subjectList = (LinkedList<Subject>)session.getAttribute("subjectList");
				LinkedList[] lectureList = (LinkedList[])session.getAttribute("lectureList");
				LinkedList<Lecture> lecList = lectureList[0];
				LinkedList<Member> memList = lectureList[1];
				LinkedList<Subject> subList = lectureList[2];
				int startNumber = (nowPageNumber - 1) / pageCount * range + 1;
				int endNumber = startNumber + range - 1;
				if (nowPageNumber <= 1) {
					prevDisabled = " disabled";
				}
				if (nowPageNumber >= lastPage) {
					nextDisabled = " disabled";
				}
%>
	<div class="container mb-5" style="width:1200px;">
		<div class="p-3 mb-2 d-flex flex-wrap bg-secondary text-light">
<%
		for(int i = 0; i < subjectList.size(); i++){
%>
			<label><input type="checkbox" name="subject" class="subject" value="<%=subjectList.get(i).getCode() %>" /> <%=subjectList.get(i).getSubject_name() %></label>&nbsp;
<%
		}
%>
		</div>
		
		<div class="row">
			<div class="col-12 d-flex flex-wrap  justify-content-between">
<%
			if(lecList != null) {
				for(int i = 0; i < lecList.size(); i++) {
					String video = lecList.get(i).getVideo().substring(lecList.get(i).getVideo().indexOf("v=") + 2, lecList.get(i).getVideo().indexOf("&"));
%>
				<div class="card my-2" style="width: 15rem;">
				  <a href="#"><img src="https://img.youtube.com/vi/<%=video %>/0.jpg" class="card-img-top" alt="..."></a>
				  <div class="card-body">
				    <h5 class="card-title"><a href="#"><%=lecList.get(i).getLecture_title() %></a></h5>
				    <h6 class="card-text"><%if(lecList.get(i).getPrice() == 0) { %><span class="badge badge-pill badge-primary">FREE</span><%} else { %><span class="badge badge-pill badge-danger"><%=lecList.get(i).getPrice() %>원</span><%} %></h6>
				    <p class="card-text"><span class="badge badge-pill badge-info"><%=subList.get(i).getSubject_name() %></span></p>
				    <h5 class="card-text float-right"><%=memList.get(i).getName() %> 선생님</h5>
				    <input type="hidden" name="subject"/>
				  </div>
				</div>
<%
				}
			}
%>				
			</div>
		</div>
	</div>
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <li class="page-item<%=prevDisabled %>">
						      <a class="page-link" href="freeBoard.do?page=<%=nowPageNumber - 1 %>" tabindex="-1">Previous</a>
						    </li>
<%
				for (int i = startNumber; i <= Math.min(endNumber, lastPage); i++) {					    
%>
						    <li class="page-item<% if(nowPageNumber==i){%> active<%} %>"><a class="page-link" href="freeBoard.do?page=<%=i%>"><%=i %></a></li>
<%
				}
%>
						    <li class="page-item<%=nextDisabled%>">
						      <a class="page-link" href="freeBoard.do?page=<%=nowPageNumber + 1 %>">Next</a>
						    </li>
						  </ul>
						</nav>
	
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
			$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			$(window).resize(function(){
				$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			});
			/////////////////////////////////////////////////////// ajax 해보는중
			$(document).on("click", ".subject", function(){
				var checkedSub = [];
				$(".subject[name='subject']:checked").each(function(){
					checkedSub.push($(this).val());
				});
				var param = "subject=";
				for(int i = 0; i < checkedSub.length;i++) {
					param += checkedSub(i) + ",";
				}
				param = param.substring(0,-1);
				
				$.ajax({
	                type:"POST",                //전송방식
	                url:"lectureListSubChecked.do",    //주소
	                data:param,            //전송데이터
	                dataType:"xml", // 받을 때 데이터 타입
	                success:function(args){ // 이 xml 형태의 데이터를 args로 받음 (바깥으로부터 들어옴)
	                                                            // xml 형태니깐 parsing 작업을 해서 받아야함
	                    $(args).find("status").each(function(){    //status 해당 태그 검색. eaxh는 반복문
	 
	                        alert($(this).text()); // this의 text 형태로 출력해라 
	 
	                    });
	 
	                    var str = "";
	                    $(args).find("record").each(function(){    // each : 반복문 => record를 다 찾아내라
	 
	                        var id = $(this).attr("id"); // attribute 넣어라
	                        var subject = $(this).find("subject").text();
	                        var content = $(this).find("content").text();
	 
	                        str += "id=" + id +
	                            ", subject=" + subject +
	                            ", content=" + content + "<br/>";
	                    });
	 
	                    // 반복문으로 만들어낸 데이터를 html로 바꿔서 str을 출력해라
	                    $("#resultDiv").html(str); 
	                    // javascript 방식에서 out.innerHTML = data; 이거랑 같은 코딩
	 
	 
	                },
	 
	                beforeSend:showRequest,
	                // 보내기 전에!  showRequest가서 검사 (showRequest는 사용자정의)
	                //beforeSend는 true값을 받아야만, 위 ajax부분을 통해 서버로 데이터를 보냄
	                error:function(e){
	                    // 에러가 나면 e: 에러메서지가 여기 들어와 있을것임    
	                    alert(e.responseText); // error msg는 String이기 때문에 responseText
	                    //xml을 받을때는 e.responseXml
	                }
	            });
				
			});
			////////////////////////////////////////////////////////
		});
	</script>
</body>
</html>