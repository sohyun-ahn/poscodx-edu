import React from "react";
import EmaillistItem from "./EmaillistItem";
import { Emaillist_Items } from "./assets/scss/EmaillistItems.scss";

function EmaillistItems({ emaillist }) {
  return (
    <ul className={Emaillist_Items}>
      {emaillist?.map((e) => (
        <EmaillistItem
          key={e.no}
          no={e.no}
          name={e.firstName + e.lastName}
          email={e.email}
        />
      ))}
    </ul>
  );
}

export default EmaillistItems;
