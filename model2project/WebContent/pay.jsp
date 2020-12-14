<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pay</title>
<style>
body {
	width: 580px;
	height: 625px;
	border: 1px solid black;
	margin: auto;
}
.main {
	width: 550px;
	height: 90px;
	border: 1px solid black;
	margin: auto;
}
button {
    width:100px;
    border-radius:10px;
    border: none;
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
}

.btn-primary{color:#fff;background-color:#007bff;border-color:#007bfff;}.btn-primary:hover{color:#fff;background-color:#0069d9;border-color:#0062cc}.btn-primary.focus,.btn-primary:focus{box-shadow:0 0 0 .2rem rgba(0,123,255,.5)}.btn-primary.disabled,.btn-primary:disabled{background-color:#007bff;border-color:#007bff}.btn-primary:not([disabled]):not(.disabled).active,.btn-primary:not([disabled]):not(.disabled):active,.show>.btn-primary.dropdown-toggle{color:#fff;background-color:#0062cc;border-color:#005cbf;box-shadow:0 0 0 .2rem rgba(0,123,255,.5)}

.btn-success {
	color:#fff;
	background-color:#28a745;
	border-color:#28a745;
}
	
.btn-success:hover {
	color:#fff;
	background-color:#218838;
	border-color:#1e7e34
}

.btn-success.focus,.btn-success:focus {
	box-shadow:0 0 0 .2rem rgba(40,167,69,.5)
}

.btn-success.disabled,.btn-success:disabled {
	background-color:#28a745;border-color:#28a745
}

.btn-success:not([disabled]):not(.disabled).active,.btn-success:not([disabled]):not(.disabled):active,.show>.btn-success.dropdown-toggle{color:#fff;background-color:#1e7e34;border-color:#1c7430;box-shadow:0 0 0 .2rem rgba(40,167,69,.5);}

.btn {
	 float: right;
	margin-right: 188px;
	margin-top: 35px;
}
</style>
</head>
<body>
<h1 style="text-align: center;">결제화면</h1>
<section class="main">
	<a>강의번호 : </a><span>(강의번호)</span><br>
	<a>강의명 : </a><span>(강의명)</span><br>
	<a>교사명 : </a><span>(교사명)</span><br>
	<a>결제가격 : </a><span>(결제가격)</span>
</section>
<label class="reg" for="class">
	<br><select size="1" id="class"  style="height: 30px; margin-left: 15px;">
		<option value="#">카드</option>
		<option value="#">휴대폰</option>
		<option value="3">계좌이체</option>
	</select>
</label>
<fieldset>
	<legend>카드선택</legend>
	 신한<input type="radio" name="card" value="" checked />
	  현대<input type="radio" name="card" value="" />
	  하나<input type="radio" name="card" value="" />
	  우리<input type="radio" name="card" value="" />
	  KB국민<input type="radio" name="card" value="" />
	  IBK기업<input type="radio" name="card" value="" />
</fieldset>
<fieldset>
	<legend>은행선택</legend>
	 신한<input type="radio" name="card" value="" checked />
	  현대<input type="radio" name="card" value="" />
	  하나<input type="radio" name="card" value="" />
	  우리<input type="radio" name="card" value="" />
	  KB국민<input type="radio" name="card" value="" />
	  IBK기업<input type="radio" name="card" value="" />
	  카카오뱅크<input type="radio" name="card" value="" />
</fieldset>
<fieldset>
	<legend>통신사선택</legend>
	 SKT<input type="radio" name="card" value="" checked />
	  KT<input type="radio" name="card" value="" />
	  LG U+<input type="radio" name="card" value="" />
	  알뜰폰<input type="radio" name="card" value="" />
</fieldset>
<fieldset>
	<legend>정보 입력</legend>
	<a>카드번호</a><br><textarea style="height: 25px; width: 400px; resize: none; font-size: 20px;" name="methodc" placeholder="-없이 입력" required="required"></textarea><br>
<!--  	<a>전화번호</a><br><textarea style="height: 15px; width: 200px; resize: none;" name="methodp" placeholder="-없이 입력" required="required"></textarea><br>
	<a>계좌번호</a><br><textarea style="height: 15px; width: 200px; resize: none;" name="methoda" placeholder="-없이 입력" required="required"></textarea>-->
</fieldset>
<div class="btn">
	<button type="button" class="btn btn-primary">결제하기</button>
	<button type="button" class="btn btn-success">목록으로</button>
</div>
</body>
</html>