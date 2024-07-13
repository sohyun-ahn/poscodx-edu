import React from "react";
import { Emaillist_Item } from "./assets/scss/EmaillistItem.scss";

function EmaillistItem({ name, email }) {
  return (
    <li className={Emaillist_Item}>
      {name}
      <br />
      {email}
    </li>
  );
}

export default EmaillistItem;
