// Definition of routs and functions
window.Router = Backbone.Router.extend({

    routes: {
        "":"homeFun",
        "login":"loginFun",
        "register":"registerFun",
        "about":"aboutFun",
        "logout":"logoutFun"       
    },

    initialize: function () {
        Parse.initialize("DLRqHE5y4wZLfv6LDLZdoZPd4yLe2HCOkOymvgV2", "iHwkFd3jwRb8yCOr5xkCWwpXuEyRCpxQ3fZJgh07");

    },

    homeFun: function () {
        var currentUser = Parse.User.current();
        if(currentUser){
            if (!this.Home) {
                this.homePage = new Home();
                this.homePage.render();
            } else {
                this.homePage.render();
            }
            $("#container").html(this.homePage.el);
        }else{
            window.location.replace('#/login');
        }
    },

    loginFun: function () {
        if(!this.Login){
            this.loginPage = new Login();
            this.loginPage.render();
        }else{
            this.loginPage.render();
        }
        $("#container").html(this.loginPage.el);
    },

    registerFun: function(){
        if(!this.Register){
            this.registerPage = new Register();
            this.registerPage.render()
        }else{
            this.Register.delegateEvents();
        }
        $("#container").html(this.registerPage.el);        
    },

    aboutFun: function() {
        if(!this.About){
            this.aboutPage = new About();
            this.aboutPage.render()
        }else{
            this.About.delegateEvents();
        }
        $("#container").html(this.aboutPage.el); 
    },

    logoutFun: function(){
        if(currentUser){
            Parse.User.logOut();
        }
        else{}
        var currentUser = Parse.User.current();  
        if(!this.Login){
            this.loginPage = new Login();
            this.loginPage.render();
        }else{
            this.Login.delegateEvents();
        }
        $("#container").html(this.loginPage.el);        
    }
});
// end of routs and functions definition

// 
templateLoader.load(["Home","Login","Register","About"],
    function () {
        app = new Router();
        Backbone.history.start();
});
