create table users (
	user_id bigint not null,
	password varchar(20) not null,
	name varchar(20) not null,
	email varchar(30) not null,
	level varchar(20),
	primary key (user_id)
);