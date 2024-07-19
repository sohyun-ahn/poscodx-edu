import React, { useRef } from "react";
import * as styles from "./assets/scss/RegisterForm.scss";

function RegisterForm({ addEmail }) {
  const refForm = useRef(null);

  const handleSubmit = (e) => {
    e.preventDefault(); // 새로고침 방지

    addEmail({
      firstName: e.target.firstName.value,
      lastName: e.target.lastName.value,
      email: e.target.email.value,
    }); // 이 부분의 코드를 줄이기 위해 react form serialization, react form input converter 이런 걸로 해도 된다

    refForm.current.reset(); // clear form fields
  };

  return (
    <form
      ref={refForm}
      className={styles.Register_Form}
      onSubmit={handleSubmit}
    >
      <input
        type="text"
        name="firstName"
        placeholder="성"
        className={styles.InputFirstName}
      />
      <input
        type="text"
        name="lastName"
        placeholder="이름"
        className={styles.InputLastName}
      />
      <input
        type="text"
        name="email"
        placeholder="이메일"
        className={styles.InputEmail}
      />
      <input type="submit" value="등록" />
    </form>
  );
}

export default RegisterForm;
