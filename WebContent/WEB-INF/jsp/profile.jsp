<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div
	class="knotting-profile-screens col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div
		class="hidden-bg-container profile-hiddenbg col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div
			class="background-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<c:set var="bgImage" value="0" />
			<c:choose>
				<c:when
					test="${not empty approvedEntriesListing && not empty approvedEntriesListing[0].servicesRecords}">
					<c:set
						value="${approvedEntriesListing[0].servicesRecords.toArray()}"
						var="servicesRecords" />
					<c:if test="${not empty servicesRecords[0].imagePath1}">
						<c:set var="bgImage" value="${servicesRecords[0].imagePath1}" />
					</c:if>
				</c:when>
				<c:when
					test="${not empty pendingEntriesListing && not empty pendingEntriesListing[0].servicesRecords}">
					<c:set
						value="${pendingEntriesListing[0].servicesRecords.toArray()}"
						var="servicesRecords" />
					<c:if test="${not empty servicesRecords[0].imagePath1}">
						<c:set var="bgImage" value="${servicesRecords[0].imagePath1}" />
					</c:if>
				</c:when>
			</c:choose>
			<c:if test="${bgImage == '0' || bgImage == ''}">
				<c:set var="bgImage"
					value="https://storage.googleapis.com/knotting/images/misc/hidden-background-web.jpg" />
			</c:if>
			<div
				class="hidden-background col-xs-12 col-sm-12 col-md-12 col-lg-12"
				style="background-image: url('${bgImage}')"></div>
			<div class="hidden-gradient col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
		</div>
		<div class="content-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<tiles:insertAttribute name="header" />
			<div
				class="profile-header-container bg-content-header-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="title col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew green-gradient">${memberModel.name}</div>
				</div>
				<div class="days col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">joined
						${memberModel.daysSinceCreation} days ago</div>
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
				class="profile-cards-container cards-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="vendor-card card-3">
					<div class="card-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-count disable_user_selection">
							<span>00000</span>
						</div>
						<div class="card-name disable_user_selection">views</div>
					</div>
					<div class="card-bg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="bg-gradient col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
						<div class="bg-image col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
					</div>
				</div>
				<div class="vendor-card card-5">
					<div class="card-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-count disable_user_selection">
							<span>00000</span>
						</div>
						<div class="card-name disable_user_selection">shares</div>
					</div>
					<div class="card-bg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="bg-gradient col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
						<div class="bg-image col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
					</div>
				</div>
				<div class="vendor-card card-6">
					<div class="card-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-count disable_user_selection">
							<span>00000</span>
						</div>
						<div class="card-name disable_user_selection">info</div>
					</div>
					<div class="card-bg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="bg-gradient col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
						<div class="bg-image col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
					</div>
				</div>
			</div>
			<div
				class="profile-note-container info-note-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
				<div class="content hidden-xs hidden-sm col-md-10 col-lg-10">
					<div class="note-title col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="skew-container skew">${profileNote.configField1}</div>
					</div>
					<div class="note-detail col-xs-12 col-sm-12 col-md-12 col-lg-12">${profileNote.configField2}</div>
				</div>
				<div class="content col-xs-12 col-sm-12 hidden-md hidden-lg">
					<div class="note-title col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="skew-container skew">${profileNote.configField1}</div>
					</div>
					<div class="note-detail col-xs-12 col-sm-12 col-md-12 col-lg-12">${profileNote.configField3}</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
			</div>
			<div
				class="profile-basics-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div
					class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div
						class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="skew-container skew">
							<span class="orange-gradient">action centre</span>
						</div>
					</div>
					<div
						class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="skew-container skew">CONTROL</div>
					</div>
				</div>
				<div
					class="basics-section-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="basics-box">
						<form:form id="profileForm" method="post"
							modelAttribute="memberModel" action="./saveMembersProfileChanges">
							<form:hidden path="memberId" />
							<div class="basics-box-title">
								<span class="icon icon-account"></span>account details
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">
									<div class="skew-container skew">name</div>
								</div>
								<div class="field col-md-12 col-lg-12">
									<form:input path="name" type="text" disabled="true"
										data-prevvalue="${memberModel.name}" />
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">mobile</div>
								<div class="field col-md-12 col-lg-12">
									<form:input path="phoneNumber" type="text"
										class="onlyNumbers lengthTen" disabled="true"
										data-prevvalue="${memberModel.phoneNumber}"
										placeholder="your mobile number" />
									<form:hidden id="profile-phoneVerified" path="phoneVerified"
										data-prevvalue="${memberModel.phoneVerified}" />
								</div>
							</div>
							<div class="basics-field-container col-sm-12 col-md-12 col-lg-12">
								<div class="title col-md-12 col-lg-12">email</div>
								<div class="field col-md-12 col-lg-12">
									<form:input id="profile-email" path="emailId" type="text"
										disabled="true" data-prevvalue="${memberModel.emailId}"
										placeholder="your email address" />
									<form:hidden id="profile-emailVerified" path="emailVerified"
										data-prevvalue="${memberModel.emailVerified}" />
								</div>
							</div>
							<div
								class="buttons basics-button-container col-sm-12 col-md-12 col-lg-12">
								<input type="button"
									class="button ease-element profile-account-changepwd"
									value="change password" /> <input type="button"
									class="button ease-element profile-account-edit" value="edit" />
								<input type="button"
									class="button ease-element profile-account-cancel"
									value="cancel" /> <input type="button"
									class="button ease-element profile-account-save" value="save" />
							</div>
						</form:form>
					</div>
					<div class="basics-box">
						<div class="basics-box-title">
							<span class="icon icon-subscription"></span>upcoming payments
						</div>
						<div class="scroll-box">
							<c:forEach var="upcomingPayment" items="${upcomingPayments}">
								<div
									class="basics-payment-container disable_user_selection ease-element col-sm-12 col-md-12 col-lg-12">
									<div class="line-1 col-sm-12 col-md-12 col-lg-12">
										<span class="left"><fmt:formatDate
												pattern="MMM dd, yyyy"
												value="${upcomingPayment.paymentDate}" /></span><span
											class="right">${upcomingPayment.paymentAmount} INR</span>
									</div>
									<div class="line-2 col-sm-12 col-md-12 col-lg-12">for
										${upcomingPayment.entryName}</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="basics-box">
						<div class="basics-box-title">
							<span class="icon icon-criteriaedit"></span>service status
						</div>
						<div class="scroll-box">
							<c:forEach var="entriesRecord" items="${approvedEntriesListing}">
								<c:forEach var="serviceRecord"
									items="${entriesRecord.servicesRecords}">
									<form id="status${serviceRecord.memberServiceId}"
										action="changeStatus" method="post">
										<input type="hidden" name="serviceId"
											value="${serviceRecord.memberServiceId}" />
										<div
											class="basics-status-container disable_user_selection ease-element col-sm-12 col-md-12 col-lg-12">
											<div class="left">
												<div class="header col-sm-12 col-md-12 col-lg-12">${entriesRecord.name}</div>
												<div class="sub-header col-sm-12 col-md-12 col-lg-12">${serviceRecord.services.name}</div>
											</div>
											<div class="right">
												<div class="ui toggle checkbox">
													<c:choose>
														<c:when test="${serviceRecord.serviceStatus == 'Y'}">
															<input name="status" type="checkbox"
																class="status-change"
																data-id="${serviceRecord.memberServiceId}"
																checked="checked">
														</c:when>
														<c:otherwise>
															<input name="status" type="checkbox"
																class="status-change"
																data-id="${serviceRecord.memberServiceId}">
														</c:otherwise>
													</c:choose>
													<label></label>
												</div>
											</div>
										</div>
									</form>
								</c:forEach>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when
			test="${memberModel.phoneVerified == 'Y' && memberModel.emailVerified == 'Y'}">
			<div
				class="profile-services-container profile-nonbg-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div
					class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div
						class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="skew-container skew">
							<span class="orange-gradient">service desk</span>
						</div>
					</div>
					<div
						class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="skew-container skew">BUSINESS</div>
					</div>
				</div>
				<div
					class="profile-service-item-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="hidden-xs hidden-sm col-md-2 col-lg-2"></div>
					<div class="content col-xs-10 col-sm-10 col-md-8 col-lg-8">
						<div class="service-item add-new-service ease-element">
							<span>+</span><br>
							<div class="skew-container skew">add a</div>
							<br>
							<div class="skew-container skew">new service</div>
						</div>
						<c:forEach var="entriesRecord" items="${approvedEntriesListing}">
							<div class="service-item ease-element">
								<div
									class="item-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div
										class="service-name short-text-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="skew-container skew">${entriesRecord.name}</div>
									</div>
									<c:set var="serviceNames" value="" />
									<c:forEach var="serviceRecord"
										items="${entriesRecord.servicesRecords}">
										<c:choose>
											<c:when test="${not empty serviceNames}">
												<c:set var="serviceNames"
													value="${serviceNames}, ${serviceRecord.services.name}" />
											</c:when>
											<c:otherwise>
												<c:set var="serviceNames"
													value="${serviceRecord.services.name}" />
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<div
										class="service-type short-text-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="skew-container skew">${serviceNames}</div>
									</div>
									<c:set var="entryFound" value="false" />
									<c:forEach var="pendingEntry" items="${pendingEntriesListing}">
										<c:if
											test="${pendingEntry.memberEntryId == entriesRecord.memberEntryId}">
											<c:set var="entryFound" value="true" />
										</c:if>
									</c:forEach>
									<c:set var="entryStatus" value="" />
									<c:if test="${entryFound == 'true'}">
										<c:set var="entryStatus" value="changes awaiting approval" />
									</c:if>
									<div
										class="service-action-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="service-action-item">
											<a
												href="viewApprovedService?serviceid=${entriesRecord.memberEntryId}"><span
												class="icon icon-view ease-element"></span></a>
										</div>
										<c:choose>
											<c:when test="${not empty entryStatus}">
												<div class="service-action-item disabled">
													<span class="icon icon-edit ease-element"></span>
												</div>
											</c:when>
											<c:otherwise>
												<div class="service-action-item">
													<a
														href="editApprovedService?serviceid=${entriesRecord.memberEntryId}"><span
														class="icon icon-edit ease-element"></span></a>
												</div>
											</c:otherwise>
										</c:choose>
									</div>
									<c:if test="${not empty entryStatus}">
										<div
											class="service-info-tag-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div
												class="service-info-tag col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<div class="skew-container skew">${entryStatus}</div>
											</div>
										</div>
									</c:if>
								</div>
								<c:choose>
									<c:when test="${not empty entriesRecord.servicesRecords}">
										<c:set value="${entriesRecord.servicesRecords.toArray()}"
											var="servicesRecords" />
										<c:choose>
											<c:when test="${not empty servicesRecords[0].imagePath1}">
												<c:set var="serviceBg"
													value="${servicesRecords[0].imagePath1}" />
												<div
													class="item-bg ease-element col-xs-12 col-sm-12 col-md-12 col-lg-12"
													style="background-image: url('${serviceBg}')"></div>
											</c:when>
											<c:otherwise>
												<div class="item-bg col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<div class="item-bg col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
									</c:otherwise>
								</c:choose>
							</div>
						</c:forEach>
						<c:forEach var="entriesRecord" items="${pendingEntriesListing}">
							<c:set var="entryFound" value="false" />
							<c:forEach var="aprovedEntry" items="${approvedEntriesListing}">
								<c:if
									test="${aprovedEntry.memberEntryId == entriesRecord.memberEntryId}">
									<c:set var="entryFound" value="true" />
								</c:if>
							</c:forEach>
							<c:if test="${entryFound == 'false'}">
								<div class="service-item ease-element">
									<div
										class="item-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div
											class="service-name short-text-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="skew-container skew">${entriesRecord.name}</div>
										</div>
										<c:set var="serviceNames" value="" />
										<c:forEach var="serviceRecord"
											items="${entriesRecord.servicesRecords}">
											<c:choose>
												<c:when test="${not empty serviceNames}">
													<c:set var="serviceNames"
														value="${serviceNames}, ${serviceRecord.services.name}" />
												</c:when>
												<c:otherwise>
													<c:set var="serviceNames"
														value="${serviceRecord.services.name}" />
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<div
											class="service-type short-text-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="skew-container skew">${serviceNames}</div>
										</div>
										<div
											class="service-action-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="service-action-item">
												<a
													href="viewPendingService?serviceid=${entriesRecord.memberEntryStagingId}"><span
													class="icon icon-view ease-element"></span></a>
											</div>
											<div class="service-action-item">
												<a
													href="editPendingService?serviceid=${entriesRecord.memberEntryStagingId}"><span
													class="icon icon-edit ease-element"></span></a>
											</div>
										</div>
										<div
											class="service-info-tag-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div
												class="service-info-tag col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<div class="skew-container skew">yet to be approved</div>
											</div>
										</div>
									</div>
									<c:choose>
										<c:when test="${not empty entriesRecord.servicesRecords}">
											<c:set value="${entriesRecord.servicesRecords.toArray()}"
												var="servicesRecords" />
											<c:choose>
												<c:when test="${not empty servicesRecords[0].imagePath1}">
													<c:set var="serviceBg"
														value="${servicesRecords[0].imagePath1}" />
													<div
														class="item-bg ease-element col-xs-12 col-sm-12 col-md-12 col-lg-12"
														style="background-image: url('${serviceBg}')"></div>
												</c:when>
												<c:otherwise>
													<div
														class="item-bg col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<div class="item-bg col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>
										</c:otherwise>
									</c:choose>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<div class="hidden-xs hidden-sm col-md-2 col-lg-2"></div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div
				class="verification_pending profile-nonbg-container info-note-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="note-title col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">
						user trusts&nbsp;<span>authentic information</span>
					</div>
				</div>
				<div class="note-detail hidden-xs hidden-sm col-md-12 col-lg-12">
					You'll be able to add your services once you have completed a few
					tasks.<br> It isn't as tough as doing Yoga.
				</div>
				<div class="note-detail col-xs-12 col-sm-12 hidden-md hidden-lg">
					You'll be able to add your services once you have completed a few
					tasks. It isn't as tough as doing Yoga.
				</div>
				<div
					class="image_container hidden-sm col-md-12 col-lg-12 align-center">
					<img
						src="https://storage.googleapis.com/knotting/images/misc/yoga.png" />
				</div>
				<div
					class="image_container col-sm-12 hidden-md hidden-lg align-center">
					<img
						src="https://storage.googleapis.com/knotting/images/misc/yoga_mobile.png" />
				</div>
				<div class="simple-text col-xs-12 col-sm-12 col-md-12 col-lg-12">All
					that you'll have to do is</div>
				<div
					class="buttons-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<c:if
						test="${empty memberModel.phoneNumber || memberModel.phoneVerified == 'N'}">

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class=" col-xs-3 col-xs-3 col-md-5 col-lg-5"></div>
							<div class="buttons col-xs-6 col-sm-6 col-md-2 col-lg-2">
								<input id="add_mobile_number" type="button"
									value="add a mobile number" class="ease-element" />
							</div>
							<div class=" col-xs-3 col-xs-3 col-md-5 col-lg-5"></div>
						</div>
					</c:if>
					<c:if
						test="${not empty memberModel.emailId && memberModel.emailVerified == 'N'}">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class=" col-xs-3 col-xs-3 col-md-5 col-lg-5"></div>
							<div class="buttons col-xs-6 col-sm-6 col-md-2 col-lg-2">
								<input type="button" value="verify email address"
									class="verify_email ease-element" />
							</div>
							<div class=" col-xs-3 col-xs-3 col-md-5 col-lg-5"></div>
						</div>
					</c:if>
					<c:if test="${empty memberModel.emailId}">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class=" col-xs-3 col-xs-3 col-md-5 col-lg-5"></div>
							<div class="buttons col-xs-6 col-sm-6 col-md-2 col-lg-2">
								<input type="button" value="add an email address"
									class="add_email ease-element" />
							</div>
							<div class=" col-xs-3 col-xs-3 col-md-5 col-lg-5"></div>
						</div>
					</c:if>
				</div>
				<div class="simple-text hidden-xs hidden-sm col-md-12 col-lg-12">we
					people at knotting are scared of unverified mobile and email
					addresses</div>
				<div class="simple-text col-xs-12 col-sm-12 hidden-md hidden-lg">we
					people at knotting are scared of<br>unverified mobile and email
					addresses</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>