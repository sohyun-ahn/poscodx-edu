/*
URL 다루기
*/

// 예 1) 리다이렉트
// location.href = url;

// 예 2) ajax 통신
// $.ajax(url)
// fetch(url) // ES6 이후로 나온 ajax 통신하는 함수
// axios(url) // library

var url = "http://www.mysite.com/user?name=둘리&email=dooly@gmail.com";

// parameter 부분은 escape 처리 해야함.
// 1. escape : URL 전부를 encoding, 사용(x) , deprecated
var url2 = escape(url);
console.log(url2); // http%3A//www.mysite.com/user%3Fname%3D%uB458%uB9AC%26email%3Ddooly@gmail.com

// 2. encodeURI : URL 전체 중 파라미터만 encoding 한다. url 전체를 encoding 해야하는 경우 사용(o)
var url3 = encodeURI(url);
console.log(url3); // http://www.mysite.com/user?name=%EB%91%98%EB%A6%AC&email=dooly%40gmail.com

// 3. encodeURIComponent : 값만 encoding 해야하는 경우 사용(o)
var url4 = encodeURIComponent(url);
console.log(url4); // 잘못 사용한 예시 // http%3A%2F%2Fwww.mysite.com%2Fuser%3Fname%3D%EB%91%98%EB%A6%AC%26email%3Ddooly%40gmail.com

// 4. encodeURIComponent 잘 사용한 예시
var url = "http://www.mysite.com/user";
var formData = {
  name: "둘리",
  email: "dooly@gmail.com",
};

var toQueryString = function (o) {
  var qs = [];
  for (prop in o) {
    qs.push(prop + "=" + encodeURIComponent(o[prop]));
  }

  return qs.join("&");
};

console.log(url + "?" + toQueryString(formData)); // http://www.mysite.com/user?name=%EB%91%98%EB%A6%AC&email=dooly%40gmail.com
