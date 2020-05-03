create sequence visit_seq start with 1 increment by 1;

create table visit (
    id bigint primary key default nextval('visit'),
    site_id bigint not null references site(id),
    time timestamp not null,
    country varchar(128) not null,
    device varchar(32) not null
)
