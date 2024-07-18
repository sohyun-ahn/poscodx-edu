import React, { useState, useEffect } from "react";
import Main from "./component/Main";
import Gallery from "./component/Gallery";
import Guestbook from "./component/Guestbook";
import Error404 from "./component/Error404";

export default function App() {
  const [route, setRoute] = useState("");

  const handlerHashChange = () => {
    console.log(window.location.hash); // localhost:8080/#/guestbook -> console에 #/guestbook 이 출력
    setRoute(window.location.hash.slice(1)); // #/guestbook -> /guestbook(1)); // #/guestbook -> /guestbook
  };

  useEffect(() => {
    window.addEventListener("hashchange", handlerHashChange);

    return () => {
      window.removeEventListener("hashchange", handlerHashChange);
    };
  }, []);

  return (() => {
    switch (route) {
      case "":
      case "/":
        return <Main />;
      case "/guestbook":
        return <Guestbook />;
      case "/gallery":
        return <Gallery />;
      default:
        return <Error404 />;
    }
  })();
}
