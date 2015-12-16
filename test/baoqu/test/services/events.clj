(ns baoqu.test.services.events
  (:use [clojure.test])
  (:require [baoqu.services.events :as ev]
            [baoqu.services.users :as us]
            [baoqu.init.fixtures :as fix]
            [baoqu.utils.functions :as fn]))

(def sample-user {:username "john.aloha@corporation.com" :user 22 })
(def sample-event {:name "new event" :user 22 })

(deftest create
  (testing "it should return the same structure"
    (fix/create-drop)
    (let [sample sample-event
          user (:user sample)
          name (:name sample)
          result (ev/create name user)]
      (is (= (:name result) "new event")))))

(deftest join-user
  (testing "a given user joins an event"
    (fix/create-drop)
    (let [user (:id (us/create sample-user))
          event (:id (ev/create (:name sample-event) (:id user)))
          user-event (ev/join event user)]
      (is (fn/not-nil? user-event)))))

(deftest join-all-users
  (testing "when all users join a specific event")
  (fix/create-drop)
  (let [creator (:id (us/create sample-user))
        event (:id (ev/create (:name sample-event) (:id creator)))]
    ;; When all users join the event
    (ev/join-all-users-to event)
    ;; The event should have users
    (is (> (ev/count-users-by-event event) 0))))

(deftest list-all
  (testing "when listing all events")
  (fix/create-drop)
  (let [sample sample-event
        user (:user sample)
        name (:name sample)
        result (ev/create name user)
        event-count (count (ev/list-all))]
    (is (= event-count 1))))
