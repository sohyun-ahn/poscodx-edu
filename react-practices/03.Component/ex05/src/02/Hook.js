import React, { useState, useEffect } from "react";

export default function Hook({ color }) {
  const [backgroundColor, setBackgroundColor] = useState(null);
  const [text, setText] = useState("");

  /**
   * 1. Alternative 01: getDerivedStateFromProps
   */
  if (color !== backgroundColor) {
    setBackgroundColor(color);
  }

  /**
   * 2. After Rendering
   * class component(componentDidUpdate, componentDidMount)
   */

  useEffect(() => {
    console.log("After Rendering: update text or update background color");
  });

  /**
   * 3. After Rendering
   * 어떤 특정 상태의 변화에 반응하는 함수
   */
  useEffect(() => {
    console.log("After Rendering: update text");
  }, [text]);

  /**
   * 4. Alternative 02: componentDidMount & componentWillUnmount
   */

  useEffect(() => {
    console.log("After Mount(componentDidMount)");
    return () => {
      console.log("After Unmount(componentWillUnmount)");
    };
  }, []);

  return (
    <>
      <h3
        style={{
          width: 300,
          height: 50,
          backgroundColor: backgroundColor,
        }}
      />
      <input
        type="text"
        value={text}
        onChange={(e) => setText(e.target.value)}
      />
    </>
  );
}
