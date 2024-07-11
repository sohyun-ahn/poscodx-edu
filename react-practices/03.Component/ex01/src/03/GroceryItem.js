import React, { Component } from "react";

export default class GroceryItem extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <li>
        <strong>{this.props.name}</strong>
        <span>{this.props.count}</span>
      </li>
    );
  }
}
