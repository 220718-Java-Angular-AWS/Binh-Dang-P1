insert into users (first_name, last_name, user_name, email, password) values ('binh', 'dang', 'binhdang', 'binhdang@email.com', 'password1');
insert into users (first_name, last_name, user_name, email, password) values ('ben', 'dang', 'bendang', 'bendang@email.com', 'password1');
insert into users (first_name, last_name, user_name, email, password) values ('pat', 'wins', 'patwins', 'patwins@email.net', 'password1');


UPDATE users
SET admin = TRUE
WHERE user_id = 1;

select * from users;
select user_id, user_name, email from users;




insert into tasks (title, message, amount, user_id) values ('sleeping', 'making up sleep', 00.00, 1);
insert into tasks (title, message, amount, user_id) values ('eating', 'eat for the gains', 1000, 2);



select * from tasks;

update tasks 
SET reimbursed = 'approved'
WHERE task_id = 3;