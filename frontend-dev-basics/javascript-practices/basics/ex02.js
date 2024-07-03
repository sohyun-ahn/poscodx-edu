/*
변수와 자료형(data type)

[기본타입, primitive type]
- undefined
- number
- string
- boolean
[객체***]
- object
    1. new 생성자 함수 사용해서 생성
     Number()    => object 타입의객체
     String()    => object 타입의객체
     Boolean()   => object 타입의객체
     Object()    => object 타입의객체
     Array()     => object 타입의객체
     Date()      => object 타입의객체
     RegExp()    => object 타입의객체
     Function()  => function 타입의객체
    2. {}
    3. []
    4. null
- function

 */

/*
자바스크립트의 객체를 분류하는 또 다른 방법
1. 내장 객체
   - 자바 스크립트 엔진 내부에 미리 내장(정의)되어있는 객체
   - Number, String, Boolean, Array, Date, RegExp, Function, Math, JSON, ... : 생성자함수
   - parseInt, setTimeout, setInterval, ...  : 일반함수

   자바스크립트는 array 중요!

2. host 객체
   - 자바스크립트 엔진이 embedded 되어 있는 호스 환경에 있는 객체
   - browser 호스트: screen, location, navigator, document(DOM), XmlHttpRequest
   - Node 호스트 : http, os, fs, path, process, ...

3. 사용자 객체
   - 자바스크립트 엔진이 코드를 실행하면서 생성되는 객체 

*/

console.log("====== 기본타입(undefined, number, string, boolean) ======");
var u; // var u = undefined과 동일, 자바스크립트는 선언과 정의가 구분이 없다.
var i = 10;
var s = "hello world";
var b = true;

console.log("u:" + typeof u);
console.log("i:" + typeof i);
console.log("s:" + typeof s);
console.log("b:" + typeof b);

console.log("=== 객체타입(function) ======");
function f1(a, b) {
  return a + b;
}

var f2 = function (a, b) {
  return a + b;
};

var f3 = new Function("a", "b", "return a+b;");

console.log(f1(10, 20), f2(10, 20), f3(10, 20));
console.log("f1:" + typeof f1);
console.log("f2:" + typeof f2);
console.log("f3:" + typeof f3);

console.log("=== Primitive Type도 메소드를 호출할 수 있다.(유사객체) ===");
console.log(b2.valueOf());
console.log(b.valueOf()); // console.log(new Boolean(b).valueof());

console.log("========================================");
function ff() {
  return console.log("Hello function!");
}

var f = ff();

var f2 = new ff();

var f3 = new Function();

console.log("f3: " + typeof f3);

console.log("f2: " + typeof f2);
console.log("f: " + typeof f);
