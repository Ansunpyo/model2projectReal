<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int intro_num = (Integer)request.getAttribute("intro_num");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제하기</title>
<style>
	#passForm{
		width: 400px;
		margin: auto;
		border: 1px solid orange;
	}
</style>
</head>
<body>
	<section id="passForm">
		<form name="deleteForm" action="introDeletePro.do?intro_num=<%=intro_num %>" method="post">
			<input type="hidden" name="page" value="<%=nowPage %>"/>
			<table>
				<tr>
					<td>
						<label>글 비밀번호 :</label>
					</td>
					<td>
						<input name="password" type="password" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="삭제" />
						&nbsp;&nbsp;
						<input type="button" value="돌아가기" onClick="javascript:history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>