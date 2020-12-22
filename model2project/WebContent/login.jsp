<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/login.css">

<script src="js/jquery-3.5.1.js"></script>

<script src="js/login.js"></script>

</head>
<body>
	<img src="images/logo_black.png" onclick="location.href='index.do'" class="logoToMain" />
	<div class="loginCont">
		<div class="tabs">
			<div class="joinTab">
				Join
			</div>
			<div class="loginTab on">
				Login
			</div>
		</div>
		<div class="loginFormDiv">
			<div class="loginFormDiv2">
				<form action="login.do" method="post">
					<div>
						<input type="text" class="inputSlot" name="id" placeholder="ID" required="required" autocomplete="off" />
					</div>
					<div>
						<input type="password" class="inputSlot" name="pw" placeholder="Password" required="required" autocomplete="off" />
					</div>
					<div class="loginExtra">
						<label><input type="checkbox" name="rememberID" value="remember"/> Remember Me</label>
						<span class="forgotPW"><a href="forgotPw.do"><h6>Forgot Password?</h6></a></span>
					</div>
					<div>
						<input type="submit" value="로그인" />
					</div>
				</form>
			</div>
		</div>
		<div class="joinFormDiv">
			<div class="joinFormDiv2">
					<div class="tabs2">
						<div class="studentTab on">학생회원</div>
						<div class="teacherTab">교사회원</div>
					</div>
				<form action="join.do" method="post" class="studentInfo">
					<input type="hidden" name="classify" value="학생" />
					<div>
						<div>
							<input type="text" class="inputSlot" id="idSlot1" name="id" placeholder="ID" required="required" autocomplete="off" />
							<div id="alertDiv1"></div>
						</div>
						<div>
							<input type="password" class="inputSlot" name="pw" placeholder="Password" required="required" autocomplete="off" />
						</div>
						<hr/>
						<div>
							<input type="text" class="inputSlot" name="name" placeholder="Name" required="required" autocomplete="off" />
						</div>
						<div>
							<input type="text" class="inputSlot" name="email" placeholder="Email" required="required" autocomplete="off" />
						</div>
						<div class="genderDiv">
							Gender :<br />
							<input type="radio" name="gender" value="남" checked /> 남<br />
							<input type="radio" name="gender" value="여" /> 여
						</div>
						<input type="submit" id="joinSubmit1" value="회원가입" />
					</div>
				</form>
				<form action="join.do" method="post" class="teacherInfo">
					<input type="hidden" name="classify" value="교사" />
					<div>
						<div>
							<input type="text" class="inputSlot" id="idSlot2" name="id" placeholder="ID" required="required" autocomplete="off" />
							<div id="alertDiv2"></div>
						</div>
						<div>
							<input type="password" class="inputSlot" name="pw" placeholder="Password" required="required" autocomplete="off" />
						</div>
						<hr/>
						<div>
							<input type="text" class="inputSlot" name="name" placeholder="Name" required="required" autocomplete="off" />
						</div>
						<div>
							<input type="text" class="inputSlot" name="email" placeholder="Email" required="required" autocomplete="off" />
						</div>
						<div class="genderDiv">
							Gender :<br />
							<input type="radio" name="gender" value="남" checked /> 남<br />
							<input type="radio" name="gender" value="여" /> 여
						</div>
						<div>
							<hr />
							<div>
								<input type="text" class="inputSlot" name="major" placeholder="Major" required="required" autocomplete="off" />
							</div>
							<div>
								<input type="text" class="inputSlot" name="education" placeholder="Education" required="required" autocomplete="off" />
							</div>
						</div>
						<input type="submit" id="joinSubmit2" value="회원가입" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>