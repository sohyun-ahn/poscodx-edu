select version(), current_date, now() from dual;

-- 수학함수, 사칙연산도 된다.
select sin(pi()/4), 1 + 2 * 3 - 4 / 5 from dual;

-- 대소문자 구분이 없다.
seLecT VerSion(), current_Date, Now() From DUal;

-- table 생성: DDL
create table pet(
	name varchar(100),
    owner varchar(50),
    species varchar(20),
    gender char(1),
    birth date,
    death date
    );

-- schema 확인
describe pet;
desc pet;

-- table 삭제 
drop table pet;
show tables;

-- insert: DML(C)
insert into pet values('망곰이', '안소현', 'bear', 'f', '2022-01-01', null);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet set name='귀염망곰' where name='망곰이';

-- delete: DML(D)
delete from pet where name='귀염망곰';

-- load data: mysql(CLI)전용
load data local infile '/root/pet.txt' into table pet;

-- select 연습 
select name, species
	from pet
    where name = 'bowser'; 
    
select name, species, birth
	from pet
    where birth >= '1998-01-01';
    
select name, gender from pet where gender = 'f' and species = 'dog';

select name, species from pet where species = 'bird' or species = 'snake';

select name, birth
	from pet 
	order by birth ASC;
    
select name, birth
	from pet 
	order by birth DESC;
    
select name, death
	from pet 
	where death is null;    
    
select name
	from pet 
	where name like '%fy';  
    
select name
	from pet 
	where name like '%w%';  
    
select name
	from pet 
	where name like 'b____';

-- count(통계함수)는 null을 안셈 // count(death) = 1
select count(death)
    from pet;
    
-- indexing column으로 세기때문에 빠르게 셈. 
-- name, count(*) 이렇게 같이 두면 잘못된 쿼리. (name은 그냥 제일 위의 것이 나옴)
select count(*), max(birth)
    from pet;
