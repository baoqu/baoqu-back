(ns baoqu.routes.events
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.events :as service]
            [baoqu.services.users :as users]
            [clojure.core.async :as async :refer [go-loop <! >! close!]]
            [baoqu.utils.mime :as utils]))

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

(defn status
  "Serves current event status"
  {:handler-type :catacumba/websocket}
  [{:keys [in out]}]
  (go-loop []
    (if-let [received (<! in)]
      (do
        (>! out (utils/to-ws {:status "FATAL"}))
        (recur))
      (close! out))))
