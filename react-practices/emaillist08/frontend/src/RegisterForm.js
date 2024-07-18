import React, { useState, useEffect } from "react";
import * as styles from "./assets/scss/RegisterForm.scss";

function RegisterForm({ addEmail }) {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault(); // 새로고침 방지

    addEmail({
      firstName: e.target.firsName.value,
      lastName: e.target.lastName.value,
      email: e.target.email.value,
    }); // 이 부분의 코드를 줄이기 위해 react form serialization, react form input converter 이런 걸로 해도 된다

    clearInput();
  };

  const clearInput = () => {
    setFirstName("");
    setLastName("");
    setEmail("");
  };

  return (
    <form className={styles.Register_Form} onSubmit={handleSubmit}>
      <input
        type="text"
        name="firstName"
        value={firstName}
        placeholder="성"
        className={styles.InputFirstName}
        onChange={(e) => {
          setFirstName(e.target.value);
        }}
      />
      <input
        type="text"
        name="lastName"
        value={lastName}
        placeholder="이름"
        className={styles.InputLastName}
        onChange={(e) => setLastName(e.target.value)}
      />
      <input
        type="text"
        name="email"
        value={email}
        placeholder="이메일"
        className={styles.InputEmail}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input type="submit" value="등록" />
    </form>
  );
}

export default RegisterForm;
