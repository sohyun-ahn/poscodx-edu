/*
Array 객체 함수 : Array.prototype.*
*/

var colors = ["black", "white", "yellow"];
var fruits = ["apple", "banana", "mango"];

// concat
console.log("=== concat ===");

var a1 = fruits.concat(colors); // [ 'apple', 'banana', 'mango', 'black', 'white', 'yellow' ]
console.log(a1);

// pop, push: stack 지원
console.log("=== stack 지원(pop, push) ===");

var lastFruit = fruits.pop();
console.log(fruits, lastFruit); // [ 'apple', 'banana' ] mango

var firstColor = colors.push("red");
console.log(colors, firstColor); // [ 'black', 'white', 'yellow','red' ] 4

// join
console.log("=== join ===");

var a2 = fruits.join(", ");
console.log(a2); // apple, banana

// reverse
console.log("=== reverse ===");

console.log(colors); // [ 'black', 'white', 'yellow', 'red' ]
colors.reverse();
console.log(colors); // [ 'red', 'yellow', 'white', 'black' ]

// shift, unshift: queue 지원
console.log("=== shift ===");

var nums1 = [1000, 3000, 2000, 4000, 5000, 8000];
var num1 = nums1.shift();
console.log(num1, nums1); // 1000 [ 3000, 2000, 4000, 5000, 8000 ]

// slice
console.log("=== slice ===");

var nums2 = nums1.slice(2, 4);
console.log(nums2, nums1); // [ 4000, 5000 ] [ 3000, 2000, 4000, 5000, 8000 ]

// splice : slice + 대체도 가능
// 1. index에서 count개를 삭제하고 삭제된 요소를 배열에 담아 반환
console.log("=== splice(index, count) ===");

console.log(fruits);
var fruits2 = fruits.splice(0, 2);
console.log(fruits2, fruits); // [ 'apple', 'banana' ] []

// 2. index에서 count개를 삭제하고 replace 대체한 후, 삭제된 요소를 배열에 담아 반환
console.log("=== splice(index, count, replace) ===");

var a1 = [0, 1, 2, 3, 4];
var a2 = a1.splice(1, 3, 10);
console.log(a2, a1); // [1,2,3] [0, 10, 4]

// 3. removeAt() 처럼 동작
console.log("=== removeAt(index) ===");

var a3 = [0, 1, 2, 3, 4];
a3.splice(2, 1); // == removeAt(2)
console.log(a3); // [0, 1, 3, 4]

// 4. insertAt() 처럼 동작
console.log("=== insertAt(index, item) ===");

var a4 = [0, 1, 2, 3, 4];
a4.splice(2, 0, 5); // == insertAt(2, 5)
console.log(a4); // [0, 1, 5, 2, 3, 4]
