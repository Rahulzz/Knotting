<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div
	class="support-function-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div
		class="support-function-header col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
		<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
			<div class="skew-container skew green-gradient">Coupons</div>
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
							<th>Coupon Code</th>
							<th>Discount Percentage</th>
							<th>Discount Amount</th>
							<th>From Date</th>
							<th>Expiry Date</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="coupon" items="${coupons}">
							<tr>
								<td>${coupon.couponCode}</td>
								<td>${coupon.couponDiscountPercent}</td>
								<td>${coupon.couponDiscountAmount}</td>
								<td>${coupon.couponFromDate}</td>
								<td>${coupon.couponExpiryDate}</td>
								<td><c:choose>
										<c:when test="${coupon.couponStatus == 'N'}">
											<a href="#"><input type="button" value="activate" /></a>
										</c:when>
										<c:otherwise>
											<a href="#"><input type="button" value="de-activate" /></a>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
	</div>
</div>