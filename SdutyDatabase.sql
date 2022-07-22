drop database if exists sduty;
select @@global.transaction_isolation, @@transaction_isolation;
set @@transaction_isolation="read-committed";

create database sduty;
use sduty;

create table User(
	user_seq int not null primary key auto_increment,
	user_id varchar(50) not null,   
    user_pass varchar(100) not null,
    user_name char(30) not null,
    user_tel char(50) not null,    
    user_email char(50) not null,
    user_app_token char(250),
    user_regtime timestamp not null default now(),
    user_public boolean not null default true
);

insert into user(user_id, user_pass, user_name, user_tel, user_email) values('d','d','test','01000000000','test@test.com');
select * from user;

create table t_study(
	id int primary key auto_increment,
    name varchar(100) not null,
    meet_id varchar(150) default null
);



create table t_study_detail(
	d_id int primary key auto_increment,
    s_id int,
    user_id varchar(50),
    constraint fk_study_detail foreign key (s_id) references t_study(id) on delete cascade
);

select * from t_user;

create table t_auth(
 id int primary key auto_increment,
 authcode varchar(6),
 phone varchar(11),
 expire_time datetime default now() not null
);

insert into t_auth(authcode, phone, expire_time) values('111222', '01000001111', DATE_ADD(now(), INTERVAL 3 MINUTE));
insert into t_auth(authcode, phone, expire_time) values('536927', '01049177914', date_add(now(), INTERVAL 3 MINUTE));
select * from t_auth;