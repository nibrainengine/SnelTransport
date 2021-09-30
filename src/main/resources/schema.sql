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