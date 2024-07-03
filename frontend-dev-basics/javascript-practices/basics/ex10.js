/*
Array: 배열
*/

console.log("=== 배열생성1: 내장객체(생성자함수) 사용하는 방법 ===");
var a1 = new Array();
console.log(typeof a1, a1 instanceof Array, a1.length);

// 생성자 함수의 파라미터가 하나인 경우: 배열의 크기
var a2 = new Array(10);
console.log(typeof a2, a2 instanceof Array, a2.length);

// 배열의 크기를 지정하는 별 의미가 없다: 동적으로 배열이 늘어난다.
a2[0] = 0;
a2[5] = 5;
a2[10] = 10;
console.log(a2, a2.length);

// 생성자 함수의 파라미터가 두 개 이상인 경우: 초기값 설정
var a3 = new Array(0, 2, 4, 6, 8);
console.log(a3, a3.length);

console.log("=== 배열생성2: 리터럴을 사용하는 방법===");
var a4 = [];

// 배열 요소의 타입은 동일하지 않아도 된다.
var a5 = [
  1,
  "2",
  true,
  null,
  undefined,
  { name: "둘리", age: 10 },
  function () {
    console.log("hello world");
  },
];
console.log(a5, a5.length);
a5[6]();

console.log("=== 배열순회 ===");
for (var i = 0; i < a5.length; i++) {
  console.log(a5[i]);
}

console.log("=== Array vs Object ===");
var a6 = []; // Array
a6[0] = 0;
a6["1"] = 1;
a6["2"] = 2;
a6.name = "둘리"; // length는 그대로
a6.age = 10; // name, age는 Object.prototype.toString() method를 override하지 않아서 "object"로 출력된다.

console.log(
  a6["0"],
  a6[1],
  a6[2],
  a6["name"],
  a6["age"],
  "length: " + a6.length
); // 0 1 2 둘리 10 length: 3 잘 출력된다

var o6 = {}; // Object
o6[0] = 0;
o6["1"] = 1;
o6["2"] = 2;
o6.name = "둘리";
o6.age = 10;
console.log(
  o6["0"],
  o6[1],
  o6[2],
  o6["name"],
  o6["age"],
  "length: " + o6.length
); // 0 1 2 둘리 10 length: undefined 로 출력된다

console.log("=== for ~ in: 객체 ===");
for (prop in o6) {
  console.log(prop + ": " + o6[prop]);
}

console.log("=== for ~ in: 배열 =====");
for (prop in a6) {
  console.log(prop + ":" + a6[prop]);
}
