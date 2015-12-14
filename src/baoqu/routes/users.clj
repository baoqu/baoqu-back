(ns baoqu.routes.users
  (:require [cheshire.core :as json]
            [baoqu.services.users :as service]))

(defn create
  "Creates a new user"
  [ctx]
  (let [json (:data ctx)
        name (:name json)
        age (:age json)
        saved (service/create {:name name
                               :age age})]
    (json/generate-string saved)))
