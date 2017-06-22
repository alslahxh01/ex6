<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" >
</script>
<script type="text/javascript">
$(function(){
		var count= 0;		
	$("#add").click(function(){
		count ++;
		if(count <6){
		var dd = "<p><input type=file name='f1'><span id='x"+count+"'>X</span>  </p>";	
		$("#file").append(dd);
}else{
	alert("최대 5개 가능");
}
	});
	
});
</script>


</head>
<body>
<form action="fileUpload" enctype="multipart/form-data" method="post">

<input type="text" name="name">
<input type="file" name="f1">
<button>UPLOAD</button>
</form>
<h2>이름이 다른데 여러개 넘어가는 폼</h2>
<hr>
<!-- 이름이 다른데 여러개 넘어가는 폼 -->
<form action="multiFileUpload" enctype="multipart/form-data" method="post">

<input type="text" name="name">
<input type="file" name="f1">
<input type="file" name="f2">
<button>UPLOAD</button>
</form>
<h2>파라미터 값 똑같은데 여러개 넘어가는 폼</h2>
<hr>
<!-- 파라미터 이름 똑같은데 여러개 넘어간다 -->
<form action="sameMultiFileUpload" enctype="multipart/form-data" method="post">

<input type="text" name="name">
<input type="file" name="f1">
<input type="file" name="f1">
<button>UPLOAD</button>
</form>
<h2>이름같고 갯수 유동적인 폼</h2>
<hr>
<!--파라미터 이름 없고 갯수 유동적이게 넘어가는 폼  -->
<form action="Upload" enctype="multipart/form-data" method="post">

<input type="text" name="name">
<div id="file">

</div>
<input type="button" id="add" value="파일추가">
</form>


</body>
<script type="text/javascript">
<script type="text/javascript">
$(function(){
$("#x1").click(function(){
	alert("Dd");
});
});
</script>

</script>

</html>