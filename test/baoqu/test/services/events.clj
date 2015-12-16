(ns baoqu.test.services.events
  (:use [clojure.test])
  (:require [baoqu.services.events :as ev]
            [baoqu.db.events :as db-events]
            [baoqu.db.users :as db-users]
            [baoqu.utils.functions :as fn]))

(def sample-event {:name "new event" :user-id 22 })

(defn drop-create
  []
  (fn/try-execute db-users/drop-users-events-table)
  (fn/try-execute db-users/drop-table)
  (fn/try-execute db-events/drop-table)
  (fn/try-execute db-users/create-table)
  (fn/try-execute db-events/create-table)
  (fn/try-execute db-users/create-users-events-table))

(deftest create
  (testing "it should return the same structure"
    (drop-create)
    (let [sample sample-event
          user (:user sample)
          name (:name sample)
          result (ev/create name user)]
      (is (= (:name result) "new event")))))
