create table users(
	user_id SERIAL,
	first_name varchar(200) not null,
	last_name varchar(200) not null,
	user_name varchar(200) not null,
	email varchar(200) not null,
	password varchar(200) not null,
	constraint users_pk primary key (user_id)
);

create table tasks(
	task_id SERIAL primary key,
	title varchar(200) not null,
	message varchar(200) not null,
	amount decimal not null,
	user_id int not null,
	reimbursed varchar(200) default 'pending',
	constraint tasks_users_fk foreign key (user_id) references users (user_id)
);

