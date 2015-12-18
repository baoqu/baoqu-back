-- name: q-create-events-table!
CREATE TABLE events (
       id INTEGER PRIMARY KEY AUTOINCREMENT,
       `name` varchar(255),
       circle_size int DEFAULT 3,
       approval_factor int DEFAULT 1,
       created_by int
);

-- name: q-drop-events-table!
DROP TABLE events;

-- name: q-create<!
INSERT INTO events (`name`, created_by) VALUES (:name, :user);

-- name: q-find-all
SELECT * FROM events;

-- name: q-events-by-id
SELECT * FROM events where id = :id;

-- name: q-join-event<!
INSERT INTO users_events (user_id, event_id) VALUES (:user, :event);

-- name: q-user-event-by-id
SELECT * FROM users_events WHERE id = :id;

-- name: q-count-users-by-event-id
SELECT count(distinct(user_id)) as users FROM users_events where event_id = :event

-- name: q-event-by-circle-id
SELECT distinct(ev.id) from events ev JOIN circles ci WHERE
  ci.event_id = ev.id AND
  ci.id = :id;
