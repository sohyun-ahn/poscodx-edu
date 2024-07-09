/* 모듈로 분리되어 있지 않은 App */

var App = function () {
  var app = {};
  app.textContents = "hello world";
  return app;
};

console.log(App());
