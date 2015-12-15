(ns baoqu.routes.events
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.events :as service]
            [clojure.core.async :as async :refer [go-loop <! >! close!]]))

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
  (let [id (get-in ctx [:route-params :id])
        json (:data ctx)
        user_id (:user_id json)]
    (mime/to-json
     (service/join id user_id))))

(defn status
  "Serves current event status"
  {:handler-type :catacumba/websocket}
  [{:keys [in out]}]
  (go-loop []
    (if-let [received (<! in)]
      (do
        (>! out received)
        (recur))
      (close! out))))
