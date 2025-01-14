-- Insert default languages
INSERT INTO language (name) VALUES ('en'), ('fr'), ('ar');

-- Insert default roles
INSERT INTO role (name) VALUES ('SuperAdmin'), ('Admin'), ('Manager'), ('Client');

-- Insert default permissions
INSERT INTO permission (name) VALUES
('ALL_PERMISSIONS'), 
('Add User'), ('Edit User'), ('Delete User'),
('Add Role'), ('Edit Role'), ('Delete Role'),
('Add Permission'), ('Edit Permission'), ('Delete Permission');

-- Insert default user (superadmin123)
INSERT INTO user (user_name, password, role_id, language_id, last_update) 
VALUES ('superadmin', 'superadmin', 1, 1, NOW());

-- Get the ID of the newly inserted user
SELECT @idd := LAST_INSERT_ID();

-- Update the `last_updator` column for the same record
UPDATE user 
SET last_updator = @idd 
WHERE id = @idd;


-- Assign role permissions
INSERT INTO role_permission (role_id, permission_id)
VALUES
((SELECT id FROM role WHERE name = 'SuperAdmin'), (SELECT id FROM permission WHERE name = 'ALL_PERMISSIONS')),
((SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM permission WHERE name = 'Add User'));
