/**
 * promise 지원 비동기 함수 사용법 II: async ~ await
 */

const { asyncFn02 } = require("./async-fns");

// test: success
// 함수명 앞에 await을 붙여주게 되면,
// 원래대로 기다리지않고 값을 받게 되면 undefined로 받을텐데 그러지 않고, 기다렸다가 값을 받아옴
// await은 async 함수로 wrapping를 한 다음에 해야 가능하다.
const fn = async (param) => {
  const result = await asyncFn02(param);
  console.log(result);
};

// test: fail
asyncFn02(null)
  .then((result) => {
    console.log(result);
  })
  .catch((error) => {
    console.error(error);
  });

fn("params~");

console.log("wait....");

// 사용 예시 코드
const fetchEmail = async (email) => {
  const emails = fetch("/api/");
  setEmails(emails);
};
