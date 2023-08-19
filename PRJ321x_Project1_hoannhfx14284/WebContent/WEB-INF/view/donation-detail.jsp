<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<title>Detail</title>
</head>
<body>
	<div class="container">
		<div id="header">
			<p></p>
			<h2>Chi tiết đợt quyên góp</h2>
		</div>
		
		<hr>
		
		<form:form modelAttribute="donation" accept-charset="utf-8" method="GET">
			
			<form:hidden path="id"/>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Mã đợt quyên góp:</label>
					<form:input path="code" class="form-control" readonly="true"/>
				</div>

				<div class="form-group col-md-6">
					<label>Tên đợt quyên góp:</label>
					<form:input path="name" class="form-control" readonly="true"/>
				</div>
			</div>


			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Ngày bắt đầu:</label>
					<form:input type="date" path="startDate" class="form-control"
								min="2023-01-01" max="2025-12-31" readonly="true"/>
				</div>

				<div class="form-group col-md-6">
					<label>Ngày kết thúc:</label>
					<form:input path="endDate" class="form-control" type="date"
					 			min="2023-01-01" max="2025-12-31" readonly="true"/>
				</div>
			</div>
	
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Tổng tiền quyên góp:</label>
					<form:input path="money" class="form-control" readonly="true"/>
				</div>

				<div class="form-group col-md-6">
					<label>Trạng thái:</label>
					<form:input path="statusText" class="form-control" readonly="true"/>
				</div>
			</div>


			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Tổ chức:</label>
					<form:input path="organizationName" class="form-control" readonly="true"/>
				</div>

				<div class="form-group col-md-6">
					<label>Số điện thoại:</label>
					<form:input path="phoneNumber" class="form-control" readonly="true"/>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-12">
					<label>Nội dung:</label>
					<br>
					<form:textarea path="description" rows="5" cols="148" class="form-control" 
								   placeholder="Nhập nội dung..." readonly="true"/>
				</div>
			</div>
		</form:form>
		<hr>
		
		<h4>Danh sách những người quyên góp</h4>
			
		<table class="table table-striped table-bordered">
			
			<thead class="thead-dark">
				<tr>
					<th>Họ tên</th>
					<th>Tiền quyên góp</th>
					<th>Ngày quyên góp</th>
					<th>Nội dung</th>
					<th>Trạng thái</th>						
					<th>Hành động</th>
				</tr>
			</thead>
				
			<c:forEach var="tempUserDonation" items="${usersOfDonation}">
			
				<!-- construct a "confirmUserDonation" link with userdonation id -->
				<c:url var="confirmLink" value="/donation/confirmUserDonation">
					<c:param name="userDonationId" value="${tempUserDonation.id}"></c:param>
					<c:param name="donationId" value="${tempUserDonation.donation.id}"></c:param>
				</c:url>
				
				<!-- construct a "cancelUserDonation" link with userdonation id -->
				<c:url var="cancelLink" value="/donation/cancelUserDonation">
					<c:param name="userDonationId" value="${tempUserDonation.id}"></c:param>
					<c:param name="donationId" value="${tempUserDonation.donation.id}"></c:param>
				</c:url>
				
				
				
				<tr>
				
					<td>${tempUserDonation.name}</td>
					<td>${tempUserDonation.money}</td>
					<td>${tempUserDonation.created}</td>
					<td>${tempUserDonation.text}</td>
					
					<c:if test="${tempUserDonation.status == 0}">
						<td>Chờ xác nhận</td>
					</c:if>
					
					<c:if test="${tempUserDonation.status == 1}">
						<td>Đã xác nhận</td>
					</c:if>
					
					<c:if test="${tempUserDonation.status == 2}">
						<td>Đã hủy</td>
					</c:if>
					
					<td>
						<c:if test="${tempUserDonation.status == 0}">
							<a href="${confirmLink}" class="btn btn-success">Xác nhận</a>
							<a href="${cancelLink}" class="btn btn-danger" >Hủy xác nhận</a>
						</c:if>
						
					</td>
					
					
				</tr>
			</c:forEach>
		</table>
		
	</div>
</body>
</html>