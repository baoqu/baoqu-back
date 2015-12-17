(ns baoqu.ws.events
  (:require [baoqu.ws.common :as ws]))

(defn circle-created
  "Sends an event that a circle of a given
   event has been created"
  [circle-id]
  (ws/send :events/create-circle {:id "999"}))

(defn participant-added
  "Sends an event that a participant has been
   added to a given circle"
  [participant-id]
  (ws/send :events/add-partipant {:id "999"}))

(defn status-changed
  "Sends an event that a event has changed"
  [event-id]
  (ws/send :events/status {:id "999"}))

(defn user-joined
  [user]
  (ws/send :events/join-user user))
