--
-- select 연습
--

-- 예제1: departments 테이블의 모든 데이터를 출력.
select * from departments;

-- 프로젝션(projection)
-- 예제2: employees 테이블에서 직원이 이름, 성별, 입사일을 출력 
-- 5개만 출력 : limit 0,5
select first_name, gender, hire_date from employees limit 0, 5;

-- as(alias, 생략가능)
-- 예제3: employees 테이블에서 직원의 이름, 성별, 입사일을 출력 
select first_name as 이름, gender as '성별', hire_date as '입사일'
from employees;

select concat(first_name, ' ', last_name) as '이름' from employees;

-- distinct
-- 예제4: titles 테이블에서 직급은 어떤 것들이 있는지 직급이름을 한번씩만 출력
select distinct (title) from titles;


--
-- where절
-- 예제1: employees 테이블에서 1991년 이전에 입사한 직원의 이름, 
--       성별, 입사일을 출력
SELECT concat( first_name, ' ', last_name ) AS 이름,
         gender AS 성별, 
         hire_date AS 입사일
    FROM employees
   WHERE hire_date < '1991-01-01';

     
-- 논리연산자
-- 예제2: employees 테이블에서 1989년 이전에 입사한 여직원의 이름, 
-- 입사일을 출력
SELECT concat( first_name, ' ', last_name ) AS 이름,
         hire_date AS 입사일
    FROM employees
   WHERE gender='f'
     AND hire_date < '1989-01-01';
     
-- in 연산자 
-- 예제3: dept_emp 테이블에서 부서 번호가 d005나 d009에 속한 사원의 사번, 부서번호 출력
SELECT emp_no, dept_no    
   FROM dept_emp
  WHERE dept_no in( 'd005', 'd009' );
  
-- like검색 
-- 예제4: employees 테이블에서 1989년에 입사한 직원의 이름, 입사일을 출력  
SELECT concat( first_name, ' ', last_name ) AS 이름,
         hire_date AS 입사일
    FROM employees
   WHERE hire_date LIKE '1989%';
   
SELECT first_name, hire_date
    FROM employees
   WHERE hire_date between '1989-01-01' and '1989-12-31';
   
-- salaries 테이블에서 2001년 급여가 70000불 이하의 직원만 사번, 급여로 출력하되 급여는 10자리로 부족한 자리수는 *로 표시
SELECT emp_no, LPAD( cast(salary as char), 10, '*')      
  FROM salaries     
 WHERE from_date like '2001-%'       
   AND salary < 70000;
   
-- 남자 직원의 이름, 성별, 입사일을 입사일순(선임순)으로 출력
select first_name, gender, hire_date from employees where gender='M' order by hire_date asc;

-- 직원의 사번, 월급을 사번(asc), 월급(desc) 로 출력
select * from salaries order by emp_no asc, salary desc;



