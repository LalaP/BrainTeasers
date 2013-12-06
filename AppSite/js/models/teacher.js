//:::::::: Teacher Model
window.MyTeacher = Parse.Object.extend({
  className: "Teacher"
});
//:::::::: Teachers Collection
window.MyTeachers = Parse.Collection.extend({
  model: MyTeacher
});
