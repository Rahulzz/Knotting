<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form id="repaymentForm" method="post" action="./proceedToPay"
	class="col-sm-12 col-md-12 col-lg-12">
	<div
		class="knotting-vendor-screens menu-blur-section repayment-screen col-sm-12 col-md-12 col-lg-12">
		<div class="col-xs-1 col-sm-1 col-md-2 col-lg-2"></div>
		<div class="content col-xs-10 col-sm-10 col-md-8 col-lg-8">
			<div class="payment-failure-header col-sm-12 col-md-12 col-lg-12">
				<div class="payment-retry-title col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">your payment didn't go
						through</div>
				</div>
				<div class="payment-retry-subtitle col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew green-gradient">don't worry,
						we've got your back</div>
				</div>
				<div class="payment-retry-text col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">Choose the appropriate
						payment method and try one more time</div>
				</div>
			</div>
			<div class="edit-decision-box col-sm-12 col-md-12 col-lg-12">
				<div class="section-detail">Click the 'proceed to pay' button
					to go ahead with your payment process. Upon successful payment your
					services would be taken up for moderation. To view the subscription
					rates for the available services, kindly click the rate card option
					on the menu.</div>
				<div class="decision-option">
					<input id="grab-money" type="button"
						class="services-section-button ease-element"
						value="proceed to pay" />
				</div>
			</div>
			<div class="payment-box col-sm-12 col-md-12 col-lg-12">
				<div class="info-container hidden-md hidden-lg remove-for-web">
					<div class="edit-services-list col-sm-12 col-md-12 col-lg-12">
						<div class="section-title col-sm-12 col-md-12 col-lg-12">payment
							options</div>
						<div class="section-explainer col-sm-12 col-md-12 col-lg-12">Choose
							the payment option that best suits you. Kindly do not refresh the
							page once you have clicked on the payment option. We are working
							to add more options to this list.</div>
						<div class="payment-list col-sm-12 col-md-12 col-lg-12">
							<div
								class="list-payment-item ease-element disable_user_selection col-sm-12 col-md-12 col-lg-12"
								data-payment="P">
								<div class="payment-content col-sm-12 col-md-12 col-lg-12">
									<div class="icon icon-payment-online"></div>
									<div class="payment-detail">
										<div class="detail-title">Debit card / Credit card / Net
											banking</div>
										<div class="detail-explainer">You will be redirected to
											PayU Money to complete your payment securely.</div>
									</div>
								</div>
							</div>
							<div
								class="list-payment-item ease-element disable_user_selection col-sm-12 col-md-12 col-lg-12"
								data-payment="E">
								<div class="payment-content col-sm-12 col-md-12 col-lg-12">
									<div class="icon icon-payment-executive"></div>
									<div class="payment-detail">
										<div class="detail-title">Pay to our executive</div>
										<div class="detail-explainer">This option is available
											only for establishments visited by our executives.</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="bill-container">
					<div class="paper-vending-slot col-sm-12 col-md-12 col-lg-12"></div>
					<div class="paper-holder col-sm-12 col-md-12 col-lg-12">
						<div class="paper receipt-paper col-sm-12 col-md-12 col-lg-12">
							<div class="paper-header col-sm-12 col-md-12 col-lg-12">Invoice</div>
							<div class="tear-paper col-sm-12 col-md-12 col-lg-12">
								<div></div>
							</div>
							<div class="paper-service-list col-sm-12 col-md-12 col-lg-12">
								<div class="paper-service-header col-sm-12 col-md-12 col-lg-12">items
									added</div>
								<c:forEach begin="0" end="${serviceList.length() -1}"
									var="index">
									<div class="paper-service-item col-sm-12 col-md-12 col-lg-12">
										<div class="paper-service-detail col-sm-7 col-md-7 col-lg-7">
											${serviceList.getJSONObject(index).getString("Name")}<br>
											<c:set var="payment"
												value='${serviceList.getJSONObject(index).getString("Payment")}' />
											<c:choose>
												<c:when test="${payment == 'paid'}">
													<span style="color: #22c48a;">Subscription already
														paid</span>
												</c:when>
												<c:otherwise>
													<span style="color: #f82740;">Subscription not paid</span>
												</c:otherwise>
											</c:choose>
										</div>
										<div class="paper-service-cost col-sm-5 col-md-5 col-lg-5">${serviceList.getJSONObject(index).getString("Cost")}
											INR</div>
									</div>
								</c:forEach>
							</div>
							<div class="tear-paper col-sm-12 col-md-12 col-lg-12">
								<div></div>
							</div>
							<div class="coupon-box col-sm-12 col-md-12 col-lg-12">
								<div class="paper-service-header col-sm-12 col-md-12 col-lg-12">
									have a coupon?</div>
								<div class="coupon-add-box col-sm-12 col-md-12 col-lg-12">
									<input id="add-coupon" type="text" placeholder="XXXXXX" /> <input
										id="apply-coupon" type="button" value="apply"
										class="ease-element" />
								</div>
								<div class="coupon-selected-box col-sm-12 col-md-12 col-lg-12"></div>
							</div>
							<div class="paper-sub-highlights col-sm-12 col-md-12 col-lg-12">
								<div class="sub-highlight-item col-sm-12 col-md-12 col-lg-12">
									<div class="paper-highlight-detail col-sm-7 col-md-7 col-lg-7">
										Total cost</div>
									<div id="paper-total-amount"
										class="paper-highlight-cost col-sm-5 col-md-5 col-lg-5">${serviceAmount}
										INR</div>
								</div>
								<div class="sub-highlight-item col-sm-12 col-md-12 col-lg-12">
									<div class="paper-highlight-detail col-sm-7 col-md-7 col-lg-7">
										Coupon discount</div>
									<div id="paper-discount-amount"
										class="paper-highlight-cost col-sm-5 col-md-5 col-lg-5">${discountAmount}
										INR</div>
								</div>
							</div>
							<div class="paper-highlighted-line col-sm-12 col-md-12 col-lg-12">
								<div class="paper-service-header col-sm-12 col-md-12 col-lg-12">
									final cost</div>
								<div id="paper-final-cost"
									class="paper-highlighted-value col-sm-12 col-md-12 col-lg-12">${finalAmount}
									INR</div>
							</div>
							<div class="paper-footer col-sm-12 col-md-12 col-lg-12">
								<div class="icon icon-mail"></div>
								<div class="footer-text">a receipt for payment would be
									mailed to you upon successful payment.</div>
							</div>
						</div>
					</div>
				</div>
				<div class="info-container hidden-xs hidden-sm remove-for-mobile">
					<div class="edit-services-list col-sm-12 col-md-12 col-lg-12">
						<div class="section-title col-sm-12 col-md-12 col-lg-12">your
							services</div>
						<div class="section-explainer col-sm-12 col-md-12 col-lg-12">The
							following are the list of services you have selected. Place the
							mouse cursor over the icon to view the details.</div>
						<div class="section-list col-sm-12 col-md-12 col-lg-12">
							<c:forEach begin="0" end="${serviceList.length() -1}" var="index">
								<div class="list-service-item">
									<c:set var="payment"
										value='${serviceList.getJSONObject(index).getString("Payment")}' />
									<c:choose>
										<c:when test="${payment == 'paid'}">
											<c:set var="paymentCode"
												value="<span style='color: #22c48a;'>Subscription already paid</span>" />
										</c:when>
										<c:otherwise>
											<c:set var="paymentCode"
												value="<span style='color: #f82740;'>Subscription not paid</span>" />
										</c:otherwise>
									</c:choose>
									<c:set var="status"
										value='${serviceList.getJSONObject(index).getString("Status")}' />
									<c:choose>
										<c:when test="${status == 'active'}">
											<c:set var="statusCode"
												value="This service is currently active" />
										</c:when>
										<c:otherwise>
											<c:set var="statusCode"
												value="This service is currently inactive" />
										</c:otherwise>
									</c:choose>
									<c:set var="name"
										value='${serviceList.getJSONObject(index).getString("Name")}' />
									<c:set var="cost"
										value='${serviceList.getJSONObject(index).getString("Cost")}' />
									<c:set var="expiry"
										value='${serviceList.getJSONObject(index).getString("Expiry")}' />
									<img
										src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
										class='knotting-menu-image-${serviceList.getJSONObject(index).getString("Code")} knotting-tooltip'
										data-html="<b>${name}</b><br><br>Subscription cost - ${cost} INR<br><br>Expiry - ${expiry}<br><br>${statusCode}<br><br>${paymentCode}"
										data-position="right center" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="edit-services-list col-sm-12 col-md-12 col-lg-12">
						<div class="section-title col-sm-12 col-md-12 col-lg-12">payment
							options</div>
						<div class="section-explainer col-sm-12 col-md-12 col-lg-12">Choose
							the payment option that best suits you. Kindly do not refresh the
							page once you have clicked on the payment option. We are working
							to add more options to this list.</div>
						<div class="payment-list col-sm-12 col-md-12 col-lg-12">
							<div
								class="list-payment-item ease-element disable_user_selection col-sm-12 col-md-12 col-lg-12"
								data-payment="P">
								<div class="payment-content col-sm-12 col-md-12 col-lg-12">
									<div class="icon icon-payment-online"></div>
									<div class="payment-detail">
										<div class="detail-title">Debit card / Credit card / Net
											banking</div>
										<div class="detail-explainer">You will be redirected to
											PayU Money to complete your payment securely.</div>
									</div>
								</div>
							</div>
							<div
								class="list-payment-item ease-element disable_user_selection col-sm-12 col-md-12 col-lg-12"
								data-payment="E">
								<div class="payment-content col-sm-12 col-md-12 col-lg-12">
									<div class="icon icon-payment-executive"></div>
									<div class="payment-detail">
										<div class="detail-title">Pay to our executive</div>
										<div class="detail-explainer">This option is available
											only for establishments visited by our executives.</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-1 col-sm-1 col-md-2 col-lg-2"></div>
	</div>
</form:form>
<script type="text/javascript">
retryPaymentListeners();
</script>