create sequence users_seq start with 1 increment by 1;

create table users (
    id bigint primary key default nextval('users_seq'::regclass),
    username varchar(64) not null unique,
    full_name varchar(128) not null,
    password varchar(64) not null
);

insert into users(username, full_name, password) values
('and_lvov_sky', 'Viktor Ilvovskyi', '$2y$12$O/E7inTqFfH/ofdqcE72a.lYFmYLw8GAIURCxMOBQQ5oIMF02E7Py'); -- password is 'password'
