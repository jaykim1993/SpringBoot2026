create table questBoard(
num int auto_increment primary key, 
writer varchar(20),
subject varchar(50), 
reg_date datetime default now(), 
ref int,
re_step int, 
content varchar(1000),
img varchar(50)
);

select * from questBoard;

truncate questBoard;
drop table questBoard;