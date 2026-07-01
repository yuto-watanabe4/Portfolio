INSERT INTO users (role, user_name, password, created_at)
VALUES ('ADMIN', 'admin', 'password123', CURRENT_TIMESTAMP)
    ON CONFLICT (user_name) DO NOTHING;