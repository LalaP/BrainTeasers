// Definition of routs and functions
window.Router = Backbone.Router.extend({

    routes: {
        "":"homeFun",
        "login":"loginFun",
        "register":"registerFun",
        "about":"aboutFun",
        "logout":"logoutFun",
        "newClass":"addClassFun"       
    },

    initialize: function () {
        Parse.initialize("DLRqHE5y4wZLfv6LDLZdoZPd4yLe2HCOkOymvgV2", "iHwkFd3jwRb8yCOr5xkCWwpXuEyRCpxQ3fZJgh07");

    },

    homeFun: function () {
        var currentUser = Parse.User.current();
        if(currentUser){
            var name = Parse.User.current().get('username');           
            if (!this.Home) {
                this.homePage = new Home({username:name});
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
        Parse.User.logOut();
        if(!this.Login){
            this.loginPage = new Login();
            this.loginPage.render();
        }else{
            this.Login.delegateEvents();
        }
        $("#container").html(this.loginPage.el);        
    },

    //  addClassFun: function () {
    //     var currentUser = Parse.User.current();
    //     if(currentUser){
    //         var name = Parse.User.current().get('username');           
    //         if (!this.AddClass) {
    //             this.addClassPage = new AddClass({username:name});
    //             this.addClassPage.render();
    //         } else {
    //             this.addClassPage.render();
    //         }
    //         $("#container").html(this.addClassPage.el);
    //     }else{
    //         window.location.replace('#/login');
    //     }
    // }
});
// end of routs and functions definition

// 
templateLoader.load(["Home","Login","Register","About"],
    function () {
        app = new Router();
        Backbone.history.start();
});
