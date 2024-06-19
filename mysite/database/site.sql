-- insert
desc site;
insert into site values(null, 'MySite', '/assets/images/pochaco.png', '안녕하세요. 안소현의  <strong style="color:#009900">MySite</strong>에 오신 것을 환영합니다.','이 사이트는  웹 프로그래밍 실습과제 예제 사이트입니다.\n
						메뉴는 수업 배운 거 있는 거 없는 거 다 합쳐서
						만들어 놓은 사이트 입니다.\n
						<strong><u>멋지죠</u>?</strong> \n\n
						방문한 여러분, 지금 제 사이트가 어떻게든 굴러는 가거든요?? \n
						혹시 안굴러가면 방명록에 (착.하.게.) 말해주세요...☆⛤ \n\n'); -- default data 세팅
select * from site;
delete from site; 
update site set profile="/assets/upload-images/pochaco.png" where no=2;