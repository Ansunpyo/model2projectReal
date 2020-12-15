<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<select name="chg_code" id="chg_code">

<option value="철수">철수</option>

<option value="영희">영희</option>

</select>

<select name="chg_code2" id="chg_code2"></select>


<script>
$("#chg_code").on("change", function() {

        var thisVal = $(this).val(); //선택한 값을 변수에 담아줍니다.

        var thisdep = 2; //depthno 가 2번째 인걸 인식 시켜줍시다.

        var param = "mode=getcatNm"; //보통 한개의 페이지에서 파라메타 값으로 다른 작업을 하기때문에 이렇게 되어있습니다.

        param += "&val=" + encodeURIComponent(thisVal); // 문자타입이 서로 다르면 수신받는 곳에서 잘못 해석될 수도 있으니 공통된 문자인 아스크문자로 인코딩

        param += "&dep=" + encodeURIComponent(thisdep); // 위와 같이 아스크문자로 인코딩 해준다음 depthno가 2라는 값을 param 변수에 추가해줍니다.

        $.ajax({

            async : false, type : "POST", url : "/admin/bbs/dep_chg.php", data : param, dataType : "html",

            error : function(response, status, request) {   // 통신 에러 발생시 처리

                //alert("[알림!] 데이터 통신 도중 오류가 발생하였습니다.");

            },

            success: function (resHtml) {

                $("#chg_code2").empty().html(resHtml); // id="chg_code2"를 먼저 빈값으로(empty()) 바꿔주고 성공적으로 가져온 데이터를 넣어줍니다.

            }

        });

    });
</script>
</body>
</html>