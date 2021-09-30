INSERT INTO address (id, street, houseNumber, zipCode, city, country, latitude, longitude) VALUES (1, 'Teststraat1', '12B', '7411KB', 'Best', 'The Netherlands', 1.5, 12.33);
INSERT INTO address (id, street, houseNumber, zipCode, city, country, latitude, longitude) VALUES (2, 'Teststraat2', '2', '1234KB', 'Groningen', 'The Netherlands', 12, 12.33);
INSERT INTO address (id, street, houseNumber, zipCode, city, country, latitude, longitude) VALUES (3, 'T3ststraat', '12431', '1337xD', 'Zeist', 'The Netherlands', 14, 12);
INSERT INTO pickUpHub (id, addressId, isDisabled, url) VALUES (1, 1, false, 'http://testurl.com/api');
INSERT INTO pickUpHub (id, addressId, isDisabled, url) VALUES (2, 3, true, 'http://disabledurl.com/disabled');
INSERT INTO pickUpHub (id, addressId, isDisabled, url) VALUES (3, 2, false, 'http://localhost:8080');