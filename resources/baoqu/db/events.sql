-- name: q-create-events-table!
CREATE TABLE events (
       id INTEGER PRIMARY KEY AUTOINCREMENT,
       `name` varchar(255),
       circle_size int,
       approval_factor int,
       created_by int
);

-- name: q-drop-events-table!
DROP TABLE events;

-- name: q-create<!
INSERT INTO events (`name`, created_by) VALUES (:name, :user);

-- name: q-find-all
SELECT * FROM events;

-- name: q-find-by-id
SELECT * FROM events where id = :id;

-- name: q-join-event<!
INSERT INTO users_events (user_id, event_id) VALUES (:user, :event);

-- name: q-find-user-event-by-id
SELECT * FROM users_events WHERE id = :id;
