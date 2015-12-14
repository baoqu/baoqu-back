;;  _   _ ___  ___ _ __ ___
;; | | | / __|/ _ \ '__/ __|
;; | |_| \__ \  __/ |  \__ \
;;  \__,_|___/\___|_|  |___/
;;

(ns baoqu.routes.users
  (:require [cheshire.core :as json]
            [baoqu.persis.users :as db]))

(defn create
  "Creates a new user"
  [ctx]
  (let [json (:data ctx)
        name (:name json)
        age (:age json)
        saved (db/create {:name name
                          :age age})]
    (json/generate-string saved)))
