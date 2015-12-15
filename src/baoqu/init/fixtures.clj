(ns baoqu.init.fixtures
  (:require [baoqu.db.events :as events]
            [baoqu.db.users :as users]))

(defn create-tables
  "Creates database schema"
  []
  (events/create-table)
  (users/create-table)
  (users/create-user-event-table))

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
  (create-tables)
  (load-users)
  (load-events))
