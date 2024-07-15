import React from "react";
import { tab_view } from "./assets/scss/TabView.scss";

function TabView({ content }) {
  return <div className={tab_view}>{content}</div>;
}

export default TabView;
