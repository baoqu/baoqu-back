(ns baoqu.db.events
  (:require
   [baoqu.db.connection :refer [connection]]
   [yesql.core :refer [defqueries]]))

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
