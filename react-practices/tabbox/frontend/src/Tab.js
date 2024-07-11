import React from "react";

function Tab({ active, name }) {
  return <li className={active ? "active" : ""}>{name}</li>;
}

export default Tab;
