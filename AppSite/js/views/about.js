window.About = Backbone.View.extend({

    initialize:function () {
        // this.template = _.template(directory.tpl.templateLoader.get('home'));
        // this.template = templates['Home'];
    },

    render:function () {
        $(this.el).html(this.template());
        return this;
    },

    events:{

    }
});