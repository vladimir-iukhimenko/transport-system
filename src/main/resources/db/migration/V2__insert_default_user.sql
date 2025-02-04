insert into roles (id, name)
values (1, 'ROLE_ADMIN');

insert into users (id, enabled, password, username)
values (1, true, 'ts_admin', 'ts_admin');

insert into user_roles (user_id, role_id)
values (1, 1);