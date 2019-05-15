<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="service-result"
	class="knotting-result-screen col-sm-12 col-md-12 col-lg-12">
	<tiles:insertAttribute name="header" />
	<div id="home-searchbox"
		class="home-section banner-section menu-blur-section col-sm-12 col-md-12 col-lg-12">
		<div class="result-banner-container col-sm-12 col-md-12 col-lg-12">
			<div class="home-banner-img col-sm-12 col-md-12 col-lg-12">
				<img
					src="https://storage.googleapis.com/knotting/images/service-banner/web/${serviceDetails.serviceCode}.jpg"
					class="hidden-xs hidden-sm" /> <img
					src="https://storage.googleapis.com/knotting/images/service-banner/mobile/${serviceDetails.serviceCode}.jpg"
					class="hidden-md hidden-lg" />
			</div>
			<div class="home-banner-overlay col-sm-12 col-md-12 col-lg-12">
				<div class="result-counter-section col-sm-12 col-md-12 col-lg-12">
					<div class="col-sm-1 col-md-1 col-lg-1"></div>
					<div class="col-sm-10 col-md-10 col-lg-10">
						<div class="result-category-title col-sm-6 col-md-7 col-lg-7">
							<img class="knotting-menu-image-${serviceDetails.serviceCode}"
								src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII=" />
							<span>${serviceDetails.name}</span>
						</div>
						<div class="result-record-count col-sm-6 col-md-5 col-lg-5">
							<c:if test="${not empty serviceList}">
								<div id="show-sort"
									class="criteria-item-sort criteria-edit vendor-dynamic-action icon icon-criteriasort badge-element no-badge"
									data-display="sort" data-badge="1"></div>
							</c:if>
							<c:if test="${not empty serviceList || not empty criteriaFilter}">
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
						</div>
					</div>
					<div class="col-sm-1 col-md-1 col-lg-1"></div>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${not empty serviceList || not empty criteriaFilter}">
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
										test="${not empty serviceList && answerList[0] != answerList[1]}">
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
	<c:if test="${not empty serviceList}">
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
			<c:when test="${not empty serviceList}">
				<div
					class="result-results-section-items col-sm-10 col-md-10 col-lg-10">
					<c:forEach var="service" items="${serviceList}">
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
									<div class="title short-text-box"><div>${service.memberEntries.name}</div></div>
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
												<td class="note">average cost <c:choose>
														<c:when test="${fn:contains(service.priceRange, ',')}">
															<c:set var="priceList" value="${fn:split(service.priceRange, ',')}" />
															<c:forEach var="price" items="${priceList}" varStatus="loop">
																<fmt:formatNumber value="${price}" type="number" />
																<c:if test="${loop.index != fn:length(cityList)}">
																 / 
																</c:if>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<fmt:formatNumber value="${service.priceRange}"
																type="number" />
														</c:otherwise>
													</c:choose> INR
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
									<c:set var="cityList"
										value="${fn:split(service.memberEntries.additionalAreasServiced, ',')}" />
									<div class="item">
										<table>
											<tr>
												<td class="logo"><span class="icon icon-filtercity"></span></td>
												<c:choose>
													<c:when test="${fn:length(cityList) == 1}">
														<td class="note">accepts work for 1 city</td>
													</c:when>
													<c:otherwise>
														<td class="note">accepts work for
															${fn:length(cityList)} cities</td>
													</c:otherwise>
												</c:choose>
											</tr>
										</table>
									</div>
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
	<c:if test="${not empty serviceList && lastRecord != totalRecords}">
		<div class="result-more-section col-sm-12 col-md-12 col-lg-12">
			<div class="more-button" data-first="${firstRecord}"
				data-last="${lastRecord}" data-total="${totalRecords}">load
				more</div>
		</div>
	</c:if>
</div>