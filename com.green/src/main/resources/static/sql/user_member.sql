-- DB(스키마이름) springBootDB

create database springBootDB;
use springBootDB;

-- 테이블 이름 : user_member


create table user_member(
    no int auto_increment primary key,
    id varchar(20) not null unique,
    pw varchar(100) not null,
    mail varchar(50) not null,
    phone varchar(50) not null,

    reg_date datetime default current_timestamp,        -- 최초 가입일
    mod_date datetime default current_timestamp 
             on update current_timestamp               -- 수정 시 자동 갱신
);

drop table board;
create table board(
	no int auto_increment primary key,
    id varchar(20) not null,
    title varchar(200) not null,
	content varchar(800) not null,
    writer varchar(20) not null,
    createdAt datetime default current_timestamp 
             on update current_timestamp
    );
    
    create table board_user(
    no int auto_increment primary key,
    id varchar(20) not null,
    pw varchar(100) not null,
    nickname varchar(100) not null,
    mail varchar(50) not null,
    phone varchar(50) not null,

    reg_date datetime default current_timestamp,        -- 최초 가입일
    mod_date datetime default current_timestamp 
             on update current_timestamp               -- 수정 시 자동 갱신
);
    
