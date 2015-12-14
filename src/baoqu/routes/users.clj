(ns baoqu.routes.users
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.users :as service]))

(defn create
  "Creates a new user"
  [ctx]
  (let [json (:data ctx)
        name (:name json)
        age (:age json)
        saved (service/create {:name name
                               :age age})]
    (mime/to-json saved)))
