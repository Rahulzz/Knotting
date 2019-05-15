<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div
	class="knotting-edit-service-screens col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<form:form id="servicesForm" method="post"
		modelAttribute="servicesModel" action="./saveServices"
		enctype="multipart/form-data">
		<div
			class="hidden-bg-container edit-service-hiddenbg col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div
				class="background-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="map-container col-sm-12 col-md-12 col-lg-12">
					<div id="edit-services-map"
						class="address-map col-sm-12 col-md-12 col-lg-12"></div>
				</div>
				<div class="hidden-gradient col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
			</div>
			<div
				class="content-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<tiles:insertAttribute name="header" />
				<div
					class="edit-service-container info-note-container bg-content-header-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="title col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="skew-container skew green-gradient">add a new
							service</div>
					</div>
					<div class="note-detail hidden-xs hidden-sm col-md-12 col-lg-12">
						Adding a new service to your profile is a simple 5 stage process.
						Every section is curated to deeply understand the work you do.
						Answering all<br>questions would help the user understand
						your service. Now, let's get started.
					</div>
					<div class="note-detail col-xs-12 col-sm-12 hidden-md hidden-lg">
						Adding a new service to your profile is a simple 5 stage process.
						Every section is curated to deeply understand the work you do.
						Answering all questions would help the user understand your
						service. Now, let's get started.</div>
				</div>
				<div
					class="edit-service-tabs col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="col-xs-1 col-sm-1 col-md-2 col-lg-2"></div>
					<div class="content col-xs-10 col-sm-10 col-md-8 col-lg-8">
						<div
							class="service-tabs-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="service-tab active" data-stage="1">
								<div class="icon icon-add-basic-details"></div>
								<div>
									basic<br>details
								</div>
							</div>
							<div class="service-tab" data-stage="2">
								<div class="icon icon-astronaut-service"></div>
								<div>
									choose the<br>services
								</div>
							</div>
							<div class="service-tab" data-stage="3">
								<div class="icon icon-add-service-details"></div>
								<div>
									service<br>details
								</div>
							</div>
							<div class="service-tab" data-stage="4">
								<div class="icon icon-add-emergency-league"></div>
								<div>
									emergency<br>league
								</div>
							</div>
							<div class="service-tab" data-stage="5">
								<div class="icon icon-astronaut-price"></div>
								<div>
									subscription<br>details
								</div>
							</div>
						</div>
						<div
							class="service-scroller-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="scroller-tab"></div>
						</div>
					</div>
					<div class="col-xs-1 col-sm-1 col-md-2 col-lg-2"></div>
				</div>
			</div>
		</div>
		<div
			class="services-section-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="col-xs-1 col-sm-1 col-md-2 col-lg-2"></div>
			<div class="content col-xs-10 col-sm-10 col-md-8 col-lg-8">
				<div class="services-section col-sm-12 col-md-12 col-lg-12"
					data-stage="1">
					<div class="edit-decision-box col-sm-12 col-md-12 col-lg-12">
						<div class="section-detail">Kindly fill all the mandatory
							fields, cities you serve and upload your estabishment document.
							Please fill in actuals. You will be asked to verify your primary
							phone number.</div>
						<div class="decision-option">
							<input type="button"
								class="edit-service-back services-section-button ease-element"
								value="go back a step" data-page="" disabled="disabled" /><input
								type="button" id="vendor-entry-stage-1-submit"
								class="services-section-button ease-element"
								value="proceed to next" />
						</div>
					</div>
					<div
						class="multi-basics-section-box view-service-basics-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="multi-basics-box">
							<div class="basics-box">
								<div class="basics-box-title">
									<span class="icon icon-account"></span>contact details
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">
											name<span class="mandatory"> *</span>
										</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-name"
											path="memberEntriesStaging.name" type="text"
											placeholder="enter a name for your service" />
										<form:hidden id="edit-services-entry-id"
											path="memberEntriesStaging.memberEntryId" />
										<form:hidden name="services.servicesId"
											path="memberEntriesStaging.createdDate" />
										<form:hidden name="services.servicesId"
											path="memberEntriesStaging.createdBy" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">email address</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-email"
											path="memberEntriesStaging.emailAddress" type="text"
											placeholder="enter your email address" />
										<form:hidden path="memberEntriesStaging.emailVerified" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">
											address<span class="mandatory"> *</span>
										</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-address"
											path="memberEntriesStaging.locationAddress" type="text"
											placeholder="enter your postal address" />
										<form:hidden id="edit-services-position"
											path="memberEntriesStaging.locationPoint" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">
											your primary phone number<span class="mandatory"> *</span>
										</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-phone1"
											path="memberEntriesStaging.phone1" type="text"
											placeholder="ex: 04412345678 or 9876543210"
											class="onlyNumbers lengthEleven"
											data-prevvalue="${servicesModel.memberEntriesStaging.phone1}" />
										<c:choose>
											<c:when
												test="${not empty servicesModel.memberEntriesStaging.phone1 && servicesModel.memberEntriesStaging.phone1verified == 'Y'}">
												<form:hidden id="edit-services-phone1-verify"
													path="memberEntriesStaging.phone1verified"
													data-number="${servicesModel.memberEntriesStaging.phone1}"
													data-prevvalue="${servicesModel.memberEntriesStaging.phone1verified}" />
											</c:when>
											<c:otherwise>
												<form:hidden id="edit-services-phone1-verify"
													path="memberEntriesStaging.phone1verified" data-number=""
													data-prevvalue="${servicesModel.memberEntriesStaging.phone1verified}" />
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">additional phone
											numbers</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-phone2"
											path="memberEntriesStaging.phone2" type="text"
											placeholder="ex: 04412345678 or 9876543210"
											class="onlyNumbers lengthEleven" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">additional phone
											numbers</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-phone3"
											path="memberEntriesStaging.phone3" type="text"
											placeholder="ex: 04412345678 or 9876543210"
											class="onlyNumbers lengthEleven" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">additional phone
											numbers</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-phone4"
											path="memberEntriesStaging.phone4" type="text"
											placeholder="ex: 04412345678 or 9876543210"
											class="onlyNumbers lengthEleven" />
									</div>
								</div>
							</div>
						</div>
						<div class="multi-basics-box">
							<div class="basics-box">
								<div class="basics-box-title">
									<span class="icon icon-emergency"></span>social links
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">website</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-websiteUrl"
											path="memberEntriesStaging.websiteUrl" type="text"
											placeholder="enter your website url" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">facebook</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-facebookUrl"
											path="memberEntriesStaging.facebookUrl" type="text"
											placeholder="enter your facebook url" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">twitter</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-twitterUrl"
											path="memberEntriesStaging.twitterUrl" type="text"
											placeholder="enter your twitter url" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">instagram</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-instagramUrl"
											path="memberEntriesStaging.instagramUrl" type="text"
											placeholder="enter your instagram url" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">youtube</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-youtubeUrl"
											path="memberEntriesStaging.youtubeUrl" type="text"
											placeholder="enter your youtube url" />
									</div>
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">
										<div class="skew-container skew">google plus</div>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:input id="edit-services-googleplusUrl"
											path="memberEntriesStaging.googleplusUrl" type="text"
											placeholder="enter your google+ url" />
									</div>
								</div>
							</div>
						</div>
						<div class="multi-basics-box">
							<div class="basics-box">
								<div class="basics-box-title">
									<span class="icon icon-doc"></span>proof of establishment
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">kindly upload your
										registration document or PAN card(company/individual). This document would be
										manually verified by our team. This helps us reduce false
										entries.</div>
									<c:choose>
										<c:when
											test="${not empty servicesModel.memberEntriesStaging.documentPath}">
											<div id="doc-proof-file-name-preview"
												class="file-details uploaded ease-element col-sm-12 col-md-12 col-lg-12">1
												file selected</div>
										</c:when>
										<c:otherwise>
											<div id="doc-proof-file-name-preview"
												class="file-details ease-element col-sm-12 col-md-12 col-lg-12">no
												image uploaded</div>
										</c:otherwise>
									</c:choose>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<form:hidden path="memberEntriesStaging.documentPath" />
										<div
											class="image-upload-buttons col-sm-12 col-md-12 col-lg-12">
											<div class="buttons col-sm-12 col-md-12 col-lg-12">
												<div class="button-container col-sm-12 col-md-12 col-lg-12">
													<div class="button-file-show">browse image</div>
													<div class="button-file-hide">
														<input id="file-doc-proof" class="upload-proof-image"
															type="file" accept=".jpg,.jpeg,.png"
															name="memberEntriesStaging.multipartFile" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="basics-box">
								<div class="basics-box-title">
									<span class="icon icon-areas"></span>cities you serve
								</div>
								<div
									class="basics-field-container col-sm-12 col-md-12 col-lg-12">
									<div class="title col-md-12 col-lg-12">choose the list of
										cities where you would be able to service. These are the
										cities in which your details would be shown. You can add
										multiple cities to your list.</div>
									<div id="edit-service-selected-cities"
										class="cities-list col-sm-12 col-md-12 col-lg-12">
										<c:if
											test="${not empty servicesModel.memberEntriesStaging.additionalAreasServiced}">
											<c:set var="cities"
												value="${fn:split(servicesModel.memberEntriesStaging.additionalAreasServiced, ',')}" />
											<c:forEach var="city" items="${cities}">
												<div class="tag" data-tagvalue="${city}" data-tagtype="city">
													<div>
														${city}&nbsp;&nbsp;&nbsp;&nbsp;<span
															onclick="javascript:removeCity(this);"
															class="icon icon-close"></span>
													</div>
												</div>
											</c:forEach>
										</c:if>
									</div>
									<div class="field col-sm-12 col-md-12 col-lg-12">
										<input id="search-for-city-name" autocomplete="off"
											data-country="ind" placeholder="add a city" />
										<form:hidden id="edit-cities-serviced"
											path="memberEntriesStaging.additionalAreasServiced" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="services-section col-sm-12 col-md-12 col-lg-12"
					data-stage="2">
					<div class="edit-decision-box col-sm-12 col-md-12 col-lg-12">
						<div class="section-detail">Click on the services you wish
							to add to your profile. Details on the service will be asked in
							the next stage. A minimum of one service needs to be selected and
							every service has a subscription fee. To view the rate card
							kindly click the rate card option in the menu.</div>
						<div class="decision-option">
							<input type="button"
								class="edit-service-back services-section-button ease-element"
								value="go back a step" data-page="1" /><input type="button"
								id="vendor-entry-stage-2-submit"
								class="services-section-button ease-element"
								value="proceed to next" />
						</div>
					</div>
					<div
						class="service-options-container col-sm-12 col-md-12 col-lg-12">
						<c:choose>
							<c:when
								test="${not empty servicesModel.memberEntriesStaging.servicesRecords}">
								<c:forEach var="offeredService" items="${offeredServices}">
									<c:set var="selected" value="false" />
									<c:forEach var="selectedService"
										items="${servicesModel.memberEntriesStaging.servicesRecords}">
										<c:if
											test="${offeredService.servicesId == selectedService.services.servicesId}">
											<c:set var="selected" value="true" />
										</c:if>
									</c:forEach>
									<c:choose>
										<c:when test="${selected == 'true'}">
											<div class="service-option selected ease-element"
												data-id="${offeredService.servicesId}"
												data-name="${offeredService.name}" data-age="old">
												<div class="service-image">
													<img
														src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
														class="knotting-menu-image-${offeredService.serviceCode}" />
												</div>
												<div class="service-title">${offeredService.name}</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="service-option ease-element"
												data-id="${offeredService.servicesId}"
												data-name="${offeredService.name}" data-age="new">
												<div class="service-image">
													<img
														src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
														class="knotting-menu-image-${offeredService.serviceCode}" />
												</div>
												<div class="service-title">${offeredService.name}</div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="offeredService" items="${offeredServices}">
									<div class="service-option ease-element"
										data-id="${offeredService.servicesId}"
										data-name="${offeredService.name}" data-age="new">
										<div class="service-image">
											<img
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
												class="knotting-menu-image-${offeredService.serviceCode}" />
										</div>
										<div class="service-title">${offeredService.name}</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="services-section col-sm-12 col-md-12 col-lg-12"
					data-stage="3">
					<div class="edit-decision-box col-sm-12 col-md-12 col-lg-12">
						<div class="section-detail">Kindly answer the list of
							service related questions to help us understand your profession.
							This would help us show the right vendor to the user. You can
							upload a maximum of 20 pictures. Each file shouldn't be more than
							10MB.</div>
						<div class="decision-option">
							<input type="button"
								class="edit-service-back services-section-button ease-element"
								value="go back a step" data-page="2" /> <input type="button"
								id="vendor-entry-stage-3-submit"
								class="services-section-button ease-element"
								value="proceed to next" />
						</div>
					</div>
					<div
						class="edit-service-detail-container col-sm-12 col-md-12 col-lg-12">
						<div
							class="edit-service-detail-tabs col-sm-12 col-md-12 col-lg-12"></div>
						<div
							class="edit-service-detail-content col-sm-12 col-md-12 col-lg-12"></div>
					</div>
				</div>
				<div class="services-section col-sm-12 col-md-12 col-lg-12"
					data-stage="4">
					<div class="edit-decision-box col-sm-12 col-md-12 col-lg-12">
						<div class="section-detail">We categorize Vendors based on
							their emergency league enrollment. An urgent request from a user
							will always be sent to the emergency league members. Urgency
							requires priority.</div>
						<div class="decision-option">
							<input type="button"
								class="edit-service-back services-section-button ease-element"
								value="go back a step" data-page="3" /> <input type="button"
								id="vendor-entry-stage-4-submit"
								class="services-section-button ease-element"
								value="proceed to next" />
						</div>
					</div>
					<form:hidden id="edit-service-emrgncy-accptd"
						path="memberEntriesStaging.emergencyRequestAccepted" />
					<img
						src="https://storage.googleapis.com/knotting/images/ads-banner/el-poster-landscape-1.jpg"
						class="el-poster hidden-xs hidden-sm" />
					<div
						class="explanation-box hidden-xs hidden-sm col-md-12 col-lg-12">
						Do you have the team to accept emergency requests? If yes, sign up
						for the Emergency League.<br>Your details will be shown to
						users who come with an urgent request.
					</div>
					<div
						class="explanation-box col-xs-12 col-sm-12 hidden-md hidden-lg">
						Do you have the team to accept emergency requests? If yes, sign up
						for the Emergency League. Your details will be shown to users who
						come with an urgent request.</div>
					<div class="el-choice-box col-sm-12 col-md-12 col-lg-12">
						<c:choose>
							<c:when
								test="${servicesModel.memberEntriesStaging.emergencyRequestAccepted == 'N'}">
								<input type="button" value="i cannot join"
									class="ease-element selected" data-value="N" />
							</c:when>
							<c:otherwise>
								<input type="button" value="i cannot join" class="ease-element"
									data-value="N" />
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when
								test="${servicesModel.memberEntriesStaging.emergencyRequestAccepted == 'Y'}">
								<input type="button" value="join me in"
									class="ease-element selected" data-value="Y" />
								<form:input id="emergencyNumber"
									path="memberEntriesStaging.emergencyPhoneNumber" type="text"
									placeholder="emergency contact number"
									class="onlyNumbers lengthTen"
									data-prevvalue="${servicesModel.memberEntriesStaging.emergencyPhoneNumber}" />
							</c:when>
							<c:otherwise>
								<input type="button" value="join me in" class="ease-element"
									data-value="Y" />
								<form:input id="emergencyNumber"
									path="memberEntriesStaging.emergencyPhoneNumber" type="text"
									placeholder="emergency contact number"
									class="onlyNumbers lengthTen"
									data-prevvalue="${servicesModel.memberEntriesStaging.emergencyPhoneNumber}"
									disabled="true" />
							</c:otherwise>
						</c:choose>
						<form:hidden id="edit-services-emergencyPhoneNumber-verify"
							path="memberEntriesStaging.emergencyPhoneNumberVerified"
							data-number=""
							data-prevvalue="${servicesModel.memberEntriesStaging.emergencyPhoneNumberVerified}" />
					</div>
				</div>
				<div class="services-section col-sm-12 col-md-12 col-lg-12"
					data-stage="5">
					<div class="edit-decision-box col-sm-12 col-md-12 col-lg-12">
						<div class="section-detail">Click the 'proceed to pay'
							button to go ahead with your payment process. Upon successful
							payment your services would be taken up for moderation. To view
							the subscription rates for the available services, kindly click
							the rate card option on the menu.</div>
						<div class="decision-option">
							<input type="button"
								class="edit-service-back services-section-button ease-element"
								value="go back a step" data-page="4" /> <input id="no-money"
								type="button"
								class="services-section-button payment-waived ease-element"
								value="proceed to save" /><input id="grab-money" type="button"
								class="services-section-button payment-reqd ease-element"
								value="proceed to pay" /><input id="just-save-details"
								type="button"
								class="services-section-button payment-not-reqd ease-element"
								value="save changes" />
						</div>
					</div>
					<div class="payment-box col-sm-12 col-md-12 col-lg-12">
						<div class="info-container hidden-md hidden-lg remove-for-web">
							<div
								class="edit-services-list payment-reqd col-sm-12 col-md-12 col-lg-12">
								<div class="section-title col-sm-12 col-md-12 col-lg-12">payment
									options</div>
								<div class="section-explainer col-sm-12 col-md-12 col-lg-12">Choose
									the payment option that best suits you. Kindly do not refresh
									the page once you have clicked on the payment option. We are
									working to add more options to this list.</div>
								<div class="payment-list col-sm-12 col-md-12 col-lg-12">
									<div
										class="list-payment-item ease-element disable_user_selection col-sm-12 col-md-12 col-lg-12"
										data-payment="P">
										<div class="payment-content col-sm-12 col-md-12 col-lg-12">
											<div class="icon icon-payment-online"></div>
											<div class="payment-detail">
												<div class="detail-title">Debit card / Credit card /
													Net banking</div>
												<div class="detail-explainer">You will be redirected
													to PayU Money to complete your payment securely.</div>
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
									<div class="paper-service-list col-sm-12 col-md-12 col-lg-12"></div>
									<div class="tear-paper col-sm-12 col-md-12 col-lg-12">
										<div></div>
									</div>
									<div class="coupon-box col-sm-12 col-md-12 col-lg-12">
										<div
											class="paper-service-header col-sm-12 col-md-12 col-lg-12">
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
											<div
												class="paper-highlight-detail col-sm-7 col-md-7 col-lg-7">
												Total cost</div>
											<div id="paper-total-amount"
												class="paper-highlight-cost col-sm-5 col-md-5 col-lg-5"></div>
										</div>
										<div class="sub-highlight-item col-sm-12 col-md-12 col-lg-12">
											<div
												class="paper-highlight-detail col-sm-7 col-md-7 col-lg-7">
												Coupon discount</div>
											<div id="paper-discount-amount"
												class="paper-highlight-cost col-sm-5 col-md-5 col-lg-5"></div>
										</div>
									</div>
									<div
										class="paper-highlighted-line col-sm-12 col-md-12 col-lg-12">
										<div
											class="paper-service-header col-sm-12 col-md-12 col-lg-12">
											final cost</div>
										<div id="paper-final-cost"
											class="paper-highlighted-value col-sm-12 col-md-12 col-lg-12"></div>
									</div>
									<div class="paper-footer col-sm-12 col-md-12 col-lg-12">
										<div class="icon icon-mail"></div>
										<div class="footer-text"></div>
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
								<div class="section-list col-sm-12 col-md-12 col-lg-12"></div>
							</div>
							<div
								class="edit-services-list payment-reqd col-sm-12 col-md-12 col-lg-12">
								<div class="section-title col-sm-12 col-md-12 col-lg-12">payment
									options</div>
								<div class="section-explainer col-sm-12 col-md-12 col-lg-12">Choose
									the payment option that best suits you. Kindly do not refresh
									the page once you have clicked on the payment option. We are
									working to add more options to this list.</div>
								<div class="payment-list col-sm-12 col-md-12 col-lg-12">
									<div
										class="list-payment-item ease-element disable_user_selection col-sm-12 col-md-12 col-lg-12"
										data-payment="P">
										<div class="payment-content col-sm-12 col-md-12 col-lg-12">
											<div class="icon icon-payment-online"></div>
											<div class="payment-detail">
												<div class="detail-title">Debit card / Credit card /
													Net banking</div>
												<div class="detail-explainer">You will be redirected
													to PayU Money to complete your payment securely.</div>
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
			</div>
			<div class="col-xs-1 col-sm-1 col-md-2 col-lg-2"></div>
		</div>
		<input id="save_type" type="hidden" name="saveType" value="" />
		<input id="pg_amount" type="hidden" name="amount" value="" />
		<form:hidden id="total_amount" path="total" value="" />
		<form:hidden id="discount_amount" path="discount" value="" />
		<form:hidden id="coupon_id" path="coupon" value="" />
		<input id="executive_number" type="hidden" name="executivePhoneNumber"
			value="" />
	</form:form>
</div>
<div class="vendor-service-answer-container">
	<c:forEach var="selectedService"
		items="${servicesModel.memberEntriesStaging.servicesRecords}">
		<div class="vendor-service-answers"
			data-serviceid="${selectedService.services.servicesId}">
			<input type="text" value="${selectedService.experience}"
				data-name="experience" /> <input type="text"
				value="${selectedService.communitiesServed}"
				data-name="communitiesServed" /> <input type="text"
				value="${selectedService.advancePercentage}"
				data-name="advancePercentage" /> <input type="text"
				value="${selectedService.priceRange}" data-name="priceRange" /> <input
				type="text" value="${selectedService.imagePath1}"
				data-name="imagePath1" /> <input type="text"
				value="${selectedService.imagePath2}" data-name="imagePath2" /> <input
				type="text" value="${selectedService.imagePath3}"
				data-name="imagePath3" /> <input type="text"
				value="${selectedService.imagePath4}" data-name="imagePath4" /> <input
				type="text" value="${selectedService.imagePath5}"
				data-name="imagePath5" /> <input type="text"
				value="${selectedService.imagePath6}" data-name="imagePath6" /> <input
				type="text" value="${selectedService.imagePath7}"
				data-name="imagePath7" /> <input type="text"
				value="${selectedService.imagePath8}" data-name="imagePath8" /> <input
				type="text" value="${selectedService.imagePath9}"
				data-name="imagePath9" /> <input type="text"
				value="${selectedService.imagePath10}" data-name="imagePath10" /> <input
				type="text" value="${selectedService.imagePath11}"
				data-name="imagePath11" /> <input type="text"
				value="${selectedService.imagePath12}" data-name="imagePath12" /> <input
				type="text" value="${selectedService.imagePath13}"
				data-name="imagePath13" /> <input type="text"
				value="${selectedService.imagePath14}" data-name="imagePath14" /> <input
				type="text" value="${selectedService.imagePath15}"
				data-name="imagePath15" /> <input type="text"
				value="${selectedService.imagePath16}" data-name="imagePath16" /> <input
				type="text" value="${selectedService.imagePath17}"
				data-name="imagePath17" /> <input type="text"
				value="${selectedService.imagePath18}" data-name="imagePath18" /> <input
				type="text" value="${selectedService.imagePath19}"
				data-name="imagePath19" /> <input type="text"
				value="${selectedService.imagePath20}" data-name="imagePath20" /> <input
				type="text" value="${selectedService.servicesCriteria1}"
				data-name="servicesCriteria1" /> <input type="text"
				value="${selectedService.servicesCriteria2}"
				data-name="servicesCriteria2" /> <input type="text"
				value="${selectedService.servicesCriteria3}"
				data-name="servicesCriteria3" /> <input type="text"
				value="${selectedService.servicesCriteria4}"
				data-name="servicesCriteria4" /> <input type="text"
				value="${selectedService.servicesCriteria5}"
				data-name="servicesCriteria5" /> <input type="text"
				value="${selectedService.servicesCriteria6}"
				data-name="servicesCriteria6" /> <input type="text"
				value="${selectedService.servicesCriteria7}"
				data-name="servicesCriteria7" /> <input type="text"
				value="${selectedService.servicesCriteria8}"
				data-name="servicesCriteria8" /> <input type="text"
				value="${selectedService.servicesCriteria9}"
				data-name="servicesCriteria9" /> <input type="text"
				value="${selectedService.servicesCriteria10}"
				data-name="servicesCriteria10" /> <input type="text"
				value="${selectedService.servicesCriteria11}"
				data-name="servicesCriteria11" /> <input type="text"
				value="${selectedService.servicesCriteria12}"
				data-name="servicesCriteria12" /> <input type="text"
				value="${selectedService.servicesCriteria13}"
				data-name="servicesCriteria13" /> <input type="text"
				value="${selectedService.servicesCriteria14}"
				data-name="servicesCriteria14" /> <input type="text"
				value="${selectedService.servicesCriteria15}"
				data-name="servicesCriteria15" /> <input type="text"
				value="${selectedService.servicesCriteria16}"
				data-name="servicesCriteria16" /> <input type="text"
				value="${selectedService.servicesCriteria17}"
				data-name="servicesCriteria17" /> <input type="text"
				value="${selectedService.servicesCriteria18}"
				data-name="servicesCriteria18" /> <input type="text"
				value="${selectedService.servicesCriteria19}"
				data-name="servicesCriteria19" /> <input type="text"
				value="${selectedService.servicesCriteria20}"
				data-name="servicesCriteria20" /> <input type="text"
				value="${selectedService.servicesCriteria21}"
				data-name="servicesCriteria21" /> <input type="text"
				value="${selectedService.servicesCriteria22}"
				data-name="servicesCriteria22" /> <input type="text"
				value="${selectedService.servicesCriteria23}"
				data-name="servicesCriteria23" /> <input type="text"
				value="${selectedService.servicesCriteria24}"
				data-name="servicesCriteria24" /> <input type="text"
				value="${selectedService.servicesCriteria25}"
				data-name="servicesCriteria25" /> <input type="text"
				value="${selectedService.servicesCriteria26}"
				data-name="servicesCriteria26" /> <input type="text"
				value="${selectedService.servicesCriteria27}"
				data-name="servicesCriteria27" /> <input type="text"
				value="${selectedService.servicesCriteria28}"
				data-name="servicesCriteria28" /> <input type="text"
				value="${selectedService.servicesCriteria29}"
				data-name="servicesCriteria29" /> <input type="text"
				value="${selectedService.servicesCriteria30}"
				data-name="servicesCriteria30" /><input type="text"
				data-name="serviceStatus" value="${selectedService.serviceStatus}" />
			<input type="text" data-name="createdDate"
				value="${selectedService.createdDate}" /> <input type="text"
				data-name="createdBy" value="${selectedService.createdBy}" />
		</div>
	</c:forEach>
</div>
<div class="vendor-service-question-container">
	<c:forEach var="service" items="${offeredServices}">
		<div class="vendor-service-questions"
			data-serviceid="${service.servicesId}">
			<input type="hidden" name="services.servicesId"
				class="modifiable-field" value="${service.servicesId}" />
			<div class="edit-service-answers">
				<div class="basics-box-title">
					<span class="icon icon-servicesdetails"></span>${service.name}
					details
				</div>
				<c:forEach var="serviceDetail" items="${serviceQuestions}"
					varStatus="loop">
					<c:if
						test="${serviceDetail.services.servicesId == service.servicesId}">
						<div
							class="modifiable-field-container block col-sm-12 col-md-12 col-lg-12"
							data-conditionfield="${serviceDetail.serviceDetailPreConditionField}"
							data-conditionanswer="${serviceDetail.serviceDetailPreConditionAnswer}"
							data-initialstate="${serviceDetail.serviceDetailInitialState}"
							data-fieldtype="${serviceDetail.serviceDetailType}">
							<div class="title-text col-md-12 col-lg-12">
								${serviceDetail.serviceDetailQuestion} <span class="mandatory">
									*</span>
							</div>
							<c:set var="answerArray"
								value="${fn:split(serviceDetail.serviceDetailAnswerList, '#')}" />
							<c:if test="${serviceDetail.serviceDetailType == 'dropdown'}">
								<div class="dropdown col-sm-12 col-md-12 col-lg-12">
									<div class="ui inline dropdown">
										<input type="hidden" class="modifiable-field"
											name="${serviceDetail.serviceDetailFieldName}"
											data-name="${serviceDetail.serviceDetailFieldName}" />
										<div class="text">${serviceDetail.serviceDetailPlaceHolder}</div>
										<i class="dropdown icon"></i>
										<div class="menu">
											<c:choose>
												<c:when
													test="${serviceDetail.serviceDetailTooltipRequired == 'Y'}">
													<c:set var="tooltipArray"
														value="${fn:split(serviceDetail.serviceDetailTooltipList, '#')}" />
													<c:forEach var="answer" items="${answerArray}"
														varStatus="loop">
														<div class="item knotting-tooltip"
															data-content="${tooltipArray[loop.index]}"
															data-position="right center">${answer}</div>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach var="answer" items="${answerArray}">
														<div class="item">${answer}</div>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${serviceDetail.serviceDetailType == 'input'}">
								<div class="field col-sm-12 col-md-12 col-lg-12">
									<input name="${serviceDetail.serviceDetailFieldName}"
										class="modifiable-field onlyNumbers ${serviceDetail.serviceDetailFieldValidator}" type="text"
										data-name="${serviceDetail.serviceDetailFieldName}"
										placeholder="${serviceDetail.serviceDetailPlaceHolder}" />
								</div>
							</c:if>
							<c:if test="${serviceDetail.serviceDetailType == 'checkbox'}">
								<c:forEach var="answer" items="${answerArray}">
									<div class="ui checkbox">
										<input name="${serviceDetail.serviceDetailFieldName}"
											data-name="${serviceDetail.serviceDetailFieldName}"
											class="modifiable-field" type="checkbox" value="${answer}">
										<label>${answer}</label>
									</div>
								</c:forEach>
							</c:if>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<div class="edit-service-photos">
				<div class="edit-service-photo-title col-sm-12 col-md-12 col-lg-12">
					<div class="edit-service-photo-count"
						data-service="${service.servicesId}">0/20 images selected</div>
					<div class="edit-service-photo-upload">
						<div class="image-upload-buttons col-sm-12 col-md-12 col-lg-12">
							<div class="buttons col-sm-12 col-md-12 col-lg-12">
								<div class="button-container col-sm-12 col-md-12 col-lg-12">
									<div class="button-file-show">select images</div>
									<div class="button-file-hide">
										<input class="file-service-images"
											data-service="${service.servicesId}" type="file"
											accept=".jpg,.jpeg,.png" name="" multiple="multiple" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div
					class="edit-service-photo-preview-box col-sm-12 col-md-12 col-lg-12"
					data-service="${service.servicesId}"></div>
			</div>
			<input type="hidden" name="serviceStatus" data-name="serviceStatus"
				class="modifiable-field" value="${serviceDetail.serviceStatus}" />
			<input type="hidden" name="createdDate" data-name="createdDate"
				class="modifiable-field" value="${serviceDetail.createdDate}" /> <input
				type="hidden" name="createdBy" data-name="createdBy"
				class="modifiable-field" value="${serviceDetail.createdBy}" />
		</div>
	</c:forEach>
</div>
<div class="server-status-details col-sm-12 col-md-12 col-lg-12">
	<div class="content">
		<div id="load">
			<div>G</div>
			<div>N</div>
			<div>I</div>
			<div>K</div>
			<div>R</div>
			<div>O</div>
			<div>W</div>
		</div>
		<div id="server-status-text">this could take a while, hold on</div>
	</div>
</div>