(ns baoqu.test.services.events
  (:use [clojure.test])
  (:require [baoqu.services.events :as ev]))

(def sample-event {:name "new event" :user-id 22 })

;; tag::mocking-sample[]
(deftest create
  (testing "it should return the same structure"
    ;; Mocking persistence to return the same structure
    (with-redefs-fn {#'baoqu.db.events/create (fn [event] event)}
      #(let [sample sample-event
             user (:user sample)
             name (:name sample)
             result (ev/create name user)]
        (is (= (:name result) "new event"))))))
;; end::mocking-sample[]
