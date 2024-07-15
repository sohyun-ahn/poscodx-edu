import React from "react";
import { styled_tab, styled_active } from "./assets/scss/Tab.scss";

function Tab({ no, active, name, handleSelectedTab }) {
  return (
    <li
      className={`${styled_tab} ${active ? styled_active : ""}`}
      onClick={() => handleSelectedTab(no)}
    >
      {name}
    </li>
  );
}

export default Tab;
