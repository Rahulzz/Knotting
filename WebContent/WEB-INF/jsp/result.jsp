<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="vendor-result"
	class="knotting-result-screen menu-blur-section col-sm-12 col-md-12 col-lg-12">
	<div class="result-criteria-section col-sm-12 col-md-12 col-lg-12">
		<div class="criteria-title col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-1 col-md-1 col-lg-1"></div>
			<div class="col-sm-10 col-md-10 col-lg-10">
				<div class="skew-container skew">result displayed for your
					criteria</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1"></div>
		</div>
		<div class="criteria-items-container col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-1 col-md-1 col-lg-1"></div>
			<div class="col-sm-10 col-md-10 col-lg-10">
				<div class="criteria-item ease-element">
					<div class="criteria-default">
						<div class="icon criteria-icon icon-services"></div>
						<div class="title hidden-sm">
							<c:forEach var="activeService" items="${servicesList}">
								<c:if
									test="${activeService.services.serviceCode == criteriaService}">
							${activeService.services.name}</c:if>
							</c:forEach>
						</div>
						<div class="title hidden-md hidden-lg">Service</div>
					</div>
					<div class="filter-answer-section">
						<div class="choice-header">select the service</div>
						<c:forEach var="activeService" items="${servicesList}"
							varStatus="loop">
							<c:if test="${loop.index%5 == 0}">
								</ul>
								<ul class="ul-service">
							</c:if>
							<c:choose>
								<c:when
									test="${activeService.services.serviceCode == criteriaService}">
									<li id="${activeService.services.serviceCode}" class="selected">${activeService.services.name}</li>
								</c:when>
								<c:otherwise>
									<li id="${activeService.services.serviceCode}">${activeService.services.name}</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
				<div class="criteria-item capitalize ease-element">
					<div class="criteria-default">
						<div class="icon criteria-icon icon-filtercity"></div>
						<div class="title hidden-sm">${criteriaCity}</div>
						<div class="title hidden-md hidden-lg">City</div>
					</div>
					<div class="filter-answer-section">
						<div class="choice-header">select the city</div>
						<c:forEach var="city" items="${citiesList}" varStatus="loop">
							<c:if test="${loop.index%5 == 0}">
								</ul>
								<ul class="ul-city">
							</c:if>
							<c:choose>
								<c:when test="${fn:containsIgnoreCase(city, criteriaCity)}">
									<li class="selected">${city}</li>
								</c:when>
								<c:otherwise>
									<li>${city}</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
				<div class="criteria-item ease-element">
					<div class="criteria-default">
						<div class="icon criteria-icon icon-filterrange"></div>
						<div class="title hidden-sm">${criteriaRange}</div>
						<div class="title hidden-md hidden-lg">Range</div>
					</div>
					<div class="filter-answer-section">
						<div class="choice-header">select your price range</div>
						<c:forEach var="range" items="${rangeList}" varStatus="loop">
							<c:if test="${loop.index%5 == 0}">
								</ul>
								<ul class="ul-range">
							</c:if>
							<c:choose>
								<c:when test="${fn:containsIgnoreCase(range, criteriaRange)}">
									<li class="selected">${range}</li>
								</c:when>
								<c:otherwise>
									<li>${range}</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
				<div class="criteria-item ease-element">
					<div class="criteria-default">
						<div class="icon criteria-icon icon-filterurgency"></div>
						<div class="title hidden-sm">
							<c:choose>
								<c:when test="${criteriaUrgency == 'normal'}">I have time for the wedding</c:when>
								<c:when test="${criteriaUrgency == 'emergency'}">It is an emergency</c:when>
							</c:choose>
						</div>
						<div class="title hidden-md hidden-lg">Urgency</div>
					</div>
					<div class="filter-answer-section">
						<div class="choice-header">how urgent is this?</div>
						<ul class="ul-urgency">
							<c:choose>
								<c:when test="${'normal' == criteriaUrgency}">
									<li id="normal" class="selected">I have time for the
										wedding</li>
								</c:when>
								<c:otherwise>
									<li id="normal">I have time for the wedding</li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${'emergency' == criteriaUrgency}">
									<li id="emergency" class="selected">It is an emergency</li>
								</c:when>
								<c:otherwise>
									<li id="emergency">It is an emergency</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
				<c:if test="${not empty resultServices}">
					<div id="show-sort"
						class="criteria-item-sort criteria-edit vendor-dynamic-action icon icon-criteriasort badge-element no-badge"
						data-display="sort" data-badge="1"></div>
				</c:if>
				<c:if test="${not empty resultServices || not empty criteriaFilter}">
					<c:choose>
						<c:when test="${not empty criteriaFilter}">
							<div id="show-filter"
								class="criteria-item-filter ease-element criteria-edit vendor-dynamic-action icon icon-criteriafilter badge-element"
								data-display="filter" data-badge="${badgeCount}"></div>
						</c:when>
						<c:otherwise>
							<div id="show-filter"
								class="criteria-item-filter ease-element criteria-edit vendor-dynamic-action icon icon-criteriafilter badge-element no-badge"
								data-display="filter" data-badge="0"></div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div id="home-search"
					class="criteria-item-filter ease-element criteria-edit submit-criteria icon icon-filtersearch"
					data-display="search"></div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1"></div>
		</div>
	</div>
	<c:if test="${not empty resultServices || not empty criteriaFilter}">
		<div
			class="result-dynamic-section filter-dynamic-section col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-1 col-md-1 col-lg-1"></div>
			<div class="dynamic-content-container col-sm-10 col-md-10 col-lg-10">
				<div
					class="dynamic-content-section dynamic-content-filter col-sm-12 col-md-12 col-lg-12">
					<div class="filter-options-content col-sm-12 col-md-12 col-lg-12">
						<c:forEach var="filter" items="${filterList}">
							<c:set var="answerList"
								value="${fn:split(filter.serviceDetailAnswerList, '#')}" />
							<c:choose>
								<c:when test="${filter.serviceDetailType == 'input' }">
									<c:if
										test="${not empty resultServices && answerList[0] != answerList[1]}">
										<div class="field-box range-box knotting-tooltip"
											data-content="${filter.serviceDetailFilterTitle}"
											data-position="right center">
											<div class="options-container">
												<input type="text" value=""
													class="range-slider filter-element" data-changed="no"
													data-changedonload="no" data-start="" data-end=""
													data-field="${filter.serviceDetailFieldName}"
													data-lower="${answerList[0]}"
													data-higher="${answerList[1]}"
													data-field="${filter.serviceDetailFieldName}"
													data-type="range" />
											</div>
										</div>
									</c:if>
								</c:when>
								<c:otherwise>
									<div class="field-box">
										<div class="title">${filter.serviceDetailFilterTitle}</div>
										<div class="options-container">
											<c:choose>
												<c:when test="${filter.serviceDetailAnswerList == 'Yes#No'}">
													<div class="ui inline dropdown filter-element"
														data-field="${filter.serviceDetailFieldName}"
														data-changed="no" data-changedonload="no"
														data-changedvalue="" data-type="dropdown">
														<div class="text">${filter.serviceDetailFilterPlaceHolder}</div>
														<div class="menu">
															<c:forEach var="option" items="${answerList}">
																<div class="item">${option}</div>
															</c:forEach>
														</div>
													</div>
												</c:when>
												<c:otherwise>
													<div class="ui inline multiple dropdown filter-element"
														data-field="${filter.serviceDetailFieldName}"
														data-changed="no" data-changedonload="no"
														data-changedvalue="" data-type="dropdown">
														<div class="text">${filter.serviceDetailFilterPlaceHolder}</div>
														<div class="menu">
															<c:forEach var="option" items="${answerList}">
																<div class="item">${option}</div>
															</c:forEach>
														</div>
													</div>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="filter-decision-content col-sm-12 col-md-12 col-lg-12">
						<input id="filter-apply" type="button"
							class="button ease-element apply" value="apply"> <input
							id="filter-reset" type="button" class="button ease-element reset"
							value="reset"> <input id="filter-remove" type="button"
							class="button ease-element remove" value="remove">
					</div>
				</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1"></div>
		</div>
	</c:if>
	<c:if test="${not empty resultServices}">
		<div
			class="result-dynamic-section sort-dynamic-section col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-1 col-md-1 col-lg-1"></div>
			<div class="dynamic-content-container col-sm-10 col-md-10 col-lg-10">
				<div class="dynamic-content-section col-sm-12 col-md-12 col-lg-12">
					<div class="filter-options-content col-sm-12 col-md-12 col-lg-12">
						<div class="sort-box disable_user_selection ease-element"
							data-sortonload="no" data-sorttype="eio">
							<div class="title">sort vendors by</div>
							<div class="value">experience in increasing order</div>
						</div>
						<div class="sort-box disable_user_selection ease-element"
							data-sortonload="no" data-sorttype="edo">
							<div class="title">sort vendors by</div>
							<div class="value">experience in decreasing order</div>
						</div>
						<div class="sort-box disable_user_selection ease-element"
							data-sortonload="no" data-sorttype="cio">
							<div class="title">sort vendors by</div>
							<div class="value">cost in increasing order</div>
						</div>
						<div class="sort-box disable_user_selection ease-element"
							data-sortonload="no" data-sorttype="cdo">
							<div class="title">sort vendors by</div>
							<div class="value">cost in decreasing order</div>
						</div>
					</div>
					<div class="filter-decision-content col-sm-12 col-md-12 col-lg-12">
						<input id="sort-apply" type="button"
							class="button ease-element apply" value="apply"> <input
							id="sort-reset" type="button" class="button ease-element reset"
							value="reset"> <input id="sort-remove" type="button"
							class="button ease-element remove" value="remove">
					</div>
				</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1"></div>
		</div>
	</c:if>
	<div class="result-price-detail-section col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-1 col-md-1 col-lg-1"></div>
		<div class="col-sm-10 col-md-10 col-lg-10">
			<div class="price-detail-header col-sm-12 col-md-12 col-lg-12">
				<table>
					<tr>
						<td class="criteria-icon"><span
							class="icon icon-filterrange green-gradient"></span></td>
						<td><div class="skew-container skew green-gradient">Average
								Price</div></td>
					</tr>
				</table>
			</div>
			<div class="price-detail-detail col-sm-12 col-md-9 col-lg-9">${serviceDetails.pricingDetails}</div>
			<div class="result-record-count hidden-sm col-md-3 col-lg-3">
				<table>
					<tr>
						<td class="text">vendors</td>
						<td class="number" rowspan="2">${totalRecords}</td>
					</tr>
					<tr>
						<td class="text">found</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-sm-1 col-md-1 col-lg-1"></div>
	</div>
	<div class="result-results-section col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-1 col-md-1 col-lg-1"></div>
		<c:choose>
			<c:when test="${not empty resultServices}">
				<div
					class="result-results-section-items col-sm-10 col-md-10 col-lg-10">
					<c:forEach var="service" items="${resultServices}">
						<a href="details/${service.memberServiceCode}">
							<div class="result-item-container">
								<c:if test="${not empty service.imagePath1Thumbnail}">
									<div class="result-item-images image-single">
										<div class="vendor-image">
											<img data-src="${service.imagePath1Thumbnail}" />
										</div>
									</div>
								</c:if>
								<div class="result-item-details">
									<div class="title">${imageCount}${service.memberEntries.name}</div>
									<div class="rating">
										<table>
											<tr>
												<td class="star"><span class="icon icon-nostar"></span></td>
												<td class="star"><span class="icon icon-nostar"></span></td>
												<td class="star"><span class="icon icon-nostar"></span></td>
												<td class="star"><span class="icon icon-nostar"></span></td>
												<td class="star"><span class="icon icon-nostar"></span></td>
												<td class="note">yet to be rated</td>
											</tr>
										</table>
									</div>
									<div class="item">
										<table>
											<c:choose>
												<c:when
													test="${service.memberEntries.emergencyRequestAccepted == 'Y'}">
													<tr>
														<td class="logo accept"><span
															class="icon icon-filterurgency"></span></td>
														<td class="note">accepts emergency request</td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td class="logo deny"><span
															class="icon icon-filterurgency"></span></td>
														<td class="note">doesn't accept emergency request</td>
													</tr>
												</c:otherwise>
											</c:choose>
										</table>
									</div>
									<div class="item">
										<table>
											<tr>
												<td class="logo"><span class="icon icon-filterrange"></span></td>
												<td class="note">average cost <fmt:formatNumber
														value="${service.priceRange}" type="number" /> INR
												</td>
											</tr>
										</table>
									</div>
									<div class="item">
										<table>
											<tr>
												<td class="logo"><span
													class="icon icon-result-experience"></span></td>
												<fmt:parseNumber var="experience" type="number"
													value="${service.experience}" />
												<c:choose>
													<c:when test="${experience == 1}">
														<td class="note">1 year of experience</td>
													</c:when>
													<c:otherwise>
														<td class="note">${service.experience} years of
															experience</td>
													</c:otherwise>
												</c:choose>
											</tr>
										</table>
									</div>
									<%-- 									<c:set var="cityList" --%>
									<%-- 										value="${fn:split(service.memberEntries.additionalAreasServiced, ',')}" /> --%>
									<!-- 									<div class="item"> -->
									<!-- 										<table> -->
									<!-- 											<tr> -->
									<!-- 												<td class="logo"><span class="icon icon-filtercity"></span></td> -->
									<%-- 												<c:choose> --%>
									<%-- 													<c:when test="${fn:length(cityList) == 1}"> --%>
									<!-- 														<td class="note">accepts work for 1 city</td> -->
									<%-- 													</c:when> --%>
									<%-- 													<c:otherwise> --%>
									<!-- 														<td class="note">accepts work for -->
									<%-- 															${fn:length(cityList)} cities</td> --%>
									<%-- 													</c:otherwise> --%>
									<%-- 												</c:choose> --%>
									<!-- 											</tr> -->
									<!-- 										</table> -->
									<!-- 									</div> -->
									<!-- 									<div class="item"> -->
									<%-- 										<c:forEach var="city" items="${cityList}"> --%>
									<%-- 											<div class="city-tag">${city}</div> --%>
									<%-- 										</c:forEach> --%>
									<!-- 									</div> -->
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<div class="no-assets-found col-sm-12 col-md-12 col-lg-12">
					<img
						src="https://storage.googleapis.com/knotting/images/misc/no-records.png" />
				</div>
			</c:otherwise>
		</c:choose>
		<div class="col-sm-1 col-md-1 col-lg-1"></div>
	</div>
	<c:if test="${not empty resultServices && lastRecord != totalRecords}">
		<div class="result-more-section col-sm-12 col-md-12 col-lg-12">
			<div class="more-button" data-first="${firstRecord}"
				data-last="${lastRecord}" data-total="${totalRecords}">load
				more</div>
		</div>
	</c:if>
</div>