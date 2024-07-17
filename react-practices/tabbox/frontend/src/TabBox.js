import React, { useState } from "react";
import Tabs from "./Tabs";
import TabView from "./TabView";
import { tab_box } from "./assets/scss/TabBox.scss";
import tabs from "./assets/json/data";

function TabBox() {
  // tabs 데이터를 json/data.js 파일에 넣음

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
