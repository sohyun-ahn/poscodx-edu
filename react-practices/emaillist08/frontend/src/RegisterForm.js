import React, { useState, useEffect } from "react";
import {
  Register_Form,
  InputFirstName,
  InputLastName,
  InputEmail,
} from "./assets/scss/RegisterForm.scss";

function RegisterForm({ emaillist, setNewEmaillist }) {
  const [no, setNo] = useState(Date.now());
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");

  useEffect(() => {
    setNo(Date.now());
  }); // rendering 할때마다 새로운 no 세팅

  const handleSubmit = (e) => {
    e.preventDefault(); // 새로고침 방지

    const newEmaillistItem = {
      no: no,
      firstName: firstName,
      lastName: lastName,
      email: email,
    };
    setNewEmaillist([...emaillist, newEmaillistItem]);
    clearInput();
  };

  const clearInput = () => {
    setFirstName("");
    setLastName("");
    setEmail("");
  };

  return (
    <form className={Register_Form} onSubmit={handleSubmit}>
      <input
        type="text"
        name="firstName"
        value={firstName}
        placeholder="성"
        className={InputFirstName}
        onChange={(e) => {
          setFirstName(e.target.value);
        }}
      />
      <input
        type="text"
        name="lastName"
        value={lastName}
        placeholder="이름"
        className={InputLastName}
        onChange={(e) => setLastName(e.target.value)}
      />
      <input
        type="text"
        name="email"
        value={email}
        placeholder="이메일"
        className={InputEmail}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input type="submit" value="등록" />
    </form>
  );
}

export default RegisterForm;
