(ns baoqu.services.circles
  (:require [baoqu.db.circles :as c]
            [baoqu.ws.events :as ws]))

(defn find-or-create-circle
  "Finds an available circle, if not it creates
  a new one for the current event-id"
  [event-id]
  (let [circle (c/find-available-circle event-id)]
    (if circle
      circle
      (let [circle (c/add-circle-to-event event-id {:name "circle"})]
        (ws/create-circle (:id circle))
        circle))))

(defn add-user-to-circle
  "Adds a given user to a given circle as a participant"
  [user-id circle]
  (let [circle-id      (:id circle)
        event-id       (:event_id circle)
        participant    (c/add-participant-to-circle event-id user-id)
        participant-id (:id participant)]
    (ws/add-participant participant)
    (ws/change-status event-id)))

(defn add-participant
  "Adds a existing participant to an available circle"
  [event-id user-id]
  (let [circle (find-or-create-circle event-id)]
    (add-user-to-circle user-id circle)))

(defn find-all-participants
  "Lists all participants"
  []
  (c/find-all-participants))
