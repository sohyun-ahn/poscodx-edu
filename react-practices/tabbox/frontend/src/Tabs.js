import React from "react";
import Tab from "./Tab";
import { styled_tabs } from "./assets/scss/Tabs.scss";

function Tabs({ tabs }) {
  return (
    <ul className={styled_tabs}>
      {tabs.map((tab) => (
        <Tab key={tab.no} active={tab.active} name={tab.name} />
      ))}
    </ul>
  );
}

export default Tabs;
