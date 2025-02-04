DO $$
DECLARE
admin_role_name VARCHAR(255);
    admin_username VARCHAR(255);
    admin_password VARCHAR(255);
    role_id INTEGER;
    user_id INTEGER;
BEGIN
    admin_role_name := 'ROLE_ADMIN';
    admin_username := 'ts_admin';
    admin_password := 'ts_admin';
insert into roles (name)
values (admin_role_name);

insert into users (enabled, password, username)
values (true, admin_password, admin_username);

select users.id into user_id from users where username = admin_username;
select roles.id into role_id from roles where name = admin_role_name;
insert into user_roles(user_id, role_id) values (user_id, role_id);
END;
$$