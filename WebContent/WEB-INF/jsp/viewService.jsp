<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div
	class="knotting-view-service-screens col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<form:form method="post" modelAttribute="servicesModel">
		<div
			class="hidden-bg-container view-service-hiddenbg col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div
				class="background-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<c:set var="bgImage" value="0" />
				<c:if test="${not empty servicesModel.servicesRecords}">
					<c:set value="${servicesModel.servicesRecords.toArray()}"
						var="servicesRecords" />
					<c:set var="bgImage" value="${servicesRecords[0].imagePath1}" />
				</c:if>
				<c:if test="${empty bgImage || bgImage == '0' || bgImage == ''}">
					<c:set var="bgImage"
						value="https://storage.googleapis.com/knotting/images/misc/hidden-background-web.jpg" />
				</c:if>
				<div
					class="hidden-background col-xs-12 col-sm-12 col-md-12 col-lg-12"
					style="background-image: url('${bgImage}')"></div>
				<div class="hidden-gradient col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
			</div>
			<div
				class="content-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<tiles:insertAttribute name="header" />
				<div
					class="profile-header-container bg-content-header-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="title col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="skew-container skew green-gradient">${servicesModel.name}</div>
					</div>
					<div class="days col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<c:choose>
							<c:when test="${servicesModel.daysSinceCreation != 0}">
								<div class="skew-container skew">joined
									${servicesModel.daysSinceCreation} days ago</div>
							</c:when>
							<c:otherwise>
								<div class="skew-container skew">joined today</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="rating-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="stars col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<span class="icon icon-nostar"></span> <span
								class="icon icon-nostar"></span> <span class="icon icon-nostar"></span>
							<span class="icon icon-nostar"></span> <span
								class="icon icon-nostar"></span>
						</div>
						<div class="detail col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="skew-container skew">yet to be rated</div>
						</div>
					</div>
				</div>
				<div
					class="profile-note-container info-note-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
					<div class="content col-xs-12 col-sm-12 col-md-10 col-lg-10">
						<c:choose>
							<c:when test="${servicesModel.emergencyRequestAccepted == 'Y'}">
								<div class="note-title col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="skew-container skew">${emergencyYes.configField1}</div>
								</div>
								<div class="note-detail hidden-xs hidden-sm col-md-12 col-lg-12">${emergencyYes.configField2}</div>
								<div class="note-detail col-xs-12 col-sm-12 hidden-md hidden-lg">${emergencyYes.configField3}</div>
							</c:when>
							<c:otherwise>
								<div class="note-title col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="skew-container skew">${emergencyNo.configField1}</div>
								</div>
								<div class="note-detail hidden-xs hidden-sm col-md-12 col-lg-12">${emergencyNo.configField2}</div>
								<div class="note-detail col-xs-12 col-sm-12 hidden-md hidden-lg">${emergencyNo.configField3}</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
				</div>
				<div
					class="view-service-basics-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div
						class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div
							class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="skew-container skew">
								<span class="orange-gradient">basic details</span>
							</div>
						</div>
						<div
							class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="skew-container skew">BASICS</div>
						</div>
					</div>
					<div
						class="basics-section-box view-service-basics-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="basics-box">
							<div class="basics-box-title">
								<span class="icon icon-account"></span>contact details
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">phone number(s)</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									${servicesModel.phone1}
									<c:if test="${not empty servicesModel.phone2}"> / ${servicesModel.phone2}</c:if>
									<c:if test="${not empty servicesModel.phone3}"> / ${servicesModel.phone3}</c:if>
									<c:if test="${not empty servicesModel.phone4}"> / ${servicesModel.phone4}</c:if>
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">email address</div>
								</div>
								<div class="field col-md-12 col-lg-12">${servicesModel.emailAddress}</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">address</div>
								</div>
								<div class="field col-md-12 col-lg-12">${servicesModel.locationAddress}</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">emergency request phone</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<c:choose>
										<c:when
											test="${servicesModel.emergencyRequestAccepted == 'Y'}">
								${servicesModel.emergencyPhoneNumber}</c:when>
										<c:otherwise>
											<span class="emergency-no">doesn't accept</span> emergency requests
									</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="basics-box">
							<div class="basics-box-title">
								<span class="icon icon-emergency"></span>social links
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">website</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<c:choose>
										<c:when test="${not empty servicesModel.websiteUrl}">
									${servicesModel.websiteUrl}
									</c:when>
										<c:otherwise>
											<span class="emergency-no">no website</span> url specified
									</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">facebook</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<c:choose>
										<c:when test="${not empty servicesModel.facebookUrl}">
									${servicesModel.facebookUrl}
									</c:when>
										<c:otherwise>
											<span class="emergency-no">no facebook</span> profile specified
									</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">twitter</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<c:choose>
										<c:when test="${not empty servicesModel.twitterUrl}">
									${servicesModel.twitterUrl}
									</c:when>
										<c:otherwise>
											<span class="emergency-no">no twitter</span> handle specified
									</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">instagram</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<c:choose>
										<c:when test="${not empty servicesModel.instagramUrl}">
									${servicesModel.instagramUrl}
									</c:when>
										<c:otherwise>
											<span class="emergency-no">no insta</span> page specified
									</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">youtube</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<c:choose>
										<c:when test="${not empty servicesModel.youtubeUrl}">
									${servicesModel.youtubeUrl}
									</c:when>
										<c:otherwise>
											<span class="emergency-no">no youtube</span> profile specified
									</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">google+</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<c:choose>
										<c:when test="${not empty servicesModel.googleplusUrl}">
									${servicesModel.googleplusUrl}
									</c:when>
										<c:otherwise>
											<span class="emergency-no">no google+</span> profile specified
									</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="basics-box">
							<div class="basics-box-title">
								<span class="icon icon-subscription"></span>subscription details
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">subscription term</div>
								</div>
								<div class="field col-md-12 col-lg-12">${servicesModel.subscriptionTerm}
									year</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">amount for current term</div>
								</div>
								<div class="field col-md-12 col-lg-12">${servicesModel.subscriptionAmount}
									INR</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">term expiry date</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<fmt:formatDate pattern="MMM dd, yyyy"
										value="${servicesModel.subscriptionExpiry}" />
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">payment transaction
										id(s)</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<c:forEach var="transaction"
										items="${servicesModel.transactions}">
										<c:if test="${transaction.paymentStatus == 'success'}">
										${transaction.transactionId}
									</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
					<div
						class="basics-section-box view-service-basics-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="basics-box">
							<div
								class="basics-box-title col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-doc"></span>proof of establishment
							</div>
							<div
								class="basics-box-explain col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="skew-container skew">document submitted as a
									proof</div>
							</div>
							<a href="${servicesModel.documentPath}" data-lightbox="doc-proof">
								<div
									class="picture-box ease-element col-xs-12 col-sm-12 col-md-12 col-lg-12"
									style="background-image: url('${servicesModel.documentPath}')"></div>
							</a>
						</div>
						<div class="basics-box">
							<div class="basics-box-title">
								<span class="icon icon-areas"></span>cities you serve
							</div>
							<div class="basics-box-explain">
								<div class="skew-container skew">you have agreed to serve
									the following cities</div>
							</div>
							<div class="scroll-box">
								<c:set var="areaarray"
									value="${fn:split(servicesModel.additionalAreasServiced, ',')}" />
								<c:forEach var="area" items="${areaarray}">
									<div class="tag">
										<div>${area}</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<c:set var="viewCount" value="0" />
						<c:set var="shareCount" value="0" />
						<c:set var="infoCount" value="0" />
						<div class="basics-box">
							<div class="basics-box-title">
								<span class="icon icon-view"></span>view details
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">views so far</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<fmt:formatNumber pattern="00000" value="${viewCount}" />
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">shares so far</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<fmt:formatNumber pattern="00000" value="${shareCount}" />
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">we've informed you</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<fmt:formatNumber pattern="00000" value="${infoCount}" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div
			class="view-service-detail-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div
				class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div
					class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">
						<span class="orange-gradient">service details</span>
					</div>
				</div>
				<div
					class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">DETAILS</div>
				</div>
			</div>
			<div
				class="view-service-item-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="col-xs-1 col-sm-1 col-md-2 col-lg-2"></div>
				<div class="content col-xs-10 col-sm-10 col-md-8 col-lg-8">
					<c:if test="${fn:length(servicesModel.servicesRecords) > 1}">
						<div
							class="service-list-header-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<c:forEach var="serviceRecord"
								items="${servicesModel.servicesRecords}">
								<input type="button" value="${serviceRecord.services.name}"
									data-call="${serviceRecord.memberServiceId}" />
							</c:forEach>
						</div>
					</c:if>
					<div
						class="service-list-answers-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<c:forEach var="serviceRecord"
							items="${servicesModel.servicesRecords}" varStatus="loop">
							<c:choose>
								<c:when test="${loop.index == 0}">
									<div
										class="service-list-answers-box selected col-xs-12 col-sm-12 col-md-12 col-lg-12"
										data-section="${serviceRecord.memberServiceId}">
								</c:when>
								<c:otherwise>
									<div
										class="service-list-answers-box col-xs-12 col-sm-12 col-md-12 col-lg-12"
										data-section="${serviceRecord.memberServiceId}">
								</c:otherwise>
							</c:choose>
							<div class="service-answers">
								<div class="basics-box-title">
									<span class="icon icon-servicesdetails"></span>${serviceRecord.services.name}
									details
								</div>
								<div class="scroll-box">
									<c:forEach var="serviceDetail" items="${servicesQuestions}" varStatus="field">
										<c:if
											test="${serviceDetail.services.servicesId == serviceRecord.services.servicesId}">
											<div class="detail-box"
												data-conditionfield="${serviceDetail.serviceDetailPreConditionField}"
												data-conditionanswer="${serviceDetail.serviceDetailPreConditionAnswer}"
												data-initialstate="${serviceDetail.serviceDetailInitialState}"
												data-fieldtype="${serviceDetail.serviceDetailType}">
												<div class="detail-question">
													${serviceDetail.serviceDetailQuestion}</div>
												<div class="detail-answer">
													<form:input id="${serviceDetail.serviceDetailFieldName}" data-fieldidentifier="${field.index}:${serviceDetail.serviceDetailFieldName}"
														path="servicesRecords[${loop.index}].${serviceDetail.serviceDetailFieldName}"
														disabled="true" />
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
								<script type="text/javascript">
								prepareServiceAnswerSet();
								</script>
							</div>
							<div class="service-images">
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath1}">
										<a href="${serviceRecord.imagePath1}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath1Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath2}">
										<a href="${serviceRecord.imagePath2}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath2Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath3}">
										<a href="${serviceRecord.imagePath3}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath3Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath4}">
										<a href="${serviceRecord.imagePath4}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath4Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath5}">
										<a href="${serviceRecord.imagePath5}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath5Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath6}">
										<a href="${serviceRecord.imagePath6}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath6Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath7}">
										<a href="${serviceRecord.imagePath7}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath7Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath8}">
										<a href="${serviceRecord.imagePath8}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath8Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath9}">
										<a href="${serviceRecord.imagePath9}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath9Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath10}">
										<a href="${serviceRecord.imagePath10}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath10Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath11}">
										<a href="${serviceRecord.imagePath11}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath11Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath12}">
										<a href="${serviceRecord.imagePath12}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath12Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath13}">
										<a href="${serviceRecord.imagePath13}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath13Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath14}">
										<a href="${serviceRecord.imagePath14}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath14Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath15}">
										<a href="${serviceRecord.imagePath15}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath15Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath16}">
										<a href="${serviceRecord.imagePath16}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath16Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath17}">
										<a href="${serviceRecord.imagePath17}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath17Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath18}">
										<a href="${serviceRecord.imagePath18}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath18Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath19}">
										<a href="${serviceRecord.imagePath19}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath19Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${not empty serviceRecord.imagePath20}">
										<a href="${serviceRecord.imagePath20}"
											data-lightbox="service-set-${serviceRecord.memberServiceId}">
											<div class="picture-box ease-element"
												style="background-image: url('${serviceRecord.imagePath20Thumbnail}')"></div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:"><div
												class="picture-box ease-element"></div></a>
									</c:otherwise>
								</c:choose>
							</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-xs-1 col-sm-1 col-md-2 col-lg-2"></div>
		</div>
</div>
</form:form>
</div>