window.Register = Backbone.View.extend({

    initialize:function () {

    },

    render:function () {
        $(this.el).html(this.template());
        return this;
    },

    events: {
    	'click #signup':'registerUser'
    },

    registerUser: function () {
    	var firstname = this.$('#fName').val();
    	var lastname = this.$('#lName').val();
    	var email = this.$('#email').val();
    	var password = this.$('#password').val();
    	var user = new Parse.User();
    	
		user.set("username", email);
		user.set("password", password);
		user.set("email", email);
		user.set("firstname", firstname);
		user.set("lastname", lastname);
		 
		user.signUp(null, {
		 	success: function(user) {
			  	var teacherIns = new MyTeacher();

			 	var userid = Parse.User.current();
	            var teacherACL = new Parse.ACL();
							
				teacherACL.setReadAccess(userid,true);
	            teacherACL.setWriteAccess(userid,true);
	            teacherIns.setACL(teacherACL);

	            teacherIns.save(
	                {	         
	                    firstName: firstname,
	                    lastName: lastname,
	                    email: email
	                },

	                {
	                success: function(teacher) {
	                    this.location.replace('');
	                },
	                error: function(error){
	                    console.log('Error:' + error.code + ' - ' + error.message);
	                }
	            });
		  	},
		 	error: function(user, error) {
		    	// Show the error message somewhere and let the user try again.
		    	console.log('Error:' + error.code + ' - ' + error.message);
		  	}
		});
    }
});