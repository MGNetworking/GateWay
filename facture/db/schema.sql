-- create to schema for webclient
------------------------------------------------------
DROP SCHEMA IF EXISTS facturation CASCADE;
CREATE SCHEMA facturation
    AUTHORIZATION postgres;

COMMENT ON SCHEMA facturation
    IS 'schema de gestion des facturation';
------------------------------------------------------
-- for delete table
------------------------------------------------------
drop table IF EXISTS facturation.facture CASCADE;
drop table IF EXISTS facturation.ItemProduit CASCADE;

------------------------------------------------------
-- facture
------------------------------------------------------
CREATE TABLE facturation.facture
(
    id_facture serial primary key,
    date       date NOT NULL

);

------------------------------------------------------
-- ItemProduit
------------------------------------------------------
CREATE TABLE facturation.ItemProduit
(

    id_itemProduit serial primary key ,
    id_facture bigint NOT NULL,
    id_produit bigint NOT NULL,
    quantity double precision not null ,
    prix numeric(10,2) NOT NULL



);
