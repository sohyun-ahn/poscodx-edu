import React from "react";

function Clock01() {
  const now = new Date();
  let hour = now.getHours();
  let min = now.getMinutes();
  let sec = now.getSeconds();

  // 지양해야하는 코드
  if (hour < 10) {
    hour = "0" + hour;
  }

  if (min < 10) {
    min = "0" + min;
  }

  if (sec < 10) {
    sec = "0" + sec;
  }

  return (
    <>
      <div>
        {hour} : {min} : {sec}
      </div>
    </>
  );
}

export default Clock01;
