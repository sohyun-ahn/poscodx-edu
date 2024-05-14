--
-- 문자열 함수
--

-- upper, ucase : UPPERCASE 만들어줌
select upper('seoul'), ucase('SeouL') from dual;
select upper(first_name) from employees;

-- lower, lcase : LOWERCASE 만들어줌
select lower('SEOUL'), lcase('SeouL') from dual;
select lower(first_name) from employees;

-- substring(문자열, index, length) index는 1부터 시작한다!!
select substring('hello World', 3, 2); 

-- 예제: 1989년에 입사한 직원들의 이름, 입사일을 출력 
select first_name, hire_date from employees where substring(hire_date, 1, 4) = '1989';

-- lpad(값, 총자리수, 채울문자나숫자): 부족한 값은 채울값으로 왼쪽부터 채워짐, 왼쪽 정렬, rpad(오른쪽 정렬) 
select lpad('1234', 10, '-'), rpad('1234', 10, '-') from dual; -- result : ------1234, 1234------

-- 예제) 직원들의 월급을 오른쪽 정렬(빈공간은 *)
select lpad(salary, 10, '*') from salaries;

-- trim(): 문자열에서 특정문자들을 왼쪽부터는 trim(leading '벗겨낼 문자' from '문자열'),
-- 			오른쪽방향(trailing '삭제할 문자' from '문자열')으로 제거시킴
-- 			양쪽방향 (both ~~)
-- ltrim, rtrim
select concat("---", ltrim('     hello     '), "---"),
concat("---", rtrim('     hello     '), "---"),
concat("---", trim(leading 'x' from 'xxxhelloxxxx'), "---"), 
concat("---", trim(trailing 'x' from 'xxxhelloxxxx'), "---"),
concat("---", trim(both 'x' from 'xxxhelloxxxx'), "---") from dual; 

-- length
select length('hello') from dual; -- result : 5

