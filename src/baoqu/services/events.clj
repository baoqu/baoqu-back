(ns baoqu.services.events
  (:require [baoqu.db.events :as db]))

(defn create
  "Creates a new event"
  [name user]
  (db/create {:name name :user user}))

(defn join
  "Adds a user to the current event"
  [id user_id]
  (db/join id user_id))

(defn join-all-users-to
  "Joins all users to a specific event"
  [event_id]
  (db/join-all-users-to event_id))

(defn count-users-by-event
  "Counts all users in a specific event"
  [event_id]
  (db/count-users-by-event event_id))

(defn list-all
  []
  (db/find-all))
