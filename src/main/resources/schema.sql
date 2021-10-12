DROP table if exists PickupHub;
DROP table if exists address;

CREATE TABLE address (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    street NVARCHAR2(255),
    houseNumber NVARCHAR2(255),
    zipCode NVARCHAR2(255),
    city NVARCHAR2(255),
    country NVARCHAR2(255),
    latitude decimal,
    longitude decimal
);

CREATE TABLE PickupHub(
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    addressId int,
    isDisabled boolean,
    url NVARCHAR(255)
);
DROP TABLE IF EXISTS Zone;
DROP TABLE IF EXISTS zone;
DROP TABLE IF EXISTS supplierProduct;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS productCategory;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS courierAvailablePeriod;

create table zone (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    title NVARCHAR2(255)
);

create table supplierProduct (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    supplierId int,
    productId int,
    quantity int,
    basePrice decimal(10,2),
    pickUpAddressId int,
    eta datetime
);

create table product (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    supplierProductId NVARCHAR2(255),
    name NVARCHAR2(255),
    price decimal(10,2),
    sizeId int,
    isArchived boolean
);

create table productCategory (
    productId int,
    categoryId int
);

create table category (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    name NVARCHAR2(255)
);

create table courierAvailablePeriod (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    courierId int,
    start datetime,
    end datetime,
    price decimal(18,2),
    isApproved boolean
);
