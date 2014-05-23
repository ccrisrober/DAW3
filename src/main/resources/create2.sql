-- ---------------------------------------------------------------------- --
-- Script generated with: DeZign for Databases V8.1.2                     --
-- Target DBMS:           MySQL 5                                         --
-- Project file:          23.dez                                          --
-- Project name:                                                          --
-- Author:                                                                --
-- Script type:           Database creation script                        --
-- Created on:            2014-05-23 00:15                                --
-- ---------------------------------------------------------------------- --

-- DB: Tienda_crodriguezbe
-- USER: cdl
-- PASSWORD: pikachu

-- ---------------------------------------------------------------------- --
-- Add tables                                                             --
-- ---------------------------------------------------------------------- --

-- ---------------------------------------------------------------------- --
-- Add table "User"                                                       --
-- ---------------------------------------------------------------------- --

CREATE TABLE User_ (
    id_usu INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
    name VARCHAR(20),
    surname VARCHAR(20),
    surname2 VARCHAR(20),
    direction VARCHAR(50),
    telephone VARCHAR(10),
    password VARCHAR(50),
    nickname VARCHAR(20),
    CONSTRAINT PK_User PRIMARY KEY (id_usu)
);

-- ---------------------------------------------------------------------- --
-- Add table "Card"                                                       --
-- ---------------------------------------------------------------------- --

CREATE TABLE Card (
    num_card VARCHAR(19) NOT NULL,
    date_catd DATE NOT NULL,
    id_card INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
    CONSTRAINT PK_Card PRIMARY KEY (id_card)
);

-- ---------------------------------------------------------------------- --
-- Add table "Category"                                                   --
-- ---------------------------------------------------------------------- --

CREATE TABLE Category (
    id_cat INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT PK_Category PRIMARY KEY (id_cat)
);

-- ---------------------------------------------------------------------- --
-- Add table "Product"                                                    --
-- ---------------------------------------------------------------------- --

CREATE TABLE Product (
    id_prod INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price FLOAT NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    id_cat INTEGER,
    CONSTRAINT PK_Product PRIMARY KEY (id_prod)
);

-- ---------------------------------------------------------------------- --
-- Add table "Order_"                                                     --
-- ---------------------------------------------------------------------- --

CREATE TABLE Order_ (
    id_ord INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
    price FLOAT NOT NULL DEFAULT 0,
    name_rec VARCHAR(20) NOT NULL,
    surname_rec VARCHAR(20) NOT NULL,
    direction VARCHAR(50) NOT NULL,
    telephone VARCHAR(10) NOT NULL,
    id_usu INTEGER DEFAULT 0,
    id_card INTEGER DEFAULT 0,
	status varchar(20) not null,
    CONSTRAINT PK_Order_ PRIMARY KEY (id_ord)
);

-- ---------------------------------------------------------------------- --
-- Add table "Item"                                                       --
-- ---------------------------------------------------------------------- --

CREATE TABLE Item (
    quantity INTEGER NOT NULL DEFAULT 0,
    id_ord INTEGER NOT NULL,
    id_prod INTEGER NOT NULL,
    PRIMARY KEY (id_ord, id_prod)
);

-- ---------------------------------------------------------------------- --
-- Add table "Image"                                                      --
-- ---------------------------------------------------------------------- --

CREATE TABLE Image (
    id_img INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
    image VARCHAR(100) NOT NULL,
    id_prod INTEGER,
    CONSTRAINT PK_Image PRIMARY KEY (id_img)
);

-- ---------------------------------------------------------------------- --
-- Add foreign key constraints                                            --
-- ---------------------------------------------------------------------- --

ALTER TABLE Image ADD CONSTRAINT Product_Image 
    FOREIGN KEY (id_prod) REFERENCES Product (id_prod);

ALTER TABLE Product ADD CONSTRAINT Category_Product 
    FOREIGN KEY (id_cat) REFERENCES Category (id_cat);

ALTER TABLE Order_ ADD CONSTRAINT User_Order_ 
    FOREIGN KEY (id_usu) REFERENCES User_ (id_usu);

--ALTER TABLE Order_ ADD CONSTRAINT Card_Order_ 
--    FOREIGN KEY (id_card) REFERENCES Card (id_card);

ALTER TABLE Item ADD CONSTRAINT Order__Item 
    FOREIGN KEY (id_ord) REFERENCES Order_ (id_ord);

ALTER TABLE Item ADD CONSTRAINT Product_Item 
    FOREIGN KEY (id_prod) REFERENCES Product (id_prod);

	
-- INSERCIONES
	
INSERT INTO CDL.USER_ ("NAME", SURNAME, SURNAME2, DIRECTION, TELEPHONE, PASSWORD, NICKNAME) 
	VALUES ('Cristian', 'Rodríguez', 'Bernal', 'Calle de la Piruleta, Nº22 1ºA', '656585932', 'aaf202922f558555d54ee4d5719dbea53aa08031', 'maldicion069');

INSERT INTO CDL.CATEGORY ("NAME") 
	VALUES ('Zapatería');
INSERT INTO CDL.CATEGORY ("NAME") 
	VALUES ('Informática');
INSERT INTO CDL.CATEGORY ("NAME") 
	VALUES ('Droguería');

	