<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="knotting-home-screens col-sm-12 col-md-12 col-lg-12">
	<tiles:insertAttribute name="header" />
	<div id="home-searchbox"
		class="home-section banner-section menu-blur-section criteria-bw-section col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div
			class="home-banner-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div
				class="home-banner-pattern hidden-xs hidden-sm col-md-12 col-lg-12"></div>
			<div
				class="home-banner-gradient hidden-xs hidden-sm col-md-12 col-lg-12"></div>
			<div class="home-banner-img col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<video autoplay loop
					poster="https://storage.googleapis.com/knotting/images/home/home_banner.jpg"
					muted class="hidden-xs hidden-sm">
					<!-- 					<source -->
					<!-- 						data-src="https://storage.googleapis.com/knotting/videos/home-vid-web.webm" -->
					<!-- 						type="video/webm"></source> -->
					<source data-src="${homeBanner.configField2}" type="video/mp4"></source>
				</video>
				<script type="text/javascript">
					playVideo();
				</script>
				<img src="${homeBanner.configField5}" class="hidden-md hidden-lg" />
			</div>
			<div
				class="home-banner-overlay col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div
					class="home-banner-text skew col-xs-12 col-sm-12 col-md-12 col-lg-12">
					all your wedding needs in one place</div>
				<div
					class="home-banner-subtext skew col-xs-12 col-sm-12 col-md-12 col-lg-12">
					we help you connect several small dots to make up<br>your
					whole wedding
				</div>
				<div class="home-banner-filter col-sm-12 col-md-12 col-lg-12">
					<div class="filter-option-container">
						<div class="filter-option">
							<table>
								<tr>
									<td width="40%"><span class="icon icon-services"></span></td>
									<td class="name">services</td>
								</tr>
							</table>
							<div class="filter-answer-section">
								<div class="choice-header">select the service</div>
								<c:forEach var="memberService" items="${serviceList}"
									varStatus="loop">
									<c:if test="${loop.index%5 == 0}">
										</ul>
										<ul class="ul-service">
									</c:if>
									<li id="${memberService.services.serviceCode}">${memberService.services.name}</li>
								</c:forEach>
							</div>
						</div>
						<div class="filter-option">
							<table>
								<tr>
									<td width="40%" class="logo"><span
										class="icon icon-filtercity"></span></td>
									<td class="name">city</td>
								</tr>
							</table>
							<div class="filter-answer-section">
								<div class="choice-header">select the city</div>
								<ul class="ul-city">
									<li class="no-select">choose a service</li>
								</ul>
							</div>
						</div>
						<div class="filter-option">
							<table>
								<tr>
									<td width="40%" class="logo"><span
										class="icon icon-filterrange"></span></td>
									<td class="name">range</td>
								</tr>
							</table>
							<div class="filter-answer-section">
								<div class="choice-header">select your price range</div>
								<ul class="ul-range">
									<li class="no-select">choose a city</li>
								</ul>
							</div>
						</div>
						<div class="filter-option">
							<table>
								<tr>
									<td width="40%"><span class="icon icon-filterurgency"></span></td>
									<td class="name">urgency</td>
								</tr>
							</table>
							<div class="filter-answer-section">
								<div class="choice-header">how urgent is this?</div>
								<ul class="ul-urgency">
									<li id="normal">I have time for the wedding</li>
									<li id="emergency">It is an emergency</li>
								</ul>
							</div>
						</div>
						<div id="home-inform"
							class="filter-option search knotting-tooltip"
							data-content="We'll ask our vendors to get in touch with you. Feel pampered!!!."
							data-position="top center">
							<input type="hidden" id="share-details-to" />
							<table>
								<tr>
									<td width="40%"><span class="icon icon-howitworks-contact"></span></td>
									<td class="name">inform</td>
								</tr>
							</table>
						</div>
						<div id="home-search" class="filter-option search">
							<table>
								<tr>
									<td width="40%"><span class="icon icon-filtersearch"></span></td>
									<td class="name">search</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div
					class="home-banner-footer col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
					<div class="to-left col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<img
							src="https://storage.googleapis.com/knotting/images/misc/knotting_EL_logo.png"
							class="brand-el" />
					</div>
					<div class="to-right hidden-xs hidden-sm col-md-5 col-lg-5">
						<div class="credit-box">
							<div class="credit-title">video shot by</div>
							<div class="credit-logo">
								<a href="details/${homeBanner.configField3}"><img
									src="${homeBanner.configField1}" class="banner-credit" /></a>
							</div>
						</div>
					</div>
					<div class="to-right col-xs-5 col-sm-5 hidden-md hidden-lg">
						<div class="credit-box">
							<div class="credit-title">pic shot by</div>
							<div class="credit-logo">
								<a href="details/${homeBanner.configField6}"><img
									src="${homeBanner.configField4}" class="banner-credit" /></a>
							</div>
						</div>
					</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="home-simple"
		class="home-section hidden-xs hidden-sm col-md-12 col-lg-12">
		<div
			class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div
				class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="skew-container skew">
					<span class="orange-gradient">who we are</span>
				</div>
			</div>
			<div
				class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="skew-container skew">SIMPLE</div>
			</div>
		</div>
		<div class="horizontal-scroll-container">
			<div class="layer-1 scroll-element">
				<img
					src="https://storage.googleapis.com/knotting/images/home/whoweare-1.png" />
			</div>
			<div class="layer-2 scroll-element">
				<img
					src="https://storage.googleapis.com/knotting/images/home/whoweare-2.png" />
			</div>
			<div class="layer-3 scroll-element">
				<img
					src="https://storage.googleapis.com/knotting/images/home/whoweare-3.jpg" />
			</div>
		</div>
	</div>
	<div id="home-magic"
		class="home-section col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div
			class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div
				class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="skew-container skew">
					<span class="orange-gradient">how it works</span>
				</div>
			</div>
			<div
				class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="skew-container skew">MAGIC</div>
			</div>
		</div>
		<div
			class="astronaut-flow-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
			<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
				<div
					class="title-line skew bebas col-xs-12 col-sm-12 col-md-12 col-lg-12">a
					wedding plan is similar to space travel</div>
				<div
					class="title-line pad-for-mobile skew col-xs-12 col-sm-12 col-md-12 col-lg-12">we'll
					help you fly at ease</div>
				<div
					class="astronaut-flow-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div
						class="astronaut-flow-sub-box col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div
							class="astronaut-moment hidden-xs hidden-sm col-md-3 col-lg-3">
							<div class="astro-boy col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-astronaut-wedding green-gradient"></span>
							</div>
							<div
								class="astro-description col-xs-12 col-sm-12 col-md-12 col-lg-12">
								once your wedding<br>talks are on
							</div>
						</div>
						<div class="astronaut-moment col-xs-4 col-sm-4 col-md-3 col-lg-3">
							<div class="astro-boy col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-astronaut-service"></span>
							</div>
							<div
								class="astro-description col-xs-12 col-sm-12 col-md-12 col-lg-12">
								come over to knotting,<br>choose the service
							</div>
						</div>
						<div class="astronaut-moment col-xs-4 col-sm-4 col-md-3 col-lg-3">
							<div class="astro-boy col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-astronaut-location"></span>
							</div>
							<div
								class="astro-description col-xs-12 col-sm-12 col-md-12 col-lg-12">
								select the<br>wedding city
							</div>
						</div>
						<div class="astronaut-moment col-xs-4 col-sm-4 col-md-3 col-lg-3">
							<div class="astro-boy col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-astronaut-price"></span>
							</div>
							<div
								class="astro-description col-xs-12 col-sm-12 col-md-12 col-lg-12">
								choose your<br>price range
							</div>
						</div>
					</div>
					<div
						class="astronaut-flow-sub-box col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="astronaut-moment col-xs-4 col-sm-4 col-md-3 col-lg-3">
							<div class="astro-boy col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-astronaut-urgency"></span>
							</div>
							<div
								class="astro-description col-xs-12 col-sm-12 col-md-12 col-lg-12">
								tell us if your<br>request is urgent
							</div>
						</div>
						<div class="astronaut-moment col-xs-4 col-sm-4 col-md-3 col-lg-3">
							<div class="astro-boy col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-astronaut-wait"></span>
							</div>
							<div
								class="astro-description col-xs-12 col-sm-12 col-md-12 col-lg-12">
								be pampered, receive<br>calls from our vendors
							</div>
						</div>
						<div class="astronaut-moment col-xs-4 col-sm-4 col-md-3 col-lg-3">
							<div class="astro-boy col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-astronaut-view"></span>
							</div>
							<div
								class="astro-description col-xs-12 col-sm-12 col-md-12 col-lg-12">
								be proactive, view<br>the entire list of vendors
							</div>
						</div>
						<div
							class="astronaut-moment hidden-xs hidden-sm col-md-3 col-lg-3">
							<div class="astro-boy col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<span class="icon icon-astronaut-sleep green-gradient"></span>
							</div>
							<div
								class="astro-description col-xs-12 col-sm-12 col-md-12 col-lg-12">
								now, your wedding<br>dreams are on
							</div>
						</div>
					</div>
				</div>
				<!-- 				<div -->
				<!-- 					class="home-button-container col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
				<!-- 					<input type="button" value="give a try" /> -->
				<!-- 				</div> -->
			</div>
			<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
		</div>
	</div>
	<c:if test="${displayMostViewed.configField1 == 'Y'}">
		<div id="home-services"
			class="home-section hidden-xs hidden-sm col-md-12 col-lg-12">
			<div
				class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div
					class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">
						<span class="orange-gradient">most viewed services</span>
					</div>
				</div>
				<div
					class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">SERVICES</div>
				</div>
			</div>
			<div
				class="freq-services-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
				<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
					<c:forEach var="service" items="${freqServices}">
						<a href="serviceResult?service=${service.serviceCode}">
							<div
								class="knotting-fav-service col-xs-6 col-sm-6 col-md-3 col-lg-3">
								<div class="fav-service-detail-container">
									<div class="stats-container ease-element">
										<div class="knotting-fav-service-image">
											<img
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
												class="knotting-menu-image-${service.serviceCode}">
										</div>
										<div class="skew">
											<div class="counter-number">
												<fmt:formatNumber pattern="00000"
													value="${fn:length(service.memberServices)}" />
											</div>
											<div class="counter-number-title bottom">vendors</div>
											<div class="counter-number-title">available</div>
										</div>
									</div>
									<div class="detail-container">
										<div class="knotting-fav-service-image">
											<img
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
												class="knotting-menu-image-${service.serviceCode}">
										</div>
										<div class="knotting-fav-service-title skew">${service.name}</div>
										<div class="knotting-fav-service-description skew">${service.description}</div>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
			</div>
		</div>
	</c:if>
	<c:if
		test="${displayRecents.configField1 == 'Y' && fn:length(recents) == 30}">
		<div id="home-recents"
			class="home-section col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div
				class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div
					class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">
						<span class="orange-gradient">newly joined</span>
					</div>
				</div>
				<div
					class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="skew-container skew">RECENTS</div>
				</div>
			</div>
			<div
				class="home-recents-image-container grid col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<c:forEach var="recentService" items="${recents}">
					<a href="details/${recentService.memberServiceCode}">
						<figure class="effect-apollo">
							<img src="${recentService.imagePath1Thumbnail}"
								alt="${recentService.memberEntries.name}" />
							<figcaption>
								<h2>${recentService.memberEntries.name}</h2>
								<fmt:parseNumber var="experience" type="number"
									value="${recentService.experience}" />
								<p>
									<c:choose>
										<c:when test="${experience == 1}">1 year of<br>experience in<br>${recentService.services.name}</c:when>
										<c:when test="${experience > 1}">${recentService.experience} years of<br>experience in<br>${recentService.services.name}</c:when>
									</c:choose>
								</p>
							</figcaption>
						</figure>
					</a>
				</c:forEach>
			</div>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${empty userId}">
			<div id="home-joinus"
				class="home-section col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div
					class="home-joinus-content-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div
						class="home-joinus-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div
							class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div
								class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="skew-container skew">
									<span class="orange-gradient">join the knotting team</span>
								</div>
							</div>
							<div
								class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="skew-container skew">VENDORS</div>
							</div>
						</div>
						<div
							class="title-line bebas col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="skew-container skew">our secret recipe is all
								set to spice up your business</div>
						</div>
						<div
							class="title-line pad-for-mobile col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="skew-container skew">let's build the best
								wedding cosmos ever</div>
						</div>
						<div
							class="recipe-pack-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="recipe-pack col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<img
									src="https://storage.googleapis.com/knotting/images/home/join-us-cosmo-web.png"
									class="hidden-xs hidden-sm" /> <img
									src="https://storage.googleapis.com/knotting/images/home/join-us-cosmo-mobile.png"
									class="hidden-md hidden-lg" />
							</div>
							<div class="recipe-info col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div
									class="title-line pad-for-mobile close-typo hidden-xs hidden-sm col-md-12 col-lg-12">
									<div class="skew-container skew">knotting, the only fair
										wedding system online. we don't feature/recommend vendors.</div>
								</div>
								<div
									class="title-line pad-for-mobile close-typo col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="skew-container skew">be a part of this
										network for as low as ${cheapestSubscription} INR a year</div>
								</div>
								<div
									class="title-line pad-for-mobile close-typo col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="skew-container skew">bring knotting into your
										world and experience the change</div>
								</div>
								<div
									class="register-vendor-button-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="launch-register ease-element">create a
										knotting account now</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div id="home-stats"
				class="home-section col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div
					class="home-joinus-content-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div
						class="home-joinus-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div
							class="home-section-title-container col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div
								class="home-section-title-foreground col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="skew-container skew">
									<span class="orange-gradient">your data</span>
								</div>
							</div>
							<div
								class="home-section-title-background col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="skew-container skew">STATS</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>