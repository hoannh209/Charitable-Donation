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
<title>List Users</title>
</head>
<body>
	<div class="container">
		<div id="header">
			<p></p>
			<h2>Danh Sách Người Dùng</h2>
		</div>
	
	<hr>
	
		<div id="content">
			<input type="button" value="Thêm mới" 
					onclick="window.location.href='showFormForAddUser'; return false;"
					class="btn btn-primary"
			/>
			
			<p/>
			
			<div>
				<form:form action="searchUsers" method="GET" style="float: right;">
					<input placeholder="Nhập SĐT..." type="text" name="theSearchPhoneNumber" />
					<input type="submit" value="Search" />
				</form:form>
				<p style="clear: both;"/>
			</div>
			
			
			
			<table class="table table-striped table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>Họ tên</th>
						<th>Email</th>
						<th>Số điện thoại</th>
						<th>Tài khoản</th>
						<th>Vai trò</th>
						<th>Trạng thái</th>
						<th>Hành động</th>
					</tr>
				</thead>
				
				<c:forEach var="tempUser" items="${pagedListHolder.pageList}">
				
					<!-- construct an "update" link with user id -->
					<c:url var="updateLink" value="/user/showFormForUpdate">
						<c:param name="userId" value="${tempUser.id}"></c:param>
					</c:url>
					
					<!-- construct a "delete" link with user id -->
					<c:url var="deleteLink" value="/user/delete">
						<c:param name="userId" value="${tempUser.id}"></c:param>
					</c:url>
					
					<!-- construct a "lock" link with user id -->
					<c:url var="lockLink" value="/user/lockUser">
						<c:param name="userId" value="${tempUser.id}"></c:param>
					</c:url>
					
					<!-- construct a "unlock" link with user id -->
					<c:url var="unlockLink" value="/user/unlockUser">
						<c:param name="userId" value="${tempUser.id}"></c:param>
					</c:url>
					
					
				
					<tr>
						<td>${tempUser.fullName}</td>
						<td>${tempUser.email}</td>
						<td>${tempUser.phoneNumber}</td>
						<td>${tempUser.userName}</td>
						<td>${tempUser.role.roleName}</td>
						
						<c:if test="${tempUser.status == 1}">  
							<td style="color: green; font-weight: bold;">
								Hoạt động
							</td>
						</c:if>
						<c:if test="${tempUser.status == 0}">  
							<td style="color: red; font-weight: bold;">
								Đã khóa
							</td>
						</c:if>  
						
						<td>
							<a href="#" class="btn btn-info btn-sm ">Gửi</a>
							<a href="${updateLink}" class="btn btn-success btn-sm ">Sửa</a>
							<a href="#" class="btn btn-warning btn-sm">Chi tiết</a>
							<br>
							<a href="${deleteLink}" class="btn btn-danger btn-sm mt-1" 
							   onclick="if(!(confirm('Bạn chắc chắn muốn xóa ${tempUser.fullName}'))) return false">
								Xóa
							</a>
							
							<c:if test="${tempUser.status == 1}">
								<a href="${lockLink}" class="btn btn-danger btn-sm mt-1">Khóa</a>
							</c:if>
							
							<c:if test="${tempUser.status == 0}">
								<a href="${unlockLink}" class="btn btn-success btn-sm mt-1">Mở</a>
							</c:if>
							
						
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<!-- PAGELISTHOLDER -->
			<jsp:useBean id="pagedListHolder" scope="request"
			             type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/user/list" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>

			<tg:paging pagedListHolder="${pagedListHolder}"
					   pagedLink="${pagedLink}" />
			
			
			
		</div>
	</div>
</body>
</html>