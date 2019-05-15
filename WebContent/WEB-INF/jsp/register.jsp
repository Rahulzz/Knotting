<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<br>
<br>
<form:form id="registerForm" method="post" modelAttribute="memberModel"
	action="./saveMember">
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
		<form:hidden path="phoneVerified" value="${memberModel.phoneVerified}" />
		<tr>
			<td>name</td>
			<td><form:input type="text" path="name" /></td>
		</tr>
		<tr>
			<td>emailId</td>
			<td><form:input type="text" path="emailId" /></td>
		</tr>
		<tr>
			<td>phone number</td>
			<td><form:input type="text" path="phoneNumber" class="onlyNumbers checkAndShowOtp" /></td>
			<td><a class="sendOtp" href="#" style="display: none;">verify
					using otp</a></td>
		</tr>
		<tr>
			<td>password</td>
			<td><form:input type="text" path="userPassword" /></td>
		</tr>
		<tr>
			<td>confirm password</td>
			<td><form:input type="text" path="confirmUserPassword" /></td>
		</tr>
		<tr>
			<td><input type="button" class="submitForm"
				value="register user" /></td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<br>
	<table border="1" class="otpVerifyField" style="display: none;">
		<tr>
			<td>otp confirmation</td>
			<td><input type="text" class="otpConfirmation" data-key="" /></td>
		</tr>
		<tr>
			<td colspan="2"><input class="verifyOtp" type="button"
				value="verify otp" /></td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<br>
	<a href="socialRegister?id=googleplus">Register using Google
		Account</a>
	<br>
	<br>
	<br>
	<br>
	<a href="socialRegister?id=facebook">Register using Facebook
		Account</a>
</form:form>