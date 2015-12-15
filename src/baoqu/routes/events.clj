(ns baoqu.routes.events
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.events :as service]))

(defn create
  "Creates a new event"
  [ctx]
  (let [json (:data ctx)
        name (:name json)
        user_id (:user_id json)
        saved (service/create {:name name
                               :user_id user_id})]
    (mime/to-json saved)))
