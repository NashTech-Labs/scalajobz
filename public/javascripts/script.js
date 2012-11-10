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
								minlength : 6,
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
							Email_Addrss_To_Apply_To : {
								required : true,
								email : true
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


		}); // end document.ready
