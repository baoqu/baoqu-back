(ns baoqu.routes.users
  (:require [baoqu.utils.mime :as mime]
            [baoqu.services.users :as service]
            [bouncer.core :as b]
            [bouncer.validators :as v]))

(defn is-valid
  "Validates whether a user is valid or not and return a boolean"
  [json]
  (b/valid? json :username v/required))

(defn get-errors
  "Validates whether a user is valid or not and returns validation errors"
  [json]
  (b/validate json :username v/required))

(defn create
  "Creates a new user"
  [ctx]
  (let [json (:data ctx)]
    (if (is-valid json)
      (mime/to-json (service/create {:username (:username json)}))
      (mime/to-json (get-errors json)))))

(defn list
  "Lists all users"
  [ctx]
  (mime/to-json (service/list-all)))
