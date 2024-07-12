import React from "react";
import { styled_tab, styled_active } from "./assets/scss/Tab.scss";

function Tab({ active, name }) {
  return (
    <li className={`${styled_tab} ${active ? styled_active : ""}`}>{name}</li>
  );
}

export default Tab;
