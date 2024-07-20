import React, { useState } from "react";
import { _Card, Card_Title, Card_Title_Open } from "./assets/scss/Card.scss";
import TaskList from "./TaskList";

function Card({ card }) {
  const [isOpen, setIsOpen] = useState(false);

  const handleChange = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className={_Card}>
      <div
        className={`${Card_Title} ${isOpen ? Card_Title_Open : ""}`}
        onClick={handleChange}
      >
        {card.title}
      </div>
      {card.description}
      {isOpen && (
        <div className="Card_Details">
          <TaskList cardNo={card.no} />
        </div>
      )}
    </div>
  );
}

export default Card;
