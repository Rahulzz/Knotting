<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="include" />
</head>
<body>
	<div class="blur_section col-sm-12 col-md-12 col-lg-12">
		<tiles:insertAttribute name="message" />
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
</body>
<script src="https://storage.googleapis.com/knotting/js/lightbox.min.js"
	type="text/javascript"></script>
</html>