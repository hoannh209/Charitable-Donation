<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Chi tiet dot quyen gop</title>
</head>
<body>
	<div class="container">
		
		<div class="col-lg-6 mt-3" style="float: left;">
			<div class="mb-5">
            	<h3 class="h5 d-flex align-items-center mb-4 text-primary"><span><i class="fa fa-align-left"></i></span>&nbsp;&nbsp;Nội dung của đợt quyên góp</h3>
            	<p>${donation.name}</p>
            	<div></div>
            	<h4 style="margin-top: 50px;">Danh sách quyên góp</h4>
            	
            		<c:forEach var="temp" items="${usersOfDonation}">
            			<c:if test="${temp.status == 1}">
	            			<div style="margin-top: 30px;">
	            				<p><i class="fa fa-user-circle-o"></i>&nbsp;&nbsp;<b>${temp.name}</b></p>
	            				<p style="margin-left: 25px;">${temp.created}</p>
	            				<p style="margin-left: 25px;">${temp.money} VNĐ</p>
	            			</div>
            			</c:if>
            		</c:forEach>
            		
            	
            	
          	</div>
          	
		</div>
		
		
		
		<div class="col-lg-6 mt-3" style="float: left;">
			<div class="bg-light p-3 border rounded mb-4">
				<form:form modelAttribute="donation" accept-charset="utf-8" method="GET">
				
				
            	<h3 class="text-primary  mt-3 h5 pl-3 mb-3 ">Thông tin</h3>
            	<ul class="list-unstyled pl-3 mb-0">
	              <li class="mb-2"><strong class="text-black">Mã đợt quyên góp:</strong> ${donation.code}</li>
	              <li class="mb-2"><strong class="text-black">Tên đợt quyên góp:</strong> ${donation.name}</li>
	              <li class="mb-2"><strong class="text-black">Ngày bắt đầu:</strong> ${donation.startDate}</li>
	              <li class="mb-2"><strong class="text-black">Ngày kết thúc:</strong> ${donation.endDate}</li>
	              <li class="mb-2"><strong class="text-black">Cá nhân/Tổ chức:</strong> ${donation.organizationName}</li>
	              <li class="mb-2"><strong class="text-black">Số điện thoại: </strong> ${donation.phoneNumber}</li>
	              <li class="mb-2"><strong class="text-black">Tổng tiền quyên góp nhận được:</strong> ${donation.money} VNĐ</li>
	              <li class="mb-2"><strong class="text-black">Trạng thái:</strong> ${donation.statusText}</li>
            	</ul>
            	
            	</form:form>
            	
            	
          </div>
          
          <c:if test="${donation.status == 1}">
          <div class="bg-light p-3 border rounded d-grid gap-2" >
          	
          	<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#myModal">
   				Quyên góp
  			</button>
          	
          	
  			<!-- The Modal -->
			<div class="modal" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<form action="${pageContext.request.contextPath}/userdonation/saveUserForDonation" method="post">
					      <!-- Modal Header -->
					      <div class="modal-header">
					        <h4 class="modal-title">Quyên góp: ${donation.name}</h4>
					        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					      </div>
					
					      <!-- Modal body -->
					      <div class="modal-body">
					      
					      	<input type="hidden" id="donationId" name="donationId" value="${donation.id}">
					      
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
          </div>
          </c:if>
          
		</div>
		
	</div>

</body>
</html>