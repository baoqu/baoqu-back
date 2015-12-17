(ns baoqu.ws.common
  (:require [clojure.core.async :as a]
            [baoqu.utils.mime :as mime]))

(def events-bus
  (a/chan 1 (map (fn [msg] (String. (mime/to-ws msg))))))

(def events-mult (a/mult events-bus))

(defn create-channel
  "Creates a new core.async channel"
  []
  (a/tap events-mult (a/chan)))

(defn send
  "Sends messages to events topic"
  [topic message]
  (a/put! events-bus message))
