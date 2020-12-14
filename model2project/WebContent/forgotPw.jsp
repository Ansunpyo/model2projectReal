<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">

	<script src="js/jquery-3.5.1.js"></script>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<style>
		body {
			background: black;
		}
		.logoToMain {
			cursor: pointer;
		}
		.forgotCont {
			background: white;
			width: 300px;
			height: 200px;
			margin: auto;
			opacity: 0.9;
			padding: 20px;
			text-align: center;
		}
		input {
			margin-top: 20px;
		}
	</style>
</head>
<body>
	<img src="images/logo_black.png" onclick="location.href='index.do'" class="logoToMain" />
	<div class="forgotCont">
		<form action="pwCheck.do">
			<input type="text" class="form-control" name="id" placeholder="ID" autocomplete="off" required="required" />
			<input type="text" class="form-control" name="email" placeholder="EMAIL" autocomplete="off" required="required" />
			<input type="submit" class="btn btn-primary" value="비밀번호 찾기" />
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>

</html>