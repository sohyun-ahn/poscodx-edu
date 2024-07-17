// callback 지원 비동기 함수
exports.asyncFn01 = function (param1, callback) {
  // 비동기 코드: 파일 시스템(file io), 네트워크 통신 (fetch), SQL to DB, setTimer, ..., etc
  setTimeout(() => {
    // 비동기 처리가 됐다 치고~
    if (param1 !== null) {
      // 결과 처리
      callback(null, "callback success: " + param1);
    } else {
      // 에러 처리
      callback(new Error("error: param1 is null"), null);
    }
  }, 3000);
};

// promise 지원 비동기함수
exports.asyncFn02 = function (param) {
  return new Promise((resolve, reject) => {
    // 비동기 코드 넣기
    setTimeout(() => {
      // 비동기 처리가 됐다 치고~
      if (param1 !== null) {
        // 성공 처리
        resolve({ result: "success" });
      } else {
        // 에러 처리
        reject(new Error("error: param1 is null"));
      }
    }, 3000);
  });
};
