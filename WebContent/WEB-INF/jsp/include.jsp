<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${not empty favicon}">
		<link rel="icon" type="image/png" href="${favicon}" />
	</c:when>
	<c:otherwise>
		<link rel="icon" type="image/png"
			href="https://storage.googleapis.com/knotting/images/misc/favicon.png" />
	</c:otherwise>
</c:choose>
<link
	href="https://storage.googleapis.com/knotting/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/dropdown.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/checkbox.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://storage.googleapis.com/knotting/css/popup.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/transition.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/jquery-confirm.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/jquery.mCustomScrollbar.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/jquery.toast.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://storage.googleapis.com/knotting/css/loaders.css"
	rel="stylesheet" type="text/css" />
<link href="https://storage.googleapis.com/knotting/css/normalize.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/ion.rangeSlider.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/ion.rangeSlider.skinFlat.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/lightbox.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://storage.googleapis.com/knotting/css/datatables.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://storage.googleapis.com/knotting/css/segment.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://storage.googleapis.com/knotting/css/rail.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://storage.googleapis.com/knotting/css/sticky.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/css/knotting_styles.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/css/knotting_media_rules_styles.css"
	rel="stylesheet" type="text/css" />
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-112024781-1"></script>
<script>
	window.dataLayer = window.dataLayer || [];
	function gtag() {
		dataLayer.push(arguments);
	}
	gtag('js', new Date());

	gtag('config', 'UA-112024781-1');
</script>
<!-- End Google Analytics -->
<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
<script src="https://storage.googleapis.com/knotting/js/jquery-1.9.1.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/jquery.hoverIntent.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/bootstrap.min.js"
	type="text/javascript"></script>
<script src="https://storage.googleapis.com/knotting/js/dropdown.min.js"
	type="text/javascript"></script>
<script src="https://storage.googleapis.com/knotting/js/checkbox.min.js"
	type="text/javascript"></script>
<script src="https://storage.googleapis.com/knotting/js/popup.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/transition.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/jquery-confirm.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/jquery.mCustomScrollbar.concat.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/jquery.toast.min.js"
	type="text/javascript"></script>
<script src="https://storage.googleapis.com/knotting/js/loaders.css.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/masonry.pkgd.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/ion.rangeSlider.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/jquery.gsap.min.js"
	type="text/javascript"></script>
<script src="https://storage.googleapis.com/knotting/js/TweenMax.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/TimelineMax.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/ScrollMagic.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/TweenLite.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/animation.gsap.min.js"
	type="text/javascript"></script>
<script src="https://storage.googleapis.com/knotting/js/velocity.min.js"
	type="text/javascript"></script>
<script src="https://storage.googleapis.com/knotting/js/velocity.ui.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/jquery.easing.1.3.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/datatables.min.js"
	type="text/javascript"></script>
<script src="https://storage.googleapis.com/knotting/js/sticky.min.js"
	type="text/javascript"></script>
<script
	src="https://storage.googleapis.com/knotting/js/visibility.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/js/knotting_validator.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/js/knotting_script.js"
	type="text/javascript"></script>
<meta name="theme-color" content="#151718" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="keywords"
	content="Wedding,Planners,Information,Knotting,Kalyanam,Mangalyam,Makeup,Decoration,Venue,Mandapam,Music,Band,Photo,Photography,Video,Love">
<c:choose>
	<c:when test="${not empty description}">
		<meta name="description" content="${description}">
		<meta property="og:description" content="${description}" />
	</c:when>
	<c:otherwise>
		<meta name="description"
			content="Hazzle free Wedding service aggregator covering the whole of India. Find details of vendors who suit your wedding needs without having the trouble to even register.">
		<meta property="og:description"
			content="Hazzle free Wedding service aggregator covering the whole of India. Find details of vendors who suit your wedding needs without having the trouble to even register." />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${not empty title}">
		<title>${title}</title>
		<meta property="og:title" content="${title}" />
	</c:when>
	<c:otherwise>
		<title>Knotting</title>
		<meta property="og:title" content="Knotting" />
	</c:otherwise>
</c:choose>