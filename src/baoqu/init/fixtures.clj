(ns baoqu.init.fixtures
  (:require [baoqu.db.events :as events]
            [baoqu.utils.functions :as fn]
            [baoqu.db.users :as users]))

(defn create-drop
  "Creates database schema"
  []
  (fn/try-execute users/drop-users-events-table)
  (fn/try-execute users/drop-table)
  (fn/try-execute events/drop-table)
  (fn/try-execute users/create-table)
  (fn/try-execute events/create-table)
  (fn/try-execute users/create-users-events-table))

(defn load-users
  []
  (users/create {:username "johny"}))

(defn load-events
  "Generate a list of users"
  []
  (events/create {:name "new-event"
                  :user "1"
                  :approval-factor 3
                  :circle-size 3}))

(defn load-all
  "Loads all fixtures"
  []
  (create-drop)
  (load-users)
  (load-events))
