import React from "react";
import { tab_view } from "./assets/scss/TabView.scss";

function TabView({ content }) {
  return (
    <div className={tab_view}>
      {content != "" ? content : "TabView 입니다. 메뉴를 클릭하세요."}
    </div>
  );
}

export default TabView;
