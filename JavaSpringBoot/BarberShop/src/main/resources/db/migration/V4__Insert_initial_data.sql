-- Insert default languages
INSERT INTO language (name) VALUES ('en'), ('fr'), ('ar');

-- Insert default roles
INSERT INTO role (name) VALUES ('SuperAdmin'), ('Admin'), ('Manager'), ('Client');

-- Insert default permissions
INSERT INTO permission (name) VALUES
('ALL_PERMISSIONS'), ('Add User'), ('Edit User'), ('Delete User'),
('Add Role'), ('Edit Role'), ('Delete Role'),
('Add Permission'), ('Edit Permission'), ('Delete Permission'),
('Add Rate'), ('Edit Rate'), ('Delete Rate'),
('Add Appointment'), ('Edit Appointment'), ('Delete Appointment');

-- Insert default user (superadmin123)
INSERT INTO user (user_name, password) VALUES ('super.admin', 'superadmin123');

-- Assign role permissions
INSERT INTO role_permission (role_id, permission_id)
VALUES
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'ALL_PERMISSIONS')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Add User'));

-- Insert rates
INSERT INTO rate (rate1Name, rate2Name, rate1Nbr, rate2Nbr, date, `use`) VALUES ('USD', 'LBP', 1, 89000, CURRENT_DATE, 1);

-- Insert default calendar settings
INSERT INTO calendar_settings (name, value) VALUES ('schedule', '00:05 am to 11:55 pm');
