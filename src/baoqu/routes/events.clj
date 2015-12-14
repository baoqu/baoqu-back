(ns baoqu.routes.events
  (:require [cheshire.core :as json]
            [baoqu.services.events :as service]))

(defn create
  "Creates a new event"
  [ctx]
  (let [json (:data ctx)
        name (:name json)
        user-id (:user-id json)
        saved (service/create {:name name
                          :user-id user-id})]
    (json/generate-string saved)))
