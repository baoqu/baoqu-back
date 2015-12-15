-- name: q-create-users-table!
CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(255));

-- name: q-create<!
INSERT INTO users (username) VALUES (:username)

-- name: q-find-all
SELECT * FROM users

-- name: q-create-users-events-table!
CREATE TABLE users_events (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    event_id INTEGER
);
