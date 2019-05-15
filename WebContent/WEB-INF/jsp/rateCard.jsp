<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="text-pages col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
	<div class="content col-xs-10 col-sm-10 col-md-10 col-lg-10">
		<div class="page-title col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="skew-container skew green-gradient">rate card</div>
		</div>
		<div class="page-text-content col-xs-12 col-sm-12 col-md-12 col-lg-12">We
			understand the uniqueness and business of every wedding service.
			Hence, we have our rate card curated to deliver the best options. We
			operate on a yearly model and charge the most affordable subscription
			price. You get an un-partial, clean and a transparent online service
			at Knotting.</div>
		<div
			class="page-highlight-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="skew-container skew">Let's do business</div>
		</div>
		<div class="page-card-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<c:forEach var="subscription" items="${subscriptions}">
				<div
					class="rate-card-row ease-element col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="section-1 col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="image col-xs-2 col-sm-2 col-md-2 col-lg-2">
							<img
								src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII="
								class="knotting-menu-image-${subscription.services.serviceCode}" />
						</div>
						<div class="description col-xs-10 col-sm-10 col-md-10 col-lg-10"><div><div class="highlight skew-container skew">${subscription.services.name}</div> - ${subscription.services.description}</div></div>
					</div>
					<div class="section-2 col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="term col-xs-3 col-sm-3 col-md-3 col-lg-3">
							<div class="card-term">
								<div class="year skew">
									one year<span></span>
								</div>
								<div class="sub skew">
									subscription<span></span>
								</div>
							</div>
						</div>
						<div class="count col-xs-3 col-sm-3 col-md-3 col-lg-3">
							<div class="card-term">
								<div class="year skew">
									<fmt:formatNumber pattern="00000"
										value="${fn:length(subscription.services.memberServices)}" />
									<span></span>
								</div>
								<div class="sub skew">
									vendors<span></span>
								</div>
							</div>
						</div>
						<div class="cost col-xs-3 col-sm-3 col-md-3 col-lg-3">
							<div class="card-term">
								<div class="year skew">
									${subscription.subscriptionRate} inr <span></span>
								</div>
								<div class="sub skew">
									annually<span></span>
								</div>
							</div>
						</div>
						<div class="button col-xs-3 col-sm-3 col-md-3 col-lg-3">
							<input type="button" value="select" class="button-gradient" onclick="javascript:addNewServiceInfoOverlay();" />
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
</div>