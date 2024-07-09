/**
 * Destruction (구조분해)
 */

// ex1 - basic
const user = {
  firstName: "둘",
  lastName: "리",
  age: 20,
};

// const firstName = user.firstName
// const lastName = user.lastName
// const age = user.age

const { firstName, lastName, age } = user;
console.log(firstName, age); // 둘 20

// ex02 - default value
const goods = {
  name: "TV",
  price: 100000,
  countStock: 10,
};

const { name, price, countStock = 0, countSold = 0 } = goods;
console.log(name, price, countStock, countSold); // TV 100000 10 0

// ex03 - 구조 분해 대상이 되는 객체의 속성 이름과 다른 변수 이름을 사용할 때...
const person = {
  n: "마이콜",
  c: "korea",
};

const { n: fullname, c: country } = person;
console.log(fullname, country); // 마이콜 korea

// ex04 - 내부 객체(nested object)의 구조 분해
const student = {
  email: "dooly@gmail.com",
  score: {
    math: 30,
    korean: 100,
    science: 60,
  },
};

const {
  email,
  score: { math, korean, science },
} = student;
console.log(email, math, korean, science); // dooly@gmail.com 30 100 60

// ex05 - 함수 파라미터
const avgScore = ({ email, score: { math, korean, science, music = 0 } }) => {
  console.log(`${email}: ${(math + korean + science + music) / 4}`);
};
avgScore(student);

// ex06 - 배열의 구조 분해: Basic
const color = [155, 200, 75];
let [red, green, blue] = color;
console.log(red, green, blue); // 155 200 75

// ex07 - 배열의 구조 분해: Default value
[red, green, blue, alpha = 0] = color;
console.log(red, green, blue, alpha); // 155 200 75 0

// ex08 - 배열의 구조 분해: Skip Value
const [, , colorOfBlue] = color;
console.log(colorOfBlue); // 75

// ex09 - swap
let x = 10;
let y = 20;
console.log(x, y);

let temp = x;
x = y;
y = temp;
console.log(x, y); // 20 10

[y, x] = [x, y]; // 배열의 구조 분해를 이용한 swap
console.log(x, y); // 10 20

// ex10 - ...array : array spread operator
const colors = ["red", "orange", "yellow", "green", "blue", "indigo", "violet"];
const [firstColor, secondColor, ...otherColors] = colors;
console.log(firstColor, secondColor, otherColors);

// ex10-01: 가변 파라미터 함수에 ...[]를 사용할 수 있다.
const printColor = function () {
  Array.from(arguments).forEach((e) => console.log(e)); // arrow function 은 arguments 인자를 가지고 있지않다.
};

// printColor(colors[0], colors[1], colors[2], colors[3], colors[4], colors[5], colors[6])
printColor(...colors);

// ex10-02: ...를 함수의 파라미터 이름으로 사용하기: arguments 대용으로 사용 가능
const printColor2 = (...colors) => {
  console.log(colors);
};

printColor2("red", "orange", "yellow");
