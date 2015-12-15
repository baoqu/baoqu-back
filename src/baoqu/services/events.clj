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
