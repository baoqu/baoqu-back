(ns baoqu.db.users
  (:require
   [baoqu.db.connection :refer [connection]]
   [yesql.core :refer [defqueries require-sql]]))

(defqueries "baoqu/db/users.sql"
  {:connection connection})

(defn create-table
  "Creates user table"
  []
  (q-create-users-table!))

(defn create-user-event-table
  "Creates users_events table"
  []
  (q-create-users-events-table!))

(defn create
  "Inserts a new record in the database"
  [user]
  (q-create<! user))

(defn find-all
  "Returns all users"
  []
  (q-find-all))
