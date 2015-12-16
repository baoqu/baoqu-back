(ns baoqu.db.users
  (:require
   [baoqu.db.connection :refer [connection column-id just-first-row]]
   [yesql.core :refer [defqueries require-sql]]))

(defqueries "baoqu/db/users.sql"
  {:connection connection})

(defn create-table
  "Creates user table"
  []
  (q-create-users-table!))

(defn drop-table
  "Deletes user table"
  []
  (q-drop-users-table!))

(defn create-users-events-table
  "Creates users_events table"
  []
  (q-create-users-events-table!))

(defn drop-users-events-table
  "Deletes users_events_table"
  []
  (q-drop-users-events-table!))

(defn create
  "Inserts a new record in the database"
  [user]
  (let [id (get (q-create<! user) column-id)]
    (q-find-by-id {:id id} just-first-row)))

(defn find-by-id
  [user-id]
  (q-find-by-id {:id user-id}))

(defn find-all
  "Returns all users"
  []
  (q-find-all))

(defn find-by-username
  "Gets a given user by his/her username"
  [username]
  (q-find-by-username {:username username} just-first-row))
