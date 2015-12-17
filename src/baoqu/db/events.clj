(ns baoqu.db.events
  (:require
   [baoqu.db.connection :refer [connection column-id just-first-row]]
   [baoqu.db.users :as us]
   [yesql.core :refer [defqueries require-sql]]))

(defqueries "baoqu/db/events.sql"
  {:connection connection})

;;                      _                      _
;;                     | |                    | |
;;   ___ _ __ ___  __ _| |_ ___   ______    __| |_ __ ___  _ __
;;  / __| '__/ _ \/ _` | __/ _ \ |______|  / _` | '__/ _ \| '_ \
;; | (__| | |  __/ (_| | ||  __/          | (_| | | | (_) | |_) |
;;  \___|_|  \___|\__,_|\__\___|           \__,_|_|  \___/| .__/
;;                                                        | |
;;                                                        |_|

(defn create-table
  "creates the event type table"
  []
  (q-create-events-table!))

(defn drop-table
  "Drops table"
  []
  (q-drop-events-table!))

;;                        _
;;                       (_)
;;   __ _ _   _  ___ _ __ _  ___  ___
;;  / _` | | | |/ _ \ '__| |/ _ \/ __|
;; | (_| | |_| |  __/ |  | |  __/\__ \
;;  \__, |\__,_|\___|_|  |_|\___||___/
;;     | |
;;     |_|

(defn create
  "Create a new event and returns saved record"
  [event]
  (let [id (get (q-create<! event) column-id)]
    (q-find-by-id {:id id} just-first-row)))

(defn find-by-id
  "Gets a given event by id"
  [event-id]
  (q-find-by-id {:id event-id}))

(defn find-all
  "Gets all events"
  []
  (q-find-all))

(defn find-user-event-by-id
  [id]
  (q-find-user-event-by-id {:id id} just-first-row))

(defn join
  "Inserts a new entry to user_events table"
  [id user_id]
  (let [id (get (q-join-event<! {:event id :user user_id}) column-id)]
    (q-find-user-event-by-id {:id id} just-first-row)))

(defn join-all-users-to
  "Joins all users to a specific event"
  [event_id]
  (doseq [row (us/find-all)]
    (join event_id (:id row))))

(defn count-users-by-event
  "Counts users within a given event"
  [event_id]
  (q-count-users-by-event-id {:event event_id} {:result-set-fn first
                                                :row-fn :users}))

(defn find-by-circle
  "Finds the event a circle belongs to"
  [circle-id]
  (q-find-by-circle-id {:id circle-id} just-first-row))
