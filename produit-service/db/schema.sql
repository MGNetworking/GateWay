-- Schema for web service produit
------------------------------------------------------
DROP SCHEMA IF EXISTS product CASCADE;
CREATE SCHEMA product
    AUTHORIZATION postgres;

COMMENT ON SCHEMA product
    IS 'schema de gestion des produits';
------------------------------------------------------
-- for delete table
------------------------------------------------------
drop table IF EXISTS product.produit CASCADE;

------------------------------------------------------
-- produit
------------------------------------------------------
CREATE TABLE product.produit
(
    id_produit serial,
    nom        varchar(255)   NOT NULL,
    prix       NUMERIC(10, 2)  NOT NULL,
    quantite   int NOT NULL,

    constraint PK_id_produit PRIMARY KEY (id_produit)

);
