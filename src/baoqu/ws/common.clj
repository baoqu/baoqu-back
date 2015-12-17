(ns baoqu.ws.common
  (:require [clojure.core.async :as a]
            [baoqu.utils.mime :as mime]))

;;       _                            _
;;      | |                          | |
;;   ___| |__   __ _ _ __  _ __   ___| |___
;;  / __| '_ \ / _` | '_ \| '_ \ / _ \ / __|
;; | (__| | | | (_| | | | | | | |  __/ \__ \
;;  \___|_| |_|\__,_|_| |_|_| |_|\___|_|___/
;;

(def events-bus
  (a/chan 1 (map (fn [msg] (String. (mime/to-ws msg))))))

(def events-mult (a/mult events-bus))

(defn subscribe
  "Creates a new core.async channel"
  []
  (a/tap events-mult (a/chan)))

(defn send
  "Sends messages to events topic"
  [topic message]
  (a/put! events-bus (merge {:topic topic} message)))

;;      _ _                 _       _
;;     | (_)               | |     | |
;;   __| |_ ___ _ __   __ _| |_ ___| |__
;;  / _` | / __| '_ \ / _` | __/ __| '_ \
;; | (_| | \__ \ |_) | (_| | || (__| | | |
;;  \__,_|_|___/ .__/ \__,_|\__\___|_| |_|
;;             | |
;;             |_|

(defmulti dispatch
  (fn [message]
    (:topic (mime/from-ws (String. message)))))

(defmethod dispatch :events/create-circle
  [message]
  message)

(defmethod dispatch :events/add-participant
  [message]
  message)

(defmethod dispatch :events/status
  [message]
  message)
