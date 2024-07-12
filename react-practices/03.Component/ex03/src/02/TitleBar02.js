import React from "react";

function TitleBar02() {
  const handleClick = function () {
    console.log("TitledBar02: clicked!");
  };
  return (
    <div>
      <h4 onClick={handleClick}>
        Function Handler in Function Component(click here!)
      </h4>
    </div>
  );
}

export default TitelBar02;
