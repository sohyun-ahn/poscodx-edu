
-- guestbook_log
update guestbook_log set count = count + 1 where date = current_date(); -- result 0이라면 insert해야함
insert into guestbook_log values(current_date(), 1);

-- 두번째 글일땐 insert안하고 update만
update guestbook_log set count = count - 1 where date = (select date(reg_date) from guestbook where no = 29); -- subquery 이용해서 하기 

select * from guestbook;
select * from guestbook_log;

delete from guestbook_log; 
