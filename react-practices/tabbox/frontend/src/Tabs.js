import React from "react";
import Tab from "./Tab";

function Tabs({ tabs }) {
  return (
    <ul>
      {tabs.map((tab) => (
        <Tab key={tab.no} active={tab.active} name={tab.name} />
      ))}
    </ul>
  );
}

export default Tabs;
