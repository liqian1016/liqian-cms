<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 响应式布局 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<!--  引入bootstrop样式-->
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="resource/js/jquery-3.2.1.js"></script>

<script type="text/javascript">
	$("#d").load(test.html);
</script>
</head>
<body>
<!-- container:样式   一般加到最大的div上  表示当前页面 -->
<div id="d"></div>
<!-- <div class="container">
	上
	<div class="row" style="background-color: red;height: 200px"></div>
	下 
	<div class="row">
		左
		<div class="col-md-3" style="background-color: pink;height: 400px"></div>
		右
		<div class="col-md-9" style="background-color: blue;height: 400px" id="d"></div>
	</div>


</div> -->

</body>
</html>