<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="overlay_container col-sm-12 col-md-12 col-lg-12">
	<c:if test="${not empty memberModel}">
		<div
			class="overlay_content_container login_container col-sm-10 col-md-5 col-lg-5">
			<form:form id="loginForm" method="post" modelAttribute="memberModel"
				action="./loginMember">
				<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
					<div>Sign in using your knotting account</div>
					<div class="icon icon-close"></div>
				</div>
				<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
					<div class="field col-sm-12 col-md-5 col-lg-5">
						<form:input type="text" path="loginUsername" class="input"
							placeholder="enter your email or mobile number *" />
					</div>
					<div class="field col-sm-12 col-md-5 col-lg-5">
						<form:input type="password" path="userPassword" class="input"
							placeholder="enter your password *" />
					</div>
					<div class="button calc_line_height col-sm-12 col-md-5 col-lg-2">
						<input type="submit" value="sign in" />
					</div>
				</div>
				<div
					class="overlay_text forgot_password col-sm-12 col-md-12 col-lg-12">
					<a class="iforgotmypassword" href="javascript:">Forgot your
						knotting password?</a>
				</div>
				<div class="overlay_social_button col-sm-12 col-md-5 col-lg-5">
					<div
						class="social_button facebook facebook_login col-sm-12 col-md-11 col-lg-11">
						<div class="icon icon-facebook calc_line_height"></div>
						<div class="calc_line_height">Sign in with Facebook</div>
					</div>
				</div>
				<div class="overlay_social_button col-sm-12 col-md-5 col-lg-5">
					<div
						class="social_button google google_login col-sm-12 col-md-11 col-lg-11">
						<div class="icon icon-google calc_line_height"></div>
						<div class="calc_line_height">Sign in with Google</div>
					</div>
				</div>
				<div class="overlay_text col-sm-12 col-md-12 col-lg-12">
					Don't have a knotting account to display your services? <a
						href="javascript:" class="redirectaction letmeregister">Register
						here</a>
				</div>
			</form:form>
		</div>
	</c:if>
	<div
		class="overlay_content_container forgotpassword_container col-sm-10 col-md-5 col-lg-5">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Your new password will be sent to your mobile</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
			<div class="field col-sm-12 col-md-8 col-lg-8">
				<input id="forgotPasswordNumber" type="text"
					class="input onlyNumbers lengthTen"
					placeholder="enter your registered mobile number" />
			</div>
			<div class="button calc_line_height col-sm-12 col-md-4 col-lg-4">
				<input id="sendForgotPassword" type="button" value="send new password" />
			</div>
		</div>
		<div class="vendor-seperator col-sm-12 hidden-md hidden-lg"></div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">
			Don't have a knotting account to display your services? <a
				href="javascript:" class="redirectaction letmeregister">Register
				here</a>
		</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">
			Already have a knotting account? <a href="javascript:"
				class="redirectaction letmelogin">Get started here</a>
		</div>
	</div>
	<c:if test="${not empty memberModel}">
		<div
			class="overlay_content_container registeruser_container col-sm-10 col-md-5 col-lg-5">
			<form:form id="registerForm" method="post"
				modelAttribute="memberModel" action="./saveMember">
				<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
					<div>Create a knotting account</div>
					<div class="icon icon-close"></div>
				</div>
				<div
					class="overlay_text registermessage col-sm-12 col-md-12 col-lg-12"
					data-default="We require the following details to create your account">
					We require the following details to create your account</div>
				<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
					<div class="field col-sm-12 col-md-6 col-lg-6">
						<form:input type="text" path="name" class="input"
							placeholder="enter a name for your account *" />
					</div>
					<div class="field col-sm-12 col-md-6 col-lg-6">
						<form:input type="text" path="emailId"
							placeholder="enter your email address" />
					</div>
				</div>
				<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
					<div class="field col-sm-12 col-md-6 col-lg-6">
						<form:input type="text" path="phoneNumber"
							class="input onlyNumbers lengthTen"
							placeholder="enter your mobile number *" />
						<form:hidden path="phoneVerified"
							value="${memberModel.phoneVerified}" />
					</div>
					<div class="field noborder hidden-sm col-md-6 col-lg-6"></div>
				</div>
				<div class="overlay_text col-sm-12 col-md-12 col-lg-12">For
					your safety, use a mixed case alphanumeric password with special
					characters. Your password should be atleast 6 characters long.</div>
				<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
					<div class="field col-sm-12 col-md-6 col-lg-6">
						<form:input type="password" path="userPassword" class="input"
							placeholder="set a password *" />
					</div>
					<div class="field col-sm-12 col-md-6 col-lg-6">
						<form:input type="password" path="confirmUserPassword" class="input"
							placeholder="confirm your password *" />
					</div>
				</div>
				<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
					<div class="button calc_line_height col-sm-12 col-md-4 col-lg-4">
						<input id="registeraccount" type="button"
							value="create an account" />
					</div>
					<div class="hidden-xs hidden-sm col-md-8 col-lg-8"></div>
				</div>
				<div class="vendor-seperator col-sm-12 hidden-md hidden-lg"></div>
				<div class="overlay_social_button col-sm-12 col-md-5 col-lg-5">
					<div
						class="social_button facebook facebook_register col-sm-12 col-md-11 col-lg-11">
						<div class="icon icon-facebook calc_line_height"></div>
						<div class="calc_line_height">Register using Facebook</div>
					</div>
				</div>
				<div class="overlay_social_button col-sm-12 col-md-5 col-lg-5">
					<div
						class="social_button google google_register col-sm-12 col-md-11 col-lg-11">
						<div class="icon icon-google calc_line_height"></div>
						<div class="calc_line_height">Register using Google</div>
					</div>
				</div>
				<div class="overlay_text col-sm-12 col-md-12 col-lg-12">
					Already have a knotting account? <a href="javascript:"
						class="redirectaction letmelogin">Get started here</a>
				</div>
			</form:form>
		</div>
	</c:if>
	<div
		class="overlay_content_container verifyotp_container col-sm-10 col-md-5 col-lg-5">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Help us verify your mobile number</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12 otp_timer">
			OTP is valid for<span class="icon icon-timer"></span><span
				class="otpvaliditytimer">14:59</span> mins
		</div>
		<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
			<div class="field col-sm-12 col-md-3 col-lg-3">
				<input type="text" class="input onlyNumbers otpConfirmation"
					placeholder="enter your OTP *" data-key="" />
			</div>
			<div class="button calc_line_height col-sm-12 col-md-2 col-lg-2">
				<input id="verifyotp" type="button" value="verify" />
			</div>
			<div class="col-sm-12 col-md-5 col-lg-5"></div>
			<div
				class="overlay_text calc_line_height col-sm-12 col-md-2 col-lg-2">
				<a id="resendOtp" href="javascript:"><input name="phoneNumber"
					type="hidden" /><span class="icon icon-refresh"></span> resend OTP</a>
			</div>
		</div>
	</div>
	<div
		class="overlay_content_container verifynumber_container col-sm-10 col-md-5 col-lg-5">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Help us verify your number</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
			<div class="field col-sm-12 col-md-9 col-lg-9">
				<input id="verifyNumber" type="text" class="input onlyNumbers"
					placeholder="enter your mobile number" />
			</div>
			<div class="button calc_line_height col-sm-12 col-md-3 col-lg-3">
				<input type="button" value="send otp" />
			</div>
		</div>
		<div class="vendor-seperator col-sm-12 hidden-md hidden-lg"></div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">This
			verification is to avoid messages being sent accidentally.</div>
	</div>
	<div
		class="overlay_content_container informnumber_container col-sm-10 col-md-5 col-lg-5">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Help us verify your number</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
			<div class="field col-sm-12 col-md-9 col-lg-9">
				<input id="verifyNumber" type="text" class="input onlyNumbers"
					placeholder="enter your mobile number" />
			</div>
			<div class="button calc_line_height col-sm-12 col-md-3 col-lg-3">
				<input type="button" value="send otp" />
			</div>
		</div>
		<div class="vendor-seperator col-sm-12 hidden-md hidden-lg"></div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">Our
			vendors will contact you in this number.</div>
	</div>
	<div
		class="overlay_content_container verifymail_container col-sm-10 col-md-5 col-lg-5">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Verify your email address</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">A
			verification mail would have been sent to you while registering with
			us.</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">Follow
			the instructions sent on the mail to verify.</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12"></div>
		<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
			<div class="button calc_line_height col-sm-12 col-md-4 col-lg-4">
				<input id="resend-email-verification" type="button"
					value="resend mail to verify" />
			</div>
			<div class="col-sm-12 col-md-8 col-lg-8"></div>
		</div>
	</div>
	<div
		class="overlay_content_container newservice_info_container col-sm-10 col-md-5 col-lg-5">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Sorry to interrupt</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">The
			following information will be required from your end to add a new
			service.</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">1&nbsp;&nbsp;Details of your service</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">2&nbsp;&nbsp;A
			document to prove the existence of your establishment. It should
			either be your registration document or your PAN card.</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">3&nbsp;&nbsp;5
			photos to best potray your work (optional).</div>
		<div class="overlay_fields col-sm-12 col-md-12 col-lg-12">
			<div class="vendor-seperator col-sm-12 hidden-md hidden-lg"></div>
			<div class="button calc_line_height col-sm-12 col-md-6 col-lg-6">
				<input id="overlay-add-service" type="button"
					value="proceed to add a service" />
			</div>
		</div>
	</div>
	<div
		class="overlay_content_container image-preview_container col-sm-10 col-md-8 col-lg-8">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Image preview</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay-image-preview col-sm-12 col-md-12 col-lg-12">
			<img src="">
		</div>
	</div>
	<div
		class="overlay_content_container executive_payment_container col-sm-10 col-md-5 col-lg-5">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Executive Payment</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">Dear
			executive, please enter your mobile number. We'll validate your
			number to approve payment.</div>
		<div class="overlay_fields col-sm-12 col-md-8 col-lg-8">
			<div class="field border-right col-sm-12 col-md-12 col-lg-12">
				<input id="executive-phone-number" type="text"
					class="input onlyNumbers lengthTen"
					placeholder="enter your mobile number *" />
			</div>
		</div>
		<div class="overlay_fields col-sm-12 col-md-4 col-lg-4">
			<div class="button calc_line_height col-sm-12 col-md-12 col-lg-12">
				<input id="executive-phone-verification" type="button"
					value="validate my number" />
			</div>
			<div class="col-sm-12 col-md-8 col-lg-8"></div>
		</div>
	</div>
	<div
		class="overlay_content_container change_password_container col-sm-10 col-md-5 col-lg-5">
		<div class="overlay_title col-sm-12 col-md-12 col-lg-12">
			<div>Change you password</div>
			<div class="icon icon-close"></div>
		</div>
		<div class="overlay_text col-sm-12 col-md-12 col-lg-12">For your
			safety, use a mixed case alphanumeric password with special
			characters. Your password should be atleast 6 characters long.</div>
		<div class="overlay_fields col-sm-12 col-md-4 col-lg-4">
			<div class="field border-right col-sm-12 col-md-12 col-lg-12">
				<input id="vendor-new-password" type="text" class="input"
					placeholder="set a password *" />
			</div>
		</div>
		<div class="overlay_fields col-sm-12 col-md-4 col-lg-4">
			<div class="field border-right col-sm-12 col-md-12 col-lg-12">
				<input id="vendor-new-confirm-password" type="text" class="input"
					placeholder="confirm your password *" />
			</div>
		</div>
		<div class="overlay_fields col-sm-12 col-md-4 col-lg-4">
			<div class="button calc_line_height col-sm-12 col-md-12 col-lg-12">
				<input id="vendor-change-password" type="button"
					value="change password" />
			</div>
			<div class="col-sm-12 col-md-8 col-lg-8"></div>
		</div>
	</div>
</div>