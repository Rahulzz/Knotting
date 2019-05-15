
$(document).ready(function() {

	gradientPolyFill();

	finalFiles = [];

	errorMessageDuration = 10000;
	infoMessageDuration = 5000;
	quickInfoMessageDuration = 1000;
	successMessageDuration = 5000;

	if(!isMobile()){
		setMenuBoxHeight();
	}
	
	$('img').visibility({
		type       : 'image',
		transition : 'fade in',
		duration   : 1000
	});

	$('img').bind('contextmenu', function(e) {
		return false;
	});

	var screenWidth = $(window).width();

	if($('#search-for-city-name').length > 0){
		google.maps.event.addDomListener(window, 'load', initializeCityAutoComplete);
	}

	prepImageForPreview();

	if($('.knotting-tooltip').length > 0){
		$('.knotting-tooltip').popup();
	}

	interval = null;

	onlyNumberListener();

	$('.lengthTen').keydown(function(event) {
		if($(this).val().length == 10){
			if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) {
				event.preventDefault(); 
			}
		}		
	});
	
	$('.lengthEleven').keydown(function(event) {
		if($(this).val().length == 11){
			if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) {
				event.preventDefault(); 
			}
		}		
	});

	/* Menu padding - starts */
	var menuColumnCount = 1;
	var menuItemWidth = 420;//including margin
	if(screenWidth > 700){
		menuColumnCount = 1;
	}
	if(screenWidth > 1200){
		menuColumnCount = 3;
	}
	if(screenWidth > 1600){
		menuColumnCount = 4;
	}
	var menuPadding = (((screenWidth-20) - (menuItemWidth*menuColumnCount))/2);

	$('.knotting-menu-services-container').css('padding-left',(menuPadding-1)+'px');	
	$('.knotting-menu-services-container').css('padding-right',(menuPadding-1)+'px');
	$('.knotting-menu-vendor-container').css('padding-left',(menuPadding-1)+'px');	
	$('.knotting-menu-vendor-container').css('padding-right',(menuPadding-1)+'px');
	/* Menu padding - ends */

	$('.launch-login').click(function(){
		$('#mobile-menu-options').fadeOut();
		$('.overlay_container').fadeIn(function(){
			$('.blur_section').addClass('blur_content');
			$('.menu-blur-section').removeClass('menu-blur');
			$('.login_container').css('display','table');
			positionOverlay('login_container');
		});
	});

	$('.launch-register').click(function(){
		$('.overlay_container').fadeIn(function(){
			$('.blur_section').addClass('blur_content');
			$('.registeruser_container').css('display','table');
			positionOverlay('registeruser_container');
		});
	});

	$('.iforgotmypassword').click(function(){
		$('.overlay_content_container:visible').fadeOut(function(){
			$('.forgotpassword_container').css('display','table');
			positionOverlay('forgotpassword_container');
			$('.forgotpassword_container').data('source','login_container');			
		});
	});

	$('.letmelogin').click(function(){
		$('.overlay_content_container:visible').fadeOut(function(){
			$('.login_container').css('display','table');
			positionOverlay('login_container');
		});
	});

	$('.letmeregister').click(function(){
		$('.overlay_content_container:visible').fadeOut(function(){
			$('.registeruser_container').css('display','table');
			positionOverlay('registeruser_container');
		});
	});

	$('#share-details-submit').click(function(){
		var shareMobileNumber = $('#share-details-number').val();
		var errorMessage = validatePhoneNumber(shareMobileNumber);

		if(errorMessage == ''){
			$('.overlay_container').fadeIn(function(){
				$('.blur_section').addClass('blur_content');
				$('.verifynumber_container').css('display','table');
				positionOverlay('verifynumber_container');
			});
		}
		else{
			throwAnErrorMessage(errorMessage);
		}

	});

	/* calculate and apply the line-height for elements which has calc_line_height as its class. */
	$('.calc_line_height').each(function(){
		$(this).css('line-height', $(this).height()+'px');
	});

	$('.calc_height').each(function(){
		$(this).css('height', $(this).parent().height()+'px');
	});

	$('.icon-close').click(function(){
		closeOverlay();
	});

	$('.facebook_register').click(function(){
		location.href = 'socialRegister?id=facebook';
	});

	$('.google_register').click(function(){
		location.href = 'socialRegister?id=googleplus';
	});

	$('.facebook_login').click(function(){
		location.href = 'socialLogin?id=facebook';
	});

	$('.google_login').click(function(){
		location.href = 'socialLogin?id=googleplus';
	});

	$('.registeruser_container').find('input[name="name"]').change(function(){	
		$('.registermessage').html($('.registermessage').data('default'));
		var errorMessage = '';
		var name = $('.registeruser_container').find('input[name="name"]').val();
		errorMessage = validateName(name);
		if(errorMessage != ''){
			$('.registermessage').html('<span class="errormsgfield">'+errorMessage+'</span>');
		}
	});

	$('.registeruser_container').find('input[name="emailId"]').change(function(){	
		$('.registermessage').html($('.registermessage').data('default'));
		var errorMessage = '';
		var emailId = $('.registeruser_container').find('input[name="emailId"]').val();
		errorMessage = validateEmail(emailId);
		if(errorMessage == ''){
			var status = checkEmailAvailability(emailId);
			if(status != 'valid'){
				errorMessage = status;
			}
		}
		if(errorMessage != ''){
			throwAnErrorMessage(errorMessage);
		}
	});

	$('.registeruser_container').find('input[name="phoneNumber"]').change(function(){
		$('.registermessage').html($('.registermessage').data('default'));
		var errorMessage = '';
		var phoneNumber = $('.registeruser_container').find('input[name="phoneNumber"]').val();
		errorMessage = validatePhoneNumber(phoneNumber);
		if(errorMessage == ''){
			var status = checkPhoneAvailability(phoneNumber);
			if(status != 'valid'){
				errorMessage = status;
			}
		}
		if(errorMessage != ''){
			throwAnErrorMessage(errorMessage);
		}
	});

	$('.registeruser_container').find('input[name="userPassword"]').change(function(){	
		$('.registermessage').html($('.registermessage').data('default'));
		var errorMessage = '';
		var userPassword = $('.registeruser_container').find('input[name="userPassword"]').val();
		errorMessage = validatePassword(userPassword);
		if(errorMessage != ''){
			throwAnErrorMessage(errorMessage);
		}
	});

	$('.registeruser_container').find('input[name="confirmUserPassword"]').change(function(){	
		$('.registermessage').html($('.registermessage').data('default'));
		var errorMessage = '';
		var userPassword = $('.registeruser_container').find('input[name="userPassword"]').val();
		var confirmUserPassword = $('.registeruser_container').find('input[name="confirmUserPassword"]').val();
		errorMessage = validateConfirmPassword(userPassword,confirmUserPassword);
		if(errorMessage != ''){
			throwAnErrorMessage(errorMessage);
		}
	});

	$('#registeraccount').click(function(){		
		$('.registermessage').html($('.registermessage').data('default'));
		var errorMessage = '';
		var name = $('.registeruser_container').find('input[name="name"]').val();
		var emailId = $('.registeruser_container').find('input[name="emailId"]').val();
		var phoneNumber = $('.registeruser_container').find('input[name="phoneNumber"]').val();
		var userPassword = $('.registeruser_container').find('input[name="userPassword"]').val();
		var confirmUserPassword = $('.registeruser_container').find('input[name="confirmUserPassword"]').val();

		errorMessage = validateName(name);

		if(errorMessage == '' && emailId.length > 0){
			errorMessage = validateEmail(emailId);
		}
		if(errorMessage == '' && emailId.length > 0){
			var status = checkEmailAvailability(emailId);
			if(status != 'valid'){
				errorMessage = status;
			}
		}
		if(errorMessage == ''){
			errorMessage = validatePhoneNumber(phoneNumber);
		}
		if(errorMessage == ''){
			var status = checkPhoneAvailability(phoneNumber);
			if(status != 'valid'){
				errorMessage = status;
			}
		}
		if(errorMessage == ''){
			errorMessage = validatePassword(userPassword);
		}
		if(errorMessage == ''){
			errorMessage = validateConfirmPassword(userPassword,confirmUserPassword);
		}

		if(errorMessage != ''){
			$('.registermessage').html('<span class="errormsgfield">'+errorMessage+'</span>');
		}
		else{
			launchOverlayAndSendOtp(phoneNumber,'registeruser_container');
		}
	});

	$('#verifyotp').click(function(){
		var twoFactorOtpValue = $('.otpConfirmation').val();
		var twoFactorOtpKey = $('.otpConfirmation').data('key');
		var parentScreen = $('.otpConfirmation').data('source');

		var message = verifyOtp(twoFactorOtpKey, twoFactorOtpValue);

		if(message != ''){
			throwAnErrorMessage(message);
		}
		else{
			if(parentScreen == 'registeruser_container'){
				$('input[name="phoneVerified"]').val('Y');
				if($('#registerForm').find('input[name="phoneVerified"]').val() == 'Y'){
					$('#registerForm').submit();
				}
				else{
					closeOverlay();
					if($('.registermessage:visible')){
						throwAnErrorMessage('Error creating your account. Try after sometime.');
					}
				}
			}
			else if(parentScreen == 'edit-stage-1'){
				$('#edit-services-phone1-verify').val('Y');
				$('#edit-services-phone1-verify').data('number',$('#edit-services-phone1').val());
				shiftStage(2);
				$('.services-section-container .services-section:visible').fadeOut(function(){
					$('.services-section-container .services-section[data-stage="2"]').fadeIn(function(){
						closeOverlay();
					});
				});
			}
			else if(parentScreen == 'edit-stage-4'){
				pageNumber = 5;
				$('.payment-box .bill-container .paper-holder .paper').hide();

				$('#edit-services-emergencyPhoneNumber-verify').val('Y');
				$('#edit-services-emergencyPhoneNumber-verify').data('number',$('#emergencyNumber').val());

				if($('.service-options-container .service-option.selected[data-age="new"]').length > 0){
					calculateVendorSubscriptionAmount();
				}
				else{
					displayVendorSubscriptionReceipt();
				}

				shiftStage(pageNumber);
				$('.services-section-container .services-section:visible').fadeOut(function(){
					$('.services-section-container .services-section[data-stage="'+pageNumber+'"]').fadeIn(function(){
						closeOverlay();
						paymentListeners();
					});
				});

				removeMobileOrWebComponents();
			}
			else if(parentScreen == 'profile_edit'){
				$('#profile-phoneVerified').val('Y');
				closeOverlay();
				$('#profileForm').submit();				
			}
			else if(parentScreen == 'check_executive'){
				closeOverlay();
				$('#save_type').val('E');
				monitorUploadProgress();
				prepareServicesFormDataForSave();
				$('#servicesForm').submit();
			}
			else if(parentScreen == 'share_service_details'){
				closeOverlay();
				shareDetailsToUser();
			}
			else if(parentScreen == 'inform_vendor_details'){
				closeOverlay();
				shareDetailsToVendor();
			}
		}
	});

	$('#resendOtp').click(function(){
		var phoneNumber = $(this).find('input[name="phoneNumber"]').val();
		resendOtp(phoneNumber);
	});

	$('.profile-account-edit').click(function(){
		$('.basics-box .profile-account-edit').hide();
		$('.basics-box .profile-account-changepwd').hide();
		$('.basics-box .profile-account-save').show();
		$('.basics-box .profile-account-cancel').show();
		$('.basics-field-container .field input').css('color','#ffffff');
		$('.basics-field-container .field input').prop('disabled',false);
		$('.basics-field-container .field input').eq(0).focus();
	});

	$('.basics-box .profile-account-cancel').click(function(){
		$('.basics-box .profile-account-edit').show();
		$('.basics-box .profile-account-changepwd').show();
		$('.basics-box .profile-account-save').hide();
		$('.basics-box .profile-account-cancel').hide();
		$('.basics-field-container .field input').css('color','#a09e9e');
		$('.basics-field-container .field input').prop('disabled',true);
		$('.basics-field-container .field input').each(function(){
			$(this).val($(this).data('prevvalue'));
		});		
	});

	$('#add_mobile_number').click(function(){
		$('.profile-account-edit').trigger('click');
		$('.basics-field-container').eq(1).find('.field input').focus();
	});

	$('.verify_email').click(function(){
		$('.overlay_container').fadeIn(function(){
			//shanmu - to be changed
			$('.blur_section').addClass('blur_content');
			$('.verifymail_container').css('display','table');
			positionOverlay('verifymail_container');
		});
	});

	$('.add_email').click(function(){
		$('.profile-account-edit').trigger('click');
		$('.basics-field-container').eq(2).find('.field input').focus();
	});

	$('.services-container .services-tabs .tab').click(function(){
		displayServiceTabContent($(this));
	});

	$('.docpath-container .doc-shadow-overlay').mouseover(function(){
		$('.docpath-container .doc-overlay').fadeIn();
	});

	$('.docpath-container .doc-overlay').mouseout(function(){
		$('.docpath-container .doc-overlay').fadeOut();
	});

	$('.stages-container .stage').click(function(){
		if(!$(this).hasClass('current')){
			var stageContent = $(this).data('stagecontent');
			$('.stages-container .stage:visible').removeClass('current');
			$(this).addClass('current');
			$('.vendor-services-stages-content .content-container:visible').fadeOut(function(){
				$('.vendor-services-stages-content .content-container[data-stage="'+stageContent+'"]').fadeIn();
			});
		}
	});

	$('.service-options-container .service-option').click(function(){
		var serviceAge = $(this).data('age');
		var clickedObject = $(this);
		if($(this).hasClass('selected')){
			if(serviceAge == 'old'){
				$('.blur_section').addClass('blur_content');
				$.confirm({
					title: 'Confirm!',
					content: 'You are about to remove a service off your list. You have already paid for this service. Are you sure to remove?',
					confirmButton: 'Yes, please remove',
					confirmButtonClass: 'btn-danger',
					cancelButton: 'NO never !',
					cancelButtonClass: 'btn-success',
					opacity: 1,
					confirm: function(){
						$(clickedObject).removeClass('selected');
						$('.blur_section').removeClass('blur_content');
					},
					cancel: function(){
						$('.blur_section').removeClass('blur_content');
					}
				});
			}
			else{
				$(this).removeClass('selected');
			}
		}
		else{
			$(this).addClass('selected');
//			throwAQuickInfoMessage('You have selected '+$('.service-options-container .service-option.selected').length+' services. Click the proceed button to go to the next stage.');
		}
	});

	$('#overlay-add-service').click(function(){
		closeOverlay();
		location.href = 'addService';
	});

	$('#vendor-entry-stage-1-submit').click(function(){

		var errorMessage = '';
		var pageNumber = 2;
		var isMobile = false;

		errorMessage = validateName($('#edit-services-name').val());

		var phone1 = $('#edit-services-phone1').val();
		if(errorMessage == ''){
			var mobile = validatePhoneNumber(phone1);
			if(mobile == ''){
				isMobile = true;
			}
			else{
				errorMessage = validateFixedLineNumber(phone1);
			}
		}

		var address = $('#edit-services-address').val();
		if(errorMessage == ''){
			errorMessage = validateAddress(address);
		}

		var email = $('#edit-services-email').val();
		if(errorMessage == '' && email.length > 0){
			errorMessage = validateEmail(email);
		}

		var phone2 = $('#edit-services-phone2').val();
		if(errorMessage == '' && phone2.length > 0){
			var mobile = validatePhoneNumber(phone2);
			if(mobile != ''){
				errorMessage = validateFixedLineNumber(phone2);
			}
		}

		var phone3 = $('#edit-services-phone3').val();
		if(errorMessage == '' && phone3.length > 0){
			var mobile = validatePhoneNumber(phone3);
			if(mobile != ''){
				errorMessage = validateFixedLineNumber(phone3);
			}
		}

		var phone4 = $('#edit-services-phone4').val();
		if(errorMessage == '' && phone4.length > 0){
			var mobile = validatePhoneNumber(phone4);
			if(mobile != ''){
				errorMessage = validateFixedLineNumber(phone4);
			}
		}

		var url = $('#edit-services-websiteUrl').val();
		if(errorMessage == '' && url.length > 0){
			errorMessage = validateWebsiteUrl(url);
		}

		if (errorMessage == '' && ($('#file-doc-proof')[0].files.length <= 0 && !$('#doc-proof-file-name-preview').hasClass('uploaded'))) {
			errorMessage = 'You need to upload an image for proof of establishment';
		}

		if(errorMessage == '' && $('#edit-service-selected-cities .tag[data-tagtype="city"]').length <= 0){
			errorMessage = 'Please choose the cities you would serve';
		}

		if(errorMessage == ''){
			var selectedCities = '';
			$('#edit-service-selected-cities .tag[data-tagtype="city"]').each(function(){
				selectedCities += $(this).data('tagvalue')+',';
			});
			$('#edit-cities-serviced').val(selectedCities);

			if($('#edit-services-phone1-verify').val() == 'Y' && ($('#edit-services-phone1-verify').data('number') == phone1)){
				shiftStage(pageNumber);
				$('.services-section-container .services-section:visible').fadeOut(function(){
					$('.services-section-container .services-section[data-stage="'+pageNumber+'"]').fadeIn();
				});
			}
			else{
				if(isMobile){
					launchOverlayAndSendOtp(phone1,'edit-stage-1');
				}
				else{
					$('#edit-services-phone1-verify').val('Y');
					$('#edit-services-phone1-verify').data('number',$('#edit-services-phone1').val());
					shiftStage(2);
					$('.services-section-container .services-section:visible').fadeOut(function(){
						$('.services-section-container .services-section[data-stage="2"]').fadeIn();
					});
				}
			}
		}
		else{
			throwAnErrorMessage(errorMessage);
		}
	});

	$('#vendor-entry-stage-2-submit').click(function(){

		if($('.service-options-container .service-option.selected').length > 0){
			// Clear previous content
			$('.edit-service-detail-tabs').html('');
//			$('.edit-service-detail-content').html('');

			var pageNumber = 3;
			var tabCounter = 0;
			var selectedServiceCode = [];

			$('.service-options-container .service-option.selected').each(function(){
				var serviceCode = $(this).data('id');
				var serviceName = $(this).data('name');
				var tabCode = '';

				selectedServiceCode.push(serviceCode);

				if($('.service-options-container .service-option.selected').length > 1){
					tabCode = '<input type="button" class="edit-service-tab" value="'+serviceName+'" data-service="'+serviceCode+'" />';
					$('.edit-service-detail-tabs').append(tabCode);
					$('.edit-service-detail-tabs').show();
				}
				else{
					$('.edit-service-detail-tabs').hide();
				}
			});

			$('.edit-service-detail-container .edit-service-detail-tabs .edit-service-tab').eq(0).addClass('selected');

			//hide all boxes
			$('.edit-service-detail-box').hide();			

			$('.service-options-container .service-option.selected').each(function(){
				tabCounter++;
				var serviceCode = $(this).data('id');
				var serviceAge = $(this).data('age');
				var contentCode = '';

				if($('.edit-service-detail-box[data-service="'+serviceCode+'"]').length <= 0){

					contentCode = $('.vendor-service-question-container .vendor-service-questions[data-serviceid="'+serviceCode+'"]').clone();

					/* Assign the appropriate name field */
					$(contentCode).find('.modifiable-field').each(function(){
						var bindingPath = 'memberServicesStagingList['+(tabCounter-1)+'].'+$(this).attr('name');
						$(this).attr('name',bindingPath);
						$(this).attr('id','service-field-'+generateRandomNumber());
					});

					/* Assign values if edit operation is to be perfomed on selected services */
					if(serviceAge == 'old'){
						$(contentCode).find('.modifiable-field').each(function(){
							var fieldName = $(this).data('name');
							if($(this).attr('type') == 'checkbox'){
								var values = $('.vendor-service-answer-container .vendor-service-answers[data-serviceid="'+serviceCode+'"] input[data-name="'+fieldName+'"]').val().split(',');
								if($.inArray($(this).val(), values) > -1){
									// setting the checked poperty of checkbox to true doesn't work with semantic UI hence using ATTRIBUTE instead of PROPERTY
									$(this).attr("checked",true);
								}
							}
							else{
								if(typeof fieldName != 'undefined'){
									var value = $('.vendor-service-answer-container .vendor-service-answers[data-serviceid="'+serviceCode+'"] input[data-name="'+fieldName+'"]').val();
									$(this).attr('value', value);
								}
							}
						});

						var imageMaxCount = 20;		
						var validImageCount = 0;
						var previewBox = $(contentCode).find('.edit-service-photo-preview-box');

						for(var i=1; i<=imageMaxCount; i++){
							if($('.vendor-service-answer-container .vendor-service-answers[data-serviceid="'+serviceCode+'"] input[data-name="imagePath'+i+'"]').val() != ''){
								$(contentCode).find('.edit-service-photo-count[data-service="'+serviceCode+'"]').text((++validImageCount)+'/20 images selected');
								var imagePath = $('.vendor-service-answer-container .vendor-service-answers[data-serviceid="'+serviceCode+'"] input[data-name="imagePath'+i+'"]').val();
								$(previewBox).prepend('<div class="preview-image-box" data-index="'+i+'"><div class="preview-image-close icon icon-close ease-element" onclick="removeUploadedImage('+i+')"></div><div class="preview-image" style="background-image: url(\''+imagePath+'\')" data-image="'+imagePath+'"></div></div>');
							}
						}
					}

					/* Hide required sections */
					if(serviceAge != 'old'){
						$(contentCode).find('.modifiable-field-container[data-initialstate="hide"]').each(function(){
							$(this).hide();
						});		
					}

					contentCode = '<div class="edit-service-detail-box col-sm-12 col-md-12 col-lg-12" data-service="'+serviceCode+'" data-counter="'+(tabCounter-1)+'">'+$(contentCode).html()+'</div>';
					$('.edit-service-detail-content').append(contentCode);

					/* Create required event listeners */
					$('.edit-service-detail-box .modifiable-field-container').each(function(){
						if($(this).data('conditionfield') != 'NA'){

							var pathPrefix = $(this).find('input').attr('name').split('.')[0];
							var parentFieldName = pathPrefix+'.'+$(this).data('conditionfield');
							var parentFieldId = $('input[name="'+parentFieldName+'"]').attr('id');
							var childFieldId = $(this).find('input').attr('id');
							var parentFieldAnswers = $(this).data('conditionanswer').split('#');
							parentFieldAnswers = $.map(parentFieldAnswers, function(n,i){return n.toLowerCase();});
							var parentDatatype = $('#'+parentFieldId).closest('.modifiable-field-container').data('fieldtype');

							if(parentDatatype == 'dropdown'){
								$('#'+parentFieldId).on('change', function(){
									if($.inArray($(this).val().toLowerCase(), parentFieldAnswers) > -1){
										$('#'+childFieldId).closest('.modifiable-field-container').show();
									}
									else{
										triggerChildFieldAction(childFieldId);
										$('#'+childFieldId).closest('.modifiable-field-container').hide();
									}
								});

								//perform the check for edit operation
								if(serviceAge == 'old'){
									if($.inArray($('#'+parentFieldId).val().toLowerCase(), parentFieldAnswers) > -1){
										$('#'+childFieldId).closest('.modifiable-field-container').show();
									}
									else{
										triggerChildFieldAction(childFieldId);
										$('#'+childFieldId).closest('.modifiable-field-container').hide();
									}
								}
							}
							else if(parentDatatype == 'checkbox'){
								$('input[name="'+parentFieldName+'"]').on('change', function(){
									if($.inArray($(this).val().toLowerCase(), parentFieldAnswers) > -1){
										if ($(this).prop('checked') == true){
											$('#'+childFieldId).closest('.modifiable-field-container').show();
										}
										else{
											triggerChildFieldAction(childFieldId);
											$('#'+childFieldId).closest('.modifiable-field-container').hide();
										}
									}
								});

								//perform the check for edit operation
								if(serviceAge == 'old'){
									var showField = false;
									$('input[name="'+parentFieldName+'"]:checked').each(function(){
										if($.inArray($(this).val().toLowerCase(), parentFieldAnswers) > -1){
											showField = true;
											return false;
										}
									});

									if (showField){
										$('#'+childFieldId).closest('.modifiable-field-container').show();
									}
									else{
										triggerChildFieldAction(childFieldId);
										$('#'+childFieldId).closest('.modifiable-field-container').hide();
									}
								}
							}
							else if(parentDatatype == 'input'){
								$('#'+parentFieldId).on('change', function(){
									if($.inArray($(this).val().toLowerCase(), parentFieldAnswers) > -1){
										$('#'+childFieldId).closest('.modifiable-field-container').show();
									}
									else{
										triggerChildFieldAction(childFieldId);
										$('#'+childFieldId).closest('.modifiable-field-container').hide();
									}
								});

								//perform the check for edit operation
								if(serviceAge == 'old'){
									if($.inArray($('#'+parentFieldId).val().toLowerCase(), parentFieldAnswers) > -1){
										$('#'+childFieldId).closest('.modifiable-field-container').show();
									}
									else{
										triggerChildFieldAction(childFieldId);
										$('#'+childFieldId).closest('.modifiable-field-container').hide();
									}
								}
							}
						}
					});					

					if(serviceAge == 'old'){
						$('.edit-service-detail-box').each(function(){
							var numOfRange = $('input[data-name=\'priceRange\']').length;
							if(numOfRange > 1){
								var ranges = $('input[data-name=\'priceRange\']').eq(0).val().split(',');	
								$('input[data-name=\'priceRange\']').each(function(index){
									$(this).val(ranges[index]);
								});
							}
						});
					}
				}
				else{
					$('.edit-service-detail-box[data-service="'+serviceCode+'"]').attr('data-counter',(tabCounter-1));
					/* Assign the appropriate name field */
					$('.edit-service-detail-box[data-service="'+serviceCode+'"]').find('.modifiable-field').each(function(){
						var bindingPath = 'memberServicesStagingList['+(tabCounter-1)+'].'+$(this).attr('name');
						$(this).attr('name',bindingPath);
						$(this).attr('id','service-field-'+generateRandomNumber());
					});
				}
			});

			$('.edit-service-detail-box').each(function(){
				var serviceCode = $(this).data('service');
				if(jQuery.inArray(serviceCode,selectedServiceCode) == -1){
					$(this).remove();
				}
			});

			//display only the first box
			$('.edit-service-detail-box').eq(0).show();

			// common event listeners
			onlyNumberListener();
			addServiceListeners();

			$('.edit-service-detail-container .edit-service-detail-tabs .edit-service-tab').unbind('click');
			$('.edit-service-detail-container .edit-service-detail-tabs .edit-service-tab').click(function(){
				if(!$(this).hasClass('selected')){
					$('.edit-service-detail-container .edit-service-detail-tabs .edit-service-tab').removeClass('selected');
					$(this).addClass('selected');
					var serviceId = $(this).data('service');
					$('.edit-service-detail-box:visible').fadeOut(function(){
						$('.edit-service-detail-box[data-service="'+serviceId+'"]').fadeIn();
					});
				}
			});

			$('.file-service-images').unbind('change');
			$('.file-service-images').change(function(){
				uploadedImagePreview($(this));
			});

			shiftStage(pageNumber);
			$('.services-section-container .services-section:visible').fadeOut(function(){
				$('.services-section-container .services-section[data-stage="'+pageNumber+'"]').fadeIn(function(){
					$('.ui.dropdown').dropdown({
						on: 'hover'
					});
					$('.knotting-tooltip').popup();
					$('.ui.checkbox').checkbox();
				});
			});
		}
		else{
			throwAnErrorMessage('You need to select at least one service to proceed.');
		}
	});

	$('#vendor-entry-stage-3-submit').click(function(){
		var mandatoryErrorMessage = '';
		var pageNumber = 4;

		/* Array to hold the names of checkbox */
		var checkboxNames = [];

		$('.edit-service-detail-content .modifiable-field-container').each(function(){
			if($(this).css('display') != 'none'){
				if($(this).find('input').attr('type') != 'checkbox'){
					var value = $(this).find('input.modifiable-field').val();
					if(value == null || value.length <= 0 || value == ''){
						mandatoryErrorMessage = 'Kindly answer all questions to proceed';
						throwAnErrorMessage(mandatoryErrorMessage);
						return false;
					}
				}
				else{
					var name = $(this).find('input.modifiable-field').attr('name');
					if($.inArray(name, checkboxNames) == -1){
						checkboxNames.push(name);
					}
				}
			}
		});

		/* Validate if checkbox have any values selected */
		$.each(checkboxNames, function(index, value) {
			if($('input[name="'+value+'"]:checkbox:checked').length <= 0)
			{
				mandatoryErrorMessage = 'Kindly answer all questions to proceed';
				throwAnErrorMessage(mandatoryErrorMessage);
			}
		});

		if(mandatoryErrorMessage == ''){
			$('.edit-service-detail-content .modifiable-field-container').each(function(){
				if($(this).css('display') == 'none'){
					$(this).find('.dropdown').dropdown('restore defaults');
				}
			});

			shiftStage(pageNumber);
			$('.services-section-container .services-section:visible').fadeOut(function(){
				$('.services-section-container .services-section[data-stage="'+pageNumber+'"]').fadeIn();
			});
		}
	});

	$('#emergencyNumber').change(function(){
		if($(this).val() != $(this).data('prevvalue')){
			$('#edit-services-emergencyPhoneNumber-verify').val('N');
		}
		else{
			var prevVerifyStatus = $('#edit-services-emergencyPhoneNumber-verify').data('prevvalue');
			$('#edit-services-emergencyPhoneNumber-verify').val(prevVerifyStatus);
		}
	});

	$('#edit-services-phone1').change(function(){
		if($(this).val() != $(this).data('prevvalue')){
			$('#edit-services-phone1-verify').val('N');
		}
		else{
			var prevVerifyStatus = $('#edit-services-phone1-verify').data('prevvalue');
			$('#edit-services-phone1-verify').val(prevVerifyStatus);
		}
	});

	$('#vendor-entry-stage-4-submit').click(function(){
		var mandatoryErrorMessage = '';
		var pageNumber = 5;

		if($('.services-section .el-choice-box input[type=button].selected').length <= 0){
			mandatoryErrorMessage = 'Please tell us whether you would join the emergency league';
		}
		var emergencyPhoneNumber = $('#emergencyNumber').val();
		if(mandatoryErrorMessage == '' && $('.services-section .el-choice-box input[type=button].selected').data('value') == 'Y'){
			mandatoryErrorMessage = validatePhoneNumber(emergencyPhoneNumber);
		}

		if(mandatoryErrorMessage == ''){
			if(($('#edit-service-emrgncy-accptd').val() == 'N')
					|| ($('#edit-service-emrgncy-accptd').val() == 'Y' && $('#edit-services-emergencyPhoneNumber-verify').val() == 'Y') 
					|| ($('#edit-services-phone1').val() == $('#emergencyNumber').val())){

				$('.payment-box .bill-container .paper-holder .paper').hide();

				$('#edit-services-emergencyPhoneNumber-verify').val('Y');

				if($('.service-options-container .service-option.selected[data-age="new"]').length > 0){
					calculateVendorSubscriptionAmount();
				}
				else{
					displayVendorSubscriptionReceipt();
				}

				shiftStage(pageNumber);
				$('.services-section-container .services-section:visible').fadeOut(function(){
					$('.services-section-container .services-section[data-stage="'+pageNumber+'"]').fadeIn(function(){
						paymentListeners();
					});
				});

				removeMobileOrWebComponents();
			}
			else{
				$('#edit-services-emergencyPhoneNumber-verify').val('N');
				launchOverlayAndSendOtp(emergencyPhoneNumber,'edit-stage-4');
			}
		}
		else{
			throwAnErrorMessage(mandatoryErrorMessage);
		}
	});

	if($('.retry-payment-options').length > 0){
		var onlineHeight = $('.online-payment').height();
		var executiveHeight = $('.executive-payment').height();
		if(onlineHeight < executiveHeight){
			$('.online-payment .payment-content div').eq(0).css('height',($('.executive-payment .payment-content div').eq(0).height()+15)+'px');
			$('.online-payment .payment-content div').eq(1).css('height',($('.executive-payment .payment-content div').eq(1).height()+15)+'px');
			$('.online-payment .payment-content div').eq(2).css('height',($('.executive-payment .payment-content div').eq(2).height()+15)+'px');
			$('.online-payment .payment-content div').eq(3).css('height',($('.executive-payment .payment-content div').eq(3).height()+5)+'px');
		}
		else{
			$('.executive-payment .payment-content div').eq(0).css('height',($('.online-payment .payment-content div').eq(0).height()+15)+'px');
			$('.executive-payment .payment-content div').eq(1).css('height',($('.online-payment .payment-content div').eq(1).height()+15)+'px');
			$('.executive-payment .payment-content div').eq(2).css('height',($('.online-payment .payment-content div').eq(2).height()+15)+'px');
			$('.executive-payment .payment-content div').eq(3).css('height',($('.online-payment .payment-content div').eq(3).height()+5)+'px');
		}
	}

	if($('#edit-services-address').length > 0){
		$('#edit-services-address').change(function(){
			var address = $(this).val();
			if(address.length > 0){
				plotDrggableMarkerOnMap('edit-services-map', 'edit-services-position', address);
			}
		});
	}

	if($('#edit-services-address').length > 0 && $('#edit-services-address').val().length > 0){
		var address = $('#edit-services-address').val();
		if(address.length > 0){
			plotDrggableMarkerOnMap('edit-services-map', 'edit-services-position', address);
		}
	}

//	$('.zoom-preview').click(function(){
//	var imageSource = $(this).attr('src');
//	if($(this).attr('src').indexOf('default_image.jpg') == -1){
//	$('.blur_section').addClass('blur_content');
//	$('.overlay_container').fadeIn(function(){
//	$('.image-preview_container').fadeIn(function(){
//	$('.image-preview_container img').attr('src',imageSource);
//	});
//	});
//	}
//	});

	/* CSS related script */

	//TODO: check and remove this if not required.
	$('.button, .button-2, .icon-button, .button-file-hide').mousedown(function(){
		$(this).addClass('pressed');
	});

	$('.button, .button-2, .icon-button, .button-file-hide').mouseup(function(){
		$(this).removeClass('pressed');
	});

	$('.stages-container').each(function(){
		var stagesContainerWidth = $(this).width();
		var stageWidth = $(this).find('.stage').eq(0).width();
		var stageCount = $(this).find('.stage').length;
		var connectorCount = $(this).find('.connect-container').length;
		var connectorWidth = (stagesContainerWidth - (stageWidth * stageCount))/connectorCount;
		$(this).find('.connect-container').css('width',(connectorWidth-8)+'px');
	});		

	$('.stages-container').each(function(){
		var stagesContainerWidth = $(this).width() - 1;
		var servicesPerLine = 6;
		if(screenWidth > 700){
			servicesPerLine = 3;
		}
		if(screenWidth > 1200){
			servicesPerLine = 7;
		}
		if(screenWidth > 1600){
			servicesPerLine = 8;
		}
		var widthPerService = stagesContainerWidth/servicesPerLine;			
		$('.vendor-services-options .service-button-container').css('width',widthPerService+'px');
		$('.vendor-services-options .service-button-container').css('height',widthPerService+'px');
	});		

	$('.vendor-services-options .service-button-container .service-overlay .overlay-content').hoverIntent(function(){
		$(this).parent().prev().find('img').addClass('remove-grayscale');
	},
	function(){
		$(this).parent().prev().find('img').removeClass('remove-grayscale');
	});

	$('.switch-item').click(function(){
		if(!$(this).hasClass('pressed')){
			$('.switch-item').removeClass('pressed');
			$(this).addClass('pressed');
		}
	});

//	if($('#home-joinus .home-joinus-particles').length >0){
//	$('#home-joinus .home-joinus-particles').particleground({
//	dotColor: '#1f2122',
//	lineColor: '#1f2122',
//	density: 30000
//	});
//	}

	if($('#home-recents .home-recents-image-container').length > 0){
		var containerWidth = $('#home-recents .home-recents-image-container').width() - 1;
		var imageWidth = 0;
		if(screenWidth > 700 && screenWidth <= 1200){
			imageWidth = containerWidth / 5;
			$('#home-recents .home-recents-image-container .recent-vendor-container').slice(16,30).remove();
		}
		if(screenWidth > 1200 && screenWidth <= 1600){
			imageWidth = containerWidth / 10;
			$('#home-recents .home-recents-image-container .recent-vendor-container').slice(24,30).remove();
		}
		if(screenWidth > 1600){
			imageWidth = containerWidth / 10;
		}
		$('#home-recents .home-recents-image-container figure.effect-apollo').css('width',imageWidth+'px');
		$('#home-recents .home-recents-image-container figure.effect-apollo').css('height',imageWidth+'px');
	}

	$('.recent-vendor-container').hoverIntent(function(){
		$(this).find('.photo-snap-details').fadeIn(200);
	},function(){
		$(this).find('.photo-snap-details').fadeOut(200);
	});

//	$.jInvertScroll(['.horizontal-scroll-container .scroll-element.layer-1', '.horizontal-scroll-container .scroll-element.layer-2', '.horizontal-scroll-container .scroll-element.layer-3']);

	$('#resend-email-verification').click(function(){
		closeOverlay();
		throwAnInfoMessage('Preparing your verification email. Kindly hold on.');
		var email = $('#profile-email').val();
		$.ajax({
			async: true,
			url: '/sendUserVerificationEmail',
			data: {email : email},
			type: 'POST',
			success: function (response) {
				throwASuccessMessage('Verification mail sent to '+email);
			}
		}); 
	});

	$('.basics-box .profile-account-save').click(function(){
		var errorMessage = '';
		var phoneNumber = $('#profileForm input[name="phoneNumber"]');
		var emailId = $('#profileForm input[name="emailId"]');
		if($(phoneNumber).val() != $(phoneNumber).data('prevvalue')){
			$('#profile-phoneVerified').val('N');
			errorMessage = validatePhoneNumber($(phoneNumber).val());		
		}
		if(errorMessage == '' && $(emailId).val() != $(emailId).data('prevvalue')){
			$('#profile-emailVerified').val('N');
			errorMessage = validateEmail($(emailId).val());
		}

		if(errorMessage == ''){
			if($(phoneNumber).val() != $(phoneNumber).data('prevvalue')){
				launchOverlayAndSendOtp($(phoneNumber).val(), 'profile_edit');		
			}
			else{
				$('#profileForm').submit();
			}
		}
		else{
			throwAnErrorMessage(errorMessage);
		}

	});

	$('#executive-phone-verification').click(function(){		
		var errorMessage = '';
		var phoneNumber = $('#executive-phone-number').val();

		errorMessage = validatePhoneNumber(phoneNumber);

		if(errorMessage == ''){
			$.ajax({
				async: true,
				url: '/checkifKnottingExecutive',
				type: 'POST',
				data: {'phoneNumber' : phoneNumber},
				success: function (response) {
					if(response == 'N'){
						throwAnErrorMessage('Your number doesn\'t match the number of our executives.');
					}
					else if(response == 'Y'){
						$('#executive_number').val(phoneNumber);
						launchOverlayAndSendOtp(phoneNumber, 'check_executive');
					}
					else if(response == 'X'){
						throwAnErrorMessage('This number doesn\'t exist in our system. Kindly proceed with online payment.');
					}
				}
			});
		}
		else{
			throwAnErrorMessage(errorMessage);
		}
	});

	$('.profile-account-changepwd').click(function(){
		vendorChangePassword();
	});

	$('#vendor-change-password').click(function(){
		var errorMessage = '';
		var password = $('#vendor-new-password').val();
		var confirmPassword = $('#vendor-new-confirm-password').val();

		errorMessage = validatePassword(password);

		if(errorMessage == ''){
			errorMessage = validateConfirmPassword(password,confirmPassword);
		}

		if(errorMessage == ''){
			changeUserPassword(password);
		}
		else{
			throwAnErrorMessage(errorMessage);
		}
	});

	$('.edit-service-back').click(function(){
		var pageNumber = $(this).data('page');
		shiftStage(pageNumber);
		$('.services-section-container .services-section:visible').fadeOut(function(){
			$('.services-section-container .services-section[data-stage="'+pageNumber+'"]').fadeIn();
		});
	});

	$('.knotting-menu-item-link').hoverIntent(function(){
		var menuItem = $(this).data('item');
		var topValue = Math.abs(parseInt($('.knotting-menu-'+menuItem).css('top')));
		var velocitySpeed = (topValue * 1);
		$('.knotting-menu-'+menuItem).velocity({top:'0px'}, { duration: velocitySpeed });
	});

	$('.knotting-menu-options').on('mouseleave', function(e) {
		setMenuBoxHeight();
	});

	$('.home-banner-filter .filter-option').hoverIntent(function(){
		if(!$(this).find('.filter-answer-section').is(":visible")){
			$('.home-banner-filter .filter-option').removeClass('viewed');
			$(this).addClass('viewed');
			$('.filter-answer-section:visible').velocity('slideUp', {duration: 300});
			$(this).find('.filter-answer-section').velocity('slideDown', {duration: 300});
		}
	}, function(){});

	$('.knotting-result-screen .criteria-item').hoverIntent(function(){
		if(!$(this).find('.filter-answer-section').is(":visible")){
			$('.filter-answer-section:visible').velocity('slideUp', {duration: 300});
			$(this).find('.filter-answer-section').velocity('slideDown', {duration: 300});
		}
	}, function(){});

	$('.filter-answer-section').on('mouseleave', function() {
		$(this).slideUp();
		$(this).parent().removeClass('viewed');
	});

	$('.home-banner-overlay').click(function(){
		$('.filter-answer-section').each(function(){
			$(this).slideUp();
			$(this).parent().removeClass('viewed');
		});
	});

	$('.knotting-result-screen').click(function(){
		$('.filter-answer-section').each(function(){
			$(this).slideUp();
		});
	});

	actionListenerForFilterOptions();

	if($('.result-item-container').length > 0){
		var resultItemContainerWidth = $('.result-results-section-items').width();
		var resultColumnCount = 1;
		var resultItemWidth = $('.result-item-container').width();

		if(screenWidth > 700){
			resultColumnCount = 2;
		}
		if(screenWidth > 1200){
			resultColumnCount = 4;
		}
		if(screenWidth > 1600){
			resultColumnCount = 4;
		}
		var gutterSpace = ((resultItemContainerWidth - (resultItemWidth*resultColumnCount))/ (resultColumnCount - 1));

		$('.result-results-section-items').masonry({
			itemSelector: '.result-item-container',
			gutter: gutterSpace,
			horizontalOrder: true,
			stagger: 30
		});
	}

	$('.knotting-result-screen .result-results-section .result-item-container').hoverIntent(function(){
		$(this).addClass('hovered');
	},
	function(){
		$(this).removeClass('hovered');
	});

	$('#home-inform').click(function(){
		var errorMessage = '';		
		errorMessage = validateFilterCriteria();
		if(errorMessage != ''){
			throwAnErrorMessage(errorMessage);
		}
		else{
			$('.overlay_container').fadeIn(function(){
				$('.blur_section').addClass('blur_content');
				$('.informnumber_container').css('display','table');
				positionOverlay('informnumber_container');
			});
		}
	});

	$('.informnumber_container input[type=button]').click(function(){
		var phoneNumber = $('.informnumber_container #verifyNumber').val();
		var errorMessage = validatePhoneNumber(phoneNumber);

		if(errorMessage == ''){
			$('#share-details-to').val(phoneNumber);
			launchOverlayAndSendOtp(phoneNumber, 'inform_vendor_details');
		}
		else{
			throwAnErrorMessage(errorMessage);
		}
	});

	$('#home-search').click(function(){
		var errorMessage = '';		
		errorMessage = validateFilterCriteria();
		if(errorMessage != ''){
			throwAnErrorMessage(errorMessage);
		}
		else{
			//TODO: check if filter or sort is available and frame url accordingly
			var service = $('.ul-service li.selected').attr('id');
			var city = $('.ul-city li.selected').text().toLowerCase();
			var range = $('.ul-range li.selected').text().split(' ')[0].toLowerCase();
			var urgency = $('.ul-urgency li.selected').attr('id');
			var filter = getUrlParameter('filter');
			var sort = getUrlParameter('sort');
			var callUrl = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency;

			if(typeof(filter) != 'undefined'){
				callUrl += '&filter='+filter;
			}
			if(typeof(sort) != 'undefined'){
				callUrl += '&sort='+sort;
			}

			location.href = callUrl;
		}
	});

	$('.result-more-section .more-button').click(function(){		
		fetchNextPageOfResult();
	});

	$('.details-banner-overlay img').hoverIntent(function(){
//		$('.details-banner-img').removeClass('grayscale');
		$('.picture-frame').addClass('pause-animation');
	},
	function(){
//		$('.details-banner-img').addClass('grayscale');
		$('.picture-frame').removeClass('pause-animation');
	});

	if($('.details-banner-header').length > 0){
		setInterval( function(){
			var oldValue = parseInt($('.details-banner-header .details-age .secs').text());
			var newValue = oldValue + 1;
			var newStringValue = ''+ newValue;
			if(newStringValue.length > 7){
				$('.details-banner-header .details-age .days').text((parseInt($('.details-banner-header .details-age .days').text()) + 1));
				$('.details-banner-header .details-age .secs').text('0000000');
			}
			else{
				newStringValue = pad(7,newValue,'0');
				$('.details-banner-header .details-age .secs').text(newStringValue);
			}
		}, 1);
	}

	if($('.details-banner-header').length > 0){
		if($('.details-section-left').height() > $('.details-section-right').height()){
			$('.details-section-left').css('border-right','1px dashed #2c2d2f');
		}
		else{
			$('.details-section-right').css('border-left','1px dashed #2c2d2f');
		}
	}

	$('.verifynumber_container input[type=button]').click(function(){
		var phoneNumber = $('.verifynumber_container #verifyNumber').val();
		var errorMessage = validatePhoneNumber(phoneNumber);

		if(errorMessage == ''){
			$('#share-details-from').val(phoneNumber);
			launchOverlayAndSendOtp(phoneNumber, 'share_service_details');
		}
		else{
			throwAnErrorMessage(errorMessage);
		}
	});

	// home scroll setup
	if($('.knotting-home-screens').length > 0){
		var windowWidth =  $(window).width();
		var layer1Width = $('.horizontal-scroll-container .layer-1').width() - windowWidth;
		var layer2Width = $('.horizontal-scroll-container .layer-2').width() - windowWidth;
		var layer3Width = $('.horizontal-scroll-container .layer-3').width() - windowWidth;

		var controller = new ScrollMagic.Controller();

		var tween = new TimelineMax().add([TweenMax.to('.horizontal-scroll-container .layer-1', 20, {left: '-'+layer1Width+'px', ease: Power0.easeOut}),
		                                   TweenMax.to('.horizontal-scroll-container .layer-2', 20, {left: '-'+layer2Width+'px', ease: Power0.easeOut}),
		                                   TweenMax.to('.horizontal-scroll-container .layer-3', 20, {left: '-'+layer3Width+'px', ease: Power0.easeOut})]);

		new ScrollMagic.Scene({triggerElement: '.horizontal-scroll-container', duration: 250}).on('start', function () {
		}).on('end', function () {

		}).setTween(tween).addTo(controller);
	}

	if($('.hover-content .hover-step-container').length > 0){
		var parentWidth = $('.hover-content .hover-step-container').width();
		// 8px pixels subtracted considering the step border
		var remainingSpace = parentWidth - ($('.hover-content .hover-step-container .step-box').width() * 4) - 1;
		var connectorWidth = remainingSpace / 7;

		$('.hover-detail-section .hover-content .hover-step-container .connector-box').css('width', connectorWidth+'px');
		$('.hover-detail-section').each(function(){
			$(this).find('.connector-box').first().css('width', (connectorWidth*2)+'px');
			$(this).find('.connector-box').last().css('width', (connectorWidth*2)+'px');
		});

	}

//	$('#hover-detail-holder').mousemove(function(e){
//	var overlayWidth = e.pageX - $(this).offset().left;
//	// condition to make sure the holder's shadow doesn't get cut by the parent container.
//	if(overlayWidth >= 10 && overlayWidth <= $('#hover-detail-holder').width()){
//	$('#hover-detail-holder .holder-stick').css('left', (overlayWidth - 10)+'px');
//	$('#hover-detail-user').css('width', overlayWidth+'px');
//	}
//	});

	$('.hover-detail-container .hover-parent-section .hover-detail-section').each(function(){
		var parentWidth = $(this).width();
		$(this).find('.hover-title').css('width', parentWidth+'px');
		$(this).find('.hover-content').css('width', parentWidth+'px');
	});

	$('#flow-user').hover(function(){
		$('.user-connect').addClass('activate-user-flow');
	},
	function(){
		$('.user-connect').removeClass('activate-user-flow');
	});

	$('#flow-vendor').hover(function(){
		$('.vendor-connect').addClass('activate-vendor-flow');
	},
	function(){
		$('.vendor-connect').removeClass('activate-vendor-flow');
	});

	fitCriteriaDropdownBox();

	$('.dynamic-content-filter .ui.dropdown').dropdown({
		useLabels: false,
		on: 'hover',
		onChange: function(){
			$('#filter-apply').addClass('active');
			$('#filter-reset').addClass('active');
			$(this).attr('data-changed','yes');
			$('#show-filter').removeClass('no-badge');
			$('#show-filter').attr('data-badge',$('.filter-element[data-changed="yes"]').length);
		}
	});

//	$('.ui.dropdown').dropdown();

	if($('.dynamic-content-section').length > 0){
		setFilterAndSortValues();

		calculateFilterBoxSize();

		$('.range-slider').each(function(index){
			var lowerRange = parseInt($(this).data('lower'));
			var higherRange = parseInt($(this).data('higher'));
			var steps = 1;

			if(higherRange <= 100){
				steps = 10;
			}
			else if(higherRange <= 1000){
				steps = 100;
			}
			else if(higherRange <= 10000){
				steps = 1000;
			}
			else if(higherRange <= 100000){
				steps = 10000;
			}
			else if(higherRange <= 1000000){
				steps = 50000;
			}

			var label;

			function Label (container) {
				this.$label1 = $(container).find(".irs-from");
				this.$label2 = $(container).find(".irs-to");
				this.active = false;
				this.ACTIVE = "irs-active";
			}

			Label.prototype = {   
					start: function () {
						if (!this.active) {
							this.active = true;
							this.$label1.addClass(this.ACTIVE);
							this.$label2.addClass(this.ACTIVE);
						}
					},

					end: function () {
						this.$label1.removeClass(this.ACTIVE);
						this.$label2.removeClass(this.ACTIVE);
						this.active = false;
					}
			};

			$(this).ionRangeSlider({
				type: "double",
				grid: true,
				hide_min_max: true,
				min: lowerRange,
				max: higherRange,
				prettify_enabled: false,
				grid_num: 3,
//				step: steps,
				force_edges: true,
				values_separator: " to ",
				onStart: function (data) {
					label = new Label(data.slider);
				},
				onChange: function () {
					$('#filter-apply').addClass('active');
					$('#filter-reset').addClass('active');
					$('.range-slider').eq(index).attr('data-changed','yes');
					$('#show-filter').removeClass('no-badge');
					$('#show-filter').attr('data-badge',$('.filter-element[data-changed="yes"]').length);
				}
			});

			//label gets intialized in the onstart method of ionrangeslider
			$(this).parent().parent().hoverIntent(function(){
				label.start();
			},
			function(){
				label.end();
			});
		});
	}

	$('#filter-reset').click(function(){
		if($(this).hasClass('active')){
			resetFilter();
		}
	});

	$('#show-filter').click(function(){
		if($('.filter-dynamic-section').is(':visible')){
			$('.filter-dynamic-section').velocity('slideUp', {duration: 300});
			resetFilter();
		}
		else{
			$('.filter-dynamic-section').velocity('slideDown', {duration: 300});
		}
	});

	$('#show-sort').click(function(){
		if($('.sort-dynamic-section').is(':visible')){
			$('.sort-dynamic-section').velocity('slideUp', {duration: 300});
			resetSort();
		}
		else{
			$('.sort-dynamic-section').velocity('slideDown', {duration: 300});
		}
	});

	$('#filter-apply').click(function(){
		if($(this).hasClass('active')){
			var service,city,range,urgency;

			if($('#vendor-result').length > 0){
				service = $('.ul-service li.selected').attr('id');
				city = $('.ul-city li.selected').text().toLowerCase();
				range = $('.ul-range li.selected').text().split(' ')[0].toLowerCase();
				urgency = $('.ul-urgency li.selected').attr('id');
			}
			else{
				service = getUrlParameter('service');
				city = '';
				range = '';
				urgency = '';
			}

			var filterCriteria = frameFilterCriteria();

			var url = '';

			if($('#vendor-result').length > 0){
				if(typeof(getUrlParameter('sort')) != 'undefined'){
					url = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&filter='+filterCriteria+'&sort='+getUrlParameter('sort');
				}
				else{
					url = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&filter='+filterCriteria;
				}
			}
			else{
				if(typeof(getUrlParameter('sort')) != 'undefined'){
					url = 'serviceResult?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&filter='+filterCriteria+'&sort='+getUrlParameter('sort');
				}
				else{
					url = 'serviceResult?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&filter='+filterCriteria;
				}
			}

			location.href = url;
		}
	});

	$('#filter-remove').click(function(){
		if($(this).hasClass('active')){
			var service,city,range,urgency;

			if($('#vendor-result').length > 0){
				service = $('.ul-service li.selected').attr('id');
				city = $('.ul-city li.selected').text().toLowerCase();
				range = $('.ul-range li.selected').text().split(' ')[0].toLowerCase();
				urgency = $('.ul-urgency li.selected').attr('id');
			}
			else{
				service = getUrlParameter('service');
				city = '';
				range = '';
				urgency = '';
			}

			var url = '';

			if($('#vendor-result').length > 0){
				if(typeof(getUrlParameter('sort')) != 'undefined'){
					url = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&sort='+getUrlParameter('sort');
				}
				else{
					url = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency;
				}
			}
			else{
				if(typeof(getUrlParameter('sort')) != 'undefined'){
					url = 'serviceResult?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&sort='+getUrlParameter('sort');
				}
				else{
					url = 'serviceResult?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency;
				}
			}

			location.href = url;
		}
	});

	$('.sort-box').click(function(){
		if(!$(this).hasClass('active') && !$(this).hasClass('dont-select')){
			$('.sort-box').removeClass('active');
			$(this).addClass('active');
			$('#sort-apply').addClass('active');
			$('#sort-reset').addClass('active');
			$('#show-sort').removeClass('no-badge');
		}
	});

	$('#sort-reset').click(function(){
		if($(this).hasClass('active')){
			resetSort();
		}
	});

	$('#sort-remove').click(function(){
		if($(this).hasClass('active')){

			var service,city,range,urgency;

			if($('#vendor-result').length > 0){
				service = $('.ul-service li.selected').attr('id');
				city = $('.ul-city li.selected').text().toLowerCase();
				range = $('.ul-range li.selected').text().split(' ')[0].toLowerCase();
				urgency = $('.ul-urgency li.selected').attr('id');
			}
			else{
				service = getUrlParameter('service');
				city = '';
				range = '';
				urgency = '';
			}

			var url = '';

			if($('#vendor-result').length > 0){
				if(typeof(getUrlParameter('filter')) != 'undefined'){
					url = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&filter='+getUrlParameter('filter');
				}
				else{
					url = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency;
				}
			}
			else{
				if(typeof(getUrlParameter('filter')) != 'undefined'){
					url = 'serviceResult?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&filter='+getUrlParameter('filter');
				}
				else{
					url = 'serviceResult?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency;
				}
			}

			location.href = url;
		}
	});

	$('#sort-apply').click(function(){
		if($(this).hasClass('active')){

			var service,city,range,urgency;

			if($('#vendor-result').length > 0){
				service = $('.ul-service li.selected').attr('id');
				city = $('.ul-city li.selected').text().toLowerCase();
				range = $('.ul-range li.selected').text().split(' ')[0].toLowerCase();
				urgency = $('.ul-urgency li.selected').attr('id');
			}
			else{
				service = getUrlParameter('service');
				city = '';
				range = '';
				urgency = '';
			}

			var sortCriteria = $('.sort-box.active').attr('data-sorttype');

			var url = '';

			if($('#vendor-result').length > 0){
				if(typeof(getUrlParameter('filter')) != 'undefined'){
					url = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&filter='+getUrlParameter('filter')+'&sort='+sortCriteria;
				}
				else{
					url = 'result?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&sort='+sortCriteria;
				}
			}
			else{
				if(typeof(getUrlParameter('filter')) != 'undefined'){
					url = 'serviceResult?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&filter='+getUrlParameter('filter')+'&sort='+sortCriteria;
				}
				else{
					url = 'serviceResult?service='+service+'&city='+city+'&range='+range+'&urgency='+urgency+'&sort='+sortCriteria;
				}
			}

			location.href = url;
		}
	});

	$('#mobile-menu').click(function(){
		$('.menu-blur-section').addClass('menu-blur');
		$('#mobile-menu-options').fadeIn();
	});

	$('#close-mobile-menu').click(function(){
		$('.menu-blur-section').removeClass('menu-blur');
		$('#mobile-menu-options').fadeOut();
	});

	$('.service-item.add-new-service').click(function(){
		addNewServiceInfoOverlay();
	});

	if($('.scroll-box').length > 0){
		$('.scroll-box').mCustomScrollbar({
			theme:"light",
			autoHideScrollbar: true,
		});
	}

	$('.status-change').click(function(){
		var serviceId = $(this).data('id');
		$('#status'+serviceId).submit();
	});

	if($('.service-list-answers-box .service-images').length > 0){
		var screenWidth = $(window).width();
		var imagePerLine = 5;

		if(screenWidth < 1200){
			imagePerLine = 4;
		}

		var containerWidth = $('.service-list-answers-box .service-images:visible').width();
		var margin = parseInt($('.service-list-answers-box .picture-box').css('margin-right'));
		var imageWidth = (((containerWidth-(margin*(imagePerLine-1)))/imagePerLine) - 1);
		$('.service-list-answers-box .picture-box').css('width', imageWidth+'px');
		$('.service-list-answers-box .picture-box').css('height', imageWidth+'px');
	}

	if($('.view-service-item-box .service-list-header-container input').length > 0){
		$('.view-service-item-box .service-list-header-container input').eq(0).addClass('selected');

		$('.view-service-item-box .service-list-header-container input').click(function(){
			if(!$(this).hasClass('selected')){
				var sectionId = $(this).data('call');
				$('.view-service-item-box .service-list-header-container input').removeClass('selected');
				$(this).addClass('selected');
				$('.service-list-answers-container .service-list-answers-box:visible').fadeOut(function(){
					$('.service-list-answers-container .service-list-answers-box[data-section="'+sectionId+'"]').fadeIn();
				});
			}
		});
	}

	if($('.map-container').length > 0){
		var mapOptions = {
				backgroundColor : "#151718",
				zoom : 11,
				center : new google.maps.LatLng(13.0478223,
						80.0689271),
						disableDefaultUI : true,
						styles : [{"featureType": "all","elementType": "labels.text.fill","stylers": [{"saturation": 36},{"color": "#000000"},{"lightness": 40}]},{"featureType": "all","elementType": "labels.text.stroke","stylers": [{"visibility": "on"},{"color": "#000000"},{"lightness": 16}]},{"featureType": "all","elementType": "labels.icon","stylers": [{"visibility": "off"}]},{"featureType": "administrative","elementType": "all","stylers": [{"visibility": "on"},{"weight": "0.01"}]},{"featureType": "administrative","elementType": "geometry.fill","stylers": [{"color": "#4a4747"},{"lightness": 20}]},{"featureType": "administrative","elementType": "geometry.stroke","stylers": [{"color": "#000000"},{"lightness": 17},{"weight": 1.2}]},{"featureType": "administrative","elementType": "labels","stylers": [{"visibility": "on"}]},{"featureType": "landscape","elementType": "all","stylers": [{"visibility": "on"}]},{"featureType": "landscape","elementType": "geometry","stylers": [{"color": "#000000"},{"lightness": 20}]},{"featureType": "landscape","elementType": "geometry.fill","stylers": [{"color": "#151718"}]},{"featureType": "landscape","elementType": "labels","stylers": [{"visibility": "off"}]},{"featureType": "poi","elementType": "all","stylers": [{"visibility": "off"}]},{"featureType": "poi","elementType": "geometry","stylers": [{"color": "#000000"},{"lightness": 21}]},{"featureType": "poi","elementType": "geometry.fill","stylers": [{"color": "#151718"},{"visibility": "off"}]},{"featureType": "poi","elementType": "labels","stylers": [{"visibility": "off"}]},{"featureType": "road","elementType": "all","stylers": [{"visibility": "on"}]},{"featureType": "road","elementType": "geometry","stylers": [{"visibility": "on"}]},{"featureType": "road","elementType": "geometry.fill","stylers": [{"color": "#2b2424"},{"visibility": "on"}]},{"featureType": "road","elementType": "geometry.stroke","stylers": [{"weight": "0.41"}]},{"featureType": "road","elementType": "labels","stylers": [{"visibility": "off"}]},{"featureType": "road","elementType": "labels.text","stylers": [{"visibility": "on"},{"color": "#4b5053"}]},{"featureType": "road","elementType": "labels.text.fill","stylers": [{"visibility": "off"}]},{"featureType": "road.highway","elementType": "geometry.fill","stylers": [{"color": "#000000"},{"lightness": 17}]},{"featureType": "road.highway","elementType": "geometry.stroke","stylers": [{"color": "#000000"},{"lightness": 29},{"weight": 0.2}]},{"featureType": "road.arterial","elementType": "geometry","stylers": [{"color": "#000000"},{"lightness": 18}]},{"featureType": "road.local","elementType": "geometry","stylers": [{"color": "#000000"},{"lightness": 16}]},{"featureType": "transit","elementType": "all","stylers": [{"visibility": "on"}]},{"featureType": "transit","elementType": "geometry","stylers": [{"color": "#1f2426"},{"lightness": 19}]},{"featureType": "transit","elementType": "geometry.fill","stylers": [{"color": "#ff0000"},{"visibility": "off"}]},{"featureType": "transit","elementType": "labels","stylers": [{"visibility": "off"}]},{"featureType": "water","elementType": "all","stylers": [{"visibility": "off"},{"color": "#17191a"}]},{"featureType": "water","elementType": "geometry","stylers": [{"color": "#000000"},{"lightness": 17}]},{"featureType": "water","elementType": "geometry.fill","stylers": [{"color": "#151718"},{"visibility": "off"}]},{"featureType": "water","elementType": "labels","stylers": [{"visibility": "off"}]}]
		};
		var mapElement = document
		.getElementById('edit-services-map');
		addServiceMap = new google.maps.Map(mapElement,
				mapOptions);
	}

	$('#file-doc-proof').change(function(){
		checkDocumentProof();
	});

	$('.services-section .el-choice-box input[type=button]').click(function(){
		if(!$(this).hasClass('selected')){
			var elStatus = $(this).data('value');
			$('.services-section .el-choice-box input.selected').removeClass('selected');
			$(this).addClass('selected');			
			if(elStatus == 'Y'){
				$('#edit-service-emrgncy-accptd').val('Y');
				$('#emergencyNumber').prop('disabled',false);
			}
			else{
				$('#edit-service-emrgncy-accptd').val('N');
				$('#emergencyNumber').val('');
				$('#emergencyNumber').prop('disabled',true);
			}
		}
	});

	$('.services-section-container .services-section').eq(0).show();

	if($('#support-table').length > 0){
		$('#support-table').DataTable();
	}

	$('#approve-entry').click(function(){
		var entryId = $('#entry-id').val();
		location.href = 'applyAction?id='+entryId+'&actionType=APPROVE';

	});

	$('#reject-entry').click(function(){
		if($.trim($('#rejection-details').val()) != ''){
			var entryId = $('#entry-id').val();
			location.href = 'applyAction?id='+entryId+'&actionType=REJECT&rejectionDetail='+$('#rejection-details').val();
		}
		else{
			alert('Kindly fill the Rejction details.');
		}
	});

	if(isMobile() && $('.profile-service-item-box').length > 0){
		var serviceItemCount = $('.profile-service-item-box .content .service-item').length;
		var itemWidth = $('.profile-service-item-box .content .service-item').width() + (parseInt($('.profile-service-item-box .content .service-item').css('margin-left'))*2);
		var contentWidth = (serviceItemCount * itemWidth) + (parseInt($('.profile-service-item-box').css('padding-left'))*2);
		$('.profile-service-item-box .content').css('width',contentWidth+'px');
	}

	if(!isMobile() && $('.hidden-bg-container.view-service-hiddenbg .basics-section-box .basics-box').length > 0){
		var maxHeight = 0;
		$('.hidden-bg-container.view-service-hiddenbg .basics-section-box .basics-box').each(function(){
			var itemHeight = $(this).outerHeight();			
			if(itemHeight > maxHeight){
				maxHeight = itemHeight;
			}
		});
		$('.hidden-bg-container.view-service-hiddenbg .basics-section-box .basics-box').css('height',maxHeight+'px');
	}

	if($('#file-doc-proof').length > 0 && $('#file-doc-proof').val().length > 0){
		checkDocumentProof();
	}

	$('#sendForgotPassword').click(function(){
		var errorMessage = validatePhoneNumber($('#forgotPasswordNumber').val());
		if(errorMessage == ''){
			var phoneNumber = $('#forgotPasswordNumber').val();
			throwAQuickInfoMessage('Preparing your new password');
			$.ajax({
				async: false,
				url: '/forgotPassword',
				type: 'POST',
				data: {'phoneNumber' : phoneNumber},
				success: function (response) {
					var result = response.split(':');
					if(result[0] == 'SUCCESS'){
						throwASuccessMessage(result[1]);
						$('.overlay_content_container:visible').fadeOut(function(){
							$('.login_container').css('display','table');
							positionOverlay('login_container');
						});
					}
					else{
						throwAnErrorMessage(result[1]);
					}
				}
			}); 
		}
		else{
			throwAnErrorMessage(errorMessage);
		}
	});

	$('.knotting-vendor-service-screens .detail-info .detail-answer input').each(function(){
		var answer = $(this).val();
		$(this).next().prepend(answer+' ');
	});
		
	if($('.sticky-page').length > 0){
		$('.title-box-container').visibility({
	        type: 'fixed'
		});
	
		var titleBoxHeight = $('.title-box-container').outerHeight();
		$('.data-box-container .ui.sticky').sticky({
			context: '.information-container',
			offset: titleBoxHeight
		});
	}

});

function checkPhoneAvailability(phoneNumber){
	var status = '';
	$.ajax({
		async: false,
		url: '/checkPhoneAvailability',
		type: 'POST',
		data: {'phoneNumber' : phoneNumber},
		success: function (response) {
			if(response == 'AVL'){
				status = 'valid';
			}
			else if(response == 'NTAVL'){
				status = 'Your mobile number already exists in our system.';
			}
		}
	}); 

	return status;
}

function checkEmailAvailability(emailId){
	var status = '';
	$.ajax({
		async: false,
		url: '/checkEmailAvailability',
		type: 'POST',
		data: {'emailId' : emailId},
		success: function (response) {
			if(response == 'AVL'){
				status = 'valid';
			}
			else if(response == 'NTAVL'){
				status = 'Your email address already exists in our system.';
			}
		}
	}); 

	return status;
}

function sendOtp(phoneNumber,sourcePage){
	$('.overlay_content_container:visible').fadeOut(function(){
		$('.verifyotp_container input[name="phoneNumber"]').val(phoneNumber);
		if(sourcePage != ''){
			$('.verifyotp_container').data('source',sourcePage);
		}
		$('.verifyotp_container').fadeIn(function(){
			$.ajax({
				async: false,
				url: '/sendOtp',
				type: 'POST',
				data: {'phoneNumber' : phoneNumber},
				success: function (response) {
					var jsonObject = jQuery.parseJSON(response);
					$('.otpConfirmation').data('key',jsonObject.Details);
					if(jsonObject.Status == 'Success'){
						$('.otpstatusmessage').html('SMS sent to '+phoneNumber);
						$('.otpvaliditytimer').html('14:59');
						$('.otp_timer').css('opacity','1');
						otpCountdown();
					}
					else{
						$('.otpstatusmessage').html('There was a problem in sending the OTP. Please try again after sometime.');
					}
				}
			}); 
		});
	});
}

function resendOtp(phoneNumber){
	throwAQuickInfoMessage('trying to send a new OTP');
	$('.verifyotp_container input[name="phoneNumber"]').val(phoneNumber);
	$.ajax({
		async: false,
		url: '/sendOtp',
		type: 'POST',
		data: {'phoneNumber' : phoneNumber},
		success: function (response) {
			var jsonObject = jQuery.parseJSON(response);
			$('.otpConfirmation').data('key',jsonObject.Details);
			if(jsonObject.Status == 'Success'){
				throwAnInfoMessage('New SMS sent to '+phoneNumber);
				$('.otpvaliditytimer').html('14:59');
				otpCountdown();
			}
			else{
				throwAnErrorMessage('There was a problem in sending the OTP. Please try again after sometime.');
			}
		}
	}); 
}

function verifyOtp(twoFactorOtpKey, twoFactorOtpValue){
	var status = '';

	$.ajax({
		async: false,
		url: '/verifyOtp',
		type: 'POST',
		data: {'twoFactorOtpKey' : twoFactorOtpKey, 'twoFactorOtpValue' : twoFactorOtpValue},
		success: function (response) {
			var jsonObject = jQuery.parseJSON(response);
			if(jsonObject.Status == 'Success'){
				throwASuccessMessage('OTP successfully verified');
			}
			else{
				status = 'OTP verification failed.';
			}
		}
	});

	return status;
}

function closeOverlay(){
	var parent = $('.overlay_content_container:visible').data('source');
	$('.overlay_content_container:visible').data('source','');
	$('.overlay_content_container:visible').fadeOut(function(){
		if(parent){
			$('.'+parent).fadeIn();
		}
		else{
			$('.overlay_container').fadeOut();
			//shanmu - to be changed
			$('.blur_section').removeClass('blur_content');
		}
	});
}

function otpCountdown(){
	clearInterval(interval); 
	interval = setInterval(function() {
		var timer = $('.otpvaliditytimer').html();
		timer = timer.split(':');
		var minutes = parseInt(timer[0], 10);
		var seconds = parseInt(timer[1], 10);
		seconds -= 1;
		if (minutes < 0) return clearInterval(interval);
		if (minutes < 10 && minutes.length != 2) minutes = '0' + minutes;
		if (seconds < 0 && minutes != 0) {
			minutes -= 1;
			seconds = 59;
		}
		else if (seconds < 10 && length.seconds != 2) seconds = '0' + seconds;
		$('.otpvaliditytimer').html(minutes + ':' + seconds);

		if (minutes == 0 && seconds == 0)
			clearInterval(interval);
	}, 1000);
}

function displayServiceTabContent(object){
	if(!$(object).hasClass('selected')){
		var displaySection = $(object).data('tab');
		$('.services-container .services-tabs .tab').removeClass('selected');
		$(object).addClass('selected');
		$('.services-container .services-content .content-section:visible').fadeOut(function(){
			$('.services-container .services-content .content-section[data-content="'+displaySection+'"]').fadeIn(function(){
				prepImageForPreview();
			});
		});
	}
}

function prepImageForPreview(){
	$('.view-image').each(function(){
		var imageSource = $(this).find('input[type=hidden]').val();
		if(imageSource != ''){
			$(this).find('img').attr('src',imageSource);
		}
	});
}

function displayVendorSubscriptionReceipt(){

	var serviceidlist = '';
	var entryId = $('#edit-services-entry-id').val();
	var coupon = $('#coupon_id').val();

	$('.service-options-container .service-option.selected').each(function(){
		serviceidlist += $(this).data('id')+',';
	});

	$.ajax({
		async: false,
		method: "POST",
		url: "/fetchSubscriptionFeesAndDetails",
		data: { memberEntryId:entryId, serviceidlist: serviceidlist, coupon: coupon },
		success: function(data){
			var jsonObject = jQuery.parseJSON(data);
			var invoiceAmount = 0;

			$('.info-container .section-list').html('');
			$('.paper .paper-service-list').html('<div class="paper-service-header col-sm-12 col-md-12 col-lg-12">items added</div>');

			var serviceRates = '';
			var receiptServices = '';

			for(var i=0; i<jsonObject.serviceList.length; i++){
				invoiceAmount += parseInt(jsonObject.serviceList[i].Cost);
				var status = 'This service is currently inactive';
				var payment = '<span style=\'color:#f82740;\'>Subscription not paid</span>';

				if(jsonObject.serviceList[i].Status == 'active'){
					status = 'This service is currently active';
				}
				if(jsonObject.serviceList[i].Payment == 'paid'){
					payment = '<span style=\'color:#22c48a;\'>Subscription already paid</span>';
				}

				serviceRates += '<div class="list-service-item"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII=" class="knotting-menu-image-'+jsonObject.serviceList[i].Code+' knotting-tooltip" data-html="<b>'+jsonObject.serviceList[i].Name+'</b><br><br>Subscription cost - '+jsonObject.serviceList[i].Cost+' INR<br><br>Expiry - '+jsonObject.serviceList[i].Expiry+'<br><br>'+status+'<br><br>'+payment+'" data-position="right center" /></div>';
				receiptServices += '<div class="paper-service-item col-sm-12 col-md-12 col-lg-12"><div class="paper-service-detail col-sm-7 col-md-7 col-lg-7">'+jsonObject.serviceList[i].Name+'<br>'+payment+'</div><div class="paper-service-cost col-sm-5 col-md-5 col-lg-5">'+jsonObject.serviceList[i].Cost+' INR</div></div>';
			}

			$('.info-container .section-list').html(serviceRates);
			$('.paper .paper-service-list').append(receiptServices);

			$('#paper-discount-amount').text(jsonObject.discountAmount+' INR');
			$('#paper-total-amount').text(jsonObject.serviceAmount+' INR');
			$('#paper-final-cost').text(jsonObject.finalAmount+' INR');

			if(parseInt(jsonObject.serviceAmount) > 0){
				var couponContent = '';
				$('.paper-footer .footer-text').text('a receipt for payment would be mailed to you upon successful payment.');
				$('.paper').addClass('invoice-paper');
				if(parseInt(jsonObject.finalAmount) > 0){
					$('.payment-reqd').show();
				}
				else{
					$('.payment-reqd').hide();
					$('.payment-waived').show();
				}
				if(coupon != null){
					$('#discount_amount').val(jsonObject.discountAmount);
					$('#total_amount').val(jsonObject.serviceAmount);
					$('#pg_amount').val(jsonObject.finalAmount);
					couponContent = '<div class="col-sm-10 col-md-10 col-lg-10"><span class="applied-coupon">'+coupon+'</span><span class="coupon-status">coupon applied</span></div><div class="coupon-remove col-sm-2 col-md-2 col-lg-2"><a href="javascript:removeCoupon();">remove</a></div>';
				}
				else{
					couponContent = '<div class="col-sm-10 col-md-10 col-lg-10"><span class="coupon-status">no coupon applied</span></div>';
				}
				$('.coupon-selected-box').html(couponContent);
			}
			else{
				$('.paper-footer .footer-text').text('we mailed you the receipt at the time of payment. check your mailbox');
				$('.paper').removeClass('invoice-paper');
				$('.payment-not-reqd').show();
				$('.payment-reqd').hide();
			}
		}
	});

	setTimeout(function(){
		$('.payment-box .bill-container .paper-holder .paper').velocity('slideDown', {duration: 1000});
	}, 500);
}

function calculateVendorSubscriptionAmount(){

	var serviceidlist = '';
	var coupon = $('#coupon_id').val();

	$('.service-options-container .service-option.selected').each(function(){
		serviceidlist += $(this).data('id')+',';
	});

	var entryId = ($('#edit-services-entry-id').val().length > 0)?$('#edit-services-entry-id').val():null;

	$.ajax({
		async: false,
		method: "POST",
		url: "/fetchSubscriptionFeesAndDetails",
		data: { memberEntryId:entryId, serviceidlist: serviceidlist, coupon: coupon },
		success: function(data){
			var jsonObject = jQuery.parseJSON(data);
			$('#discount_amount').val(jsonObject.discountAmount);
			$('#total_amount').val(jsonObject.serviceAmount);
			$('#pg_amount').val(jsonObject.finalAmount);
			$('.info-container .section-list').html('');
			$('.paper .paper-service-list').html('<div class="paper-service-header col-sm-12 col-md-12 col-lg-12">items added</div>');

			var serviceRates = '';
			var receiptServices = '';

			for(var i=0; i<jsonObject.serviceList.length; i++){
				var status = 'This service is currently inactive';
				var payment = '<span style=\'color:#f82740;\'>Subscription not paid</span>';

				if(jsonObject.serviceList[i].Status == 'active'){
					status = 'This service is currently active';
				}
				if(jsonObject.serviceList[i].Payment == 'paid'){
					payment = '<span style=\'color:#22c48a;\'>Subscription already paid</span>';
				}

				serviceRates += '<div class="list-service-item"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACAAQMAAAD58POIAAAAA1BMVEX///+nxBvIAAAAAXRSTlMAQObYZgAAABlJREFUeNpjYBgFo2AUjIJRMApGwSigLwAACIAAAcNXzB0AAAAASUVORK5CYII=" class="knotting-menu-image-'+jsonObject.serviceList[i].Code+' knotting-tooltip" data-html="<b>'+jsonObject.serviceList[i].Name+'</b><br><br>Subscription cost - '+jsonObject.serviceList[i].Cost+' INR<br><br>Expiry - '+jsonObject.serviceList[i].Expiry+'<br><br>'+status+'<br><br>'+payment+'" data-position="right center" /></div>';
				receiptServices += '<div class="paper-service-item col-sm-12 col-md-12 col-lg-12"><div class="paper-service-detail col-sm-7 col-md-7 col-lg-7">'+jsonObject.serviceList[i].Name+'<br>'+payment+'</div><div class="paper-service-cost col-sm-5 col-md-5 col-lg-5">'+jsonObject.serviceList[i].Cost+' INR</div></div>';
			}

			$('.info-container .section-list').html(serviceRates);
			$('.paper .paper-service-list').append(receiptServices);

			$('#paper-discount-amount').text(jsonObject.discountAmount+' INR');
			$('#paper-total-amount').text(jsonObject.serviceAmount+' INR');
			$('#paper-final-cost').text(jsonObject.finalAmount+' INR');

			if(parseInt(jsonObject.serviceAmount) > 0){
				var couponContent = '';
				$('.paper-footer .footer-text').text('a receipt for payment would be mailed to you upon successful payment.');
				$('.paper').addClass('invoice-paper');
				$('.payment-not-reqd').hide();
				if(parseInt(jsonObject.finalAmount) > 0){
					$('.payment-reqd').show();
				}
				else{
					$('.payment-reqd').hide();
					$('.payment-waived').show();
				}
				if(coupon != null && coupon != ''){
					couponContent = '<div class="col-sm-10 col-md-10 col-lg-10"><span class="applied-coupon">'+coupon+'</span><span class="coupon-status">coupon applied</span></div><div class="coupon-remove col-sm-2 col-md-2 col-lg-2"><a href="javascript:removeCoupon();">remove</a></div>';
				}
				else{
					couponContent = '<div class="col-sm-10 col-md-10 col-lg-10"><span class="coupon-status">no coupon applied</span></div>';
				}
				$('.coupon-selected-box').html(couponContent);
			}
			else{
				$('.paper-footer .footer-text').text('we mailed you the receipt at the time of payment. check your mailbox');
				$('.paper').removeClass('invoice-paper');
				$('.payment-not-reqd').show();
				$('.payment-reqd').hide();
			}
		}
	});

	setTimeout(function(){
		$('.payment-box .bill-container .paper-holder .paper').velocity('slideDown', {duration: 1000});
	}, 500);
}

function serviceImageUpload(object){
	var previewCounter = 0;
	var serviceId = $(object).data('serviceid');

	$('.img-preview.img-service-'+serviceId).attr('src','./static/images/misc/default_image.jpg');
	$('.img-preview.img-service-'+serviceId).attr('data-img','no');

	var files = $(object)[0].files;

	$(files).each(function () {
		var reader = new FileReader();
		reader.readAsDataURL(this);
		reader.onload = function (e) {
			$('.img-preview.img-service-'+serviceId).eq(previewCounter).attr('src',e.target.result);
			$('.img-preview.img-service-'+serviceId).eq(previewCounter).attr('data-img','yes');
			previewCounter++;
		};
	});
}

function checkDocumentProof(){
	$('#doc-proof-file-name-preview').text('1 file selected');
	$('#doc-proof-file-name-preview').addClass('uploaded');
}

function uploadedImagePreview(object){

	var serviceId = $(object).data('service');
	var files = $(object)[0].files;
	var filesCount = files.length;
	var existingFiles = $('.edit-service-detail-content .edit-service-photo-preview-box[data-service="'+serviceId+'"] .preview-image-box').length;
	var totalFiles = existingFiles+filesCount;
	if(totalFiles > 20){
		throwAnErrorMessage('You can only upload a maximum of 20 images');
	}
	else{
		totalFiles = existingFiles;
		var fileList = '';

		$(files).each(function (idx,elm) {
			var fileSize = parseInt(this.size);
			if(fileSize < 10485760){
				$('.edit-service-detail-content .edit-service-photo-count[data-service="'+serviceId+'"]').text((++totalFiles)+'/20 images selected');
				//array to hold the files on a global scope
				var newIndex = existingFiles+(idx+1);
				finalFiles[newIndex]=elm;
				var reader = new FileReader();
				reader.readAsDataURL(this);
				reader.onload = function (e) {
					var dataImage = e.target.result;
					fileList = '<div class="preview-image-box" data-index="'+newIndex+'"><div class="preview-image-close icon icon-close ease-element" onclick="removeUploadedImage('+newIndex+')"></div><div class="preview-image" style="background-image: url(\''+dataImage+'\')" data-image="'+dataImage+'"></div></div>';
					$('.edit-service-detail-content .edit-service-photo-preview-box[data-service="'+serviceId+'"]').prepend(fileList);
				};
			}
			else{
				throwAnInfoMessage('Files greater than 10MB are excluded');
			}
		});
	}

}

function removeUploadedImage(index){
	finalFiles.splice(index,1);
	$('.preview-image-box[data-index="'+index+'"]').remove();
	restructureFileList(index);
	$('.edit-service-photo-count').text($('.preview-image-box').length+'/20 images selected');
}

function restructureFileList(index){
	$('.preview-image-box').each(function () {
		var imageId = $(this).data('index');
		var newId = (parseInt(imageId)-1);
		if(imageId > index){
			$(this).attr('data-index', newId);
			$(this).find('.preview-image-close').attr('onclick','removeUploadedImage('+newId+')');
		}
	});
}

function saveFormSaveType(submitType){
	//prepare content
	$('.edit-service-detail-box').each(function(){
		var dataCounter = $(this).data('counter');
		$(this).find('.preview-image').each(function(){
			var dataImage = $(this).data('image');
			var imageTag = '<input type="hidden" name="memberServicesStagingList['+dataCounter+'].dataImages" value="'+dataImage.replace(',', '\,')+'" />';
			$(this).append(imageTag);
		});
		//add this empty element to prevent Controller from splitting the data when only one message is sent
		$(this).append('<input type="hidden" name="memberServicesStagingList['+dataCounter+'].dataImages" value="" />');
	});

	submitEditServicesForm(submitType);
}

function submitEditServicesForm(submitType){
	if(submitType == 'E'){
		executivePaymentOverlay();
	}
	else{
		$('#save_type').val(submitType);
		if(submitType == 'S' || submitType == 'C'){
			monitorUploadProgress();
		}
		prepareServicesFormDataForSave();
		$('#servicesForm').submit();
	}
}

function initializeCityAutoComplete() {

	var options = {
			types: ['(cities)'],
			componentRestrictions: {country: "in"}
	};

	var input = document.getElementById('search-for-city-name');
	var autocomplete = new google.maps.places.Autocomplete(input, options, fetchSelectedCity);
	google.maps.event.addListener(autocomplete, 'place_changed', function() {
		$('#search-for-city-name').val('');
		fetchSelectedCity(autocomplete);
	});
}

function fetchSelectedCity(autocomplete) {
	var place = autocomplete.getPlace();
	var city = place.address_components[0].long_name;
	if($('#edit-service-selected-cities .tag[data-tagtype="city"]').length > 0){
		if($('#edit-service-selected-cities .tag[data-tagvalue="'+city+'"]').length <= 0){
			$('#edit-service-selected-cities').append('<div class="tag" data-tagtype="city" data-tagvalue="'+city+'"><div>'+city+'&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon icon-close" onclick="javascript:removeCity(this);"></span></div></div>');
		}
		else{
			throwAQuickInfoMessage(city+' has already been added.');
		}
	}
	else{
		$('#edit-service-selected-cities').html('<div class="tag" data-tagtype="city" data-tagvalue="'+city+'"><div>'+city+'&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon icon-close" onclick="javascript:removeCity(this);"></span></div></div>');
	}
}

function removeCity(object){
	$(object).parent().parent().remove();
}

function throwAnErrorMessage(error){
	$.toast({
		heading: '<b>Error</b>',
		text: error,
		position: 'top-center',
		icon: 'error',
		hideAfter: errorMessageDuration,
		stack: false
	});
}

function throwASuccessMessage(success){
	$.toast({
		heading: '<b>Success</b>',
		text: success,
		position: 'top-center',
		icon: 'success',
		hideAfter: successMessageDuration,
		stack: false
	});
}

function throwAnInfoMessage(info){
	$.toast({
		heading: '<b>Information</b>',
		text: info,
		position: 'top-center',
		icon: 'info',
		hideAfter: infoMessageDuration
	});
}

function throwAQuickInfoMessage(info){
	$.toast({
		text: info,
		position: 'top-center',
		hideAfter: quickInfoMessageDuration
	});
}

function throwAnErrorMessageWithTime(error, duration){
	$.toast({
		heading: '<b>Error</b>',
		text: error,
		position: 'top-center',
		icon: 'error',
		hideAfter: duration,
		stack: false
	});
}

function throwASuccessMessageWithTime(success, duration){
	$.toast({
		heading: '<b>Success</b>',
		text: success,
		position: 'top-center',
		icon: 'success',
		hideAfter: duration,
		stack: false
	});
}


function throwAnInfoMessageWithTime(info, duration){
	$.toast({
		text: info,
		position: 'top-center',
		hideAfter: duration
	});
}

function plotDrggableMarkerOnMap(mapId, positionId, address){
	var position = '';

	var markerPoint = {
			path: 'M 0, 0 m -75, 0 a 75,75 0 1,0 150,0 a 75,75 0 1,0 -150,0',
			fillColor: '#6ac045',
			fillOpacity: 0.8,
			scale: 0.08,
			strokeColor: '#FFFFFF',
			strokeWeight: 2
	};

	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		'address': address
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			position = results[0].geometry.location;
			addServiceMap.panTo(position);
			addServiceMap.setZoom(17);
			var marker = new google.maps.Marker({
				map: addServiceMap,
				position: position,
				icon: markerPoint
			});
			$('#'+positionId).val(position);
		}
	});
}

function launchOverlayAndSendOtp(phoneNumber, source){

	if($('.overlay_content_container:visible').length > 0){
		$('.overlay_content_container:visible').fadeOut(function(){
			$('.verifyotp_container').css('display','table');
			positionOverlay('verifyotp_container');
			$('.verifyotp_container input[name="phoneNumber"]').val(phoneNumber);
			$.ajax({
				async: false,
				url: '/sendOtp',
				type: 'POST',
				data: {'phoneNumber' : phoneNumber},
				success: function (response) {
					var jsonObject = jQuery.parseJSON(response);
					$('.otpConfirmation').data('key',jsonObject.Details);
					$('.otpConfirmation').data('source',source);
					if(jsonObject.Status == 'Success'){
						throwAnInfoMessage('SMS sent to '+phoneNumber);
						$('.otpvaliditytimer').html('14:59');
						$('.otp_timer').css('opacity','1');
						otpCountdown();
					}
					else{
						throwAnErrorMessage('There was a problem in sending the OTP. Please try again after sometime.');
					}
				}
			});
		});
	}else{
		$('.blur_section').addClass('blur_content');
		$('.overlay_container').fadeIn(function(){
			$('.verifyotp_container').css('display','table');
			positionOverlay('verifyotp_container');
			$('.verifyotp_container input[name="phoneNumber"]').val(phoneNumber);
			$.ajax({
				async: false,
				url: '/sendOtp',
				type: 'POST',
				data: {'phoneNumber' : phoneNumber},
				success: function (response) {
					var jsonObject = jQuery.parseJSON(response);
					$('.otpConfirmation').data('key',jsonObject.Details);
					$('.otpConfirmation').data('source',source);
					if(jsonObject.Status == 'Success'){
						throwAnInfoMessage('SMS sent to '+phoneNumber);
						$('.otpvaliditytimer').html('14:59');
						$('.otp_timer').css('opacity','1');
						otpCountdown();
					}
					else{
						throwAnErrorMessage('There was a problem in sending the OTP. Please try again after sometime.');
					}
				}
			}); 
		});
	}	
}

function changeUserPassword(password){
	closeOverlay();
	throwAnInfoMessage('Pushing your new password to our database');
	$.ajax({
		async: true,
		url: '/changeUserPassword',
		type: 'POST',
		data: {'password' : password},
		success: function (response) {
			if(response == 'Success'){
				throwASuccessMessage('Your password was successfully changed.');
			}
			else{
				throwAnErrorMessage('Something went wrong while saving your password. Try again after sometime');
			}
		},
		error : function (response){
			throwAnErrorMessage('Something went wrong while saving your password. Try again after sometime');
		}
	}); 
}

function vendorChangePassword(){
	$('.blur_section').addClass('blur_content');
	$('.overlay_container').fadeIn(function(){
		$('.change_password_container').css('display','table');
		positionOverlay('change_password_container');
	});
}

function addNewServiceInfoOverlay(){
	$('.blur_section').addClass('blur_content');
	$('.overlay_container').fadeIn(function(){
		$('.newservice_info_container').css('display','table');
		positionOverlay('newservice_info_container');
	});
}

function executivePaymentOverlay(){
	$('.blur_section').addClass('blur_content');
	$('.overlay_container').fadeIn(function(){
		$('.executive_payment_container').css('display','table');
		positionOverlay('executive_payment_container');
	});
}

function generateRandomNumber(){
	var randomNumber = Math.floor(1000 + Math.random() * 9000);
	return randomNumber;
}

function positionOverlay(className){
//	var screenHeight = $(window).height();
	var overlayContainerHeight = $('.'+className).height() + 60;//Padding height of 60px
//	var positionContainer = (screenHeight/2) - (overlayContainerHeight/2);
	$('.'+className).css('height',overlayContainerHeight+'px');
	$('.'+className).css('display','block');
	//$('.'+className).css('top',positionContainer+'px');
}

function retryPayment(){
	$('#repaymentForm').submit();
}

function fetchFilterCityList(){
	var loader = '<div class="loader"><div class="loader-inner ball-scale"><div></div></div></div>';
	var logo = '<span class="icon icon-filtercity"></span>';
	var service = $('.ul-service li.selected').attr('id');
	var resultList = '';
	$('.ul-city').parent().parent().find('.logo').html(loader);

	$.ajax({
		async: true,
		url: '/fetchCityListForFilter',
		data: {service : service},
		type: 'POST',
		success: function (response) {
			var jsonObject = jQuery.parseJSON(response);
			for(var i=0;i<jsonObject.length;i++){
				resultList += '<li>'+jsonObject[i]+'</li>';
			}
			$('.ul-city').html(resultList);
			$('.ul-city').parent().parent().find('.logo').html(logo);
			actionListenerForFilterOptions();
		},
		error: function(){			
			throwAnErrorMessage('Something went wrong while fetching the Cities list.');
			$('.ul-city').parent().parent().find('.logo').html(logo);
		}
	});

}

function fetchFilterRangeList(){
	var loader = '<div class="loader"><div class="loader-inner ball-scale"><div></div></div></div>';
	var logo = '<span class="icon icon-filterrange"></span>';
	var service = $('.ul-service li.selected').attr('id');
	var city = $('.ul-city li.selected').text();
	var resultList = '';
	$('.ul-range').parent().parent().find('.logo').html(loader);

	$.ajax({
		async: true,
		url: '/fetchRangeListForFilter',
		data: {service : service, city : city},
		type: 'POST',
		success: function (response) {
			var jsonObject = jQuery.parseJSON(response);
			for(var i=0;i<jsonObject.length;i++){
				resultList += '<li>'+jsonObject[i]+'</li>';
			}
			$('.ul-range').html(resultList);
			$('.ul-range').parent().parent().find('.logo').html(logo);
			actionListenerForFilterOptions();
		},
		error: function(){			
			throwAnErrorMessage('Something went wrong while fetching the Price range.');
			$('.ul-range').parent().parent().find('.logo').html(logo);
		}
	});
}

function actionListenerForFilterOptions(){
	var screenWidth = $(window).width();

	$('.filter-answer-section li').unbind('click');
	$('.filter-answer-section li').click(function() {
		if(!$(this).hasClass('no-select') && !$(this).hasClass('selected')){
			$(this).parent().parent().find('li').removeClass('selected');
			$(this).addClass('selected');
			/* mark parent box as selected */
			$(this).parent().parent().parent().addClass('selected');
			/* clear dependent values and call necessary ajax to populate dependencies */
			if($(this).parent().hasClass('ul-service')){
				$('.ul-city').parent().parent().removeClass('selected');
				$('.ul-range').parent().parent().removeClass('selected');
				$('.ul-city').html('<li class="no-select">fetching cities list</li>');
				$('.ul-range').html('<li class="no-select">choose a city</li>');				
				if($('.knotting-result-screen').length > 0 && screenWidth > 1200){
					$('.ul-service').parent().parent().find('.title').text($(this).text());
					$('.ul-city').parent().parent().find('.title').text('Choose a city');
					$('.ul-range').parent().parent().find('.title').text('Select a range');
					fitCriteriaDropdownBox();
				}
				fetchFilterCityList();
			}
			else if($(this).parent().hasClass('ul-city')){
				$('.ul-range').parent().parent().removeClass('selected');
				$('.ul-range').html('<li class="no-select">fetching price range</li>');				
				if($('.knotting-result-screen').length > 0 && screenWidth > 1200){
					$('.ul-city').parent().parent().find('.title').text($(this).text());
					$('.ul-range').parent().parent().find('.title').text('Select a range');
					fitCriteriaDropdownBox();
				}
				fetchFilterRangeList();
			}
			else{				
				if($('.knotting-result-screen').length > 0 && screenWidth > 1200){
					$(this).parent().parent().parent().find('.title').text($(this).text());
					fitCriteriaDropdownBox();
				}
			}

			$(this).parent().parent().slideUp();

			if($('.knotting-result-screen').length > 0){
				$('.criteria-edit.submit-criteria').fadeIn();
			}
		}
	});
}

function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	sURLVariables = sPageURL.split('&'),
	sParameterName,
	i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
	}
}

function fetchNextPageOfResult(){

	$('.result-more-section .more-button').addClass('loader-animation');
	$('.result-more-section .more-button').html('<table><tr><td><div class="loader"><div class="loader-inner ball-beat"><div></div><div></div><div></div></div></div></td></tr></table>');

	var service = getUrlParameter('service');
	var city = getUrlParameter('city');
	var range = getUrlParameter('range');
	var urgency = getUrlParameter('urgency');
	var filter = getUrlParameter('filter');
	var sort = getUrlParameter('sort');
	var firstRecord = parseInt($('.result-more-section .more-button').attr('data-last'));
	var totalRecords = parseInt($('.result-more-section .more-button').attr('data-total'));
	var lastRecord = 0;

	if((firstRecord + 20) > totalRecords){
		lastRecord = totalRecords;
	}
	else{
		lastRecord = firstRecord + 20;
	}

	$.ajax({
		async: true,
		url: '/fetchNextPageResult',
		data: {service : service, city : city, range : range, urgency : urgency, filter : filter, sort : sort, firstRecord : firstRecord, totalRecords : totalRecords},
		type: 'POST',
		success: function (response) {
			var jsonObject = jQuery.parseJSON(response);
			var vendorList = '';
			for(var i=0;i<jsonObject.length;i++){

				vendorList += '<a href="details/'+jsonObject[i].memberServiceCode+'"><div class="result-item-container">';

				if(jsonObject[i].imagePath1 != null && jsonObject[i].imagePath1.length > 0){
					vendorList += '<div class="result-item-images image-single"><div class="vendor-image"><img data-src="'+jsonObject[i].imagePath1+'" /></div></div>';
				}

				vendorList += '<div class="result-item-details"><div class="title">'+jsonObject[i].memberEntries.name+'</div><div class="rating"><table><tr><td class="star"><span class="icon icon-nostar"></span></td><td class="star"><span class="icon icon-nostar"></span></td><td class="star"><span class="icon icon-nostar"></span></td><td class="star"><span class="icon icon-nostar"></span></td><td class="star"><span class="icon icon-nostar"></span></td><td class="note">yet to be rated</td></tr></table></div>';

				if(jsonObject[i].memberEntries.emergencyRequestAccepted == 'Y'){
					vendorList += '<div class="item"><table><tr><td class="logo accept"><span class="icon icon-filterurgency"></span></td><td class="note">accepts emergency request</td></tr></table></div>';
				}
				else{
					vendorList += '<div class="item"><table><tr><td class="logo deny"><span class="icon icon-filterurgency"></span></td><td class="note">doesn\'t accept emergency request</td></tr></table></div>';
				}

				vendorList += '<div class="item"><table><tr><td class="logo"><span class="icon icon-filterrange"></span></td><td class="note">charges '+jsonObject[i].priceRange+' INR</td></tr></table></div>';

				if(parseInt(jsonObject[i].experience) == 1){
					vendorList += '<div class="item"><table><tr><td class="logo"><span class="icon icon-result-experience"></span></td><td class="note">1 year of experience</td></tr></table></div>';
				}
				else{
					vendorList += '<div class="item"><table><tr><td class="logo"><span class="icon icon-result-experience"></span></td><td class="note">'+jsonObject[i].experience+' years of experience</td></tr></table></div>';
				}

				if($('#service-result').length > 0){
					var cityList = jsonObject[i].memberEntries.additionalAreasServiced.split(',');

					if(cityList.length == 1){
						vendorList += '<div class="item"><table><tr><td class="logo"><span class="icon icon-filtercity"></span></td><td class="note">accepts work for 1 city</td></tr></table></div>';
					}
					else{
						vendorList += '<div class="item"><table><tr><td class="logo"><span class="icon icon-filtercity"></span></td><td class="note">accepts work for '+cityList.length+' cities</td></tr></table></div>';
					}
				}

//				vendorList += '<div class="item">';
//				for(var j=0; j<cityList.length; j++){
//				if(cityList[j].length > 0){
//				vendorList += '<div class="city-tag">'+cityList[j]+'</div>';
//				}
//				}
//				vendorList += '</div>';

				vendorList += '</div>';

				vendorList += '</div></a>';				
			}

			var $content = $(vendorList);
			$('.result-results-section-items').append($content).promise().done(function(){
				$('img').lazy({
					threshold: 100,
					defaultImage : 'data:image/png;base64,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'
				});

				if(lastRecord == totalRecords){
					$('.result-more-section .more-button').hide();
					$('.result-more-section .more-button').removeClass('loader-animation');
					$('.result-more-section .more-button').html('load more');
				}
				else{
					$('.result-more-section .more-button').removeClass('loader-animation');
					$('.result-more-section .more-button').html('load more');
				}

				$('.result-results-section-items').masonry('appended', $content);
				$('.knotting-result-screen .result-results-section .result-item-container').hoverIntent(function(){
					$(this).addClass('hovered');
				},
				function(){
					$(this).removeClass('hovered');
				});
				$('.result-more-section .more-button').attr('data-first',firstRecord);
				$('.result-more-section .more-button').attr('data-last',lastRecord);
			});			

		},
		error: function(){			
			throwAnErrorMessage('Unable to fetch Vendor details. Try after sometime.');
		}
	});
}

function pad(width, string, padding) { 
	return (width <= string.length) ? string : pad(width, padding + string, padding);
}

function shareDetailsToUser(){
	var from = $('#share-details-from').val();
	var to = $('#share-details-number').val();
	var serviceId = $('#share-details-number').data('service');

	$.ajax({
		async: true,
		url: '/sendServiceUrlToUser',
		data: {from : from, to : to, serviceId : serviceId},
		type: 'POST',
		success: function (response) {
			throwASuccessMessage('Details sent to '+to);
		}
	}); 
}

function shareDetailsToVendor(){
	var service = $('.ul-service li.selected').attr('id');
	var city = $('.ul-city li.selected').text().toLowerCase();
	var range = $('.ul-range li.selected').text().split(' ')[0].toLowerCase();
	var urgency = $('.ul-urgency li.selected').attr('id');
	var phoneNumber = $('#share-details-to').val();

	throwAnInfoMessage('Sending your information to our vendors.');

	$.ajax({
		async: true,
		url: '/vendorCallRequest',
		data: {service : service, city : city, range : range, urgency : urgency, phoneNumber : phoneNumber},
		type: 'POST',
		success: function (response) {
			if(response == 'true'){
				throwASuccessMessage('We\'ve informed our vendors. You\'ll start receiving calls in sometime.');
			}
			else{
				throwAnErrorMessage('We weren\'t able to send your detail to our vendors. Please click on the search button and proceed.');
			}
		}
	}); 
}

function fitCriteriaDropdownBox(){
	$('.criteria-items-container .criteria-item .filter-answer-section').each(function(){
		var dropdownMinWidth = $(this).prev().width();
		$(this).css('min-width', dropdownMinWidth+'px');
		//TODO: padding of 20 px is manullay added. Automate this
		$(this).find('ul').css('min-width', (dropdownMinWidth-20)+'px');
	});
}

function calculateFilterBoxSize(){

	var screenWidth = $(window).width();
	var filterBoxCount = 0;
	var sortBoxCount = 0;
	var marginSize = 0;

	if(screenWidth > 700){
		filterBoxCount = 1;
		sortBoxCount = 1;
		marginSize = 20;
	}
	if(screenWidth > 1200){
		filterBoxCount = 4;
		sortBoxCount = 4;
		marginSize = 20;
	}
	if(screenWidth > 1600){
		filterBoxCount = 5;
		sortBoxCount = 5;
		marginSize = 20;
	}

	var sidePadding = parseInt($('.dynamic-content-section').css('padding-left')) + parseInt($('.dynamic-content-section').css('padding-right'));
	var availableWidth = widthOfHiddenElement($('.result-dynamic-section'), 'dynamic-content-section') - sidePadding;
	var finalFilterWidth = ((availableWidth/filterBoxCount) - marginSize) - 1;
	var finalSortWidth = ((availableWidth/sortBoxCount) - marginSize) - 1;

	$('.field-box').css('width', finalFilterWidth+'px');
	$('.sort-box').css('width', finalSortWidth+'px');

}

function setFilterAndSortValues(){
	if(typeof(getUrlParameter('filter')) != 'undefined'){
		var filter = getUrlParameter('filter');
		var filterCriteria = filter.split('\;');
		for(var i=0; i<filterCriteria.length; i++){
			if(filterCriteria[i] != ''){
				var fieldName = filterCriteria[i].split(':')[0];
				var values = filterCriteria[i].split(':')[2];			
				var valueArray = values.split('|');

				$('.filter-element[data-field="'+fieldName+'"]').attr('data-changed','yes');
				$('.filter-element[data-field="'+fieldName+'"]').attr('data-changedonload','yes');

				if($('.filter-element[data-field="'+fieldName+'"]').data('type') == 'range'){
					$('.filter-element[data-field="'+fieldName+'"]').data().from = valueArray[0];
					$('.filter-element[data-field="'+fieldName+'"]').data().to = valueArray[1];
					$('.filter-element[data-field="'+fieldName+'"]').attr('data-start',valueArray[0]);
					$('.filter-element[data-field="'+fieldName+'"]').attr('data-end',valueArray[1]);
				}
				else{
					$.each(valueArray, function(key, value) {
						$('.filter-element[data-field="'+fieldName+'"]').dropdown('set selected',value);
					});

					$('.filter-element[data-field="'+fieldName+'"]').attr('data-changedvalue',values);
				}

				$('#filter-remove').addClass('active');
			}
		}
	}

	if(typeof(getUrlParameter('sort')) != 'undefined'){
		var sort = getUrlParameter('sort');
		$('.sort-box[data-sorttype="'+sort+'"]').addClass('active');
		$('.sort-box[data-sorttype="'+sort+'"]').attr('data-sortonload','yes');
		$('#sort-remove').addClass('active');
		$('#show-sort').removeClass('no-badge');
	}
}

function frameFilterCriteria(){

	var filterCriteria = '';

	$('.filter-element[data-changed="yes"]').each(function(){
		filterCriteria += $(this).data('field')+':'+$(this).data('type')+':';
		if($(this).data('type') == 'range'){
			filterCriteria += $(this).data().from+'|'+$(this).data().to;
		}
		else{
			filterCriteria += $(this).dropdown('get value').replace(/\,/g, '|');
		}

		filterCriteria += ';';
	});

	return filterCriteria;

}

function frameSortCriteria(){

	var sortCriteria = '';

	return sortCriteria;

}

function resetFilter(){
	$('.dynamic-content-filter .ui.dropdown').dropdown('restore defaults');
	//TODO: Find ways to rest the slider without removing the hoverintent listeners
	$('.range-slider').each(function(){
		if($(this).attr('data-changedonload') == 'yes'){
			$(this).data().from = $(this).attr('data-start');
			$(this).data().to = $(this).attr('data-end');
		}
		else{
			$(this).data().from = $(this).attr('data-lower');
			$(this).data().to = $(this).attr('data-higher');
		}
	});

	$('#filter-apply').removeClass('active');
	$('#filter-reset').removeClass('active');

	//clear all
	$('.filter-element[data-changed="yes"]').attr('data-changed','no');

	//reset the attribute
	$('.filter-element[data-changedonload="yes"]').each(function(){
		$(this).attr('data-changed','yes');
	});

	if($('.filter-element[data-changed="yes"]').length > 0){
		$('#show-filter').attr('data-badge',$('.filter-element[data-changed="yes"]').length);
	}
	else{
		$('#show-filter').attr('data-badge','0');
		$('#show-filter').addClass('no-badge');
	}
}

function resetSort(){
	$('.sort-box').removeClass('active');

	if($('.sort-box[data-sortonload="yes"]').length > 0){
		$('.sort-box[data-sortonload="yes"]').addClass('active');
		$('#show-sort').removeClass('no-badge');
	}
	else{
		$('#show-sort').addClass('no-badge');
	}

	$('#sort-apply').removeClass('active');
	$('#sort-reset').removeClass('active');
}

function widthOfHiddenElement(obj, className){
	var clone = obj.clone();
	clone.css('visibility','hidden');
	$('body').append(clone);
	var width = clone.find('.'+className).width();
	clone.remove();
	return width;
}

function dayDiff(startdate, enddate) {
	var dayCount = 0;

	while(enddate >= startdate) {
		dayCount++;
		startdate.setDate(startdate.getDate() + 1);
	}

	return dayCount; 
}

function randomNumberGenerator(min,max)
{
	return Math.floor(Math.random()*(max-min+1)+min);
}

function isMobile(){
	var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
	return isMobile;
}

function supportsWebm(){
	var testWebm = document.createElement('video');
	var webm;
	if (testWebm.canPlayType) {
		// Check for Webm support
		webm = "" !== testWebm.canPlayType('video/webm; codecs="vp8, vorbis"');
	}
	return webm;
}

function playVideo(){
	if(!isMobile()){
		var video = $('video');
//		if(supportsWebm()){
//		var source = $('video source[type="video/webm"]');
//		$(source).attr('src',$(source).data('src'));
//		}
//		else{
		var source = $('video source[type="video/mp4"]');
		$(source).attr('src',$(source).attr('data-src'));
//		}
		video.load();
	}
}

function shiftStage(pageNumber){
	$('.service-tabs-container .service-tab.active').removeClass('active');
	$('.service-tabs-container .service-tab[data-stage="'+pageNumber+'"]').addClass('active');
	var left = (pageNumber - 1) * 20;
	$('.scroller-tab').css('left', left+'%');
}

function onlyNumberListener(){
	$('.onlyNumbers').unbind('keydown');
	$('.onlyNumbers').keydown(function(event) {
		// Allow special chars + arrows 
		if (event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 
				|| event.keyCode == 27 || event.keyCode == 13 
				|| (event.keyCode >= 35 && event.keyCode <= 39)){
			return;
		}else {
			// If it's not a number stop the keypress
			if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
				event.preventDefault(); 
			}   
		}
	});
}

function addServiceListeners(){
	$('.percent').unbind('keydown');
	$('.percent').keydown(function(event) {
		var percentage = parseInt($(this).val());
		if (percentage >= 100){
			event.preventDefault();
		}
		else{
			var pressedValue = String.fromCharCode(event.which);
			percentage = parseInt(percentage + pressedValue);
			if (percentage > 100){
				throwAnInfoMessage('Dude, '+percentage+'% is way too much.');
				event.preventDefault();
			}
		}
	});
}

function paymentListeners(){
	$('#no-money').unbind('click');
	$('#no-money').click(function(){
		saveFormSaveType('C');
	});

	$('#grab-money').unbind('click');
	$('#grab-money').click(function(){
		if($('.payment-box .info-container .payment-list .list-payment-item.selected').length > 0){
			var paymentType = $('.payment-box .info-container .payment-list .list-payment-item.selected').data('payment');
			saveFormSaveType(paymentType);
		}
		else{
			throwAnErrorMessage('Kidnly select a payment option.');
		}
	});

	$('#just-save-details').unbind('click');
	$('#just-save-details').click(function(){
		saveFormSaveType('S');
	});

	$('.payment-box .info-container .payment-list .list-payment-item').unbind('click');
	$('.payment-box .info-container .payment-list .list-payment-item').click(function(){
		if(!$(this).hasClass('selected')){
			$('.payment-box .info-container .payment-list .list-payment-item.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	/* Activate the popups in the rate section */
	$('.knotting-tooltip').popup();

	$('#apply-coupon').unbind('click');
	$('#apply-coupon').click(function(){
		applyCoupon();
	});
}

function applyCoupon(){
	var coupon = $('#add-coupon').val();
	var amount = $('#total_amount').val();

	$.ajax({
		async: false,
		method: "POST",
		url: '/applyCoupon',
		data: { amount: amount, coupon: coupon },
		success: function(data){
			var jsonObject = jQuery.parseJSON(data);
			var couponContent = '';

			if(jsonObject.couponError == null){
				$('#coupon_id').val(jsonObject.couponCode);
				$('#discount_amount').val(jsonObject.discountAmount);
				$('#total_amount').val(jsonObject.totalAmount);
				$('#pg_amount').val(jsonObject.finalAmount);
				$('#paper-discount-amount').text(jsonObject.discountAmount+' INR');
				$('#paper-total-amount').text(jsonObject.totalAmount+' INR');
				$('#paper-final-cost').text(jsonObject.finalAmount+' INR');
				couponContent = '<div class="col-sm-10 col-md-10 col-lg-10"><span class="applied-coupon">'+jsonObject.couponCode+'</span><span class="coupon-status">coupon applied</span></div><div class="coupon-remove col-sm-2 col-md-2 col-lg-2"><a href="javascript:removeCoupon();">remove</a></div>';
				$('.coupon-selected-box').html(couponContent);

				if(parseInt(jsonObject.finalAmount) <= 0){
					$('.payment-reqd').hide();
					$('.payment-waived').show();
				}
				else{
					$('.payment-waived').hide();
					$('.payment-reqd').show();
				}
			}
			else{
				throwAnErrorMessage(jsonObject.couponError);
			}

			$('#add-coupon').val('');
		}
	});			
}

function removeCoupon(){
	var finalAmount = $('#total_amount').val();
	var couponContent = '<div class="col-sm-10 col-md-10 col-lg-10"><span class="coupon-status">no coupon applied</span></div>';

	$('#paper-discount-amount').text('0 INR');
	$('#paper-final-cost').text(finalAmount+' INR');
	$('#coupon_id').val('');
	$('#discount_amount').val(0);
	$('#pg_amount').val(finalAmount);
	$('.coupon-selected-box').html(couponContent);

	$('.payment-waived').hide();
	$('.payment-reqd').show();
}

function monitorUploadProgress(){
	$('.knotting-edit-service-screens').addClass('blur_content');
	$('.server-status-details').css('display', 'flex');
}

function retryPaymentListeners(){
	setTimeout(function() {
		$('.payment-box .bill-container .paper-holder .paper').velocity('slideDown', { duration : 1500 });
	}, 1500);
	$('.knotting-tooltip').popup();

	$('#grab-money').unbind('click');
	$('#grab-money').click(function() {
		if ($('.payment-box .info-container .payment-list .list-payment-item.selected').length > 0) {
			var paymentType = $('.payment-box .info-container .payment-list .list-payment-item.selected').data('payment');
			if(paymentType == 'E'){
				executivePaymentOverlay();
			}
			else{
				$('#repaymentForm').submit();
			}
		} else {
			throwAnErrorMessage('Kidnly select a payment option.');
		}
	});

	$('.payment-box .info-container .payment-list .list-payment-item').unbind('click');
	$('.payment-box .info-container .payment-list .list-payment-item').click(function(){
		if(!$(this).hasClass('selected')){
			$('.payment-box .info-container .payment-list .list-payment-item.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	removeMobileOrWebComponents();
}

function setMenuBoxHeight(){
	$('.knotting-menu-options').each(function(){
		var boxHeight = ($(this).height()+50);
		$(this).velocity({top:'-'+boxHeight+'px'}, { duration: 'normal' });
	});
}

function gradientPolyFill(){
	var testEl = document.createElement( "x-test" );
	var supportsWebkitBackgroundClipText = typeof testEl.style.webkitBackgroundClip !== "undefined" && ( testEl.style.webkitBackgroundClip = "text", testEl.style.webkitBackgroundClip === "text" );

	if(!supportsWebkitBackgroundClipText){
		$('.orange-gradient').css('color','#ff6b22');
		$('.green-gradient').css('color','#56ab2f');
		$('.cards-container .vendor-card.card-3 .card-bg .bg-gradient').css('background-color','#ee0979');
		$('.cards-container .vendor-card.card-5 .card-bg .bg-gradient').css('background-color','#7303c0');
		$('.cards-container .vendor-card.card-6 .card-bg .bg-gradient').css('background-color','#000046');
	}
}

function removeMobileOrWebComponents(){
	if(isMobile()){
		$('.remove-for-mobile').remove();
	}
	else{
		$('.remove-for-web').remove();
	}
}

function prepareServiceAnswerSet(){	
	$('.service-answers .detail-box').each(function(){
		if($(this).data('conditionfield') != 'NA'){
			var parentFieldName = $(this).data('conditionfield');
			var parentFieldId = parentFieldName;
			var childFieldName = $(this).find('input').attr('data-fieldidentifier');
			var parentFieldAnswers = $(this).data('conditionanswer').split('#');
			parentFieldAnswers = $.map(parentFieldAnswers, function(n,i){return n.toLowerCase();});
			var parentDatatype = $('#'+parentFieldId).closest('.detail-box').data('fieldtype');

			if(parentDatatype == 'dropdown'){
				if($.inArray($('#'+parentFieldId).val().toLowerCase(), parentFieldAnswers) > -1){
					$('[data-fieldidentifier="'+childFieldName+'"]').closest('.detail-box').show();
				}
				else{
					$('[data-fieldidentifier="'+childFieldName+'"]').closest('.detail-box').addClass('remove-later');
				}
			}
			else if(parentDatatype == 'checkbox'){
				var showField = false;
				$('input[name="'+parentFieldName+'"]:checked').each(function(){
					if($.inArray($(this).val().toLowerCase(), parentFieldAnswers) > -1){
						showField = true;
						return false;
					}
				});

				if (showField){
					$('[data-fieldidentifier="'+childFieldName+'"]').closest('.detail-box').show();
				}
				else{
					$('[data-fieldidentifier="'+childFieldName+'"]').closest('.detail-box').addClass('remove-later');
				}
			}
			else if(parentDatatype == 'input'){
				if($.inArray($('#'+parentFieldId).val().toLowerCase(), parentFieldAnswers) > -1){
					$('[data-fieldidentifier="'+childFieldName+'"]').closest('.detail-box').show();
				}
				else{
					$('[data-fieldidentifier="'+childFieldName+'"]').closest('.detail-box').addClass('remove-later');
				}
			}
		}
	});
	
	$('.remove-later').remove();

	if($('#priceRange').length == 1){
		var priceRangeText = $('#priceRange').closest('.detail-box').find('.detail-question').text();
		$('.price-range-text').text(priceRangeText);
		$('#priceRange').closest('.detail-box').remove();
	}
}

function triggerChildFieldAction(childFieldId){	
	var childDataType = $('#'+childFieldId).closest('.modifiable-field-container').data('fieldtype');

	if(childDataType == 'dropdown'){
		$('#'+childFieldId).closest('.ui.dropdown').dropdown('restore defaults');
	}
	else if(childDataType == 'checkbox'){
		$('#'+childFieldId).closest('.ui.checkbox').dropdown('clear');
	}

	$('#'+childFieldId).val('');
	$('#'+childFieldId).trigger('change');
	
}

function prepareServicesFormDataForSave(){
	$('.edit-service-detail-box').each(function(){
		$('.modifiable-field-container[style*="none"]').each(function(){
			$(this).remove();
		});
	});
}

function calculateTextWidth(object, padding){	
	$.fn.textWidth = function(text, font) {
		if (!$.fn.textWidth.fakeEl) $.fn.textWidth.fakeEl = $('<span>').hide().appendTo(document.body);
		$.fn.textWidth.fakeEl.text(text || this.val() || this.text()).css('font', font || this.css('font'));
		var calculatedWidth = $.fn.textWidth.fakeEl.width();
		$.fn.textWidth.fakeEl.remove();
		return calculatedWidth;
	};

	var font = $(object).css('font-size')+' '+$(object).css('font-family');
	var width = $.fn.textWidth($(object).val(), font);

	var finalWidth = width + padding;

	$(object).css('width', finalWidth+'px');
}

function loadImages(container){
	var imageWidth = $(container).width() * 0.23;
	$(container+' .source-image').each(function(){
		var sourceImage = $(this).val();
		var imageIndex = $(this).data('index');
		if(sourceImage != ''){
			var thumbnailImage = $('.thumbnail-image[data-index="'+imageIndex+'"]').val();
			var imageElement = '<div class="pic-box" style="height:'+imageWidth+'px;"><a href="'+sourceImage+'" data-lightbox="vendor-pics"><img src="https://storage.googleapis.com/knotting/images/misc/image-placeholder.jpg" data-src="'+thumbnailImage+'" /></a></div>';
			$(container).append(imageElement);
		}
		$('input[data-index="'+imageIndex+'"]').remove();
	});
}