<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty error}">
	<script type="text/javascript">
		var message = '${error}';
		throwAnErrorMessageWithTime(message, 5000);
	</script>
	<c:remove var="error" />
</c:if>
<c:if test="${not empty success}">
	<script type="text/javascript">
		var message = '${success}';
		throwASuccessMessageWithTime(message, 5000);
	</script>
	<c:remove var="success" />
</c:if>
<c:if test="${not empty info}">
	<script type="text/javascript">
		var message = '${info}';
		throwAnInfoMessageWithTime(message, 2000);
	</script>
	<c:remove var="info" />
</c:if>