create sequence site_seq start with 1 increment by 1;

create table site (
    id bigint primary key default nextval('site_seq'),
    user_id bigint not null references users(id),
    name varchar(128) not null,
    created_on timestamp default now() not null,
    link varchar(2048) not null,
    unique (user_id, name)
)
