//:::::::: Quiz Model
window.MyQuiz = Parse.Object.extend({
  className: "Quiz"
});
//:::::::: Quizes Collection
window.MyQuizes = Parse.Collection.extend({
  model: MyQuiz
});
