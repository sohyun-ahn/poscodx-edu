import React, { useState, useEffect } from "react";
import { Task_List, Input_Add_Task } from "./assets/scss/TaskList.scss";
import Task from "./Task";

function TaskList({ cardNo }) {
  const [taskList, setTaskList] = useState(null);

  // Task [R]
  const fetchTasks = async (cardNo) => {
    try {
      const response = await fetch(`/api/card?no=${cardNo}`, {
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

      setTaskList(json.data);
    } catch (err) {
      console.error(err);
    }
  };

  // Task [C]
  const addTask = async (name) => {
    try {
      const response = await fetch("/api/card", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify({
          cardNo: cardNo,
          name: name,
          done: "N",
        }),
      });

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const json = await response.json();

      if (json.result !== "success") {
        throw new Error(json.message);
      }

      setTaskList([...taskList, json.data]);
    } catch (err) {
      console.error(err);
    }
  };

  // Task [U]
  const updateTask = async (no, cardNo, checked) => {
    try {
      const response = await fetch("/api/card", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify({
          no: no,
          cardNo: cardNo,
          done: checked ? "Y" : "N",
        }),
      });

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const json = await response.json();

      if (json.result !== "success") {
        throw new Error(json.message);
      }
    } catch (err) {
      console.error(err);
    }
  };

  // Task [D]
  const deleteTask = async (no, cardNo) => {
    try {
      const response = await fetch(`/api/card/${cardNo}/task?no=${no}`, {
        method: "DELETE",
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
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchTasks(cardNo);
  }, []);

  const handleEnterKeyDown = (e) => {
    if (e.key === "Enter") {
      addTask(e.target.value);
      e.target.value = "";
    }
  };

  return (
    <div className={Task_List}>
      <ul>
        {taskList?.map((task) => (
          <Task
            key={task.no}
            task={task}
            updateTask={updateTask}
            deleteTask={deleteTask}
          />
        ))}
      </ul>
      <input
        className={Input_Add_Task}
        type="text"
        placeholder="태스크 추가"
        onKeyDown={handleEnterKeyDown}
      />
    </div>
  );
}

export default TaskList;
