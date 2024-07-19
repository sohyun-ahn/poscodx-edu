import React, { useState, useEffect } from "react";
import { Search_bar } from "./assets/scss/Searchbar.scss";

function Searchbar({ fetchEmails }) {
  const [searchItem, setSearchItem] = useState("");

  const handleChange = (e) => {
    setSearchItem(e.target.value);
    fetchEmails(e.target.value);
  };

  return (
    <div className={Search_bar}>
      <input
        type="text"
        placeholder="찾기"
        value={searchItem}
        onChange={handleChange}
      />
    </div>
  );
}

export default Searchbar;
