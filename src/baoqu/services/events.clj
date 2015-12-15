(ns baoqu.services.events
  (:require [baoqu.db.events :as db]))

(defn create
  "Creates a new user"
  [event]
  (db/create event))

(defn join
  "Adds a user to the current event"
  [id user_id]
  {:user user_id :event id})
