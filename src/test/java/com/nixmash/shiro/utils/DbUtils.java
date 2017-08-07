package com.nixmash.shiro.utils;

public class DbUtils {

    public final static String createSchemaSql =
            "DROP TABLE IF EXISTS roles;\n" +
                    "CREATE TABLE roles (\n" +
                    "  role_id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  permission VARCHAR(80) DEFAULT NULL,\n" +
                    "  role_name VARCHAR(45) DEFAULT NULL\n" +
                    ");\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS users;\n" +
                    "CREATE TABLE users (\n" +
                    "  user_id BIGINT  NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  username VARCHAR(45) DEFAULT NULL,\n" +
                    "  email VARCHAR(128) DEFAULT NULL,\n" +
                    "  password VARCHAR(255) DEFAULT NULL\n" +
                    ") ;\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS user_roles;\n" +
                    "CREATE TABLE user_roles (\n" +
                    "  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  role_name VARCHAR(45) DEFAULT NULL,\n" +
                    "  username VARCHAR(45) DEFAULT NULL\n" +
                    ");";

    public final static String dataSql =
            "INSERT INTO users (username, email, password)\n" +
                    "VALUES ('bob', 'bob@aol.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');\n" +
                    "INSERT INTO users (username, email, password)\n" +
                    "VALUES ('ken', 'ken@aol.com', 'a4e63bcacf6c172ad84f9f4523c8f1acaf33676fa76d3258c67b7e7bbf16d777');\n" +
                    "\n" +
                    "INSERT INTO roles (permission, role_name) VALUES ('nixmash:all', 'admin');\n" +
                    "INSERT INTO roles (permission, role_name) VALUES ('nixmash:view', 'user');\n" +
                    "\n" +
                    "INSERT INTO user_roles (role_name, username) VALUES ('admin', 'bob');\n" +
                    "INSERT INTO user_roles (role_name, username) VALUES ('user', 'bob');\n" +
                    "INSERT INTO user_roles (role_name, username) VALUES ('user', 'ken');";

}
