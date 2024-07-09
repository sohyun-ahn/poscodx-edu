/*
let & const
*/

// let
try {
  if (true) {
    var i = 10; // global scope
    let j = 20; // block scope
  }

  console.log(i); // 10
  //console.log(j); // ReferenceError: j is not defined
} catch (e) {
  console.error("error: " + e);
}
