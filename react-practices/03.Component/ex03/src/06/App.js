import React, { Component } from "react";
import "./assets/scss/App.scss";

export default class App extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div
        className={"App"}
        ref={(ref) => (this.outerRef = ref)}
        onScroll={(e) => {
          console.log(
            `Scroll 이벤트! outerRef.scrollTop: ${this.outerRef.scrollTop}, outerRef.clientHeight: ${this.outerRef.clientHeight}, innerRef.clientHeight: ${this.innerRef.clientHeight}`
          );
        }}
      >
        <div
          ref={(ref) =>
            (this.innerRef = ref)
          } /* 왜 이 부분을 그냥 function(ref){} 로 처리하면 this.innerRef early binding이 안될까? */
        >
          <ul>
            {Array.from({ length: 100 }, (_, i) => i + 1).map((e) => (
              <li key={e}>{`아이템 ${e}입니다.`}</li>
            ))}
          </ul>
        </div>
      </div>
    );
  }
}
