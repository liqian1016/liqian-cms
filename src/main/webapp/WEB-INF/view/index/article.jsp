<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
<center>
<div class="container">

	<h1>${article.title }</h1>
	${article.user.username }
	<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd"/>
	${article.content }

</div>

</center>
  
</body>
</html>
