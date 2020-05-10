alter table api_key
    drop constraint api_key_site_id_fkey,
    add constraint api_key_site_id_fkey
        foreign key (site_id) references site(id) on delete cascade;
