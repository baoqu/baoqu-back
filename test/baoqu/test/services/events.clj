(ns baoqu.test.services.events
  (:use [clojure.test])
  (:require [baoqu.services.events :as ev]))

(def sample-event {:name "new event" :user-id 22 })

(deftest create
  (testing "it should return the same structure"
    (let [sample sample-event
          result (ev/create sample)]
      (is (= (:name result) "new event")))))
