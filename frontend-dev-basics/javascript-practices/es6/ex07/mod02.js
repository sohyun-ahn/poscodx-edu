/**
 * unnamed export
 * default 키워드를 사용한다.
 */

// export default {
//   add: function (a, b) {
//     return a + b;
//   },
//   subtract: function (a, b) {
//     return a - b;
//   },
// };

const add = function (a, b) {
  return a + b;
};

const subtract = function (a, b) {
  return a - b;
};

export default { add, subtract };
// 이것과 같은 의미
// export default {
//   add: add,
//   subtract: subtract,
// };
