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

.btn-primary {
	color:#fff;
	background-color:#007bff;
	border-color:#007bfff;
}

.btn-primary:hover {
	color:#fff;
	background-color:#0069d9;
	border-color:#0062cc
}

.btn-primary.focus,.btn-primary:focus {
	box-shadow:0 0 0 .2rem rgba(0,123,255,.5)
}

.btn-primary.disabled,.btn-primary:disabled {
	background-color:#007bff;
	border-color:#007bff
}

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

.btn {
	float: right;
	margin-top: 75px;
}
</style>
</head>
<body>
<h1 style="text-align: center;">결제화면</h1>
<form action="introWritePro.do" method="post" enctype="multipart/form-data" name="introform">
<section class="main">
	<a>강의번호 : </a><span>(강의번호)</span><br>
	<a>강의명 : </a><span>(강의명)</span><br>
	<a>교사명 : </a><span>(교사명)</span><br>
	<a>결제가격 : </a><span>(결제가격)</span>
</section>
<label class="reg" for="class">
	<br><select size="1" id="class"  style="height: 30px; margin-left: 15px;">
		<option class="cardTab" value="card">카드</option>
		<option class="bankingTab" value="banking">계좌이체</option>
		<option class="phoneTab" value="phone">휴대폰</option>
	</select>
</label>
<fieldset class="cardForm">
	<legend>카드선택</legend>
	 신한<input type="radio" name="type" value="신한카드" checked />
	  현대<input type="radio" name="type" value="현대카드" />
	  하나<input type="radio" name="type" value="하나카드" />
	  우리<input type="radio" name="type" value="우리카드" />
	  KB국민<input type="radio" name="type" value="KB국민카드" />
	  IBK기업<input type="radio" name="type" value="IBK기업카드" />
</fieldset>
<fieldset class="bankingForm">
	<legend>은행선택</legend>
	 신한<input type="radio" name="type" value="신한은행" checked />
	  하나<input type="radio" name="type" value="하나은행" />
	  우리<input type="radio" name="type" value="우리은행" />
	  KB국민<input type="radio" name="type" value="KB국민" />
	  IBK기업<input type="radio" name="type" value="IBK기업은행" />
	  카카오뱅크<input type="radio" name="type" value="카카오뱅크" />
</fieldset>
<fieldset class="phoneForm">
	<legend>통신사선택</legend>
	 SKT<input type="radio" name="type" value="SKT" checked />
	  KT<input type="radio" name="type" value="KT" />
	  LG U+<input type="radio" name="type" value="LG U+" />
	  알뜰폰<input type="radio" name="type" value="알뜰폰" />
</fieldset>
<fieldset>
	<legend>정보 입력</legend>
<div class="cardCode">
	<a>카드번호</a><br><textarea style="height: 25px; width: 400px; resize: none; font-size: 20px;" name="pay_code" placeholder="-없이 입력" required="required"></textarea><br>
</div>
<div class="bankingCode">
	<a>계좌번호</a><br><textarea style="height: 25px; width: 400px; resize: none; font-size: 20px;" name="pay_code" placeholder="-없이 입력" required="required"></textarea><br>
</div>	
<div class="phoneCode">
	<a>전화번호</a><br><textarea style="height: 25px; width: 400px; resize: none; font-size: 20px;" name="pay_code" placeholder="-없이 입력" required="required"></textarea><br>
</div>		
</fieldset>
<div class="btn">
	<button type="button" class="btn btn-success" style="margin-right: 185px;">목록으로</button>
	<button type="button" class="btn btn-primary" style="margin-right: 10px;">결제하기</button>
</div>
</form>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(function(){
	$(".bankingForm").hide();
	$(".phoneForm").hide();
	$(".bankingCode").hide();
	$(".phoneCode").hide();
	$("#class").change(function(){
		let select = $(this).val();
		if(select == "card") {
			$(".cardForm").show();
			$(".bankingForm").hide();
			$(".phoneForm").hide();
			$(".cardCode").show();
			$(".bankingCode").hide();
			$(".phoneCode").hide();
		} else if(select == "banking") {
			$(".cardForm").hide();
			$(".bankingForm").show();
			$(".phoneForm").hide();
			$(".cardCode").hide();
			$(".bankingCode").show();
			$(".phoneCode").hide();
		} else {
			$(".cardForm").hide();
			$(".bankingForm").hide();
			$(".phoneForm").show();
			$(".cardCode").hide();
			$(".bankingCode").hide();
			$(".phoneCode").show();
		}
	});
});
</script>
</body>
</html>