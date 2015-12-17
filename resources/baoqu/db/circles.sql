-- name: q-create-circles-table!
CREATE TABLE circles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    event_id INTEGER,
    `name` varchar(255),
    `level` INTEGER
);

-- name: q-create-circle<!
INSERT INTO circles (event_id, `name`, `level`) VALUES (:event, :name, :level);

-- name: q-drop-circles-table!
DROP TABLE circles;

-- name: q-create-comments-table!
CREATE TABLE comments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    circle_id INTEGER,
    created_date timestamp,
    content text
);

-- name: q-drop-comments-table!
DROP TABLE comments;

-- name: q-create-ideas-table!
CREATE TABLE ideas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    participant_id INTEGER,
    title varchar(255)
);

-- name: q-add-idea-to-circle<!
INSERT INTO ideas (participant_id, title) VALUES (:participant,:title);

-- name: q-drop-ideas-table!
DROP TABLE ideas;

-- name: q-create-participants-table!
CREATE TABLE participants (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    circle_id INTEGER
);

-- name: q-add-participant-to-circle<!
INSERT INTO participants (user_id, circle_id) VALUES (:user, :circle);

-- name: q-drop-participants-table!
DROP TABLE participants;

-- name: q-create-ideas-participants-table!
CREATE TABLE ideas_participants (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    idea_id INTEGER
);

-- name: q-drop-ideas-participants-table!
DROP TABLE ideas_participants;
