<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="knotting-vendor-screens col-sm-12 col-md-12 col-lg-12">
	<table>
		<tr>
			<td class="bypass-screen-text">Switching you to PayU Money for
				payment<br>
				<div class="loader">
					<div class="loader-inner line-scale-party">
						<div></div>
						<div></div>
						<div></div>
						<div></div>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<form id="paymentForm" method="post" action="${url}">
		<input id="payment-key" type="hidden" name="key" value="${key}" /> <input
			id="payment-hash" type="hidden" name="hash" value="${hash}" /> <input
			id="payment-txnid" type="hidden" name="txnid" value="${txnid}" /> <input
			id="payment-service-provider" type="hidden" name="service_provider"
			value="${serviceProvider}" /> <input id="payment-amount"
			type="hidden" name="amount" value="${amount}" /> <input
			id="payment-firstname" type="hidden" name="firstname"
			value="${firstname}" /> <input id="payment-email" type="hidden"
			name="email" value="${email}" /> <input id="payment-phone"
			type="hidden" name="phone" value="${phone}" /> <input
			id="payment-productinfo" type="hidden" name="productinfo"
			value="${productinfo}" size="64" /> <input id="payment-surl"
			type="hidden" name="surl" value="${surl}" size="64" /> <input
			id="payment-furl" type="hidden" name="furl" value="${furl}" size="64" />
	</form>
</div>
<script type="text/javascript">
	$('#paymentForm').submit();
</script>