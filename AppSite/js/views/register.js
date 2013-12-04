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