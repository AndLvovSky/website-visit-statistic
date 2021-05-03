alter table users
add column email text;

update users
set email = 'viktorilvovskyi@gmail.com'
where username = 'and_lvov_sky';

alter table users
alter column email set not null;

alter table users
add constraint users_email_key unique (email);
