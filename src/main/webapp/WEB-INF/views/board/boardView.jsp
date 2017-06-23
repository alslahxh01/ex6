<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<h1>${board} VIEW</h1>

제목: ${dto.title }
작성자: ${dto.writer }
내용: ${dto.contents}
히트: ${dto.hit}
날짜: ${dto.reg_date}

<a href="./${board}Update?num=${dto.num}" id="update">UPDATE</a>
<input type="button" id="delete" value="DELETE">
</body>
<script type="text/javascript">

	$('#delete').click(function() {
		
		var result = confirm("삭제하시겠습니까?");
		var board = '${board}';
		var num = '${dto.num}';
		
		if(result){
			location.href="./"+board+"Delete?num="+num;
		}
		
	});

</script>
</html>