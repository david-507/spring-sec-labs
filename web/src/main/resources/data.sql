
-- pwd: Admin1
insert into user (id, email, username, password, avatar)
values (1, 'admin@server.org','admin','$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2','https://api.adorable.io/avatars/285/admin@server.org.png');

insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');