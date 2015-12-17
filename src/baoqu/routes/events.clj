(ns baoqu.routes.events
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.events :as service]
            [baoqu.services.users :as users]
            [baoqu.utils.mime :as utils]
            [baoqu.ws.events :as ws]
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
  ;;  (let [id (get-in ctx [:route-params :id])
  (let [json (:data ctx)
        id (:id (first (service/list-all)))
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

(defn status
  "Serves current event status"
  {:handler-type :catacumba/websocket}
  [{:keys [in out ctrl]}]
  (let [ch (ws/create-channel)]
    (go-loop []
      (let [[v p] (a/alts! [ctrl ch])]
        (cond
          (= p ctrl)
          (println "closed")
          ;; END
          (= p ch)
          (do
            (>! out v)
            (recur)))))))
