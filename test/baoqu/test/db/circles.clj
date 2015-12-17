(ns baoqu.test.db.circles
  (:use [clojure.test])
  (:require [baoqu.db.circles :as circles]
            [baoqu.db.events :as events]
            [baoqu.init.fixtures :as fix]
            [baoqu.utils.functions :as fn]))

(def sample-circle {:name "idea" :level 1})
(def sample-event {:name "new event" :user 22 })

(deftest create-circle
  (testing "creating a new circle"
    (fix/create-drop)
    (let [event (events/create sample-event)
          circle (circles/add-circle-to-event {:id event} sample-circle)]
      (is (fn/not-nil? circle))
      (is (fn/not-nil? {:id circle}))
      (is (fn/not-nil? {:name circle}))
      (is (fn/not-nil? {:level circle})))))

(deftest add-participant
  (testing "Add a participant to a given circle"
    (fix/create-drop)
    (let [event (events/create sample-event)
          circle (circles/add-circle-to-event {:id event} sample-circle)
          participant (circles/add-participant-to-circle (:id circle) 1)]
      (is (fn/not-nil? {:user_id participant}))
      (is (fn/not-nil? {:circle_id participant}))
      (is (fn/not-nil? {:id participant})))))

(deftest add-idea-to-circle
  (testing "when adding an idea to a given circle"
    (fix/create-drop)
     (let [event       (events/create sample-event)
           circle      (circles/add-circle-to-event {:id event} sample-circle)
           participant (circles/add-participant-to-circle (:id circle) 1)
           idea        (circles/add-idea-to-circle (:id participant) "my idea")]
       (is (fn/not-nil? {:id idea}))
       (is (fn/not-nil? {:participant_id idea}))
       (is (= (:title idea) "my idea")))))
