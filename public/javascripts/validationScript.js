// 
//	jQuery Validate example script
//
//	Prepared by David Cochran
//	
//	Free for your use -- No warranties, no guarantees!
//

$(document).ready(
		function() {
			// Validate
			// http://bassistance.de/jquery-plugins/jquery-plugin-validation/
			// http://docs.jquery.com/Plugins/Validation/
			// http://docs.jquery.com/Plugins/Validation/validate#toptions

			$('#login-form').validate(
					{
						rules : {
							Password : {
								required : true
							},
							EmailId : {
								required : true,
								email : true
							}
						},
						highlight : function(label) {
							$(label).closest('.control-group')
									.addClass('error');
						},
						success : function(label) {
							$(label).closest(
									'.control-group').addClass('success');
						}
					});

			$('#signup-form').validate(
					{
						rules : {
							Password : {
								minlength : 6,
								required : true
							},
							ConfirmPassword : {
								equalTo : "#Password",
								required : true
							},
							EmailId : {
								required : true,
								email : true
							}
						},
						highlight : function(label) {
							$(label).closest('.control-group')
									.addClass('error');
						},
						success : function(label) {
							label.text('OK!').addClass('valid').closest(
									'.control-group').addClass('success');
						}
					});
			
			$('#informme-form').validate(
					{
						rules : {
							Password : {
								minlength : 6,
								required : true
							},
							ConfirmPassword : {
								equalTo : "#Password",
								required : true
							},
							EmailId : {
								required : true,
								email : true
							},
							KeySkills : {
								required : true
							}
						},
						highlight : function(label) {
							$(label).closest('.control-group')
									.addClass('error');
						},
						success : function(label) {
							label.text('OK!').addClass('valid').closest(
									'.control-group').addClass('success');
						}
					});


			$('#jobpost-form').validate(
					{
						rules : {
							Position : {
								minlength : 2,
								required : true
							},
							Company : {
								minlength : 2,
								required : true
							},
							Location : {
								minlength : 2,
								required : true
							},
							JobType : {
								required : true
							},
							Skills : {
								required : true
							},
							Email_Addrss_To_Apply_To : {
								required : true,
								email : true
							},
							Link_To_Apply_To : {
								required : true,
								url : true
							},
							Description : {
								minlength : 2,
								required : true
							},
						},
						highlight : function(label) {
							$(label).closest('.control-group')
									.addClass('error');
						},
						success : function(label) {
							label.text('OK!').addClass('valid').closest(
									'.control-group').addClass('success');
						}
					});
			
			$('#editProfile-form').validate(
					{
						rules : {
							NewPassword : {
								minlength : 6,
								required : true
							},
							ConfirmPassword : {
								equalTo : "#NewPassword",
								required : true
							},
							CurrentPassword : {
								required : true,
							}
						},
						highlight : function(label) {
							$(label).closest('.control-group')
									.addClass('error');
						},
						success : function(label) {
							label.text('OK!').addClass('valid').closest(
									'.control-group').addClass('success');
						}
					});
			
			$('#forgetPassword_form').validate(
					{
						rules : {
							EmailId : {
								required : true,
								email : true
							}
						},
						highlight : function(label) {
							$(label).closest('.control-group')
									.addClass('error');
						},
						success : function(label) {
							label.text('OK!').addClass('valid').closest(
									'.control-group').addClass('success');
						}
					});
			
			$('#alert-form').validate(
					{
						rules : {
							EmailId : {
								required : true,
								email : true
							}
						},
						highlight : function(label) {
							$(label).closest('.control-group')
									.addClass('error');
						},
						success : function(label) {
							label.text('OK!').addClass('valid').closest(
									'.control-group').addClass('success');
						}
					});
			
			$('#contactus-form').validate(
					{
						rules : {
							Name : {
								minlength : 2,
								required : true
							},
							EmailAddress : {
								required : true,
								email : true
							},
							Subject : {
								minlength : 2,
								required : true
							},
							Message : {
								minlength : 2,
								required : true
							},
						},
						highlight : function(label) {
							$(label).closest('.control-group')
									.addClass('error');
						},
						success : function(label) {
							label.text('OK!').addClass('valid').closest(
									'.control-group').addClass('success');
						}
					});


		}); // end document.ready

function validateEmail(email) {
	 
	   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	   if(reg.test(email) == false) {
	      return false;
	   }
	   else
		   {
		   return true;
		   }
	}
