DO $$
DECLARE
    role_name VARCHAR(255);
    wakener_username VARCHAR(255);
    wakener_password VARCHAR(255);
    role_id INTEGER;
    user_id INTEGER;
BEGIN
    role_name := 'ROLE_USER';
    wakener_username := 'ts_wakener';
    wakener_password := '$2a$12$H18lYGCl9yVbn7kB7vf0t.v/r/uNxvdqeKYjNUDxFXp2MlEv.xBIa';
insert into roles (name)
values (role_name);

insert into users (enabled, password, username)
values (true, wakener_password, wakener_username);

select users.id into user_id from users where username = wakener_username;
select roles.id into role_id from roles where name = role_name;
insert into user_roles(user_id, role_id) values (user_id, role_id);
END;
$$