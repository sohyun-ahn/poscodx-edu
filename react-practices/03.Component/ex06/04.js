import fs from "fs";
import update from "react-addons-update";

console.log("== Sol =========================================");
let order = JSON.parse(fs.readFileSync("./json/data.json", "utf-8"));

const updateOrder = update(order, {
  // property 변경
  receive: {
    $set: "강남구 논현동...",
  },

  // nest 객체 프로퍼티 변경
  payment: {
    method: {
      $set: "mobile",
    },
  },
  products: {
    // 배열 요소 객체 프로퍼티 변경
    0: {
      amount: {
        $set: 200,
      },
    },

    // 배열 요소만 추가
    $push: [
      {
        no: "c002-003",
        name: "블루양말",
        price: 2000,
        amount: 1,
      },
    ],
  },
});

console.log(order);
