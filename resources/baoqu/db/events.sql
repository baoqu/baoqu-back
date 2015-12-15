-- name: q-create-table!
CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT, `name` varchar(255), user_id int);

-- name: q-create<!
INSERT INTO events (`name`,user_id) VALUES (:name, :user);

-- name: q-find-all
SELECT * FROM events
