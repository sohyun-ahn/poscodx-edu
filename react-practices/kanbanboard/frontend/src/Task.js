import React, { useState } from "react";
import { _Task, Task_Remove } from "./assets/scss/Task.scss";

function Task({ task, updateTask, deleteTask }) {
  const [changedTask, setChangedTask] = useState(task);

  const handleChange = (e) => {
    updateTask(changedTask.no, changedTask.cardNo, e.target.checked);
    setChangedTask({ ...changedTask, done: e.target.checked ? "Y" : "N" });
  };

  const handleDelete = () => {
    deleteTask(changedTask.no, changedTask.cardNo);
    setChangedTask(null);
  };

  if (changedTask) {
    return (
      <li className={_Task}>
        <input
          type="checkbox"
          checked={changedTask.done === "Y"}
          onChange={handleChange}
        />{" "}
        {changedTask.name}{" "}
        <a href="#" className={Task_Remove} onClick={handleDelete}></a>
      </li>
    );
  }
}

export default Task;
