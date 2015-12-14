;;                     _
;;                    | |
;; _____   _____ _ __ | |_ ___
;;/ _ \ \ / / _ \ '_ \| __/ __|
;;|  __/\ V /  __/ | | | |_\__ \
;;\___| \_/ \___|_| |_|\__|___/
;;

(ns baoqu.routes.events
  (:require [cheshire.core :as json]
            [baoqu.persis.events :as db]))

(defn create
  "Creates a new event"
  [ctx]
  (let [json (:data ctx)
        name (:name json)
        user-id (:user-id json)
        saved (db/create {:name name
                          :user-id user-id})]
    (json/generate-string saved)))
