window.Login = Backbone.View.extend({

    initialize:function () {

    },

    render:function () {
        $(this.el).html(this.template());
        return this;
    },

    events: {
    	'click #loginBtn':'loginUser',
      'click #resetPass':'sendResetPass'
    },

    loginUser: function () {
      var user = new Parse.User();
    	var myEmail = this.$('#email').val();
    	var myPassword = this.$('#password').val();
    	//var user = new Parse.User();
  		Parse.User.logIn(myEmail, myPassword, {
  			success: function(user) {
   			 // After successful login, redirect user to her/his home page
   			this.location.replace('');
  			},
 			error: function(user, error) {
    		// The login failed. Try again.
    		alert('Login failed! Try again Please. Keep in mind You need to be a singed up user.');
		    }
		  });
	  },

    sendResetPass: function(){
      var myEmail = this.$('#email').val();
      Parse.User.requestPasswordReset(myEmail, {
        success: function() {
        // Password reset request was sent successfully
        alert('Password Reset Link has been sent to your email.');
        },
        error: function(error) {
        // Show the error message somewhere
        alert("Error: " + error.code + " " + error.message);
        }
      });
    }

});

