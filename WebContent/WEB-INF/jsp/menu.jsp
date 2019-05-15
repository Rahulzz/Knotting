<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div
	class="knotting-menu-vendor knotting-menu-options hidden-sm col-md-12 col-lg-12">
	<c:if test="${not empty userId}">
		<div class="knotting-menu-vendor-container">
			<div class="knotting-menu-item">
				<div class="knotting-menu-item-image">
					<img
						src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
						class="knotting-menu-image-website" />
				</div>
				<div class="knotting-menu-item-title skew">Wedding Website</div>
				<div class="knotting-menu-item-description skew">Premium &
					Elegant websites to invite people and share your wedding moments.</div>
			</div>
			<a href="rateCard">
				<div class="knotting-menu-item">
					<div class="knotting-menu-item-image">
						<img
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
							class="knotting-menu-image-rate" />
					</div>
					<div class="knotting-menu-item-title skew">Rate card</div>
					<div class="knotting-menu-item-description skew">Most
						reasonable subscription price list. Have an idea as to how much we
						charge.</div>
				</div>
			</a> <a href="profile">
				<div class="knotting-menu-item">
					<div class="knotting-menu-item-image">
						<img
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
							class="knotting-menu-image-profile" />
					</div>
					<div class="knotting-menu-item-title skew">Profile</div>
					<div class="knotting-menu-item-description skew">All your
						details, services, insights and stats sit here. Sneak In.</div>
				</div>
			</a> <a href="logoutUser">
				<div class="knotting-menu-item">
					<div class="knotting-menu-item-image">
						<img
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
							class="knotting-menu-image-logout" />
					</div>
					<div class="knotting-menu-item-title skew">Logout</div>
					<div class="knotting-menu-item-description skew">The only way
						to step out of the wedding cosmos. We do not recommend this
						action.</div>
				</div>
			</a>
		</div>
	</c:if>
</div>
<div
	class="knotting-menu-services knotting-menu-options col-sm-12 col-md-12 col-lg-12">
	<div class="knotting-menu-services-container">
		<c:forEach var="service" items="${allServices}">
			<a href="serviceResult?service=${service.serviceCode}">
				<div class="knotting-menu-item">
					<div class="knotting-menu-item-image">
						<img
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
							class="knotting-menu-image-${service.serviceCode}" />
					</div>
					<div class="knotting-menu-item-title skew">${service.name}</div>
					<div class="knotting-menu-item-description skew">${service.description}</div>
				</div>
			</a>
		</c:forEach>
	</div>
</div>
<div id="mobile-menu-options"
	class="knotting-mobile-menu col-sm-12 hidden-md hidden-lg">
	<div class="mobile-menu-header col-sm-12">
		<c:choose>
			<c:when test="${empty userId}">
				<input type="button" value="login" class="btn launch-login" />
			</c:when>
			<c:otherwise>
				<a href="logoutUser"><input type="button" value="logout"
					class="btn" /></a>
			</c:otherwise>
		</c:choose>
		<div id="close-mobile-menu" class="icon icon-close"></div>
	</div>
	<div class="mobile-menu-disclaimer col-sm-12">Necessary options
		are shown in the mobile version. For a full blown menu, please visit
		knotting.in on your laptop.</div>
	<div class="knotting-menu-container">
		<c:if test="${not empty userId}">
			<a href="profile">
				<div class="knotting-menu-item">
					<div class="knotting-menu-item-image">
						<img
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
							class="knotting-menu-image-profile" />
					</div>
					<div class="knotting-menu-item-title skew">Profile</div>
					<div class="knotting-menu-item-description skew">All your
						details, services, insights and stats sit here. Sneak In.</div>
				</div>
			</a>
			<a href="rateCard">
				<div class="knotting-menu-item">
					<div class="knotting-menu-item-image">
						<img
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
							class="knotting-menu-image-rate" />
					</div>
					<div class="knotting-menu-item-title skew">Rate card</div>
					<div class="knotting-menu-item-description skew">Most
						reasonable subscription price list. Have an idea as to how much we
						charge.</div>
				</div>
			</a>
			<a href="javascript:">
				<div class="knotting-menu-item">
					<div class="knotting-menu-item-image">
						<img
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
							class="knotting-menu-image-website" />
					</div>
					<div class="knotting-menu-item-title skew">Wedding website</div>
					<div class="knotting-menu-item-description skew">Premium &
						Elegant websites to invite people and share your wedding moments.</div>
				</div>
			</a>
		</c:if>
		<c:if test="${empty userId}">
			<c:forEach var="service" items="${allServices}">
				<a href="serviceResult?service=${service.serviceCode}">
					<div class="knotting-menu-item">
						<div class="knotting-menu-item-image">
							<img
								src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
								class="knotting-menu-image-${service.serviceCode}" />
						</div>
						<div class="knotting-menu-item-title skew">${service.name}</div>
						<div class="knotting-menu-item-description skew">${service.description}</div>
					</div>
				</a>
			</c:forEach>
		</c:if>
	</div>
	<div class="mobile-menu-disclaimer col-sm-12">"If an ordinary bus
		conductor can stand on the same stage as India's legends, then yes,
		miracles do happen."</div>
</div>