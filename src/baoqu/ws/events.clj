(ns baoqu.ws.events
  (:require [baoqu.ws.common :as ws]
            [baoqu.db.users :as us]
            [baoqu.db.events :as ev]))

(defn create-circle
  "Sends an event that a circle of a given
   event has been created"
  [circle-id]
  (ws/send :events/create-circle {:id "999"}))

(defn add-participant
  "Sends an event that a participant has been
   added to a given circle"
  [participant]
  (let [id        (:id participant)
        user      (us/find-by-id (:user_id participant))
        circle-id (:circle_id participant)
        event     (ev/find-by-circle circle-id)]
  (ws/send :events/add-participant {:id id
                                    :name (:username user)
                                    :circle circle-id
                                    :event (:id event)})))
(defn change-status
  "Sends an event that a event has changed"
  [event-id]
  (let [event (ev/find-by-id event-id)]
        (ws/send :events/status event)))
