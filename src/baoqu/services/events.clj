(ns baoqu.services.events
  (:require [baoqu.db.events :as db]
            [baoqu.utils.mime :as mime]
            [clojure.core.async :as a]))

;; 'bus' will be used to write to ws clients
(def bus (a/chan 1 (map mime/to-ws)))

(def mult (a/mult bus))

(defn create
  "Creates a new event"
  [name user]
  (db/create {:name name :user user}))

(defn join
  "Adds a user to the current event"
  [id user_id]
  (let [joined (db/join id user_id)]
    ;; notify a new user entered the event
    (println "aaaaaaaa")
    (a/put! bus joined)
    (println "bbbbbbbb")
    ;; return the user-event entry
    joined))

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
