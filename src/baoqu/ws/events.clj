(ns baoqu.ws.events
  (:require [baoqu.ws.common :as ws]))

(defn create-circle
  "Sends an event that a circle of a given
   event has been created"
  [circle-id]
  (ws/send :events/create-circle {:id "999"}))

(defn add-participant
  "Sends an event that a participant has been
   added to a given circle"
  [participant-id]
  (ws/send :events/add-participant {:id "999"}))

(defn change-status
  "Sends an event that a event has changed"
  [event-id]
  (ws/send :events/status {:id "999"}))

(defn user-joined
  [user]
  (ws/send :events/join-user user))
