create sequence api_key_seq start with 1 increment by 1;

create table api_key (
    id bigint primary key default nextval('api_key_seq'),
    site_id bigint not null references site(id),
    key varchar(256) not null unique
)
