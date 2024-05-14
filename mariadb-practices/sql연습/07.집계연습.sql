-- 3)query 순서
--   1. from: 접근 테이블 확인
--   2. where: 조건에 맞는 row를 선택(임시 테이블)
--   3. 집계(결과 테이블)
--   4. projection
-- 예제) 사번이 10060인 사원이 받은 평균 월급
select avg(salary) from salaries where emp_no='10060';

-- 4) group by에 참여하고 있는 컬럼은 projection이 가능하다. : select 절에 올 수 있다.
-- 예제: 사원별 평균 월급은?
select emp_no, avg(salary) 
	from salaries
group by emp_no;

-- 5) having
-- 	  집계 결과(결과 테이블)에서 row를 선택해야 하는 경우 
--    이미 where절은 실행이 되었기 때문에 having절에 이 조건을 주어야 한다.
-- 예제) 평균 월급이 60000달러 이상인 남자 사원의 사번과 평균 월급을 출력하세요. 
-- salaries : emp-no, salary
-- employees : emp_no, gender
select emp_no, avg(salary) as avg_salary
	from salaries
	group by emp_no
    having avg(salary) >= 60000 ;

-- 6) order by
--    order by는 항상 맨 마지막 출력(projection)전에 한다.
select emp_no, avg(salary) as avg_salary
	from salaries
	group by emp_no
    having avg(salary) >= 60000 
    order by avg_salary asc;
    
    
-- 주의) 사번이 10060인 사원의 사번, 평균 월급, 급여의 총합을 출력하세요.

-- 문법적으로 오류 
-- 의미적으로 맞다(where) -- 문제는 안되지만, 의미가 없는 집계
select emp_no, avg(salary), sum(salary) 
	from salaries
    where emp_no = 10060;
    
-- 문법적으로 옳다. (좀 느림)
select emp_no, avg(salary), sum(salary) 
	from salaries
	group by emp_no
    having emp_no = '10060';
    



-- 실행 순서
-- from ->
-- where ->
-- group by -> 
-- select ->
-- (where절은 이미 지났기 때문에, 집계 결과에서 조건을 달아주기위해) having ->
--  order by (projection을 위해서 하는 것, 집계함수가 다시 실행되는 것이 아님!)