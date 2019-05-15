<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div
	class="support-function-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div
		class="support-function-header col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
		<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
			<div class="skew-container skew green-gradient">Members</div>
		</div>
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
	</div>
	<div
		class="support-function-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
		<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
			<div
				class="support-datatable col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="support-table" class="stripe">
					<thead>
						<tr>
							<th>Name</th>
							<th>Email</th>
							<th>Email Verified</th>
							<th>Phone</th>
							<th>Phone Verified</th>
							<th>Via Social Network?</th>
							<th>Joined On</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${members}">
							<tr>
								<td>${member.name}</td>
								<td>${member.emailId}</td>
								<td>${member.emailVerified}</td>
								<td>${member.phoneNumber}</td>
								<td>${member.phoneVerified}</td>
								<td><c:choose>
										<c:when test="${member.socialAuth == 'Y'}">Yes</c:when>
										<c:otherwise>No</c:otherwise>
									</c:choose></td>
								<td><fmt:formatDate type="both"
										value="${member.createdDate}" timeZone="IST" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
	</div>
</div>