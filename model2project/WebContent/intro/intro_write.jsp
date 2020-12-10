<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
<title>강사등록</title>
<style>
	body {
		background-color: black;
	}
	
	body * {
		margin: 0px;
		padding: 0px;
		color: #222328;
		text-decoration: none;
		background-color: #ffffff;
		box-sizing: border-box;
	}
	
	.introCont {
		width: 480px;
		margin: auto;
		opacity: 0.9;
	}
	
	#commandCell {
		text-align: center;
	}
	
	.loginFormDiv {
		border: 1px solid #bcbcbc;
		border-top: 0px;
		text-align: center;
	}

	.loginFormDiv2 {
		width: 478px;
		height: 400px;
		padding: 30px;
		padding-top: 100px;
	}
	
	.inputSlot {
	width: 370px;
	height: 40px;
	margin: 5px auto;
	padding: 0 10px;
	border: 1px solid #6f6f6f;
	border-radius: 4px;
	color: #6d6d6d;
	font-size: 1.2rem;
	font-weight: 600;
	}
	
	.forgotPW {
	position: absolute;
	top: 0;
	right: 0;
}

.loginExtra {
	margin: 10px auto;
	position: relative;
	width: 370px;
	text-align: left;
}
</style>
</head>
<body>
<div class="introCont">
	<div class="loginFormDiv">
		<div class="loginFormDiv2">		
			<form action="introWritePro.do" method="post" enctype="multipart/form-data" name="boardform">
			<div>
				<input type="text" class="inputSlot" name="contents" placeholder="소개" required="required"/> 		
			</div>
			<div>
				<input name="img1" type="file" id="img1" required="required" />
			</div>
			<section id="commandCell" style="margin-top: 120px;">
				<button type="submit" class="btn btn-primary" onclick="submit">등록하기</button>
				<button type="button" class="btn btn-success" onclick="history.back(-1);">목록으로</button>
			</section>
		</form>
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>