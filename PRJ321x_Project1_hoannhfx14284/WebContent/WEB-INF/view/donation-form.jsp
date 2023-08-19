<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<title>Save Donation</title>
</head>
<body>
	<div class="container">
		<div id="header">
			<p></p>
			<h2>Thêm Mới</h2>
		</div>
		
		<hr>
		
		<form:form action="saveDonation" modelAttribute="donation" accept-charset="utf-8" method="POST">
			
			<form:hidden path="id"/>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Mã đợt quyên góp:</label>
					<form:input path="code" class="form-control" />
				</div>

				<div class="form-group col-md-6">
					<label>Tên đợt quyên góp:</label>
					<form:input path="name" class="form-control" />
				</div>
			</div>


			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Ngày bắt đầu:</label>
					<form:input type="date" path="startDate" class="form-control"
								min="2023-01-01" max="2025-12-31" />
				</div>

				<div class="form-group col-md-6">
					<label>Ngày kết thúc:</label>
					<form:input path="endDate" class="form-control" type="date"
					 			min="2023-01-01" max="2025-12-31"/>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Tổ chức:</label>
					<form:input path="organizationName" class="form-control" />
				</div>

				<div class="form-group col-md-6">
					<label>Số điện thoại:</label>
					<form:input path="phoneNumber" class="form-control" />
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-12">
					<label>Nội dung:</label>
					<br>
					<form:textarea path="description" rows="5" cols="148" class="form-control" placeholder="Nhập nội dung..."/>
				</div>
			</div>
			
			<div class="form-row" style="float: right;">

				<a href="${pageContext.request.contextPath}/donation/list"
					class="btn btn-secondary px-5 mr-3">Đóng</a> 
				<input type="submit" value="Thêm" class="btn btn-info px-5">

			</div>
		</form:form>
		
	</div>
</body>
</html>