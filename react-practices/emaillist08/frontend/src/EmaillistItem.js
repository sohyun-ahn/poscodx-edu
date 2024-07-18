import React from "react";
import { Emaillist_Item } from "./assets/scss/EmaillistItem.scss";

function EmaillistItem({ no, name, email }) {
  return (
    <li className={Emaillist_Item}>
      <h4>{name}</h4>
      <span>{email}</span>
      <a
        href=""
        onClick={(e) => {
          e.preventDefault();
          console.log("과제입니다: " + no);
        }}
      />
    </li>
  );
}

export default EmaillistItem;
