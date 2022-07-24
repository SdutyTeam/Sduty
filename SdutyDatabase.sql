drop database if exists sduty;
select @@global.transaction_isolation, @@transaction_isolation;
set @@transaction_isolation="read-committed";

create database sduty;
use sduty;

create table User(
	seq int not null primary key auto_increment,
	id varchar(50) not null,   
    pass varchar(100) not null,
    name char(30) not null,
    tel char(50) not null,    
    email char(50) not null,
    fcm_token char(250),
    regtime timestamp not null default now(),
    user_public boolean not null default true
);
insert into user(id, pass, name, tel, email) values('d','d','test','01000000000','test@test.com');

create table Identification(
 id int primary key auto_increment,
 tel varchar(11) not null,
 code varchar(6) not null,
 expire timestamp default now() not null
);
insert into Identification(tel, code, expire) values('01012345678', '123456', DATE_ADD(now(), INTERVAL 3 MINUTE));

