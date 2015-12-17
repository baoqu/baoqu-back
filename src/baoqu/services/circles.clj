(ns baoqu.services.circles
  (:require [baoqu.db.circles :as c]
            [baoqu.ws.events :as ws]))

(defn add-participant
  "Adds a existing participant to an available circle"
  [event-id user-id]
  (let [circle (find-or-create-circle event-id)]
    (add-user-to-circle user-id circle)))

(defn find-or-create-circle
  "Finds an available circle, if not it creates
  a new one for the current event-id"
  [event-id]
  (let [circle (c/find-available-circle event-id)]
    (if circle
      circle
      (let [circle (create-circle event-id)]
        (ws/circle-created circle-id)
        circle))))

(defn add-user-to-circle
  "Adds a given user to a given circle as a participant"
  [user-id circle]
  (let [circle-id      (:id circle)
        event-id       (:event circle)
        participant    (c/add-participant-to-circle circle-id user-id)
        participant-id (:id participant)]
    (ws/participant-added participant-id)
    (ws/status-changed event-id)))
