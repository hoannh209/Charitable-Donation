<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<title>List Donations</title>
</head>
<body>
	
	<div class="container">
		<div id="header">
		
			<p></p>
			<h2>Danh Sách Đợt Quyên Góp</h2>
		
		</div>
		
		<hr>
		
		<div id="content">
		
			<input type="button" value="Thêm mới" 
					onclick="window.location.href='showFormForAddDonation'; return false;"
					class="btn btn-primary"
			/>
			
			<p/>
			
			<div>
				<form:form action="searchDonations" method="GET" style="float: right;">
					<input placeholder="Nhập trạng thái[mã, tổ chức, SĐT]..." type="text" 
						   name="keyword"/>
					<input type="submit" value="Search" />
				</form:form>
				<p style="clear: both;"/>
			</div>
			
			
		
			<table class="table table-striped table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>Mã</th>
						<th>Tên</th>
						<th>Ngày bắt đầu</th>
						<th>Ngày kết thúc</th>
						<th>Tổ chức</th>
						<th>Số điện thoại</th>
						<th>Tổng tiền</th>
						<th>Trạng thái</th>
						<th>Hành động</th>
					</tr>
				</thead>
				
				<c:forEach var="tempDonation" items="${pagedListHolder.pageList}">
				
					<!-- construct a "delete" link with donation id -->
					<c:url var="deleteLink" value="/donation/delete">
						<c:param name="donationId" value="${tempDonation.id}"></c:param>
					</c:url>
					
					<!-- construct an "update" link with donation id -->
					<c:url var="updateLink" value="/donation/showFormForUpdate">
						<c:param name="donationId" value="${tempDonation.id}"></c:param>
					</c:url>
					
					<!-- construct a "unlock" link with donation id -->
					<c:url var="unlockLink" value="/donation/unlockDonation">
						<c:param name="donationId" value="${tempDonation.id}"></c:param>
					</c:url>
					
					<!-- construct a "lock" link with donation id -->
					<c:url var="lockLink" value="/donation/lockDonation">
						<c:param name="donationId" value="${tempDonation.id}"></c:param>
					</c:url>
					
					<!-- construct a "close" link with donation id -->
					<c:url var="closeLink" value="/donation/closeDonation">
						<c:param name="donationId" value="${tempDonation.id}"></c:param>
					</c:url>
					
					<!-- construct a "showDetail" link with donation id -->
					<c:url var="showDetailLink" value="/donation/showDetail">
						<c:param name="donationId" value="${tempDonation.id}"></c:param>
					</c:url>
				
					<tr>
						<td>${tempDonation.code}</td>
						<td>${tempDonation.name}</td>
						<td>${tempDonation.startDate}</td>
						<td>${tempDonation.endDate}</td>
						<td>${tempDonation.organizationName}</td>
						<td>${tempDonation.phoneNumber}</td>
						<td>${tempDonation.money}</td>
						
						<c:if test="${tempDonation.status == 0}">  
							<td style="color: blue; font-weight: bold;">
								Mới tạo
							</td>
						</c:if>
											
						<c:if test="${tempDonation.status == 1}">  
							<td style="color: green; font-weight: bold;">
								Đang quyên góp
							</td>
						</c:if>
						<c:if test="${tempDonation.status == 2}">  
							<td style="color: red; font-weight: bold;">
								Kết thúc quyên góp
							</td>
						</c:if>
						<c:if test="${tempDonation.status == 3}">  
							<td style="color: #8b0000; font-weight: bold;">
								Đóng quyên góp
							</td>
						</c:if>
						  
						
						<td>
							<c:if test="${tempDonation.status== 0 || tempDonation.status== 1 || tempDonation.status== 2}">
								<a href="${updateLink}" class="btn btn-info btn-sm ">Cập nhật</a>
							</c:if>
						
							<a href="${showDetailLink}" class="btn btn-warning btn-sm">Chi tiết</a>
							
							<br>
							
							<c:if test="${tempDonation.status== 0 || tempDonation.status== 3}">
								<a href="${deleteLink}" class="btn btn-danger btn-sm mt-1" 
							   		onclick="if(!(confirm('Bạn chắc chắn muốn xóa Đợt quyên góp: ${tempDonation.name}'))) return false">
									Xóa
								</a>
							</c:if>
							
							<c:if test="${tempDonation.status== 0}">
								<a href="${unlockLink}" class="btn btn-success btn-sm mt-1">Quyên góp</a>
							</c:if>
							
							<c:if test="${tempDonation.status== 1}">
								<a href="${lockLink}" class="btn btn-danger btn-sm mt-1">Kết thúc</a>
							</c:if>
							
							<c:if test="${tempDonation.status== 2}">
								<a href="${closeLink}" class="btn btn-danger btn-sm mt-1">Đóng</a>
							</c:if>
							
							
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<!-- PAGELISTHOLDER -->
			<jsp:useBean id="pagedListHolder" scope="request"
			             type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/donation/list" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>

			<tg:paging pagedListHolder="${pagedListHolder}"
					   pagedLink="${pagedLink}" />
			
		</div>
	</div>
	
	
</body>
</html>