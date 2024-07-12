import React from "react";
import TabBox from "./TabBox";
import { tab_box } from "./assets/scss/TabBox.scss";
import "./assets/scss/App.scss";

function App() {
  return (
    <div id="App">
      <TabBox className={tab_box} />
    </div>
  );
}

export { App };
