import React, { useState, useEffect } from "react";
import "./assets/scss/App.scss";
import Clock from "./Clock";

export default function App() {
  const [ticks, setTicks] = useState(0);

  const getCurrentTime = function () {
    const now = new Date();
    return {
      hours: now.getHours(),
      minutes: now.getMinutes(),
      seconds: now.getSeconds(),
      tick: this.state ? this.state.tick + 1 : 0,
    };
  };
  const [currentTime, setCurrentTime] = useState(getCurrentTime());

  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentTime(getCurrentTime());
      setTicks((x) => x + 1);
    }, 1000);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <>
      <Clock
        title={`ex05: Clock Component II: 0`}
        hours={state.hours}
        minutes={state.minutes}
        seconds={state.seconds}
      />
    </>
  );
}
