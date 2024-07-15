import React, { useState } from "react";
import Tabs from "./Tabs";
import TabView from "./TabView";
import { tab_box } from "./assets/scss/TabBox.scss";
import tabs from "./assets/json/data";

function TabBox() {
  // json/data.js 파일에 넣음
  // const tabs = [
  //   { no: 1, name: "메뉴1", active: false, content: "메뉴1 입니다" },
  //   { no: 2, name: "메뉴2", active: false, content: "메뉴2 입니다" },
  //   { no: 3, name: "메뉴3", active: true, content: "메뉴3 입니다" },
  //   { no: 4, name: "메뉴4", active: false, content: "메뉴4 입니다" },
  //   { no: 5, name: "메뉴5", active: false, content: "메뉴5 입니다" },
  // ];

  const [activeNo, setActiveNo] = useState(0);
  const handleSelectedTab = (no) => {
    tabs.map((tab) => {
      if (tab.no === no) {
        tab.active = true;
        setActiveNo(no);
      } else {
        tab.active = false;
      }
    });
  };

  return (
    <div className={tab_box}>
      <Tabs
        handleSelectedTab={handleSelectedTab}
        tabs={tabs.map((e) => {
          const { content, ...rest } = e;
          return rest; // content 빼고 모두 보내기
        })}
      />
      <TabView
        content={tabs.filter((tab) => tab.active).map((tab) => tab.content)}
      />
    </div>
  );
}

export default TabBox;
