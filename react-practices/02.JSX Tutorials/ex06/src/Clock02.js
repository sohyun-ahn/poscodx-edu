import React from "react";

function Clock02() {
  const now = new Date();
  let hour = now.getHours();
  let min = now.getMinutes();
  let sec = now.getSeconds();

  return (
    <>
      <div>
        {("0" + hour).slice(-2)}
        {" : "}
        {("0" + min).slice(-2)}
        {" : "}
        {("0" + sec).slice(-2)}
      </div>
    </>
  );
}

export default Clock02;
