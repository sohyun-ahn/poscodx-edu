import React, { useState, useEffect } from "react";
import "./assets/scss/Emaillist.scss";
import RegisterForm from "./RegisterForm";
import Searchbar from "./Searchbar";
import EmaillistItems from "./EmaillistItems";

function Emaillist() {
  const emaillist = [
    { no: 1, firstName: "둘", lastName: "리", email: "dooly@gmail.com" },
    { no: 2, firstName: "마", lastName: "이콜", email: "michol@gmail.com" },
    { no: 3, firstName: "도", lastName: "우너", email: "douner@gmail.com" },
    { no: 4, firstName: "또", lastName: "치", email: "ddochi@gmail.com" },
  ];

  const [newEmaillist, setNewEmaillist] = useState(emaillist);
  const [filteredEmaillist, setFilteredEmaillist] = useState(emaillist);
  const [isSearching, setIsSearching] = useState(false);

  return (
    <div id="Emaillist">
      <RegisterForm
        emaillist={newEmaillist}
        setNewEmaillist={setNewEmaillist}
      />
      <Searchbar
        emaillist={newEmaillist}
        setIsSearching={setIsSearching}
        setFilteredEmaillist={setFilteredEmaillist}
      />
      <EmaillistItems
        emaillist={isSearching ? filteredEmaillist : newEmaillist}
      />
    </div>
  );
}

export default Emaillist;
