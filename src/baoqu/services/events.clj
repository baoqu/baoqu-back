(ns baoqu.services.events
  (:require [baoqu.db.events :as db]))

(defn create
  "create a new user"
  [event]
  (db/create event))
