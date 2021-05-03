begin;
set constraints all deferred;
commit;

begin;

truncate table visit cascade;
alter sequence visit_seq RESTART WITH 1;

truncate table users cascade;
alter sequence users_seq RESTART WITH 1;

commit;

begin;
set constraints all immediate;
commit;
