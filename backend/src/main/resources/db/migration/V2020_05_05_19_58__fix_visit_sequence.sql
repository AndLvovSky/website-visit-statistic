alter table visit
    alter column id
        set default nextval('visit_seq');
