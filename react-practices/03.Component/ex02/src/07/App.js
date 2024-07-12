import React from "react";
import { title } from "./assets/scss/App.scss";
import GroceryList from "./GroceryList";

function App() {
  const groceries = [
    { no: 1, name: "Bread", count: 20 },
    { no: 2, name: "Milk", count: 10 },
    { no: 3, name: "Egg", count: 30 },
  ];
  return (
    <div id="App">
      <h1 className={title}>Grocery List</h1>
      <GroceryList groceries={groceries} />
    </div>
  );
}

export default App;
