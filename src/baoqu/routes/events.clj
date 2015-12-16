(ns baoqu.routes.events
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.events :as service]
            [baoqu.services.users :as users]
            [baoqu.utils.mime :as utils]
            [catacumba.core :as cat]
            [clojure.core.async :as a :refer [go-loop <! >!]]))

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

(defn status-ws
  "Serves current event status"
  {:handler-type :catacumba/websocket}
  [{:keys [in out ctrl]}]
  (let [ch (a/tap service/mult (a/chan))]
    (println "empezando")
    (go-loop []
      (let [[v p] (a/alts! [ctrl ch])]
        (cond
          (= p ctrl)
          (println "channel closed")

          (= p ch)
          (do
            (println "something")
            (>! out v)
            (recur)))))))

(defn status
  [ctx]
  (println "111")
  (cat/websocket ctx status-ws))
