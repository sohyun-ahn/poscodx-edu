/*
Template Literal
*/

const no = 10;
const name = "둘리";
const email = "dooly@gmail.com";

// ex01
console.log("no: " + no + ", name: " + name + ", email: " + email);
console.log(`My name is ${name}, and my email is ${email}`); // template literal

// ex02: 다중행 & template literal
console.log(`
  no: ${no}
 name: ${name}
 email: ${email}
 `);
