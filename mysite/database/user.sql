desc user;

-- join
-- 관리자는 회원가입이 없고 내가 넣어줘야함, 사용자이름에 '관리자','admin'은 못하게 해야함
insert into user values(null, '관리자', 'admin@mysite.com', password('1234'), 'male', current_date());

-- update
update user set role='ADMIN' where name='coco';

-- login
select no, name from user where email = 'ash@gmail.com' and password=password('ash');


-- test
select * from user;

