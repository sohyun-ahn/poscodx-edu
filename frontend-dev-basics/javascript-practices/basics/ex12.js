/*
Array 확장(prototype 기반의 확장)
*/

// List 함수 사용하기
var a = [1, 2, 4];
console.log(a); // [ 1, 2, 4 ]

// a.remove(2); // 4 out 이라고 예상하지만, array에 remove란 메소드가 없기 때문에 TypeError a.remove is not a function

Array.prototype.remove = function (index) {
  // 밖에서 splice 쓰기 어려우니깐 remove 메서드로 바꾸기
  console.log("call remove: index" + index);
  this.splice(index, 1); // this : remove를 확장하는 배열
};

Array.prototype.insert = function (index, value) {
  if (value instanceof Array) {
    // for (var i = 0; i < value.length; i++) {
    //   this.splice(index++, 0, value[i]); // 배열안의 값을 insert하게
    // }

    // 오류 !
    // 콜백 함수 안의 this는 어휘 상의 this와 일치하지 않는다.
    // 나중에 실행되는 파트의 this이기 때문에.
    // this;
    // value.forEach(function (e) {
    //   this.splice(index++, 0, e);
    // console.log(this);
    //});
    // 해결 방법1
    // var that = this;
    // var _this = this;
    // value.forEach(function (e) {
    //   _this.splice(index++, 0, e); // 바깥의 this를 쓰고 싶기 때문
    // });
    // 해결 방법2 - bind() // callback 함수를 콜백으로 넘기면서 this를 맞추기
    this; // 이 this와 bind(this)랑 같은 this이다.
    value.forEach(
      function (e) {
        that.splice(index++, 0, e);
      }.bind(this)
    ); // 실행할때 내부의 this를 바꿔치기 forEach로 value를 호출할때 이 this를 쓰게하라는 말

    // 해결 방법3 - ES6 arrow function, 어휘상의 this와 일치함
    // value.forEach((e) => this.splice(index++, 0, e));
  } else {
    console.log("call insert: index" + index + ", value" + value);
    this.splice(index, 0, value); // this : insert를 확장하는 배열
  }
};

// List 함수 사용하기
var a = [1, 2, 4];
console.log(a); // [ 1, 2, 4 ]

a.insert(2, 3);
console.log(a); // [ 1, 2, 3, 4 ]

a.remove(2); // 3 out~!
console.log(a); // [1, 2, 4]

a.insert(2, ["a", "b", "c"]);
console.log(a); // [ 1, 2, 'a', 'b', 'c', 4 ]

o = {
  forEach: function (f) {
    for (p in this) {
      {
        callback(p);
        //f(p);
      }
    }
  },
};
