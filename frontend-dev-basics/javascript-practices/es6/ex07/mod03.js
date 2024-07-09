/**
 * named export
 *
 * 1. exports(require) 와 비슷하다.
 *  exports.a = ...
 *  exports.b = ...
 *
 * 2. import하는 모듈에 destucting 할 수 있다.
 *
 */

export const add = function (a, b) {
  return a + b;
};

export const subtract = function (a, b) {
  return a - b;
};
