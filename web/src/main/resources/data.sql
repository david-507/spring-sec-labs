

-- password: Admin1
insert into user (id, email, username, password, avatar)
values (1, 'admin@server.org','admin','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','https://api.adorable.io/avatars/285/admin@server.org.png'),
(2, 'jdoe@server.org','jdoe','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','https://api.adorable.io/avatars/285/jdoe@server.org.png');

insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');
insert into user_entity_roles (user_entity_id, roles) values (2,'USER');
