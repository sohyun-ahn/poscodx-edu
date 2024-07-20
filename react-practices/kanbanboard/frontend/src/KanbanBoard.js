import React, { useState, useEffect } from "react";
import { Kanban_Board } from "./assets/scss/KanbanBoard.scss";
import CardList from "./CardList";

function KanbanBoard() {
  const statusTitle = ["To Do", "Doing", "Done"];
  const [cardList, setCardList] = useState(null);

  // Card [R]
  const fetchCards = async () => {
    try {
      const response = await fetch("/api", {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: null,
      });

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const json = await response.json();

      if (json.result !== "success") {
        throw new Error(json.message);
      }

      setCardList(json.data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchCards();
  }, []);

  return (
    <div className={Kanban_Board}>
      {statusTitle.map((status) => (
        <CardList
          key={status}
          title={status}
          cards={cardList?.filter(
            (card) => card.status === status.replace(" ", "")
          )}
        />
      ))}
    </div>
  );
}

export default KanbanBoard;
