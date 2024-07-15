import React, { Component } from "react";

export default class extends Component {
  constructor(props) {
    super(props);
    // this.value = this.props.begin;
    // this.step = this.props.step;

    this.state = {
      val: this.props.begin,
      val2: 20,
      val3: 30,
    };
  }

  render() {
    return (
      <div>
        <button
          onClick={() => {
            // 상태 저장도 안한 값을 사용
            // Anti-pattern : react의 상태를 변화시키고 변화된 상태를 렌더링 시키는 것을 하지 못하게 막아둠
            // this.value += this.step;
            // console.log(this.value);
            // this.render(); // 적용안됨. react anti-pattern

            this.setState({ val: this.state.val + this.props.step }); // 상태값을 변화시키면, 반영
          }}
        >
          <strong>{"+"}</strong>
        </button>{" "}
        <span>{this.state.val}</span>
      </div>
    );
  }
}
