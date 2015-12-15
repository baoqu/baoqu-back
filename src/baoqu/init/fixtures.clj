(ns baoqu.init.fixtures
  (:require [baoqu.db.events :as events]
            [baoqu.db.users :as users]))

(defn create-tables
  "Creates database schema"
  []
  (events/create-table)
  (users/create-table))

(defn load-users
  []
  (users/create {:username "johny"}))

(defn load-events
  "Generate a list of users"
  []
  (events/create {:name "new-event" :user_id "11"}))

(defn load-all
  "Loads all fixtures"
  []
  "")
