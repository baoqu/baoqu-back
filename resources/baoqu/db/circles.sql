-- name: q-create-circles-table!
CREATE TABLE circles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    event_id INTEGER,
    `name` varchar(255),
    `level` INTEGER
);

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
    circle_id INTEGER,
    title varchar(255)
);

-- name: q-drop-ideas-table!
DROP TABLE ideas;

-- name: q-create-participants-table!
CREATE TABLE participants (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    circle_id INTEGER,
    avatar text
);

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
