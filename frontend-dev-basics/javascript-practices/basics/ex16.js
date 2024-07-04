/*
자바스크립트 함수: function 타입의 객체
*/

// 함수를 생성(정의) 방법1
function f1(a, b) {
  return a + b;
}

console.log(typeof f1, f1(10, 20));

// 함수를 생성(정의) 방법2
var f2 = function (a, b) {
  return a + b;
};

console.log(typeof f2, f2(10, 20));

// 함수를 생성(정의) 방법3
var f3 = new Function("a", "b", "return a + b;");

console.log(typeof f3, f3(10, 20));

// 함수를 생성하는 방법4 : 익명(Anonymous)함수
setTimeout(function () {
  console.log("time out!");
}, 1000);

// 함수를 생성하는 방법5 : 즉시 실행 함수
var s = (function (a, b) {
  return a + b;
})(10, 20);
console.log(s);

// 가변 파리미터 (...)
var sum = function () {
  // arguments : 내장객체인 배열
  var sum = 0;

  // 구현1
  //   for (var i = 0; i < arguments.length; i++) {
  //     sum += arguments[i];
  //   }
  //   return sum;

  // 구현2 : error
  // 불가능 코드 : arguments가 유사 배열이기 때문
  // __proto__ -> object를 가리키기 때문에 array가 아님.
  // arguments는 Array가 아니다. 유사배열이다.
  // console.log(arguments instanceof Array); // false
  //   arguments.forEach((element) => {
  //     sum += element;
  //   });

  // Array.prototype.forEach 직접 호출 + caller 바꾸기
  Array.prototype.forEach.call(arguments, (arg) => {
    sum += arg;
  });

  return sum;
};

console.log(sum(1, 2)); // 3
console.log(sum(1, 2, 3, 4, 5)); // 15
console.log(sum(1, 2, 3, 4, 5, 6, 7, 8, 9)); // 45
