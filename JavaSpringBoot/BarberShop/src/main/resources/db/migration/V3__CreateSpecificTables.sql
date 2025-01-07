-- Create service table
CREATE TABLE service (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_minutes INT NOT NULL,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Create appointment_price_service table
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

-- Create appointment table
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

-- Create calendar_settings table
CREATE TABLE calendar_settings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    last_updator INT,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (last_updator) REFERENCES user(id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

