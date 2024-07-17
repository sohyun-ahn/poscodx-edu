/**
 * callback 지원 비동기 함수 사용법
 */

const { asyncFn01 } = require("./async-fns");

// test: success
// 2. 3초 뒤 실행됨
// 함수를 호출하며 callback 을 넣어주면 됨
asyncFn01("params~~", (error, result) => {
  if (error) {
    console.error("callback error:", error);
    return;
  }
  console.log("callback result:", result);
});

console.log("wait......."); // 1. 실행

// test: fail
// 3. 3초 뒤 실행됨
asyncFn01(null, (error, result) => {
  if (error) {
    console.error("callback error:", error);
    return;
  }
  console.log("callback result:", result);
});
