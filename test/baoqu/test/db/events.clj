(ns baoqu.test.db.events
  (:use [clojure.test])
  (:require [baoqu.db.circles :as circles]
            [baoqu.db.events :as ev]
            [baoqu.init.fixtures :as fix]
            [baoqu.utils.functions :as fn]))

(def sample-user {:username "john.aloha@corporation.com" :user 22 })
(def sample-event {:name "new event" :user 22 })

(deftest create
  (testing "when creating an event"
    (let [saved (ev/create sample-event)
          s-name (:name saved)
          s-user (:created_by saved)]
      (is (fn/not-nil? saved))
      (is (= s-name "new event"))
      (is (= s-user 22)))))

(deftest find-by-id
  (testing "when trying to get an existing event by id"
    (is (= 1 1))))

(deftest find-all
  (testing "listing all events"
    (is (= 1 1))))

(deftest find-user-event-by-id
  (testing "find a user event "
    (is (= 1 1))))

(deftest count-users-by-event
  (testing "counts how many users there are in an event"
    (is (= 1 1))))
