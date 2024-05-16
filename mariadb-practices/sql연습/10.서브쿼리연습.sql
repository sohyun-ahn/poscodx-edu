--
-- subquery
--

--
-- 1) select절, insert into t1 values(...)
--
insert into board values(null, (select max(group_no) + 1 from board));

select (select 1+2 from dual) from dual;

select * from (select 1+2 from dual) a; -- alias를 해줘야 실행 가능


--
-- 2) from 절의 서브쿼리
--
select now() as n, sysdate() as s, 3 + 1 as r from dual;
select n, s
  from (select now() as n, sysdate() as s, 3 + 1 as r from dual) a;


--
-- 3) where 절의 서브쿼리
-- 서브쿼리가 where절에 있을때는 조건이 확실이 맞아야함. 싱글값끼리 비교하는지, multi-row인지, 멀티컬럼인지
-- 이 기준은 서브쿼리 앞의 연산자와 관련이 있음! ( =, in, ... )

-- 예제) 현재, Fai Bale이 근무하는 부서에서 근무하는 다른 직원의 사번과 이름을 출력하세요.

-- 현재, Fai Bale이 근무하는 부서 => result : 'd004'
select de.dept_no
  from employees e, dept_emp de 
 where e.emp_no = de.emp_no 
   and de.to_date like '9999%'
   and concat(e.first_name, ' ', e.last_name) = 'Fai Bale';

-- 'd004' 부서에 근무하는 다른 직원들
select * 
  from dept_emp de 
 where de.dept_no = 'd004'
   and de.to_date like '9999%';
   
-- 위의 두가지 쿼리를 합쳐서 서브쿼리로 한번에 결과
select e.emp_no, concat(e.first_name, ' ', e.last_name) as name
 from employees e, dept_emp de 
where e.emp_no = de.emp_no  
  and de.to_date like '9999%'
  and de.dept_no = (select de.dept_no
					  from employees e, dept_emp de 
					 where e.emp_no = de.emp_no 
					   and de.to_date like '9999%'
					   and concat(e.first_name, ' ', e.last_name) = 'Fai Bale');

-- 3-1) 단일행 연산자: =, >, <, >=, <=, <>, !=
-- 실습문제1
-- 현재, 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력하세요.
select avg(salary)
  from salaries
  where to_date like '9999%';
  
select concat(e.first_name, ' ', e.last_name) as name, s.salary
  from employees e, salaries s
  where e.emp_no = s.emp_no
   and s.to_date like '9999%'
   and s.salary < (select avg(salary)
					  from salaries
					  where to_date like '9999%')
 order by s.salary desc;

-- 실습문제2
-- 현재, 직책별 평균 급여 중에 가장 작은 직책의 직책이름과 그 평균 급여를 출력해보세요. 

-- sol1: having절에 넣는 서브쿼리
select t.title, avg(s.salary)
  from titles t, salaries s
 where t.emp_no = s.emp_no
   and t.to_date like '9999%'
   and s.to_date like '9999%'
  group by t.title
  having avg(s.salary) = (select min(avg_salary) -- 집계 함수는 조회시 다른 컬럼은 덧붙이지 말기!
						  from (select t.title, avg(s.salary) as avg_salary
								  from titles t, salaries s
								 where t.emp_no = s.emp_no
								   and t.to_date like '9999%'
								   and s.to_date like '9999%'
								  group by t.title) a);

-- sol2: top-k (order by를 해야 가능)
select t.title, avg(s.salary) as avg_salary
  from titles t, salaries s
 where t.emp_no = s.emp_no
   and t.to_date like '9999%'
   and s.to_date like '9999%'
  group by t.title
  order by avg(salary) asc
  limit 0, 1 ; -- top-k, 이 부분은 limit index가 0부터 시작함! (0, 1) 로 적던지 (1)로 적으면 됨


-- 3-2) 복수행 연산자: in, not in, 비교연산자가 붙으면서 any가 오는 경우 (ex. =any), 비교연산자가 붙으면서 all (ex. <all)

-- any 사용법
-- 1. =any: in
-- 2. >any, >=any: 최소값
-- 3. <any, <=any: 최대값
-- 4. <>any, !=any: not in

-- all 사용법
-- 1. =all 은 (x)
-- 2. >all, >=all: 최대값
-- 3. <all, <=all: 최소값
-- 4. <>all, !=all

-- 실습문제3
-- 현재 급여가 50000이상인 직원의 이름과 급여를 출력하세요.

-- sol1 : join
select concat(e.first_name, ' ', e.last_name) as name, s.salary
 from employees e, salaries s
 where e.emp_no = s.emp_no
  and s.to_date like '9999%' 
  and s.salary > 50000
 order by s.salary asc;
  
-- sol2: subquery - where(in) (multirows로 된 colums을 보여주고 싶어서)
select concat(e.first_name, ' ', e.last_name) as name, s.salary
 from employees e, salaries s
 where e.emp_no = s.emp_no
  and s.to_date like '9999%' 
  and (e.emp_no, s.salary) in (select emp_no, salary  -- multicolumns, multirows
								from salaries 
								where to_date like '9999%'
								  and salary > 50000)
 order by s.salary asc;
  
-- sol3: subquery - where(=any)
select concat(e.first_name, ' ', e.last_name) as name, s.salary
 from employees e, salaries s
 where e.emp_no = s.emp_no
  and s.to_date like '9999%' 
  and (e.emp_no, s.salary) =any (select emp_no, salary  -- multicolumns, multirows
								from salaries 
								where to_date like '9999%'
								  and salary > 50000)
 order by s.salary asc;

-- 실습문제 4:
-- 현재, 각 부서별로 최고 급여를 받고 있는 직원의 부서, 이름과 월급을 출력하세요.

-- sol1 : where절 subquery(in)
select d.dept_name, concat(e.first_name, ' ', e.last_name) as name, s.salary
from departments d, dept_emp de, employees e, salaries s
where d.dept_no = de.dept_no
  and de.emp_no = e.emp_no
  and e.emp_no = s.emp_no
  and de.to_date like '9999%'
  and s.to_date like '9999%'
  and (de.dept_no, s.salary) in (select de.dept_no, max(s.salary)
								   from dept_emp de, salaries s
								  where de.emp_no = s.emp_no
									and de.to_date like '9999%'
								    and s.to_date like '9999%'
								  group by de.dept_no) ;
	
-- sol2: from절 subquery & join
select d.dept_name, concat(e.first_name, ' ', e.last_name) as name, s.salary
  from departments d,
	   dept_emp de, 
	   employees e,
	   salaries s, 
	   (select de.dept_no, max(s.salary) as max_salary
	     from dept_emp de, salaries s
	    where de.emp_no = s.emp_no
		  and de.to_date like '9999%'
		  and s.to_date like '9999%'
	   group by de.dept_no) as sub_query
 where d.dept_no = de.dept_no
   and de.emp_no = e.emp_no
   and e.emp_no = s.emp_no
   and de.dept_no = sub_query.dept_no
   and de.to_date like '9999%'
   and s.to_date like '9999%'
   and s.salary = sub_query.max_salary ;