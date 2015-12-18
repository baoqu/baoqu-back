(ns baoqu.db.users
  (:require
   [baoqu.db.common :as q]
   [yesql.core :refer [defqueries]]))

(defqueries "baoqu/db/users.sql"
  {:connection q/connection})

;;                      _                      _
;;                     | |                    | |
;;   ___ _ __ ___  __ _| |_ ___   ______    __| |_ __ ___  _ __
;;  / __| '__/ _ \/ _` | __/ _ \ |______|  / _` | '__/ _ \| '_ \
;; | (__| | |  __/ (_| | ||  __/          | (_| | | | (_) | |_) |
;;  \___|_|  \___|\__,_|\__\___|           \__,_|_|  \___/| .__/
;;                                                        | |
;;                                                        |_|

(defn create-table
  "Creates user table"
  []
  (q-create-users-table!))

(defn drop-table
  "Deletes user table"
  []
  (q-drop-users-table!))

(defn create-users-events-table
  "Creates users_events table"
  []
  (q-create-users-events-table!))

(defn drop-users-events-table
  "Deletes users_events_table"
  []
  (q-drop-users-events-table!))

;;                        _
;;                       (_)
;;   __ _ _   _  ___ _ __ _  ___  ___
;;  / _` | | | |/ _ \ '__| |/ _ \/ __|
;; | (_| | |_| |  __/ |  | |  __/\__ \
;;  \__, |\__,_|\___|_|  |_|\___||___/
;;     | |
;;     |_|

(defn find-by-id
  "Finds a user by its id"
  [user-id]
  (q/q-find q-find-by-id {:id user-id}))

(defn create
  "Inserts a new record in the database"
  [user]
  (->
   (q/q-do-id q-create<! user)
   (find-by-id)))

(defn find-all
  "Returns all users"
  []
  (q-find-all))

(defn find-by-username
  "Gets a given user by his/her username"
  [username]
  (q/q-find q-find-by-username {:username username}))
