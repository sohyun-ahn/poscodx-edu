--
-- 수학함수 
--

-- abs
select abs(1), abs(-1) from dual;

-- ceil : 소수점 올림
select ceil(2.2), ceiling(3.14) from dual;

-- floor : 소수점 내림 
select floor(3.7), floor(-3.7) from dual;

-- mod(수, 나누는 수) : 나머지 구함 
select mod(10, 3), 10 % 3 from dual;

-- round(x) : x에 가장 근접한 정수
-- round(x, d) : x값 중에 소수점 d자리에 가장 근접한 실수
select round(1.498), round(1.5111) from dual; -- result : 1, 2 // d에 0을 준 것과 같은 의미
select round(1.498, 2), round(1.5111,2) from dual; -- result : 1.50, 1.51

-- power(x, y), pow(x, y) : x의 y승 
select power(2, 10), pow(2, 10) from dual;

-- sign(x) : 양수 1, 음수 -1, 0 0 // -1,0,1로 부호 출력
select sign(20), sign(-100), sign(0) from dual; 

-- greatest(x, y, ...): 제일 큰 값 출력,  least(x, y, ...) : 제일 작은 값 출력
select greatest(10, 40, 20, 50), least(10, 40, 20, 50) from dual;  -- result : 50, 10
select greatest('b', 'A', 'C', 'D'), least('hello', 'hela', 'hell') from dual;  -- result : D, hela






