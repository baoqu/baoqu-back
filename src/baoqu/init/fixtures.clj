(ns baoqu.init.fixtures
  (:require [baoqu.db.events :as events]
            [baoqu.utils.functions :as fn]
            [baoqu.db.users :as users]
            [baoqu.db.circles :as circles]
            [clojure.test.check.generators :as gen]
            ))

(defn create-drop
  "Creates database schema"
  []
  ;; DROPPING
  (fn/try-execute users/drop-users-events-table)
  (fn/try-execute users/drop-table)
  (fn/try-execute events/drop-table)
  (fn/try-execute circles/drop-table)
  (fn/try-execute circles/drop-comments-table)
  (fn/try-execute circles/drop-ideas-table)
  (fn/try-execute circles/drop-participants-table)
  (fn/try-execute circles/drop-ideas-participants-table)
  ;; CREATING
  (fn/try-execute users/create-table)
  (fn/try-execute events/create-table)
  (fn/try-execute users/create-users-events-table)
  (fn/try-execute circles/create-table)
  (fn/try-execute circles/create-comments-table)
  (fn/try-execute circles/create-ideas-table)
  (fn/try-execute circles/create-participants-table)
  (fn/try-execute circles/create-ideas-participants-table))

(defn load-users
  "Create a list of 50 users"
  []
  (let [names   (gen/elements ["holmes" "brooks" "finlay" "faye" "halley" "lovejoy"])
        domain  (gen/return "@corporation.com")
        key-gen (gen/return :username)
        val-gen (gen/let [x names
                          y gen/int
                          z domain]
                  (str x y z))
        map-gen (gen/not-empty (gen/map key-gen val-gen))
        user-list (gen/sample map-gen 50)]
    (doseq [user user-list]
      (users/create user))))


(defn load-default-event
  "Generate a list of events"
  []
  (let [saved (events/create {:name "Piweek 2016"
                              :user "1"
                              :approval-factor 3
                              :circle-size 3})]
    (:id saved)))

(defn join-users-to-default-event
  "Joins existing users to the created event and returns
  id of the created event"
  [event_id]
  (events/join-all-users-to event_id))

(defn load-all
  "Loads all fixtures"
  []
  (create-drop)
  (load-users)
  (->>
   (load-default-event)
   (join-users-to-default-event)))
