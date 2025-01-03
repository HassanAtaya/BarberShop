-- Create the language table
CREATE TABLE language (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Insert languages into the language table
INSERT INTO language (name) VALUES ('en'), ('fr'), ('ar');

-- Create the role table
CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Insert roles into the role table
INSERT INTO role (name) VALUES ('SuperAdmin'), ('Admin'), ('Manager'), ('Client');

-- Alter the user table to add a role_id column and a foreign key constraint
ALTER TABLE user ADD COLUMN role_id INT;
ALTER TABLE user ADD CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role(id);
ALTER TABLE user ADD COLUMN language_id INT DEFAULT 1;
ALTER TABLE user ADD CONSTRAINT fk_language FOREIGN KEY (language_id) REFERENCES language(id);
ALTER TABLE user ADD COLUMN last_update DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE user ADD COLUMN last_updator BIGINT DEFAULT NULL;

-- Create the service table
CREATE TABLE service (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_minutes INT NOT NULL,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Create the permission table
CREATE TABLE permission (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Insert permissions into the permission table
INSERT INTO permission (name) VALUES ('ALL_PERMISSIONS'), ('Add User'), ('Edit User'), ('Delete User'), ('Add Role'), ('Edit Role'), ('Delete Role'), ('Add Permission'), ('Edit Permission'), ('Delete Permission'), ('Add Rate'), ('Edit Rate'), ('Delete Rate'), ('Add Appointment'), ('Edit Appointment'), ('Delete Appointment');

-- Create the role_permission table
CREATE TABLE role_permission (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_id INT,
    permission_id INT,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES role(id),
    FOREIGN KEY (permission_id) REFERENCES permission(id),
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Insert role permissions for SuperAdmin
INSERT INTO role_permission (role_id, permission_id) VALUES
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'ALL_PERMISSIONS')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Add User')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Edit User')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Delete User')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Add Role')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Edit Role')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Delete Role')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Add Permission')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Edit Permission')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Delete Permission')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Add Rate')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Edit Rate')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Delete Rate')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Add Appointment')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Edit Appointment')),
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'Delete Appointment'));

-- Insert role permissions for Admin
INSERT INTO role_permission (role_id, permission_id) VALUES
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Add User')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Edit User')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Delete User')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Add Role')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Edit Role')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Delete Role')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Add Permission')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Edit Permission')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Delete Permission')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Add Rate')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Edit Rate')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Delete Rate')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Add Appointment')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Edit Appointment')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Delete Appointment'));

-- Insert role permissions for Manager
INSERT INTO role_permission (role_id, permission_id) VALUES
((SELECT id FROM role WHERE name = 'Manager'), (SELECT id FROM permission WHERE name = 'Add Appointment')),
((SELECT id FROM role WHERE name = 'Manager'), (SELECT id FROM permission WHERE name = 'Edit Appointment')),
((SELECT id FROM role WHERE name = 'Manager'), (SELECT id FROM permission WHERE name = 'Delete Appointment'));

-- Insert role permissions for Client
INSERT INTO role_permission (role_id, permission_id) VALUES
((SELECT id FROM role WHERE name = 'Client'), (SELECT id FROM permission WHERE name = 'Add Appointment')),
((SELECT id FROM role WHERE name = 'Client'), (SELECT id FROM permission WHERE name = 'Edit Appointment'));

-- Create the rate table
CREATE TABLE rate (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rate1Name VARCHAR(255) NOT NULL,
    rate2Name VARCHAR(255) NOT NULL,
    rate1Nbr INT NOT NULL,
    rate2Nbr INT NOT NULL,
    date DATE NOT NULL,
    `use` TINYINT(1) NOT NULL DEFAULT 0, -- Use backticks around `use`
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Insert rate into the rate table
INSERT INTO rate (rate1Name, rate2Name, rate1Nbr, rate2Nbr, date, `use`) VALUES ('USD', 'LBP', 1, 89000, CURRENT_DATE, 1);  -- Use 1 for TRUE

-- Create the appointment_price_service table
CREATE TABLE appointment_price_service (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rate_id INT,
    service_id INT,
    price DECIMAL(10, 2) NOT NULL,
    price2 DECIMAL(10, 2) NOT NULL,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (rate_id) REFERENCES rate(id),
    FOREIGN KEY (service_id) REFERENCES service(id),
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Create the appointment table
CREATE TABLE appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    time_from DATETIME NOT NULL,
    time_to DATETIME NOT NULL,
    user_id INT,
    manager_id INT,
    appointment_price_service_id INT,
    paid BOOLEAN NOT NULL DEFAULT FALSE,
    action VARCHAR(255),
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (manager_id) REFERENCES user(id),
    FOREIGN KEY (appointment_price_service_id) REFERENCES appointment_price_service(id),
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Create the calendar_settings table
CREATE TABLE calendar_settings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Insert default calendar settings
INSERT INTO calendar_settings (name, value) VALUES ('schedule', '00:05 am to 11:55 pm');
