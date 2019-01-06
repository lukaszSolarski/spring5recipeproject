/*
SPRING JDBC
Spring's DataSource initializer via Spring Boot will by default load schema.sql and data.sql from the root of the
classpath. It can cause conflict with Hibernate's DDL Auto property, so it should use setting 'none' or 'valid'.
 */
INSERT INTO category (name) VALUES ('American');
INSERT INTO category (name) VALUES ('Italian');
INSERT INTO category (name) VALUES ('Polish');
INSERT INTO category (name) VALUES ('Fast food');
INSERT INTO unit_of_measure (description) VALUES ('Teaspoon');
INSERT INTO unit_of_measure (description) VALUES ('Tablespoon');
INSERT INTO unit_of_measure (description) VALUES ('Cup');
INSERT INTO unit_of_measure (description) VALUES ('Pinch');
INSERT INTO unit_of_measure (description) VALUES ('Ounce');