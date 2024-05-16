-- webdb 로 연결 
-- DDL/DML 연습

drop table member;

create table member(
	no int not null auto_increment,
    email varchar(200) not null default '',
    password varchar(64) not null,
    name varchar(50) not null,
    department varchar(100),
    primary key(no)
);

desc member;

alter table member add column juminbunho char(13) not null;
desc member;

alter table member drop juminbunho; -- column을 날리는 것도 테이블을 변경하는 것이기 때문이다
desc member;

alter table member add column juminbunho char(13) not null after email; -- email 뒤에 넣으려고
desc member;

alter table member change column department dept varchar(100) not null; -- department를 dept라는 이름으로 변경하고 원하는 조건 작성
desc member;

alter table member add column self_intro text;
desc member;

alter table member drop juminbunho;
desc member;

--
-- insert
--

-- column 지정 안하는 경우, value를 순서대로 빠짐없이 잘 넣어줘야함!
insert 
  into member
values (null, 'ash@bit.com', password('1234'), '안소현', '개발팀', null); -- password(): mariaDB가 암호화해주는 함수
select * from member;

-- column을 지정하는 경우,
insert 
  into member(no, email, name, dept, password)
values (null, 'ash2@bit.com', '안소현', '개발팀', password('1234')); -- password(): mariaDB가 암호화해주는 함수
select * from member;

--
-- update
--
update member
   set email='ash@bit.com', password=password('4321')
   where no=2;
select * from member;

--
-- delete
--
delete 
  from member
  where no=2;
select * from member;

-- 
-- transaction 
--
-- atomicity 원자성 가짐
-- 업무(데이터 변경)단위
-- try문 안에 원하는 쿼리를 넣고 catch 안에 rollback(), return들을 넣어두게 설계한다. 
-- 그래서 모든 쿼리를 성공해야만 commit 시킨다. 
-- autocommit = 0 (=false)로 만들어서 성공시에만 commit을 하게 바꾸기
select no, email from member;

select @@autocommit; -- result : 1
insert 
  into member(no, email, name, dept, password)
values (null, 'ash2@bit.com', '안소현2', '개발2팀', password('1234')); 
select no, email from member;

-- transaction(tx) begin 
set autocommit = 0;
select @@autocommit; -- result : 0
insert 
  into member(no, email, name, dept, password)
values (null, 'ash3@bit.com', '안소현3', '개발3팀', password('1234')); 
select no, email from member; -- 여기에선 ash3까지 보이지만 다른 데서 접근해서 실제 db를 보면 ash3은 아직 커밋되어있지않음 

-- tx end
commit;
select no, email from member; -- commit을 했기때문에 db에서도 ash3이 들어있게 됨.