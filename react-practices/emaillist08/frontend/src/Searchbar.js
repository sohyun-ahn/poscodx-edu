import React, { useState, useEffect } from "react";
import { Search_bar } from "./assets/scss/Searchbar.scss";

function Searchbar({ emaillist, setIsSearching, setFilteredEmaillist }) {
  const [searchItem, setSearchItem] = useState("");

  useEffect(() => {
    if (searchItem != "") {
      setFilteredEmaillist(
        emaillist.filter(
          (e) =>
            e.firstName.includes(searchItem) ||
            e.lastName.includes(searchItem) ||
            e.email.includes(searchItem)
        )
      );
      setIsSearching(true);
    } else {
      setIsSearching(false);
    }
  }, [searchItem]);

  const handleChange = (e) => {
    setSearchItem(e.target.value);
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
