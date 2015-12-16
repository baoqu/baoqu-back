(ns baoqu.db.events
  (:require
   [baoqu.db.connection :refer [connection column-id just-first-row]]
   [yesql.core :refer [defqueries require-sql]]))

(defqueries "baoqu/db/events.sql"
  {:connection connection})

(defn create-table
  "creates the event type table"
  []
  (q-create-events-table!))

(defn drop-table
  "Drops table"
  []
  (q-drop-events-table!))

(defn create
  "Create a new event and returns saved record"
  [event]
  (let [id (get (q-create<! event) column-id)]
    (q-find-by-id {:id id} just-first-row)))

(defn find-by-id
  "Gets a given event by id"
  [event-id]
  (q-find-by-id {:id event-id}))

(defn find-all
  "Gets all events"
  []
  (q-find-all))

(defn join
  "Inserts a new entry to user_events table"
  [id user_id]
  (q-join-event! {:event id :user user_id}))
