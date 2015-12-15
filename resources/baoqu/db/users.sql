-- name: q-create-table!
CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(255));

-- name: q-create<!
INSERT INTO users (username) VALUES (:username)

-- name: q-find-all
SELECT * FROM users
