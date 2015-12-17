(ns baoqu.ws.events
  (:require [clojure.core.async :as a]
            [baoqu.utils.mime :as mime]))

(def events-bus
  (a/chan 1 (map (fn [msg] (String. (mime/to-ws msg))))))

(def events-mult (a/mult events-bus))

(defn create-channel
  "Creates a new core.async channel"
  []
  (a/tap events-mult (a/chan)))

(defn events
  "Sends messages to events topic"
  [message]
  (a/put! events-bus message))

(defn circle-created
  [circle-id]
  (println (str "CIRCLE CREATED:" circle-id)))

(defn participant-added
  [participant-id]
  (println (str "PARTICIPANT ADDED" participant-id)))

(defn status-changed
  [event-id]
  (println (str "STATUS CHANGED" event-id)))
