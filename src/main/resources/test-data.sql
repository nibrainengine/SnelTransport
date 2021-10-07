INSERT INTO supplierProduct (id, supplierId, productId, quantity, basePrice, pickUpAddressId, eta)
VALUES (1, 1, 1, 10, 900, 1, '2021-11-23 15:15:00');
INSERT INTO product (id, supplierProductId, name, price, sizeId, isArchived)
VALUES (1, 1, 'test product', 1000, 1, 0);
INSERT INTO productCategory (productId, categoryId) VALUES (1, 1);
INSERT INTO category (id, name) VALUES (1, 'category one');

INSERT INTO address (id, street, houseNumber, zipCode, city, country, latitude, longitude) VALUES (1, 'Teststraat1', '12B', '7411KB', 'Best', 'The Netherlands', 1.5, 12.33);
INSERT INTO address (id, street, houseNumber, zipCode, city, country, latitude, longitude) VALUES (2, 'Teststraat2', '2', '1234KB', 'Groningen', 'The Netherlands', 12, 12.33);
INSERT INTO address (id, street, houseNumber, zipCode, city, country, latitude, longitude) VALUES (3, 'T3ststraat', '12431', '1337xD', 'Zeist', 'The Netherlands', 14, 12);
INSERT INTO pickUpHub (id, addressId, isDisabled, url) VALUES (1, 1, false, 'http://testurl.com/api');
INSERT INTO pickUpHub (id, addressId, isDisabled, url) VALUES (2, 3, true, 'http://disabledurl.com/disabled');
INSERT INTO pickUpHub (id, addressId, isDisabled, url) VALUES (3, 2, false, 'http://localhost:8080');
INSERT INTO zone (id, title) VALUES (1, 'Utrecht');
INSERT INTO zone (id, title) VALUES (2, 'Groningen');
INSERT INTO zone (id, title) VALUES (3, 'Noord-Brabant');
INSERT INTO zone (id, title) VALUES (4, 'Limburg');