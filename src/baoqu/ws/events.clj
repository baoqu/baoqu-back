(ns baoqu.ws.events
  (:require [clojure.core.async :as a]
            [baoqu.utils.mime :as mime]))

(def events-bus (a/chan 1 (map mime/to-ws)))
(def events-mult (a/mult events-bus))

(defn events
  "Sends messages to events topic"
  [message]
  (a/put! events-bus message))
