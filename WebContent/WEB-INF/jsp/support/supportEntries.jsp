<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div
	class="support-function-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div
		class="support-function-header col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
		<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
			<div class="skew-container skew green-gradient">pending entries</div>
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
							<th>Address</th>
							<th>Request Type</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pendingEntry" items="${pendingEntries}">
							<tr>
								<td>${pendingEntry.name}</td>
								<td>${pendingEntry.locationAddress}</td>
								<c:choose>
									<c:when test="${pendingEntry.preExistingRecord == 'true'}">
										<td>Existing Entry</td>
									</c:when>
									<c:otherwise>
										<td>New Entry</td>
									</c:otherwise>
								</c:choose>
								<td><a href="supportEntryDetails?id=${pendingEntry.memberEntryStagingId}">view details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
	</div>
</div>