(ns baoqu.routes.events
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.events :as service]
            [baoqu.services.users :as users]
            [baoqu.utils.mime :as utils]
            [baoqu.ws.common :as ws]
            [catacumba.core :as cat]
            [clojure.core.async :as a :refer [go-loop <! >!]]))

;;                _
;;               | |
;;  _ __ ___  ___| |_
;; | '__/ _ \/ __| __|
;; | | |  __/\__ \ |_
;; |_|  \___||___/\__|
;;

(defn create
  "Creates a new event"
  [ctx]
  (let [json (:data ctx)
        name (:name json)
        user_id (:user_id json)
        saved (service/create name user_id)]
    (mime/to-json saved)))

(defn join
  "Adds a given user to the current event"
  [ctx]
  (let [json (:data ctx)
        id      (:id (first (service/list-all)))
        user_id (:id (users/find-by-username (:username json)))]
    (mime/to-json
     (service/join id user_id))))

(defn list
  "Lists all events"
  [ctx]
  (mime/to-json (service/list-all)))

;;               _                    _        _
;;              | |                  | |      | |
;; __      _____| |__  ___  ___   ___| | _____| |_ ___
;; \ \ /\ / / _ \ '_ \/ __|/ _ \ / __| |/ / _ \ __/ __|
;;  \ V  V /  __/ |_) \__ \ (_) | (__|   <  __/ |_\__ \
;;   \_/\_/ \___|_.__/|___/\___/ \___|_|\_\___|\__|___/
;;

(defn subscribe-to-events
  {:handler-type :catacumba/websocket}
  [{:keys [in out ctrl]}]
  (let [ch (ws/subscribe)]
    (go-loop []
      (let [[v p] (a/alts! [ctrl ch in])]
        (cond
          ;; RECEIVING MESSAGES FROM FRONT
          (= p in)
          (do ws/dispatch v)

          ;; NORMALLY CAUSE FRONT HAS CLOSED
          (= p ctrl)
          (do
            (println "closed")
            (a/close! ch))

          ;; SENDING MESSAGES TO SUBSCRIBERS
          (= p ch)
          (do
            (>! out v)
            (recur)))))))
