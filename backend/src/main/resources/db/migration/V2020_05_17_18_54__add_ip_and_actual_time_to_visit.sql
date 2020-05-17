alter table visit
    add column ip varchar(32),
    add column actual_time timestamp;

alter table visit
    alter column ip
        set not null,
    alter column actual_time
        set not null;
