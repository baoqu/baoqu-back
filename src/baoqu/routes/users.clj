(ns baoqu.routes.users
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.users :as service]))

(defn create
  "Creates a new user"
  [ctx]
  (let [json (:data ctx)
        username (:username json)
        saved (service/create {:username username})]
    (mime/to-json saved)))
