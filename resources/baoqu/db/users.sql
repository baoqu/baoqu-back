-- name: q-create-users-table!
CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(255));

-- name: q-drop-users-table!
DROP TABLE users;

-- name: q-create<!
INSERT INTO users (username) VALUES (:username);

-- name: q-find-all
SELECT * FROM users;

-- name: q-find-by-id
SELECT * FROM users where id = :id

-- name: q-create-users-events-table!
CREATE TABLE users_events (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    event_id INTEGER
);

-- name: q-drop-users-events-table!
DROP TABLE users_events;
