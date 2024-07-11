import React from "react";
import Tabs from "./Tabs";
import TabView from "./TabView";

function TabBox() {
  const tabs = [
    { no: 1, name: "메뉴1", active: false, content: "메뉴1 입니다" },
    { no: 2, name: "메뉴2", active: false, content: "메뉴2 입니다" },
    { no: 3, name: "메뉴3", active: true, content: "메뉴3 입니다" },
    { no: 4, name: "메뉴4", active: false, content: "메뉴4 입니다" },
    { no: 5, name: "메뉴5", active: false, content: "메뉴5 입니다" },
  ];
  return (
    <div className="tab-box">
      <Tabs tabs={tabs} />
      <TabView tabs={tabs} />
    </div>
  );
}

export default TabBox;
