//:::::::: Class Model
window.MyClass = Parse.Object.extend({
  className: "Class"
});
//:::::::: Classes Collection
window.MyClasses = Parse.Collection.extend({
  model: MyClass
});
