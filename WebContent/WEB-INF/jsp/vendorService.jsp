<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div
	class="knotting-vendor-service-screens sticky-page col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<form:form method="post" modelAttribute="service">
		<div
			class="title-box-container sticky-candidate ui sticky col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="left-section section hidden-xs hidden-sm">
				<img
					src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
					class="knotting-menu-image-photography service-image" />
			</div>
			<div class="right-section section">
				<div class="entry-title">${service.memberEntries.name}</div>
				<div class="entry-address">${service.memberEntries.locationAddress}</div>
				<div class="entry-service ease-element">${service.services.name}</div>
				<div class="entry-rating ease-element">
					<div class="rating-count">
						<span class="icon icon-nostar"></span>5.0
					</div>
					<div class="rating-entries">yet to be rated</div>
				</div>
			</div>
		</div>
		<div
			class="data-box-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="information-container ui segment">
				<div
					class="highlights-container left attached ui rail hidden-xs hidden-sm col-md-12 col-lg-12">
					<div
						class="sticky-section-box ui sticky col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="highlights-box">
							<div class="vendor-experience highlight-box">
								<div class="highlight-value">${service.experience}</div>
								<div class="highlight-desc">years of experience</div>
							</div>
							<div class="vendor-advance highlight-box">
								<div class="highlight-value">${service.advancePercentage}%</div>
								<div class="highlight-desc">
									of the cost to be paid as<br>advance to book the service
								</div>
							</div>
							<div class="vendor-cost highlight-box highlight-this">
								<div class="highlight-value">${service.priceRange} INR</div>
								<div class="highlight-desc">
									is the cost we charge for <span class="price-range-text"></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div
					class="addln-info-container right attached ui rail hidden-xs hidden-sm col-md-12 col-lg-12">
					<div
						class="sticky-section-box ui sticky col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="addln-info col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div id="vendor-service-reviews-box"
								class="content-section-title col-sm-12 col-md-12 col-lg-12">Emergency?</div>
							<div class="info-content">
								<c:choose>
									<c:when
										test="${service.memberEntries.emergencyRequestAccepted == 'Y'}">
								We have a team to handle your emergency requests. Call
								us at the earliest to plan the event accordingly.<br>
										<br>Our
								emergency number is<br>+91-${service.memberEntries.emergencyPhoneNumber}<br>
										<br>For
								a normal enquiry, use the general number shared below.</c:when>
									<c:otherwise>
								We don't have a team to handle emergency requests.<br>
										<br>In order to provide a delightful service, we need ample time to plan the event.</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div
							class="social-connect col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="social-items col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<c:choose>
									<c:when
										test="${not empty service.memberEntries.websiteUrl and service.memberEntries.websiteUrl != ''}">
										<a href="${service.memberEntries.websiteUrl}"
											class="ease-element" target="BLANK"><span
											class="icon icon-social-web"></span></a>
									</c:when>
									<c:otherwise>
										<span class="icon icon-social-web disabled"></span>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${not empty service.memberEntries.facebookUrl and service.memberEntries.facebookUrl != ''}">
										<a href="${service.memberEntries.facebookUrl}"
											class="ease-element" target="BLANK"><span
											class="icon icon-social-facebook"></span></a>
									</c:when>
									<c:otherwise>
										<span class="icon icon-social-facebook disabled"></span>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${not empty service.memberEntries.twitterUrl and service.memberEntries.twitterUrl != ''}">
										<a href="${service.memberEntries.twitterUrl}"
											class="ease-element" target="BLANK"><span
											class="icon icon-social-twitter"></span></a>
									</c:when>
									<c:otherwise>
										<span class="icon icon-social-twitter disabled"></span>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${not empty service.memberEntries.instagramUrl and service.memberEntries.instagramUrl != ''}">
										<a href="${service.memberEntries.instagramUrl}"
											class="ease-element" target="BLANK"><span
											class="icon icon-social-instagram"></span></a>
									</c:when>
									<c:otherwise>
										<span class="icon icon-social-instagram disabled"></span>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${not empty service.memberEntries.youtubeUrl and service.memberEntries.youtubeUrl != ''}">
										<a href="${service.memberEntries.youtubeUrl}"
											class="ease-element" target="BLANK"><span
											class="icon icon-social-youtube"></span></a>
									</c:when>
									<c:otherwise>
										<span class="icon icon-social-youtube disabled"></span>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${not empty service.memberEntries.googleplusUrl and service.memberEntries.googleplusUrl != ''}">
										<a href="${service.memberEntries.googleplusUrl}"
											class="ease-element" target="BLANK"><span
											class="icon icon-social-google"></span></a>
									</c:when>
									<c:otherwise>
										<span class="icon icon-social-google disabled"></span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
				<div class="service-details-section">
					<div class="share-box contributors">
						<div id="vendor-service-reviews-box"
							class="content-section-title col-sm-12 col-md-12 col-lg-12">Share</div>
						<div class="no-content col-sm-12 col-md-12 col-lg-12">
							<div class="talk-with-pic">
								<img
									src="https://storage.googleapis.com/knotting/images/misc/hippie-trail.jpg" />
							</div>
							<div class="talk-your-heart">
								<div class="message">Have to recite the details to your
									friend? Don't worry. Give us your friend's number, we'll share
									the details.</div>
								<div class="share-box">
									<div class="field">
										<input type="hidden" id="share-details-from" /> <input
											id="share-details-number" type="text"
											class="onlyNumbers lengthTen"
											placeholder="friend's mobile number"
											data-service="${service.memberServiceCode}" />
									</div>
									<div class="click">
										<input id="share-details-submit" type="button"
											class="ease-element" value="send" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div
						class="basic-info-box details-box contributors hidden-md hidden-lg">
						<div id="vendor-service-detail-box"
							class="content-section-title col-sm-12 col-md-12 col-lg-12">Few
							Numbers</div>
						<div class="detail-info col-sm-12 col-md-12 col-lg-12">
							<div class="detail-question col-sm-12 col-md-12 col-lg-12">We
								have been in this field for</div>
							<div class="detail-answer col-sm-12 col-md-12 col-lg-12">${service.experience}
								years</div>
						</div>
						<div class="detail-info col-sm-12 col-md-12 col-lg-12">
							<div class="detail-question col-sm-12 col-md-12 col-lg-12">In
								order to book our service you'll have to pay</div>
							<div class="detail-answer col-sm-12 col-md-12 col-lg-12">${service.advancePercentage}%
								of the total cost as advance</div>
						</div>
						<div class="detail-info col-sm-12 col-md-12 col-lg-12">
							<div class="detail-question col-sm-12 col-md-12 col-lg-12">
								Our price for <span class="price-range-text"></span> is
							</div>
							<div class="detail-answer col-sm-12 col-md-12 col-lg-12">
								${service.priceRange} INR (This is an estimate for you to
								understand our price range)</div>
						</div>
						<div class="detail-info col-sm-12 col-md-12 col-lg-12">
							<c:choose>
								<c:when
									test="${service.memberEntries.emergencyRequestAccepted == 'Y'}">
									<div class="detail-question col-sm-12 col-md-12 col-lg-12">
										We have a team to handle your emergency requests. Call us at
										the earliest to plan the event accordingly.<br> <br>Our
										emergency number is
									</div>
									<div class="detail-answer col-sm-12 col-md-12 col-lg-12">+91-${service.memberEntries.emergencyPhoneNumber}(For
										a normal enquiry, use the general number shared below)</div>
								</c:when>
								<c:otherwise>
									<div class="detail-question col-sm-12 col-md-12 col-lg-12">
										We don't have a team to handle emergency requests</div>
									<div class="detail-answer col-sm-12 col-md-12 col-lg-12">In
										order to provide a delightful service, we need ample time to
										plan the event.</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="details-box contributors service-answers">
						<div id="vendor-service-detail-box"
							class="content-section-title col-sm-12 col-md-12 col-lg-12">Details</div>
						<c:forEach var="serviceDetail" items="${servicesQuestions}"
							varStatus="field">
							<c:if
								test="${serviceDetail.serviceDetailFieldName != 'experience' and serviceDetail.serviceDetailFieldName != 'communitiesServed' and serviceDetail.serviceDetailFieldName != 'advancePercentage'}">
								<c:choose>
									<c:when
										test="${serviceDetail.serviceDetailFieldName != 'priceRange'}">
										<div
											class="detail-info detail-box modifiable-field-container col-sm-12 col-md-12 col-lg-12"
											data-conditionfield="${serviceDetail.serviceDetailPreConditionField}"
											data-conditionanswer="${serviceDetail.serviceDetailPreConditionAnswer}"
											data-initialstate="${serviceDetail.serviceDetailInitialState}"
											data-fieldtype="${serviceDetail.serviceDetailType}">
											<div class="detail-question col-sm-12 col-md-12 col-lg-12">${serviceDetail.serviceDetailTitle}</div>
											<div class="detail-answer col-sm-12 col-md-12 col-lg-12">
												<form:hidden id="${serviceDetail.serviceDetailFieldName}"
													path="${serviceDetail.serviceDetailFieldName}"
													data-fieldidentifier="${field.index}:${serviceDetail.serviceDetailFieldName}" />
												<span>${serviceDetail.serviceDetailViewTag}</span>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div
											class="detail-info detail-box details-box-hidden modifiable-field-container col-sm-12 col-md-12 col-lg-12"
											data-conditionfield="${serviceDetail.serviceDetailPreConditionField}"
											data-conditionanswer="${serviceDetail.serviceDetailPreConditionAnswer}"
											data-initialstate="${serviceDetail.serviceDetailInitialState}"
											data-fieldtype="${serviceDetail.serviceDetailType}">
											<div class="detail-question col-sm-12 col-md-12 col-lg-12">${serviceDetail.serviceDetailTitle}</div>
											<div class="detail-answer col-sm-12 col-md-12 col-lg-12">
												<form:hidden id="${serviceDetail.serviceDetailFieldName}"
													path="${serviceDetail.serviceDetailFieldName}"
													data-fieldidentifier="${field.index}:${serviceDetail.serviceDetailFieldName}" />
												<span>${serviceDetail.serviceDetailViewTag}</span>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</div>
					<script type="text/javascript">
						prepareServiceAnswerSet();
					</script>
					<div class="contact-box contributors">
						<div id="vendor-service-contact-box"
							class="content-section-title col-sm-12 col-md-12 col-lg-12">Contact</div>
						<div class="no-content col-sm-12 col-md-12 col-lg-12">
							<div class="talk-with-pic">
								<img
									src="https://storage.googleapis.com/knotting/images/misc/phone.jpg" />
							</div>
							<div class="talk-your-heart bullets">
								<div class="points">You can find us at</div>
								<div class="points">
									<div class="icon icon-phone"></div>
									<div class="data">
										+91-${service.memberEntries.phone1}
										<c:if test="${not empty service.memberEntries.phone2}">, +91-${service.memberEntries.phone2}</c:if>
										<c:if test="${not empty service.memberEntries.phone3}">, +91-${service.memberEntries.phone3}</c:if>
										<c:if test="${not empty service.memberEntries.phone4}">, +91-${service.memberEntries.phone4}</c:if>
									</div>
								</div>
								<c:if
									test="${not empty service.memberEntries.emailAddress and service.memberEntries.emailAddress != ''}">
									<div class="points">
										<div class="icon icon-mail"></div>
										<div class="data">${service.memberEntries.emailAddress}</div>
									</div>
								</c:if>
							</div>
						</div>
					</div>
					<div class="pics-box contributors">
						<div id="vendor-service-pics-box"
							class="content-section-title col-sm-12 col-md-12 col-lg-12">Photos</div>
						<c:choose>
							<c:when
								test="${empty service.imagePath1 || service.imagePath1 == ''}">
								<div class="no-content col-sm-12 col-md-12 col-lg-12">
									<div class="talk-with-pic">
										<img
											src="https://storage.googleapis.com/knotting/images/misc/coffee.jpg" />
									</div>
									<div class="talk-your-heart">
										<div class="message">We haven't shared any pictures. We
											believe in explaining our work to you in person. Just a call
											away. Let's sit over coffee and plan your wedding.</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="pic-grid-box col-sm-12 col-md-12 col-lg-12">
									<c:forEach var="index" begin="1" end="20" step="1">
										<form:hidden path="imagePath${index}" class="source-image"
											data-index="${index}" />
										<form:hidden path="imagePath${index}Thumbnail"
											class="thumbnail-image" data-index="${index}" />
									</c:forEach>
									<script>
										loadImages('.pic-grid-box');
									</script>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="cities-box contributors">
						<div id="vendor-service-reviews-box"
							class="content-section-title col-sm-12 col-md-12 col-lg-12">Cities</div>
						<div class="no-content col-sm-12 col-md-12 col-lg-12">
							<div class="talk-with-pic">
								<img
									src="https://storage.googleapis.com/knotting/images/misc/map.jpg" />
							</div>
							<div class="talk-your-heart">
								<div class="message">
									<c:set var="areaarray"
										value="${fn:split(service.memberEntries.additionalAreasServiced, ',')}" />
									<c:set var="cityCount" value="${fn:length(areaarray)}" />
									<c:choose>
										<c:when test="${cityCount == 1}">
										Bookings open for ${areaarray[0]}<br>
											<br>
									We take bookings exclusively for one city. If you feel your
									venue is nearby, but in a different city, do give us a call.
										</c:when>
										<c:otherwise>
									We take bookings for the following cities - 
											<c:forEach var="area" items="${areaarray}" varStatus="loop">${area}<c:choose>
													<c:when test="${loop.index == (cityCount - 2)}"> and </c:when>
													<c:when test="${loop.index < (cityCount - 1)}">, </c:when>
												</c:choose>
											</c:forEach>
											<br>
											<br>If you feel your
									venue is nearby, but in a different city, do give us a call.
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
					<div class="reviews-box contributors">
						<div id="vendor-service-reviews-box"
							class="content-section-title col-sm-12 col-md-12 col-lg-12">Reviews</div>
						<div class="no-content col-sm-12 col-md-12 col-lg-12">
							<div class="talk-with-pic">
								<img
									src="https://storage.googleapis.com/knotting/images/misc/typewriter.jpg" />
							</div>
							<div class="talk-your-heart">
								<div class="message">This feature isn't available yet.
									Share us your email address, we'll inform you when this feature
									is available.</div>
								<div class="share-box">
									<div class="field">
										<input type="text" placeholder="email address" />
									</div>
									<div class="click">
										<input type="button" class="ease-element" value="notify" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>