import React from "react";
import GroceryItem from "./GroceryItem";

function GroceryList() {
  return (
    <ol className="grocery-list">
      <GroceryItem name={"Bread"} count={10} />
      <GroceryItem name={"Milk"} count={20} />
      <GroceryItem name={"Egg"} count={30} />
    </ol>
  );
}

export default GroceryList;
