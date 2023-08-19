<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
<title>Quản trị</title>
</head>

<body>
	<div id="container">
		<div>
			<h3>
				<a id="link1" href="${pageContext.request.contextPath}/user/list">Quản lý người dùng</a>
			</h3>
		</div>
	
		<div>
			<h3>
				<a href="${pageContext.request.contextPath}/donation/list">Quản lý đợt quyên góp</a>
			</h3>
		</div>
		
		<div>
			<h3>
				<a id="link3" href="${pageContext.request.contextPath}/userdonation/list">Thực hiện quyên góp</a>
			</h3>
		</div>
	</div>
	
	
</body>

</html>