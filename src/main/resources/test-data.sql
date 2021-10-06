INSERT INTO zone (id, title) VALUES (1, 'Utrecht');
INSERT INTO zone (id, title) VALUES (2, 'Groningen');
INSERT INTO zone (id, title) VALUES (3, 'Noord-Brabant');
INSERT INTO zone (id, title) VALUES (4, 'Limburg');

INSERT INTO supplierProduct (id, supplierId, productId, quantity, basePrice, pickUpAddressId, eta)
VALUES (1, 1, 1, 10, 900, 1, '2021-11-23 15:15:00');
INSERT INTO product (id, supplierProductId, name, price, sizeId, isArchived)
VALUES (1, 1, 'test product', 1000, 1, 0);
INSERT INTO productCategory (productId, categoryId) VALUES (1, 1);
INSERT INTO category (id, name) VALUES (1, 'category one');
