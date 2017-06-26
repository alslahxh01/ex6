<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="../resources/js/memo.js"></script>
<script type="text/javascript">
$(function(){
	//js파일을 분리하면 $("#writer").val(); 가 안먹힐 수가 있다. 매개변수로 가져오는것을 추천한다.
	getList(1,'d','d');
	$("#btn").click(function(){
	var writer = $("#writer").val();
	var contents = $("#contents").val();
	memoWrite(writer,contents);
	});
	
	$("#btnView").click(function(){
		memoView(102);
	});
	
	
});
</script>
</head>
<body>
<div>
<form action="">
<p><input type="text" id="writer"></p>
<p><input type="text" id="contents"></p>
<input type="button" id="btn" value="Write">
</form>
</div>
<div id="result"></div>
<button id="btnView">View</button>
</body>
</html>