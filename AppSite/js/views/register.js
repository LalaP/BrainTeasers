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
    	var MyTeacher = Parse.Object.extend("Teacher");
    	var tt = new MyTeacher();

		user.set("username", email);
		user.set("password", password);
		user.set("email", email);
		user.set("firstname", firstname);
		user.set("lastname", lastname);
		 
		user.signUp(null, {
		  success: function(user) {
		  	tt.set("objectId", user.Id);
		  	tt.set("msg","Hello New World");
		    tt.save(null,{
		    	success:function(tt){
		    		alert('new teacher recorded');
		    	},
		    	error:function(tt,error){
		    		alert('fuck that');
		    	}
		    });
		    alert('User Registered');
		    this.location.replace('');

		  },
		  error: function(user, error) {
		    // Show the error message somewhere and let the user try again.
		    alert("Error: " + error.code + " " + error.message);
		  }
		});
    }
});