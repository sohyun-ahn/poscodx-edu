import React, { useState } from "react";

export default function ({ begin, step }) {
  const [val, setVal] = useState(begin);

  return (
    <div>
      <button onClick={() => setVal(val + step)}>
        <strong>{"+"}</strong>
      </button>{" "}
      <span>{val}</span>
    </div>
  );
}
