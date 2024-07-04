/*
Function.prototype.bind
Function.prototype.apply
Function.prototype.call
*/

var myObject = {
  v: "me",
  f1: function () {
    console.log(this.v);
  },
  f2: function (param1, param2) {
    console.log(param1 + " " + this.v + " " + (param2 ? param2 : ""));
  },
};

var yourObject = {
  v: "you",
};

// 일반적인 객체의 함수 호출
myObject.f1();

// apply : 호출되는 함수의 this를 파라미터로 전달되는 객체로 바꿔서 즉시 함수를 호출한다.
myObject.f1.apply(yourObject); // hello you

// call : 호출되는 함수의 this를 파라미터로 전달되는 객체로 바꿔서 즉시 함수를 호출한다. (다른 파라미터도 함께 전달)
myObject.f2.call(yourObject, "hello"); // hello you
myObject.f2.call(yourObject, "hello", "again"); // hello you again

// bind : 호출되기 전의 함수 this를 파라미터로 전달되는 객체로 세팅한다.
var f3 = function () {
  console.log(this.v);
}.bind(myObject);

f3();
