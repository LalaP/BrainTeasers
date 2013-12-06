//:::::::: Student Model
window.MyStudent = Parse.Object.extend({
  className: "Student"
});
//:::::::: Students Collection
window.MyStudents = Parse.Collection.extend({
  model: MyStudent
});
