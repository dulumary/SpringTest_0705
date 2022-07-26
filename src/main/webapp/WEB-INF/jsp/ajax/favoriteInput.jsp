<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
</head>
<body>

	<div class="container">
		<h1>즐겨 찾기 추가하기</h1>
		<label>제목</label>
		<input type="text" class="form-control" id="titleInput">
		
		<label class="mt-3">주소</label>
		<div class="d-flex">
			<input type="text" class="form-control" id="urlInput">
			<button type="button" id="duplicateBtn" class="btn btn-success">중복확인</button>
		</div>
		<div id="duplicateDiv" class="small text-danger d-none">중복된 url 입니다</div>
		<div id="availableDiv" class="small text-success d-none">사용가능 url 입니다</div>
		<button type="button" id="addBtn" class="btn btn-success btn-block mt-3">추가</button>
	</div>
	
	<script>
		$(document).ready(function() {
			
			var isCheckDuplicate = false;
			var isDuplicate = false;
			
			$("#urlInput").on("input", function() {
				isCheckDuplicate = false;
				isDuplicate = false;
				$("#availableDiv").addClass("d-none");
				$("#duplicateDiv").addClass("d-none");
			});
			
			
			
			$("#duplicateBtn").on("click", function() {
				let url = $("#urlInput").val();
				
				if(url == "") {
					alert("주소를 입력하세요");
					return ;
				}
				
				// http:// , https:// 로 시작하지 않으면 주소형식이 잘못되었습니다. 경고
				if(!url.startsWith("http://") && !url.startsWith("https://")) {
				//if(!(url.startsWith("http://") || url.startsWith("https://"))) {
					
					alert("주소형식이 잘못 되었습니다!");
					return ;
				}
				
				// url 중복여부 확인
				// api 호출 -> ajax
				$.ajax({
					type:"post",
					url:"/ajax/favorite/is_duplicate",
					data:{"url":url},
					success:function(data) {
						// {"is_duplicate":true}
						// {"is_duplicate":false}
						// 중복된 경우 
						
						isCheckDuplicate = true;
						if(data.is_duplicate) {
							isDuplicate = true;
							
							$("#duplicateDiv").removeClass("d-none");
							$("#availableDiv").addClass("d-none");
						} else {
							
							isDuplicate = false;
							$("#availableDiv").removeClass("d-none");
							$("#duplicateDiv").addClass("d-none");
						}
					
					},
					error:function() {
						alert("에러 발생");
					}	
				
				});
				
				
				
			});
			
			
			$("#addBtn").on("click", function() {
				
				
				let title = $("#titleInput").val();
				let url = $("#urlInput").val();
				
				if(title == "") {
					alert("이름을 입력하세요");
					return ;
				}
				
				if(url == "") {
					alert("주소를 입력하세요");
					return ;
				}
				
				// http:// , https:// 로 시작하지 않으면 주소형식이 잘못되었습니다. 경고
				if(!url.startsWith("http://") && !url.startsWith("https://")) {
				//if(!(url.startsWith("http://") || url.startsWith("https://"))) {
					
					alert("주소형식이 잘못 되었습니다!");
					return ;
				}
				
				// 중복 확인 진행 했는지 
				if(isCheckDuplicate == false) {
					alert("중복 체크 하세요!!");
					return ;
				}
				// 중복된 상태인지 
				if(isDuplicate) {
					alert("url이 중복되었습니다");
					return ;
				}
				
				$.ajax({ 
					type:"post",
					url:"/ajax/favorite/insert",
					data:{"name":title, "url":url},
					
					success:function(data) {
						// {"result":"success"}
						// {"result":"fail"}
						
						if(data.result == "success") {
							// 성공	
							location.href="/ajax/favorite/list";
						} else {
							// 실패
							alert("추가실패");
						}
					},
					error:function() {
						alert("에러발생");	
					}
				});
			});
			
		});
	
	
	</script>

</body>
</html>