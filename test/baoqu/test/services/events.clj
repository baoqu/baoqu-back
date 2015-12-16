(ns baoqu.test.services.events
  (:use [clojure.test])
  (:require [baoqu.services.events :as ev]
            [baoqu.init.fixtures :as fix]
            [baoqu.db.events :as db-events]
            [baoqu.db.users :as db-users]
            [baoqu.utils.functions :as fn]))

(def sample-event {:name "new event" :user 22 })

(deftest create
  (testing "it should return the same structure"
    (fix/create-drop)
    (let [sample sample-event
          user (:user sample)
          name (:name sample)
          result (ev/create name user)]
      (is (= (:name result) "new event")))))
