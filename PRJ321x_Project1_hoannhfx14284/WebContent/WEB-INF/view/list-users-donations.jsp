<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Danh sách quyên góp</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<img alt="banner" src="${pageContext.request.contextPath}/resources/image/banner.jpg" style="width: 100%;">
		<div>	
			<p/>
			<h3 style="text-align: center;">Các đợt quyên góp</h3>
		</div>
		
		
		<div>
			<table 	class="table">
				<c:forEach var="tempDonation" items="${pagedListHolder.pageList}">
					
					<!-- construct a "showDetail" link with donation id -->
					<c:url var="showDetailLink" value="/userdonation/detail">
						<c:param name="donationId" value="${tempDonation.id}"></c:param>
					</c:url>
					
					<tr>
						<td>
							<b>
								<a href="${showDetailLink}" style="text-decoration: none;">${tempDonation.name}</a>
							</b>
							
							<c:if test="${tempDonation.status == 0}">  
								<p>Mới tạo</p>
							</c:if>
							<c:if test="${tempDonation.status == 1}">  
								<p>Đang quyên góp</p>
							</c:if>
							<c:if test="${tempDonation.status == 2}">  
								<p>Kết thúc quyên góp</p>
							</c:if>
							<c:if test="${tempDonation.status == 3}">  
								<p>Đóng quyên góp</p>
							</c:if>
						</td>
						
						<td>
							Ngày bắt đầu
							<p>${tempDonation.startDate}</p>
						</td>
						
						<td>
							Ngày kết thúc
							<p>${tempDonation.endDate}</p>
						</td>
						
						<td>
							${tempDonation.organizationName}
							<p>${tempDonation.phoneNumber}</p>
						</td>
						
						<td>
							<c:if test="${tempDonation.status == 1}">
								
							
								<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#myModal" >
   									Quyên góp
  								</button>

								<!-- The Modal -->
								<div class="modal" id="myModal">
								  <div class="modal-dialog">
								    <div class="modal-content">
										<form action="${pageContext.request.contextPath}/userdonation/saveUserForDonation" method="post">
									      
									      <!-- Modal Header -->
									      <div class="modal-header">
									        <h4 class="modal-title">Quyên góp: ${tempDonation.name}</h4>
									        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
									      </div>
									
									      <!-- Modal body -->
									      <input type="hidden" id="donationId" name="donationId" value="${tempDonation.id}">
									      
									      <div class="modal-body">
											<div class="mb-3">
												<label for="full-name" class="col-form-label">Họ tên:</label>
												<input type="text" class="form-control" id="full-name" name="name">
							          		</div>
															        
											<div class="mb-3">
												<label for="money" class="col-form-label">Số tiền quyên góp:</label>
												<input type="text" class="form-control" id="money" name="money">
							      			</div>
															        
											<div class="mb-3">
												<label for="message-text" class="col-form-label">Lời nhắn:</label>
												<textarea class="form-control" rows="5" id="message-text" name="text"></textarea>
						          			</div>
					          			</div>
									
									      <!-- Modal footer -->
									      <div class="modal-footer">
									        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
	        								<button type="submit" class="btn btn-success">Quyên góp</button>
									      </div>
										</form>
								    </div>
								  </div>
								</div>
							</c:if>
						</td>
						
					</tr>
				</c:forEach>
			</table>
			
			<!-- PAGELISTHOLDER -->
			<jsp:useBean id="pagedListHolder" scope="request"
			             type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/userdonation/list" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>

			<tg:paging pagedListHolder="${pagedListHolder}"
					   pagedLink="${pagedLink}" />
		
	
		</div>
		
	</div>
	
</body>
</html>