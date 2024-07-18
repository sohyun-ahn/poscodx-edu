import React, { useState, useEffect } from "react";
import "./assets/scss/Emaillist.scss";
import RegisterForm from "./RegisterForm";
import Searchbar from "./Searchbar";
import EmaillistItems from "./EmaillistItems";

function Emaillist() {
  const [emaillist, setEmaillist] = useState(null);

  const addEmail = async (email) => {
    try {
      const response = await fetch("/api", {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(email), // java obect -> json으로 보내야함
      }); // async ! // fetch(url, option)=> promise return

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`); // throw하면 코드가 중지됨.-> catch로 간다
      }

      const json = await response.json(); // vo 나옴

      if (json.result !== "success") {
        throw new Error(json.message);
      }

      // 완벽히 success 한 경우,
      setEmaillist([json.data, ...emaillist]);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchEmails = async (keyword) => {
    try {
      const response = await fetch(`/api?kw=${keyword ? keyword : ""}`, {
        method: "get",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: null, //JSON.stringify({ keyword }),
      }); // async ! // fetch(url, option)=> promise return

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`); // throw하면 코드가 중지됨.-> catch로 간다
      }

      const json = await response.json(); // 이것도 async 이기에 await 를 해야한다.

      if (json.result !== "success") {
        throw new Error(json.message);
      }

      // 완벽히 success 한 경우,
      setEmaillist(json.data);
      console.log(json.data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchEmails();
  }, []);

  return (
    <div id="Emaillist">
      <RegisterForm addEmail={addEmail} />
      <Searchbar fetchEmails={fetchEmails} />
      <EmaillistItems emaillist={emaillist} />
    </div>
  );
}

export default Emaillist;
