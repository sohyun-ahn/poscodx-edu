-- 집계(통계) SQL 문제입니다.

-- 문제 1. 
-- 최고임금(salary)과  최저임금을 “최고임금, “최저임금”프로젝션 타이틀로 함께 출력해 보세요.
-- 두 임금의 차이는 얼마인가요? 함께 “최고임금 – 최저임금”이란 타이틀로 출력해 보세요.
select max(salary) as '최고임금', min(salary) as '최저임금', (max(salary)-min(salary)) as '최고임금 - 최저임금' from salaries;

-- 문제2.
-- 마지막으로 신입사원이 들어온 날은 언제 입니까? 다음 형식으로 출력해주세요.
-- 예) 2014년 07월 10일
select TO_CHAR(max(hire_date), 'YYYY"년" MM"월" DD"일"') from employees;

-- 문제3.
-- 가장 오래 근속한 직원의 입사일은 언제인가요? 다음 형식으로 출력해주세요.
-- 예) 2014년 07월 10일
select TO_CHAR(min(hire_date), 'YYYY"년" MM"월" DD"일"') from employees;

-- 문제4.
-- 현재 이 회사의 평균 연봉은 얼마입니까?
select avg(salary) from salaries;

-- 문제5.
-- 현재 이 회사의 최고/최저 연봉은 얼마입니까?
select max(salary), min(salary) from salaries;

-- 문제6.
-- 제일 어린 사원의 나이와 최 연장자의 나이는?
select max(birth_date) as '최연소자', min(birth_date) as '최연장자' from employees;
