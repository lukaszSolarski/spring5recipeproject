/*
SPRING JDBC
Spring's DataSource initializer via Spring Boot will by default load schema.sql and data.sql from the root of the
classpath. It can cause conflict with Hibernate's DDL Auto property, so it should use setting 'none' or 'valid'.
 */
INSERT INTO category (name) VALUES ('American');
INSERT INTO category (name) VALUES ('Italian');
INSERT INTO category (name) VALUES ('Polish');
INSERT INTO category (name) VALUES ('Fast food');
INSERT INTO unit_of_measure (description) VALUES ('teaspoon');
INSERT INTO unit_of_measure (description) VALUES ('tablespoon');
INSERT INTO unit_of_measure (description) VALUES ('tup');
INSERT INTO unit_of_measure (description) VALUES ('pinch');
INSERT INTO unit_of_measure (description) VALUES ('item');
INSERT INTO unit_of_measure (description) VALUES ('dash');