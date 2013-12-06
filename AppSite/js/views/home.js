window.Home = Backbone.View.extend({

     initialize:function () {
        var classCollection = new MyClasses();
        classCollection.fetch({
            success: function(collection) {
                collection.each(function(ourClass) {
                    var classTerm = ourClass.get('term');
                    var classDepartment = ourClass.get('department');
                    var classCourse = ourClass.get('course');
                    var classSection = ourClass.get('section');
                    var classTableRow =  '<tr>'+'<td>'+ourClass.id+'</td>'+'<td>'+classTerm+'</td>'+'<td>'+classDepartment
                    +'</td>'+'<td>'+classCourse+'</td>'+'<td>'+classSection+'</td>'+'<td><a href="view">view</a><br/><a href="edit">edit</a><br/><a href="delete">delete</a><br/></td></tr>';
                    this.$('#classesTableBody').append(classTableRow);
                },this);  
            },
            error: function(error) {

            }
        });

        this.render();
    },

    render:function () {
       $(this.el).html(this.template(this.options));
        return this;
    },

    events:{
        'click #refreshBtn':'testEffect', 
        'click #addClassBtn':'addClassFun'        
    },

    testEffect:function(){
    var name = Parse.User.current().get('username');
    $('#myName').text("you are a smart Girl");        
    }

    
});
   