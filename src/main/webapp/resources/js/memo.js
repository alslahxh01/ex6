/**
 * 
 * 		
//$.get("URL?name=?",function(data){});
//$.psot("url",{name:value} ,function(data){});
//$("#result").load()
// $.ajax({
//	url:,
//	datatype:, //get,Post
// 	data:{},
// 	success:function(data){		
// 		}
// });
 * 
 */
function memoView(num){
	
	$.get("memoView/"+num,function(data){
		alert(data.writer);
	});
	
}




function memoWrite(writer,contents){
	$.ajax({
		url:"memoWrite",
		type:"POST",
		data:{
			writer:writer,
			contents:contents
		},
		success:function(data){
			
			var result = "<table>";
			$(data).each(function(){ //data 포문 돌리기 코드 하나꺼내서 펑션에 넣자
				result = result+"<tr>";
				result  = result+"<td>"+this.num+"</td>"; //꺼내서 넣은 자기자신.
				result  = result+"<td>"+this.writer+"</td>";
				result  = result+"<td>"+this.contents+"</td>";
				result  = result+"<td>"+this.reg_date+"</td>";
				result = result+"</tr>";
			});
			result =result+"</table>";
			$("#result").html(result);
		}
	});
	
}



function getList(curPage,find,search){

	$.ajax({
		url:"getMemoList/"+curPage+"/"+find+"/"+search, //주소만으로 데이터값 보내기
		type:"GET",
		success:function(data){
//			alert(data);
//			데이터를 JSON 형태로 바꾸자
			var result = "<table>";
			$(data).each(function(){ //data 포문 돌리기 코드 하나꺼내서 펑션에 넣자
				result = result+"<tr>";
				result  = result+"<td>"+this.num+"</td>"; //꺼내서 넣은 자기자신.
				result  = result+"<td>"+this.writer+"</td>";
				result  = result+"<td>"+this.contents+"</td>";
				result  = result+"<td>"+this.reg_date+"</td>";
				result = result+"</tr>";
			});
			result =result+"</table>";
			$("#result").html(result);
		}
	});
	
}


	