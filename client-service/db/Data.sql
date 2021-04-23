insert into customer.role(role)
values ('ADMIN'),
       ('client_ARTICLE');

insert into customer.client(nom, prenom, email, password)
values ('maxime', 'ghalem', 'maxime@gmail.com' , '123456'),
       ('sylvain', 'sylvain1','sylvain@gmail.com', '123456' ),
       ('fabien', 'fabien1', 'fabien@gmail.com','123456'),
       ('ernestas', 'ernestas', 'ernestas@gmail.com' , '123456');


insert into customer.client_role(id_client, name_role)
values ('1', 'ADMIN'),
       ('1', 'client_ARTICLE'),
       ('2', 'client_ARTICLE'),
       ('3', 'client_ARTICLE'),
       ('4', 'client_ARTICLE');
