-- create to schema for webclient
------------------------------------------------------
DROP SCHEMA IF EXISTS customer CASCADE;
CREATE SCHEMA customer
    AUTHORIZATION postgres;

COMMENT ON SCHEMA customer
    IS 'schema de gestion des cleints';
------------------------------------------------------
-- for delete table
------------------------------------------------------
drop table IF EXISTS customer.client CASCADE;

------------------------------------------------------
-- client
------------------------------------------------------
CREATE TABLE customer.client
(
    id_client serial primary key,
    nom       varchar(255) NOT NULL,
    prenom    varchar(255) NOT NULL,
    email     varchar(255) NOT NULL,
    password  varchar(255) NOT NULL

);
