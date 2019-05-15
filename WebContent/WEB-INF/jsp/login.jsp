<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
Login Page
<br>
<br>
<br>
<form:form id="loginForm" method="post" modelAttribute="memberModel"
	action="./loginMember">
	<div>
		message : <span class="message_field"> <c:choose>
				<c:when test="${success != null}">${success}</c:when>
				<c:when test="${error != null}">${error}</c:when>
			</c:choose>
		</span>
	</div>
	<br>
	<br>
	<table border="1">
		<tr>
			<td>enter your registered email or phone number</td>
			<td><form:input type="text" path="loginUsername" /></td>
		</tr>
		<tr>
			<td>password</td>
			<td><form:input type="text" path="userPassword" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="login user" /></td>
		</tr>
		<tr>
			<td>enter your phone number if you have forgot your password</td>
			<td><form:input type="text" path="forgotPasswordPhoneNumber" /></td>
		</tr>
		<tr>
			<td colspan="2"><input id="login_forgotpwd" type="button" value="get new password" /></td>
		</tr>
	</table>
</form:form>
<br>
<br>
<br>
<br>
<a href="socialLogin?id=googleplus">Google Login</a>
<br>
<br>
<br>
<br>
<a href="socialLogin?id=facebook">Facebook Login</a>