import React, { Component } from "react";

export default class TitelBar01 extends Component {
  constructor(props) {
    super(props);
  }

  handleClick() {
    console.log("TitledBar01: clicked!");
  }

  render() {
    return (
      <div>
        <h4 onClick={this.handleClick}>
          Function Handler in Class Component(click here!)
        </h4>
      </div>
    );
  }
}
