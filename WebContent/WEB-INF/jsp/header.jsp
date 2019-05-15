<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="knotting-header-container">
	<div class="knotting-menu col-sm-12 col-md-12 col-lg-12">
		<tiles:insertAttribute name="menu" />
	</div>
	<div class="knotting-header col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-1 col-md-1 col-lg-1"></div>
		<div class="content col-sm-10 col-md-10 col-lg-10">
			<div class="stay-left col-sm-4 col-md-4 col-lg-4">
				<a href="/"><img
					src="https://storage.googleapis.com/knotting/images/misc/knotting_logo.png" /></a>
			</div>
			<div class="stay-right menu-options hidden-sm col-md-8 col-lg-8">
				<c:choose>
					<c:when test="${userType == 'SUPPORT' || userType == 'ADMIN'}">
						<ul>
							<li><div class="skew">
									<a href="supportMembers"
										class="knotting-menu-item-link ease-element" data-item="#">members</a>
								</div></li>
							<li><div class="skew">
									<a href="supportEntries"
										class="knotting-menu-item-link ease-element" data-item="#">entries</a>
								</div></li>
							<li><div class="skew">
									<a href="supportCoupons"
										class="knotting-menu-item-link ease-element" data-item="#">coupons</a>
								</div></li>
							<li><div class="skew">
									<a href="logoutUser"
										class="knotting-menu-item-link ease-element" data-item="#">logout</a>
								</div></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul>
							<li><div class="skew">
									<a href="javascript:"
										class="knotting-menu-item-link ease-element"
										data-item="services">services</a>
								</div></li>
							<c:choose>
								<c:when test="${not empty userId}">
									<li><div class="skew">
											<a href="javascript:"
												class="knotting-menu-item-link ease-element"
												data-item="vendor">vendor</a>
										</div></li>
								</c:when>
								<c:otherwise>
									<li><div class="skew">
											<a href="javascript:"
												class="knotting-menu-item-link ease-element launch-login"
												data-item="#">vendor login</a>
										</div></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="stay-right menu-options col-sm-8 hidden-md hidden-lg">
				<ul>
					<li><span id="mobile-menu" class="icon icon-menu"></span></li>
				</ul>
			</div>
		</div>
		<div class="col-sm-1 col-md-1 col-lg-1"></div>
	</div>
</div>