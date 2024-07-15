import React from "react";
import Tab from "./Tab";
import { styled_tabs } from "./assets/scss/Tabs.scss";

function Tabs({ tabs, handleSelectedTab }) {
  return (
    <ul className={styled_tabs}>
      {tabs.map((tab) => (
        <Tab
          key={tab.no}
          no={tab.no}
          active={tab.active}
          name={tab.name}
          handleSelectedTab={handleSelectedTab}
        />
      ))}
    </ul>
  );
}

export default Tabs;
