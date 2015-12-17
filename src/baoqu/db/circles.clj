(ns baoqu.db.circles
  (:require
   [baoqu.db.connection :refer [connection column-id just-first-row]]
   [baoqu.db.users :as us]
   [yesql.core :refer [defqueries require-sql]]))

(defqueries "baoqu/db/circles.sql"
  {:connection connection})

;;                      _                      _
;;                     | |                    | |
;;   ___ _ __ ___  __ _| |_ ___   ______    __| |_ __ ___  _ __
;;  / __| '__/ _ \/ _` | __/ _ \ |______|  / _` | '__/ _ \| '_ \
;; | (__| | |  __/ (_| | ||  __/          | (_| | | | (_) | |_) |
;;  \___|_|  \___|\__,_|\__\___|           \__,_|_|  \___/| .__/
;;                                                        | |
;;                                                        |_|

(defn drop-table
  "Drops circles table"
  []
  (q-drop-circles-table!))

(defn create-table
  "Creates circles table"
  []
  (q-create-circles-table!))

(defn create-comments-table
  "Creates circle comments table"
  []
  (q-create-comments-table!))

(defn drop-comments-table
  "Drops circle comments table"
  []
  (q-drop-comments-table!))

(defn create-ideas-table
  "Creates circle ideas table"
  []
  (q-create-ideas-table!))

(defn drop-ideas-table
  "Drops circles ideas table"
  []
  (q-drop-ideas-table!))

(defn create-participants-table
  "Creates circles participants table"
  []
  (q-create-participants-table!))

(defn drop-participants-table
  "Drops circles participants table"
  []
  (q-drop-participants-table!))

(defn create-ideas-participants-table
  "Table between ideas and participants"
  []
  (q-create-ideas-participants-table!))

(defn drop-ideas-participants-table
  "Drops table between ideas and participants"
  []
  (q-drop-ideas-participants-table!))

;;                        _
;;                       (_)
;;   __ _ _   _  ___ _ __ _  ___  ___
;;  / _` | | | |/ _ \ '__| |/ _ \/ __|
;; | (_| | |_| |  __/ |  | |  __/\__ \
;;  \__, |\__,_|\___|_|  |_|\___||___/
;;     | |
;;     |_|
