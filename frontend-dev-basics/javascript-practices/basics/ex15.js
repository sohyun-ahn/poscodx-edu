/*
 내장 객체 Date 객체 함수 : Date.prototype.*
 */

// 현재 시간
var now = new Date();
console.log(now); // 2024-07-04T02:43:25.073Z GMT+0900 (한국 표준시)

var d1 = new Date(2022, 6 /*month-1*/, 4); // 2022-07-04T 00:00:00 => 현재 시스템의 타임존이 UTC+9이기 때문에, 2022년 7월 4일 00:00:00 (로컬 시간)이 UTC 시간으로 변환되어 2022-07-03T15:00:00.000Z 이 출력됨
console.log(d1);

var d2 = new Date(2022, 6, 4, 13, 6, 40); // 2022-07-04T 13:06:40
console.log(d2);

// 객체 메서드
console.log(
  "년도 :" +
    d2.getYear() +
    1900 +
    "\n" +
    "월: " +
    d2.getMonth() +
    1 +
    "\n" +
    "일: " +
    d2.getDate() +
    "\n" +
    "시: " +
    d2.getHours() +
    "\n" +
    "분: " +
    d2.getMinutes() +
    "\n" +
    "초: " +
    d2.getSeconds() +
    "\n" +
    "밀리초: " +
    d2.getMilliseconds()
);

d2.setFullYear(2024);
console.log(d2);
d2.setMonth(11); // 12월 - 1
console.log(d2);
