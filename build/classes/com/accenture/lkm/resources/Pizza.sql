
-- Table Script goes here.

CREATE DATABASE mvcpracpizza3;
USE mvcpracpizza3;

CREATE TABLE Pizza(
pizzaId INT NOT NULL,
pizzaName VARCHAR(20),
price DOUBLE,
PRIMARY KEY(pizzaId)
);

CREATE TABLE Pizza_Order(
orderId INT NOT NULL AUTO_INCREMENT,
customerName VARCHAR(30),
contactNumber VARCHAR(10),
pizzaId INT,
numberOfPiecesOrdered INT,
bill DOUBLE,
PRIMARY KEY(orderId)
);


INSERT INTO Pizza(pizzaId,pizzaName,price) VALUES
(1001,'XYZVegS',200),
(1002,'XYZVegM',40s0),
(1003,'XYZVegL',600),
(1004,'XYZNonVegS',400);

INSERT INTO Pizza_Order(orderId,customerName,contactNumber,pizzaId,numberOfPiecesOrdered,bill) VALUES
(5001,'Peter','1234567890',1001,1,200),
(5002,'Thomas','6574893012',1001,2,400),
(5003,'Decken','1243568790',1003,1,600),
(5004,'Jenifer','1029384756',1004,2,800);

commit;


