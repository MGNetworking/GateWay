-- create to schema for webclient
------------------------------------------------------
DROP SCHEMA IF EXISTS customer CASCADE;
CREATE SCHEMA customer
    AUTHORIZATION postgres;

COMMENT ON SCHEMA customer
    IS 'schema to user management and web site articletexte';
------------------------------------------------------
-- for delete table
------------------------------------------------------
drop table IF EXISTS customer.role CASCADE;
drop table IF EXISTS customer.client CASCADE;
drop table IF EXISTS customer.client_role CASCADE;
drop table IF EXISTS customer.article CASCADE;

------------------------------------------------------
-- role
------------------------------------------------------
CREATE TABLE customer.role
(
    role varchar(30) not null primary key
);

------------------------------------------------------
-- client
------------------------------------------------------
CREATE TABLE customer.client
(
    id_client       serial primary key,
    nom          varchar(255) NOT NULL,
    prenom     varchar(255) NOT NULL,
    email         varchar(255) NOT NULL,
    password varchar(255) NOT NULL

);

------------------------------------------------------
-- client_role : associtive table
------------------------------------------------------
CREATE TABLE customer.client_role
(

    id_client   integer     NOT NULL references customer.client (id_client),
    name_role varchar(30) NOT NULL references customer.role (role),

    CONSTRAINT PK_client_role PRIMARY KEY (id_client, name_role)

);

------------------------------------------------------
-- - Table articletexte
------------------------------------------------------
CREATE table customer.article
(

    id_article   SERIAL PRIMARY KEY,
    id_client      INTEGER      NOT NULL,
    date         TIMESTAMP    NOT NULL,
    page         varchar(30)  NOT NULL,
    title        varchar(50)  NOT NULL,
    articletexte text         NOT NULL,
    pathimage    varchar(255) NOT NULL,
    commentimage varchar(50)  NOT NULL,

    CONSTRAINT FK_id_client_ARTICLE FOREIGN KEY (id_client) REFERENCES customer.client (id_client)
);
