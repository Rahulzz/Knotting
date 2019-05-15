<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div
	class="support-function-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div
		class="support-function-header col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
		<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
			<div class="skew-container skew green-gradient">entry details</div>
		</div>
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
	</div>
	<div
		class="support-function-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
		<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
			<input id="entry-id" type="hidden" value="${pendingEntry.memberEntryStagingId}" />
			<table>
				<tr>
					<td>Created by:</td>
					<td>${pendingEntry.member.name}</td>
				</tr>
				<tr>
					<td>Service Name:</td>
					<td>${pendingEntry.name}</td>
				</tr>
				<tr>
					<td>Address:</td>
					<td>${pendingEntry.locationAddress}</td>
				</tr>
				<tr>
					<td>GPS location:</td>
					<td>${pendingEntry.locationPoint}</td>
				</tr>
				<tr>
					<td>Phone 1:</td>
					<td>${pendingEntry.phone1}</td>
				</tr>
				<tr>
					<td>Phone 1 verified:</td>
					<td>${pendingEntry.phone1verified}</td>
				</tr>
				<tr>
					<td>Phone 2:</td>
					<td>${pendingEntry.phone2}</td>
				</tr>
				<tr>
					<td>Phone 3:</td>
					<td>${pendingEntry.phone3}</td>
				</tr>
				<tr>
					<td>Phone 4:</td>
					<td>${pendingEntry.phone4}</td>
				</tr>
				<tr>
					<td>Email:</td>
					<td>${pendingEntry.emailAddress}</td>
				</tr>
				<tr>
					<td>Email verified:</td>
					<td>${pendingEntry.emailVerified}</td>
				</tr>
				<tr>
					<td>Registration Document:</td>
					<td><a href="${pendingEntry.documentPath}"
						data-lightbox="reg-doc"><img
							src="${pendingEntry.documentPath}" /></a></td>
				</tr>
				<tr>
					<td>Subscription Amount:</td>
					<td>${pendingEntry.subscriptionAmount}</td>
				</tr>
				<tr>
					<td>Subscription Term:</td>
					<td>${pendingEntry.subscriptionTerm}</td>
				</tr>
				<tr>
					<td>Subscription Expiry:</td>
					<td>${pendingEntry.subscriptionExpiry}</td>
				</tr>
				<tr>
					<td>Emergency Accepted?:</td>
					<td>${pendingEntry.emergencyRequestAccepted}</td>
				</tr>
				<tr>
					<td>Emergency Phone number:</td>
					<td>${pendingEntry.emergencyPhoneNumber}</td>
				</tr>
				<tr>
					<td>Emergency Phone verified:</td>
					<td>${pendingEntry.emergencyPhoneNumberVerified}</td>
				</tr>
				<tr>
					<td>Cities signed up for:</td>
					<td>${pendingEntry.additionalAreasServiced}</td>
				</tr>
				<tr>
					<td>Website URL:</td>
					<td>${pendingEntry.websiteUrl}</td>
				</tr>
				<tr>
					<td>Facebook URL:</td>
					<td>${pendingEntry.facebookUrl}</td>
				</tr>
				<tr>
					<td>Twitter URL:</td>
					<td>${pendingEntry.twitterUrl}</td>
				</tr>
				<tr>
					<td>Instagram URL:</td>
					<td>${pendingEntry.instagramUrl}</td>
				</tr>
				<tr>
					<td>Youtube URL:</td>
					<td>${pendingEntry.youtubeUrl}</td>
				</tr>
				<tr>
					<td>Google Plus URL:</td>
					<td>${pendingEntry.googleplusUrl}</td>
				</tr>
			</table>
			<div class="service-photos col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<c:forEach var="servicesRecord"
					items="${pendingEntry.servicesRecords}">
					${servicesRecord.services.name}
					<br>
					<br>
					<div class="photo-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<a href="${servicesRecord.imagePath1}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath1}" />
						</a> <a href="${servicesRecord.imagePath2}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath2}" />
						</a> <a href="${servicesRecord.imagePath3}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath3}" />
						</a> <a href="${servicesRecord.imagePath4}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath4}" />
						</a> <a href="${servicesRecord.imagePath5}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath5}" />
						</a> <a href="${servicesRecord.imagePath6}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath6}" />
						</a> <a href="${servicesRecord.imagePath7}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath7}" />
						</a> <a href="${servicesRecord.imagePath8}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath8}" />
						</a> <a href="${servicesRecord.imagePath9}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath9}" />
						</a> <a href="${servicesRecord.imagePath10}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath10}" />
						</a> <a href="${servicesRecord.imagePath11}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath11}" />
						</a> <a href="${servicesRecord.imagePath12}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath12}" />
						</a> <a href="${servicesRecord.imagePath13}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath13}" />
						</a> <a href="${servicesRecord.imagePath14}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath14}" />
						</a> <a href="${servicesRecord.imagePath15}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath15}" />
						</a> <a href="${servicesRecord.imagePath16}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath16}" />
						</a> <a href="${servicesRecord.imagePath17}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath17}" />
						</a> <a href="${servicesRecord.imagePath18}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath18}" />
						</a> <a href="${servicesRecord.imagePath19}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath19}" />
						</a> <a href="${servicesRecord.imagePath20}"
							data-lightbox="service-set-${servicesRecord.memberServiceId}">
							<img src="${servicesRecord.imagePath20}" />
						</a>
					</div>
				</c:forEach>
			</div>
			<div class="decision-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<input id="approve-entry" type="button" value="APPROVE" /><br>
				<br>
				<textarea id="rejection-details"></textarea>
				<br>
				<br> <input id="reject-entry" type="button" value="REJECT" />
			</div>
		</div>
		<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
	</div>
</div>