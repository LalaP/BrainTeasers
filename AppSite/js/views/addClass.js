window.AddClass = Backbone.View.extend({

    initialize:function () {
        // this.template = _.template(directory.tpl.templateLoader.get('home'));
        // this.template = templates['Home'];
    },

    render:function () {
        $(this.el).html(this.template(this.options));
        return this;
    },

    events:{
        'click #addClassBtn':'addNewClass'
    },
    addNewClass:function(e){
    e.preventDefault();
    
    // Get the field attributes
        var myTerm = this.$("#termForm").val();
        var myDepartment = this.$('#departmentForm').val();
        var myCourse = this.$('#courseForm').val();
        var mySection = this.$('#sectionForm').val();
        //======= Save Into Parse Class Object
        var classIns = new MyClass();
        var userid = Parse.User.current();
        var classACL = new Parse.ACL();
        classACL.setReadAccess(userid,true);
        classACL.setWriteAccess(userid,true);
        classIns.setACL(classACL);
    
    classIns.save({
            term:  myTerm,
            department: myDepartment,
            course: myCourse,
            section: mySection
        },
        {
         success: function() {
            console.log('class was added successfully');
            //==== Redirect to class view page
            this.location.replace('');
         },
         error: function(error){
         }
    });
}

});




gameScore.save(null, {
  success: function(gameScore) {
    // Execute any logic that should take place after the object is saved.
    alert('New object created with objectId: ' + gameScore.id);
  },
  error: function(gameScore, error) {
    // Execute any logic that should take place if the save fails.
    // error is a Parse.Error with an error code and description.
    alert('Failed to create new object, with error code: ' + error.description);
  }
});