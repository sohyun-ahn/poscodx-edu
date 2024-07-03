/* null 과 undefined */

var myVar1 = undefined; // 명시적으로 undefined 대입
var myVar2; // 암시적으로 undefined 대입
var myVar3 = null;

console.log(myVar1 + ":" + myVar2 + ":" + myVar3); // output: undefined:undefined:null

// undefined와 null의 동치(equal)성 비교
console.log(myVar1 == myVar3); // 값비교
console.log(myVar1 === myVar3); // 타입비교 + 값비교

console.log("=================================");
// == : equality, 값의 동치성
console.log("4" == 4); // output: true
console.log(false == 0); // output: true
console.log("abc" == new String("abc")); // output: true

console.log(true + 11); // output: 12
console.log("abc" + new String("abc")); // output: abcabc
console.log(1 + "11"); // output: 111  // number가 string으로 형변환된다.
console.log("11" + 1); // output: 111  // number가 string으로 형변환된다.

console.log("=================================");
// ===
// 1. 타입의 동일성
// 2. 타입이 같은 경우
//     2-1. primitive type: 값의 동일성을 따짐
//     2-2. object type: 객체의 동일성
console.log("4" === 4); // output: false
console.log(1 === true); // output: false
console.log("abc" === new String("abc")); // output: false
console.log(4 === 2); // output: false
console.log(new Number(10) === new Number(10)); // output: false
