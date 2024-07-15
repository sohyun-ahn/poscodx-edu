import React, { useRef } from "react";
import logo from "./assets/images/react-logo.png";

export default function App() {
  const imgRef = useRef(null);

  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      alert("Enter키를 눌렀습니다.");
    }
    console.log(e.target.value);
  };

  const handleMouseOver = (e) => {
    // 밖에서 안으로 들어왔을때 한번 일어남
    console.log("mouseover", `x=${e.clientX}, y=${e.clientY}`); // x,y 위치 표시
  };

  const handleMouseMove = (e) => {
    // 이벤트가 발생할 때마다 좌표 출력
    const offsetTop = imgRef.current.offsetTop;
    const offsetLeft = imgRef.current.offsetLeft;
    console.log(
      "mousemove",
      `x=${e.clientX - offsetLeft}, y=${e.clientY - offsetTop}`
    ); // x,y 위치 표시
  };

  const handleMouseOut = (e) => {
    console.log("mouseout", `x=${e.clientX}, y=${e.clientY}`); // x,y 위치 표시
  };

  const handleMouseUp = (e) => {
    console.log("mouseup", `x=${e.clientX}, y=${e.clientY}`); // x,y 위치 표시
  };

  const handleMouseDown = (e) => {
    console.log("mousedown", `x=${e.clientX}, y=${e.clientY}`); // x,y 위치 표시
  };

  const handleClick = (e) => {
    console.log("click!", `x=${e.clientX}, y=${e.clientY}`); // x,y 위치 표시);
  };

  const handleDoubleClick = (e) => {
    console.log("double click!", `x=${e.clientX}, y=${e.clientY}`); // x,y 위치 표시);
  };

  return (
    <>
      <h2>Event Handler 예제</h2>
      <input
        type="text"
        placeholder="메세지를 입력 하세요"
        onKeyDown={handleKeyDown}
        onFocus={() => console.log("Focused!")}
        onBlur={() => console.log("Blurred!") /* UI 변경시 많이 사용 */}
      />
      <br />
      <br />
      <img
        style={{
          cursor: "pointer",
          width: 190,
          border: "1px solid #ccc",
        }}
        src={logo}
        ref={imgRef}
        onMouseOver={handleMouseOver}
        onMouseMove={handleMouseMove}
        onMouseOut={handleMouseOut}
        onMouseDown={handleMouseDown}
        onMouseUp={handleMouseUp}
        onClick={handleClick}
        onDoubleClick={handleDoubleClick}
      />
    </>
  );
}
