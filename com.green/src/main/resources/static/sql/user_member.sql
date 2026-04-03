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


    

    
