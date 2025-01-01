CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;

insert into user (user_name, password) values ('super.admin', 'superadmin123');