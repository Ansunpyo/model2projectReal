<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Intro" %>
<%
	Intro article = (Intro) session.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<script>
	function modifyintro(){
		modifyform.submit();
	}
</script>
<style>
	#registForm {
		width: 500px;
		height: 610px;
		border: 1ox sikud redl
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
	}
	
	.td_left {
		width: 150px;
		background: orange;
	}
	
	.td_right {
		wdth: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h2>게시판글수정</h2>
		<form action="introModifyPro.do" method="post" name="modifyform">
			<input type="hidden" name="intro_num" value="<%=article.getIntro_num() %>"/>
			<input type="hidden" name="page" value="<%=request.getParameter("page") %>"/>
			<table>
				<tr>
					<td class="td_left"><label for="contents">내용</label></td>
					<td class="td_right"><textarea id="contents" name="contents" cols="40" rows="15" required="required"><%=article.getContents() %></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="img1">사진1</label></td>
					<td class="td_right"><input name="img1" type="file" id="img1" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="img2">사진2</label></td>
					<td class="td_right"><input name="img2" type="file" id="img2" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="img3">사진3</label></td>
					<td class="td_right"><input name="img3" type="file" id="img3" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="img4">사진4</label></td>
					<td class="td_right"><input name="img4" type="file" id="img4" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="img5">사진5</label></td>
					<td class="td_right"><input name="img5" type="file" id="img5" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="img6">사진6</label></td>
					<td class="td_right"><input name="img6" type="file" id="img6" required="required" /></td>
				</tr>
			</table>
			<section id="commandCell">
				<a href="javascript:modifyintro()">[수정]</a>&nbsp;&nbsp;
				<a href="javascript:history.go(-1)">[뒤로]</a>
			</section>
		</form>
	</section>
	<!-- 게시판 등록 -->
</body>
</html>