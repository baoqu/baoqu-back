(ns baoqu.db.events
  (:require
   [baoqu.db.connection :refer [connection]]
   [yesql.core :refer [defqueries require-sql]]))

(defqueries "baoqu/db/events.sql"
  {:connection connection})

(defn create-table
  "creates the event type table"
  []
  (q-create-table!))

(defn create
  "Create a new event and returns saved record"
  [event]
  (q-create<! event))

(defn find-all
  "Gets all events"
  []
  (q-find-all))

(defn join
  "Inserts a new entry to user_events table"
  [id user_id]
  (q-join-event! {:event id :user user_id}))
