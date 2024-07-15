import React, { useRef } from "react";
import "./assets/scss/App.scss";

export default function App() {
  const outerRef = useRef(null);
  const innerRef = useRef(null);

  return (
    <div
      className={"App"}
      ref={outerRef}
      onScroll={(e) => {
        // scroll 할때마다 출력됨
        console.log(
          `Scroll 이벤트! outerWidth: ${outerRef.current.offsetWidth}, outerHeight: ${outerRef.current.offsetHeight}, scrollTop: ${e.target.scrollTop}, scrollHeight: ${e.target.scrollHeight}, clientHeight: ${e.target.clientHeight}`
        );

        // inner div scroll 이벤트
        console.log(
          `Scroll 이벤트! innerWidth: ${innerRef.current.offsetWidth}, innerHeight: ${innerRef.current.offsetHeight}, scrollTop: ${e.target.scrollTop}, scrollHeight: ${e.target.scrollHeight}, clientHeight: ${e.target.clientHeight}`
        );

        console.log(
          `Scroll 이벤트! outerRef.scrollTop: ${this.outerRef.current.scrollTop}, outerRef.clientHeight: ${this.outerRef.current.clientHeight}, innerRef.clientHeight: ${innerRef.current.clientHeight}`
        );
        /* innerRef.current.clientHeight =
          outerRef.current.scrollTop + outerRef.current.clientHeight;  이때 스크롤을 내려야한다! */
      }}
    >
      <div ref={innerRef}>
        <ul>
          {Array.from({ length: 100 }, (_, i) => i + 1).map((e) => (
            <li key={e}>{`아이템 ${e}입니다.`}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}
