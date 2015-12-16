(ns baoqu.db.events
  (:require
   [baoqu.db.connection :refer [connection column-id just-first-row]]
   [baoqu.db.users :as us]
   [yesql.core :refer [defqueries require-sql]]))

(defqueries "baoqu/db/events.sql"
  {:connection connection})

;;                       _
;;                      | |
;;   _____   _____ _ __ | |_ ___
;;  / _ \ \ / / _ \ '_ \| __/ __|
;; |  __/\ V /  __/ | | | |_\__ \
;;  \___| \_/ \___|_| |_|\__|___/
;;

(defn create-table
  "creates the event type table"
  []
  (q-create-events-table!))

(defn drop-table
  "Drops table"
  []
  (q-drop-events-table!))

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

;;                       _
;;                      | |
;;   _____   _____ _ __ | |_ ___   ______   _   _ ___  ___ _ __ ___
;;  / _ \ \ / / _ \ '_ \| __/ __| |______| | | | / __|/ _ \ '__/ __|
;; |  __/\ V /  __/ | | | |_\__ \          | |_| \__ \  __/ |  \__ \
;;  \___| \_/ \___|_| |_|\__|___/           \__,_|___/\___|_|  |___/
;;

(defn find-user-event-by-id
  [id]
  (q-find-user-event-by-id {:id id} just-first-row))

(defn join
  "Inserts a new entry to user_events table"
  [id user_id]
  (let [id (get (q-join-event<! {:event id :user user_id}) column-id)]
    (q-find-user-event-by-id {:id id})))

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
