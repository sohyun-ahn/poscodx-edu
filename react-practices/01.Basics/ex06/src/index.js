import { App } from "./App.js";
import ReactDom from "react-dom/client";
// document
//     .getElementById('root')
//     .appendChild(App());

const root = ReactDom.createRoot(document.getElementById("root"));
root.render(App());
