
function validateName(name){
	var message = '';

	if(name != null && name.length>0 && name != ''){
		if(name.length < 5){
			message = 'Name should be atleast 5 characters long';
		}
	}
	else{
		message = 'Kindly enter a valid name';
	}

	return message;
}

function validateEmail(email){
	var message = '';

	var re = /\S+@\S+\.\S+/;
	if(!re.test(email)){
		message = 'Kindly enter a valid email address';
	};

	return message;
}

function validatePhoneNumber(phoneNumber){
	var message = '';

	var re = /^[0]?[789]\d{9}$/;
	if(!re.test(phoneNumber)){
		message = 'Kindly enter a valid mobile number';
	};

	return message;
}

function validateFixedLineNumber(phoneNumber){
	var message = '';

	var re = /\d{5}([- ]*)\d{6}/;
	if(!re.test(phoneNumber)){
		message = 'Kindly enter a valid fixed line or mobile number';
	};

	return message;
}

function validatePassword(password){
	var message = '';

	if(password != null && password.length>0 && password != ''){
		if(password.length < 6){
			message = 'Password should be atleast 6 characters long';
		}
	}
	else{
		message = 'Kindly enter a valid password';
	}

	return message;
}

function validateConfirmPassword(password,confirmPassword){
	var message = '';

	if(confirmPassword != password){
		message = 'Password and confirm password fields do not match';
	}

	return message;
}

function validateWebsiteUrl(url){
	var message = '';
	
	var re = /(http(s)?:\\)?([\w-]+\.)+[\w-]+[.com|.in|.org]+(\[\?%&=]*)?/;
	if (!re.test(url)) {
		message = 'Kindly enter a valid website address';
	}

	return message;
}

function validateAddress(address){
	var message = '';
	
	if(address == null || address.length <= 10 || address == ''){
		message = 'Kindly enter a valid address';
	}

	return message;
}

function validateFilterCriteria(){
	var message = '';
	
	if($('.ul-service li.selected').length <= 0){
		message = 'Kindly select a service';
	}
	else if($('.ul-city li.selected').length <= 0){
		message = 'Kindly select a city';
	}
	else if($('.ul-range li.selected').length <= 0){
		message = 'Kindly select a price range';
	}
	else if($('.ul-urgency li.selected').length <= 0){
		message = 'Kindly select the urgency';
	}
	
	return message;
}
