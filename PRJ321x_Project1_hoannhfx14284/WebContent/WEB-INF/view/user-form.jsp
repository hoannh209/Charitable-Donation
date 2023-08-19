 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<title>Save User</title>
</head>
<body>

	<div class="container">
		<div id="header">
			<p></p>
			<h2>Thêm Mới</h2>
		</div>
		
		<hr>
		
		<form:form action="saveUser" modelAttribute="user" accept-charset="utf-8" method="POST">
			
			<form:hidden path="id"/>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Họ và tên:</label>
					<form:input path="fullName" class="form-control" />
				</div>

				<div class="form-group col-md-6">
					<label>Email:</label>
					<form:input path="email" class="form-control" />
				</div>
			</div>


			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Số điện thoại:</label>
					<form:input path="phoneNumber" class="form-control" />
				</div>

				<div class="form-group col-md-6">
					<label>Địa chỉ:</label>
					<form:input path="address" class="form-control" />
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Tài khoản:</label>
					<form:input path="userName" class="form-control" />
				</div>

				<div class="form-group col-md-6">
					<label>Mật khẩu:</label>
					<form:input path="password" class="form-control" />
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Vai trò:</label>
					
					<form:select path="role" class="form-control">
						<form:option value="Admin" label="Admin" />
						<form:option value="User" label="User" />
					</form:select>
				
				</div>
			</div>
			<div class="form-row" style="float: right;">

				<a href="${pageContext.request.contextPath}/user/list"
					class="btn btn-secondary px-5 mr-3">Đóng</a> <input type="submit"
					value="Thêm" class="btn btn-info px-5">

			</div>
		</form:form>
		
	</div>
</body>
</html>