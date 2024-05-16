-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
 from salaries
where to_date like '9999%'
  and salary >= ( select avg(salary) 
					from salaries 
				   where to_date like '9999%');
 
-- 문제2. (x)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요.
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select e.emp_no, concat(e.first_name, ' ', e.last_name) as name, d.dept_name, s.salary
  from employees e, dept_emp de, salaries s, departments d
  where e.emp_no = s.emp_no
    and e.emp_no = de.emp_no
    and d.dept_no = de.dept_no
    and (de.dept_no, s.salary) in (select de.dept_no, max(s.salary) 
									  from salaries s, dept_emp de
									 where s.emp_no = de.emp_no
									   and s.to_date like '9999%'
									   and de.to_date like '9999%'
									  group by de.dept_no)
  order by s.salary desc;


-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select e.emp_no, concat(e.first_name, ' ', e.last_name) as name, s.salary
  from dept_emp de, employees e, salaries s, (select de.dept_no, avg(s.salary) as avg_salary
												from dept_emp de, salaries s
											   where de.emp_no = s.emp_no
                                                 and de.to_date like '9999%'
												 and s.to_date like '9999%'
											   group by de.dept_no) ds
 where e.emp_no = s.emp_no
   and e.emp_no = de.emp_no
   and de.dept_no = ds.dept_no
   and de.to_date like '9999%'
   and s.to_date like '9999%'
   and ds.avg_salary < s.salary;


-- 문제4.  d, de, e => 사원, dm, e => 매니저 (사원이랑 매니저 같은 거 조건, de.dept_no = dm.dept_no table) / 2개가 join에 올라갈 수 있다
-- 현재, 사원들의 사번, 이름, 자신의 매니저 이름, 부서 이름으로 출력해 보세요.
select e.emp_no, concat(e.first_name, ' ', e.last_name), m.manager, d.dept_name
  from dept_emp de, 
       employees e, 
       departments d,
       (select dm.dept_no, concat(e.first_name, ' ', e.last_name) as manager
		  from dept_manager dm, employees e 
		 where dm.emp_no = e.emp_no
		   and dm.to_date like '9999%') m 
  where e.emp_no = de.emp_no
    and d.dept_no = de.dept_no
    and m.dept_no = de.dept_no
    and de.to_date like '9999%';


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select e.emp_no,  concat(e.first_name, ' ', e.last_name) as name, t.title, s.salary
  from dept_emp de, employees e, titles t, salaries s
where de.emp_no = e.emp_no
  and t.emp_no = e.emp_no
  and e.emp_no = s.emp_no
  and de.to_date like '9999%'
  and s.to_date like '9999%'
  and de.dept_no = (select de.dept_no
					  from dept_emp de, salaries s
					 where de.emp_no = s.emp_no
					   and de.to_date like '9999%'
					   and s.to_date like '9999%'
					 group by de.dept_no
					 order by avg(s.salary) desc
					  limit 1)
	order by s.salary asc;


-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
-- 출력예시 : 영업 20000 
select d.dept_name, round(avg(s.salary))
  from salaries s, dept_emp de, departments d
  where s.emp_no = de.emp_no
    and de.dept_no = d.dept_no
    and s.to_date like '9999%'
  group by de.dept_no
  order by round(avg(s.salary)) desc
  limit 1;
  
  
-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- 출력예시 : 개발자 20000 
select t.title, round(avg(s.salary))
  from salaries s, titles t
 where s.emp_no = t.emp_no
   and t.to_date like '9999%'
   and s.to_date like '9999%'
  group by t.title
  order by avg(s.salary) desc
  limit 1;


-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은? - join문제
-- 부서이름, 사원이름, 연봉, 매니저 이름, 매니저 연봉 순으로 출력합니다.
select d.dept_name, concat(e.first_name, ' ', e.last_name) as name, s.salary, m.manager, m.salary as manager_salary
  from departments d, employees e, salaries s, dept_emp de,
		(select dm.dept_no, concat(e.first_name, ' ', e.last_name) as manager, s.salary
		   from dept_manager dm, employees e, salaries s
		  where dm.emp_no = e.emp_no
		    and e.emp_no = s.emp_no
		    and dm.to_date like '9999%'
		    and s.to_date like '9999%') m
  where d.dept_no = de.dept_no
    and e.emp_no = de.emp_no
    and e.emp_no = s.emp_no
    and d.dept_no = m.dept_no
    and de.to_date like '9999%'
	and s.to_date like '9999%'
    and s.salary > m.salary;
  


