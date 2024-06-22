insert into user values('admin', '관리자', password('1234'), DATE_FORMAT(now(), '%Y-%m-%d'));
insert into user values('admin2', '관리자2', password('1234'), current_date());

insert into category values(null, "spring.spring..", "자바 스프링이냐 봄이냐 봄", current_date(), 'ddd');

insert into post values(null, "spring 어려움", "스프링 마스터해야지! 근데 언제 공부할거니..", now(), 3) ;
select last_insert_id() from dual;

select count(*) from user where id = 'ash';

select no, title, content, reg_date as regDate from post where category_no = 3;

select * from user;  
select * from blog;
select * from category where id='ddd' order by no ASC; 
select * from post;

update blog set logo="/assets/upload-images/buangmangom.png" where id="sohyun";

update blog set logo="/assets/upload-images/buangmangom.png" , title="DDD&#39;s Blog" where id="ddd";

select a.no, a.name, a.description, a.reg_date as regDate, (select count(*) from post where category_no = a.no)  as postCount
		  from category a
		 where a.id = 'ddd'
		 order by a.reg_date DESC;

desc user;
desc blog;
desc category;
desc post;

delete from user;



