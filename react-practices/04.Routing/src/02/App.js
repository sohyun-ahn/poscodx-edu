import React, { useState, useEffect } from "react";
import Main from "./component/Main";
import Gallery from "./component/Gallery";
import Guestbook from "./component/Guestbook";
import Error404 from "./component/Error404";

export default function App() {
  //   const [route, setRoute] = useState({ page: "/" });
  const [route, setRoute] = useState({
    page: window.location.href.substring(window.location.href.lastIndexOf("/")),
  });

  const handlerPopState = (e) => {
    setRoute(e.state ? e.state : { page: "/" }); // null일 경우 첫 화면
  };

  const handleLinkClick = (e) => {
    e.preventDefault();
    const url = e.target.href.substring(e.target.href.lastIndexOf("/"));
    console.log(url);

    // 브라우저 라우터의 원리
    window.history.pushState({ page: url }, e.target.text, url); // back 앞뒤로 하고있는 history에다 저장하는 객체, back btn처럼 기억하게, (내가넣을객체, 문자, 이동할위치)
    setRoute({ page: url });
  };

  useEffect(() => {
    window.addEventListener("popstate", handlerPopState);

    return () => {
      window.removeEventListener("popstate", handlerPopState);
    };
  }, []);

  //   const Router = function () {
  //     let component = null;

  //     switch (route.page) {
  //       case "/":
  //         component = <Main />;
  //         break;
  //       case "/gallery":
  //         component = <Gallery />;
  //         break;
  //       case "/guestbook":
  //         component = <Guestbook />;
  //         break;
  //     }

  //     return component;
  //   };

  return (
    <div>
      <ul>
        <li>
          <a href={"/"} onClick={handleLinkClick}>
            [Main]
          </a>
        </li>
        <li>
          <a href={"/gallery"} onClick={handleLinkClick}>
            [Gallery]
          </a>
        </li>
        <li>
          <a href={"/guestbook"} onClick={handleLinkClick}>
            [Guestbook]
          </a>
        </li>
      </ul>
      {(() => {
        switch (route.page) {
          case "/":
            return <Main />;
          case "/guestbook":
            return <Guestbook />;
          case "/gallery":
            return <Gallery />;
          default:
            return <Error404 />;
        }
      })()}
    </div>
  );
}
