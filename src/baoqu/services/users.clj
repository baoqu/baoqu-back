(ns baoqu.services.users
  (:require [baoqu.db.users :as db]))

(defn create
  "Creates a new user with the name passed as parameter"
  [record]
  (db/create record))

(defn list-all
  "Lists all users"
  []
  (db/find-all))

(defn find-by-username
  "Finds a given user data by his/her username"
  [username]
  (db/find-by-username username))
