import React, { useState } from "react";
import "./assets/Form.css";

export default function Form() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [validEmail, setValidEmail] = useState(false);

  return (
    <form id="joinForm" name="joinForm" method="post" action="/do/not/post">
      <label htmlFor="name">이름</label>
      <input
        id="name"
        name="name"
        value={name}
        type="text"
        autoComplete="off"
        onChange={(e) => {
          setName(e.target.value.substring(0, 10)); // 제어 컴포넌트 // 10자로 제한둠.
          console.log(e.target.value);
        }}
      />

      <label htmlFor="email">이메일</label>
      <input
        id="email"
        name="email"
        value={email}
        type="text"
        autoComplete="off"
        onChange={(e) => {
          setEmail(e.target.value);

          // email format -> regex 이용하기
          const re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
          setValidEmail(re.test(e.target.value)); // email validation check
        }}
      />
      {email === "" ? null : <b>{validEmail ? "O" : "X"}</b>}

      <label htmlFor="password">패스워드</label>
      <input id="password" name="password" type="password" autoComplete="off" />

      <fieldset>
        <legend>성별</legend>
        <label>여</label> <input type="radio" name="gender" />
        <label>남</label> <input type="radio" name="gender" />
      </fieldset>

      <label htmlFor="birthYear">생년</label>
      <select id="birthYear" name="birthYear">
        <option value="">생년을 선택하세요</option>
        <option value="1997">1997년</option>
        <option value="1998">1998년</option>
        <option value="1999">1999년</option>
        <option value="2000">2000년</option>
        <option value="2001">2001년</option>
      </select>

      <label htmlFor="description">자기소개</label>
      <textarea id="description" name="description" />

      <fieldset>
        <legend>약관동의</legend>
        <input id="agree-prov" type="checkbox" name="agreeProv" />
        <label>서비스 약관에 동의합니다.</label>
      </fieldset>

      <input type="submit" value="가입" />
    </form>
  );
}
