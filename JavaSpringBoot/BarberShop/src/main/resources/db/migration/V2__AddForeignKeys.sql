-- Add foreign key constraints after tables are created
ALTER TABLE user
    ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role(id),
    ADD CONSTRAINT fk_user_language FOREIGN KEY (language_id) REFERENCES language(id);

ALTER TABLE role_permission
    ADD CONSTRAINT fk_role_permission_role FOREIGN KEY (role_id) REFERENCES role(id),
    ADD CONSTRAINT fk_role_permission_permission FOREIGN KEY (permission_id) REFERENCES permission(id);
